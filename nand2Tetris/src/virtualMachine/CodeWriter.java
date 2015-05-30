package virtualMachine;

import java.io.BufferedWriter;
import java.io.IOException;
import static utility.Utils.*;

/**
 * Translates VM commands into Hack assembly code
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class CodeWriter {
	private BufferedWriter	bw;
	private String			inputFileName, currentFunction;
	private int				breakPoint;
	private boolean			returningAgain;

	/**
	 * Constructs a new CodeWriter for the given file
	 * 
	 * @param inputFile
	 */
	public CodeWriter(String inputFile) {
		bw = bufferedWriterFor(inputFile);
		returningAgain = false;
		currentFunction = null;
		breakPoint = 32000;
	}

	/**
	 * Sets the name for the input file
	 *
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		inputFileName = fileName;
		// DEBUG_PRINT("Input File Name: " + inputFileName);
	}

	/**
	 * Writes the bootstrap code to effect VM initialization at the beginning of the output file
	 */
	public void writeInit() {
		try {
			bw.write("@256\n"); // initialize A to 256
			bw.write("D=A\n"); // initialize D to 256
			bw.write("@SP\n"); // initialize A to 0
			bw.write("M=D\n"); // initialize SP to 256
			writeCall("Sys.init", 0); // call Sys.init, which is expected to call the main function of the main program and then enter an infinite
										// loop
		} catch (IOException ioe) {
			System.err.println("Couldn't write bootstrap to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Writes the current VM instruction to the .asm file as a comment, along with @-1 for debugging and tracking
	 * 
	 * @param line
	 */
	public void writeComment(String line) {
		try {
			bw.write("\t//" + line + "\n");
			// bw.write("@32767// sync\n");
			bw.write("@" + breakPoint++ + "// sync\n");
		} catch (IOException ioe) {
			System.err.println("Couldn't write comment //" + line + " to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Performs arithmetic on the stack
	 * 
	 * @param command
	 */
	public void writeArithmetic(String command) {
		switch (command) {
			case "add":
				addSub("+");
				break;
			case "sub":
				addSub("-");
				break;
			case "neg":
				notNeg("-");
				break;
			case "eq":
				compare("EQ");
				break;
			case "gt":
				compare("GT");
				break;
			case "lt":
				compare("LT");
				break;
			case "and":
				andOr("&");
				break;
			case "or":
				andOr("|");
				break;
			case "not":
				notNeg("!");
				break;
			default:
				throwException("Invalid command \"" + command + "\"!");
		}
	}

	/**
	 * x == y, x < y, x > y. y is popped from stack first, x second
	 * 
	 * @param operator
	 */
	private void compare(String operator) {
		String trueLabel = uniqueLabel(operator);
		String falseLabel = uniqueLabel("N" + operator);
		try {
			bw.write("@SP\n");// initialize A to 0
			bw.write("AM=M-1\n"); // decrement the SP and initialize A to SP - 1, the RAM[address] of y
			bw.write("D=M\n");// initialize D to y
			bw.write("A=A-1\n"); // initialize A to SP - 2, the RAM[address] of x
			bw.write("D=M-D\n");// initialize D to x - y
			bw.write("@" + trueLabel + "\n"); // initialize A to label for jumping to if comparison evaluates to true
			bw.write("D;J" + operator + "\n");// jump if the comparison evaluates to true
			bw.write("@0\n");// else, initialize A to 0
			bw.write("D=A\n");// initialize D to 0
			bw.write("@" + falseLabel + "\n"); // initialize A to label for jumping to if comparison evaluates to false
			bw.write("0;JMP\n");// unconditional jump to label for false comparison
			bw.write("(" + trueLabel + ")\n"); // jump here if comparison evaluates to true
			bw.write("@0\n");// initialize A to 0
			bw.write("D=A-1\n");// initialize D to -1
			bw.write("(" + falseLabel + ")\n"); // jump here if comparison evaluates to false
			bw.write("@SP\n");// initialize A to 0
			bw.write("A=M-1\n");// initialize A to the RAM[address] of the top value in the stack
			bw.write("M=D\n");// initialize the top value in the stack to 0 (if false) or -1 (if true)
		} catch (IOException ioe) {
			System.err.println("Couldn't write comparison " + operator + " to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * x + y, x - y. y is popped from stack first, x second
	 */
	private void addSub(String operator) {
		try {
			bw.write("@SP\n");// initialize A to the Stack Pointer at RAM[0]
			bw.write("AM=M-1\n"); // decrement the Stack Pointer and initialize A to the RAM[address] of y
			bw.write("D=M\n"); // initialize D to y
			bw.write("A=A-1\n"); // initialize A to RAM[address] of x in the stack
			bw.write("M=M" + operator + "D\n");// initialize RAM[address] of x in the stack to x + y / x - y

		} catch (IOException ioe) {
			System.err.println("Couldn't write x" + operator + "y to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * -x, !x. y is popped from stack first, x second
	 * 
	 * @param operator
	 */
	private void notNeg(String operator) {
		try {
			bw.write("@SP\n");// initialize A to the Stack Pointer at RAM[0]
			bw.write("A=M-1\n"); // initialize A to RAM[address] of x
			bw.write("M=" + operator + "M\n");// initialize RAM[address] of x to -x / !x
		} catch (IOException ioe) {
			System.err.println("Couldn't write " + operator + "x to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * x & y, x | y. y is popped from stack first, x second
	 * 
	 * @param operator
	 */
	private void andOr(String operator) {
		try {
			bw.write("@SP\n");// initialize A to the Stack Pointer at RAM[0]
			bw.write("AM=M-1\n"); // decrement the Stack Pointer and initialize A to the RAM[address] of y
			bw.write("D=M\n"); // initialize D to y
			bw.write("A=A-1\n"); // initialize A to RAM[address] of x in the stack
			bw.write("M=M" + operator + "D\n");// initialize RAM[address] of x in the stack to x & y / x | y
		} catch (IOException ioe) {
			System.err.println("Couldn't write x" + operator + "y to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Handles pushing and popping of the stack
	 * 
	 * @param command
	 * @param segment
	 * @param index
	 * @throws IOException
	 */
	public void WritePushPop(CommandType type, String segment, int index) {
		switch (type) {
			case C_PUSH:
				writePush(segment, index); // if we are pushing
				break;
			case C_POP:
				writePop(segment, index);// if we are popping
				break;
			default:
				throwException("Invalid CommandType \"" + type + "\"!");
		}
	}

	/**
	 * Pushes the index for the given segment to the top of the stack
	 * 
	 * @param index
	 * @param segment
	 */
	private void writePush(String segment, int index) {
		switch (Parser.segmentType(segment)) {
			case ARG:
				writeSegToD("ARG", index);
				break;
			case CONST:
				writeConstToD(index);
				break;
			case LOCAL:
				writeSegToD("LCL", index);
				break;
			case POINTER:
				writePointerToD(index);
				break;
			case STATIC:
				writeStaticToD(index);
				break;
			case TEMP:
				writeTempToD(index);
				break;
			case THAT:
				writeSegToD("THAT", index);
				break;
			case THIS:
				writeSegToD("THIS", index);
				break;
			default:
				throwException("Invalid segment \"" + segment + "\"!");
		}
		writePushDToStack();
	}

	/**
	 * Pop the top stack value and store it in RAM[segment + index]
	 * 
	 * @param segment
	 * @param index
	 */
	private void writePop(String segment, int index) {
		writePopStackToD();

		switch (Parser.segmentType(segment)) {
			case ARG:
				writeDToSeg("ARG", index);
				break;
			case LOCAL:
				writeDToSeg("LCL", index);
				break;
			case POINTER:
				writeDToPointer(index);
				break;
			case STATIC:
				writeDToStatic(index);
				break;
			case TEMP:
				writeDToTemp(index);
				break;
			case THAT:
				writeDToSeg("THAT", index);
				break;
			case THIS:
				writeDToSeg("THIS", index);
				break;
			default:
				throwException("Invalid segment \"" + segment + "\"!");
		}
	}

	/**
	 * Pushes the value of D onto the stack and increments the Stack Pointer
	 */
	private void writePushDToStack() {
		try {
			bw.write("@SP\n");// initialize A to 0
			bw.write("AM=M+1\n"); // increment the SP and initialize A to the SP
			bw.write("A=A-1\n"); // initialize A to SP - 1
			bw.write("M=D\n"); // initialize RAM[SP - 1] to value
		} catch (IOException ioe) {
			System.err.println("Couldn't push D to the stack!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * pops the top value from the stack to D and decrements the Stack Pointer
	 */
	private void writePopStackToD() {
		try {
			bw.write("@SP\n");// initialize A to the Stack Pointer at RAM[0]
			bw.write("AM=M-1\n"); // decrement the Stack Pointer and initialize A to the RAM[address] of value
			bw.write("D=M\n"); // initialize D to value
		} catch (IOException ioe) {
			System.err.println("Couldn't pop the stack to D!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes D to the value of a constant
	 * 
	 * @param index
	 */
	private void writeConstToD(int index) {
		try {
			bw.write("@" + index + "\n"); // initialize A to value
			bw.write("D=A\n"); // initialize D to value
		} catch (IOException ioe) {
			System.err.println("Couldn't write constant " + index + " to D!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes D to the value of RAM[RAM[segment] + index]
	 * 
	 * @param segment
	 */
	private void writeSegToD(String segment, int index) {
		try {
			bw.write("@" + segment + "\n"); // initialize A to segment
			bw.write("D=M\n"); // initialize D to RAM[segment]
			bw.write("@" + index + "\n"); // initialize A to index
			bw.write("A=D+A\n"); // initialize A to RAM[segment] + index
			bw.write("D=M\n"); // initialize D to RAM[RAM[segment] + index]
		} catch (IOException ioe) {
			System.err.println("Couldn't write RAM[RAM[" + segment + "] + " + index + "] to D!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes RAM[RAM[segment] + index] to the value of D
	 */
	private void writeDToSeg(String segment, int index) {
		try {
			bw.write("@R13\n");// initialize A to 13
			bw.write("M=D\n"); // initialize RAM[13] to D
			bw.write("@" + segment + "\n"); // initialize A to segment
			bw.write("D=M\n"); // initialize D to RAM[segment]
			bw.write("@" + index + "\n"); // initialize A to index
			bw.write("D=D+A\n"); // initialize D to RAM[segment] + index
			bw.write("@R14\n");// initialize A to 14
			bw.write("M=D\n"); // initialize RAM[14] to RAM[segment] + index
			bw.write("@R13\n");// initialize A to 13
			bw.write("D=M\n"); // initialize D to value
			bw.write("@R14\n");// initialize A to 14
			bw.write("A=M\n"); // initialize A to RAM[segment] + index
			bw.write("M=D\n"); // initialize RAM[RAM[segment] + index] to value
		} catch (IOException ioe) {
			System.err.println("Couldn't write D to RAM[" + segment + " + " + index + "]!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes D to the value of RAM[3 + index]
	 * 
	 * @param index
	 */
	private void writePointerToD(int index) {
		try {
			bw.write("@" + (index == 0 ? "THIS" : "THAT") + "\n"); // initialize A to 3 + index
			bw.write("D=M\n"); // initialize D to RAM[3 + index]
		} catch (IOException ioe) {
			System.err.println("Couldn't write RAM[3 + " + index + "] to D!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes RAM[3 + index] to the value of D
	 * 
	 * @param index
	 */
	private void writeDToPointer(int index) {
		try {
			bw.write("@" + (index == 0 ? "THIS" : "THAT") + "\n"); // initialize A to 3 + index
			bw.write("M=D\n"); // initialize RAM[3 + index] to D
		} catch (IOException ioe) {
			System.err.println("Couldn't write D to RAM[3 + " + index + "]!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes D to the value of RAM[fileName.index]
	 * 
	 * @param index
	 */
	private void writeStaticToD(int index) {
		try {
			bw.write("@" + inputFileName + "." + index + "\n"); // initialize A to fileName.index
			bw.write("D=M\n"); // initialize D to RAM[fileName.index]
		} catch (IOException ioe) {
			System.err.println("Couldn't write RAM[" + inputFileName + "." + index + "] to D!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes RAM[fileName.index] to the value of D
	 * 
	 * @param index
	 */
	private void writeDToStatic(int index) {
		try {
			bw.write("@" + inputFileName + "." + index + "\n"); // initialize A to fileName.index
			bw.write("M=D\n"); // initialize RAM[fileName.index] to the value of D
		} catch (IOException ioe) {
			System.err.println("Couldn't write D to RAM[" + inputFileName + "." + index + "]!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes D to the value of RAM[5 + index]
	 * 
	 * @param string
	 */
	private void writeTempToD(int index) {
		try {
			bw.write("@" + (5 + index) + "\n"); // initialize A to 5 + index
			bw.write("D=M\n"); // initialize D to RAM[5 + index]
		} catch (IOException ioe) {
			System.err.println("Couldn't write RAM[5 + " + index + "] to D!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes RAM[5 + index] to the value of D
	 * 
	 * @param string
	 */
	private void writeDToTemp(int index) {
		try {
			bw.write("@" + (5 + index) + "\n"); // initialize A to 5 + index
			bw.write("M=D\n"); // initialize RAM[5 + index] to D
		} catch (IOException ioe) {
			System.err.println("Couldn't write D to RAM[5 + " + index + "]!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Close the BufferedWriter
	 */
	public void close() {
		try {
			bw.close();
		} catch (IOException ioe) {
			System.err.println("Couldn't close BufferedWriter!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Flush the BufferedWriter
	 */
	public void flush() {
		try {
			bw.flush();
			;
		} catch (IOException ioe) {
			System.err.println("Couldn't flush BufferedWriter!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * labels the current location in the function's code so that it may be jumped to from other parts of the program in the same scope
	 * 
	 * @param label
	 */
	public void writeLabel(String label) {
		String local = localFunction(label);
		System.out.println("\t\twriting label for " + local);
		try {
			bw.write("(" + local + ")\n"); // write (label)
		} catch (IOException ioe) {
			System.err.println("Couldn't write label (" + local + ") to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * effects an unconditional jump to the location marked by the label in the same function
	 * 
	 * @param label
	 */
	public void writeGoto(String label) {
		String local = localFunction(label);
		System.out.println("\t\twriting goto for " + local);
		try {
			bw.write("@" + local + "\n"); // initialize A to (label)
			bw.write("0;JMP\n"); // jump to label
		} catch (IOException ioe) {
			System.err.println("Couldn't write goto " + local + " to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * effects a conditional jump to the location marked by the label in the same function
	 * 
	 * @param label
	 */
	public void writeIf(String label) {
		String local = localFunction(label);
		System.out.println("\t\twriting if-goto for " + local);
		writePopStackToD();
		try {
			bw.write("@" + local + "\n"); // initialize A to (label)
			bw.write("D;JNE\n"); // if D != 0, jump to label; else continue
		} catch (IOException ioe) {
			System.err.println("Couldn't write if-goto " + local + " to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * declare a function f that has |vars| local variables
	 * 
	 * @param function
	 * @param vars
	 */
	public void writeFunction(String function, int vars) {
		currentFunction = function;
		// (f) (declare a label for the function entry)
		try {
			bw.write("(" + function + ")\n"); // write (label)
		} catch (IOException ioe) {
			System.err.println("Couldn't write label (" + function + ") to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
		// repeat k times: (k = number of local variables)
		for (int i = 0; i < vars; i++) {
			// push 0 (initialize all of them to 0)
			writePush("constant", 0);
		}
	}

	/**
	 * return to the calling function
	 */
	public void writeReturn() {
		try {
			if (returningAgain) {
				bw.write("@$RETURN\n"); // initialize A to the label for return code
				bw.write("0;JMP\n"); // jump to the return code
				return;
			}

			bw.write("($RETURN)\n"); // initialize A to the label for return code
			// FRAME = LCL (FRAME is a temporary variable)
			bw.write("@LCL\n"); // initialize A to 1
			bw.write("D=M\n"); // initialize D to LCL
			bw.write("@R13\n"); // initialize A to 13 (FRAME)
			bw.write("M=D\n"); // initialize FRAME to LCL
			// RET = *(FRAME - 5) (put the return-address in a temporary variable)
			bw.write("D=M\n"); // initialize D to FRAME
			bw.write("@5\n"); // initialize A to 5
			bw.write("A=D-A\n"); // initialize A to FRAME - 5
			bw.write("D=M\n"); // initialize D to RAM[FRAME - 5]
			bw.write("@R14\n"); // initialize A to 14 (RET)
			bw.write("M=D\n"); // initialize RET to RAM[FRAME - 5]
			// *ARG = pop() (reposition the return value for the caller)
			writePopStackToD();
			bw.write("@ARG\n"); // initialize A to 2
			bw.write("A=M\n"); // initialize A to ARG
			bw.write("M=D\n"); // initialize RAM[ARG] to pop()
			// SP = ARG + 1 (restore SP of the caller)
			bw.write("D=A\n"); // initialize D to ARG
			bw.write("@SP\n"); // initialize A to 0
			bw.write("M=D+1\n"); // initialize SP to ARG + 1
			// THAT = *(FRAME - 1) (restore THAT of the caller)
			writeRestoreSeg("THAT", 1);
			// THIS = *(FRAME - 2) (restore THIS of the caller)
			writeRestoreSeg("THIS", 2);
			// ARG = *(FRAME - 3) (restore ARG of the caller)
			writeRestoreSeg("ARG", 3);
			// LCL = *(FRAME - 4) (restore LCL of the caller)
			writeRestoreSeg("LCL", 4);
			// goto RET (goto return-address (in the caller's code))
			bw.write("@R14\n"); // initialize A to 14
			bw.write("A=M\n"); // initialize A to RET
			bw.write("0;JMP\n"); // jump to RET
		} catch (IOException ioe) {
			System.err.println("Couldn't write return to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
		returningAgain = true;
	}

	/**
	 * call function f, stating that |args| arguments have already been pushed to the stack by the caller
	 * 
	 * @param function
	 * @param args
	 */
	public void writeCall(String function, int args) {
		// the return-address needs to be a unique label for every call.
		String returnAddress = uniqueLabel("Return_" + function + "_" + args);
		try {
			// push return-address (using the label declared below)
			bw.write("@" + returnAddress + "\n"); // initialize A to (return-address)
			bw.write("D=A\n"); // initialize D to return-address
			writePushDToStack(); // push the return-address to the stack
			// push LCL (save LCL base pointer of the calling function)
			bw.write("@LCL\n"); // initialize A to 1
			bw.write("D=M\n"); // initialize D to RAM[1]
			writePushDToStack();
			// push ARG (save ARG base pointer of the calling function)
			bw.write("@ARG\n"); // initialize A to 2
			bw.write("D=M\n"); // initialize D to RAM[2]
			writePushDToStack();
			// push THIS (save THIS base pointer of the calling function)
			writePointerToD(0);
			writePushDToStack();
			// push THAT (save THAT base pointer of the calling function)
			writePointerToD(1);
			writePushDToStack();
			// ARG = SP - n - 5 (reposition ARG (n = number of arguments))
			bw.write("@SP\n"); // initialize A to 0
			bw.write("D=M\n"); // initialize D to SP
			bw.write("@" + args + "\n"); // initialize A to n
			bw.write("D=D-A\n"); // initialize D to SP - n
			bw.write("@5\n"); // initialize A to 5
			bw.write("D=D-A\n"); // initialize D to SP - n - 5
			bw.write("@ARG\n"); // initialize A to 2
			bw.write("M=D\n"); // initialize ARG to SP - n - 5
			// LCL = SP (reposition LCL)
			bw.write("@SP\n"); // initialize A to 0
			bw.write("D=M\n"); // initialize D to SP
			bw.write("@LCL\n"); // initialize A to 1
			bw.write("M=D\n"); // initialize LCL to SP
			// goto f (transfer control)
			bw.write("@" + function + "\n"); // initialize A to (function)
			bw.write("0;JMP\n"); // initialize A to 0
			// (return-address) (declare a label for the return-address)
			bw.write("(" + returnAddress + ")\n"); // write (return-address)
		} catch (IOException ioe) {
			System.err.println("Couldn't write call " + function + " " + args + " to output!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * restore the segment of the caller, positioned |index| segments before FRAME
	 * 
	 * @param segment
	 * @param index
	 */
	private void writeRestoreSeg(String segment, int index) {
		try {
			bw.write("@R13\n"); // initialize A to 13 (FRAME)
			bw.write("D=M\n"); // initialize D to FRAME
			bw.write("@" + index + "\n"); // initialize A to index
			bw.write("A=D-A\n"); // initialize A to FRAME - index
			bw.write("D=M\n"); // initialize D to RAM[FRAME - index]
			bw.write("@" + segment + "\n"); // initialize A to (segment)
			bw.write("M=D\n"); // initialize segment to RAM[FRAME - index]
		} catch (IOException ioe) {
			System.err.println("Couldn't write restore " + segment + " of the caller to output!");
			ioe.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Returns a label appropriate for local function scope
	 * 
	 * @param function
	 * @param label
	 * @return
	 */
	private String localFunction(String label) {
		return currentFunction + "$" + label;
	}

}
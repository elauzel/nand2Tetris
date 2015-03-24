package compiler;

import java.io.BufferedWriter;
import java.io.IOException;

import utility.Segment;
import static utility.Utils.*;

public class VMWriter {
	private BufferedWriter	bw;

	/**
	 * Creates a new file and prepares it for writing.
	 * 
	 * @param output
	 */
	public VMWriter(String output) {
		bw = bufferedWriterFor(output);
	}

	/**
	 * Writes a VM push command.
	 * 
	 * @param type
	 * @param index
	 */
	public void writePush(Segment type, int index) {
		writeLine("push " + segmentToString(type) + " " + index);
	}

	/**
	 * Writes a VM push command.
	 * 
	 * @param type
	 * @param index
	 * @param kind
	 */
	public void writePush(Segment type, int index, Identifier kind, String name) {
		writeLine("push " + segmentToString(type) + " " + index + " // " + kind + " " + name);
	}

	/**
	 * Writes a VM pop command.
	 * 
	 * @param type
	 * @param index
	 */
	public void writePop(Segment type, int index) {
		writeLine("pop " + segmentToString(type) + " " + index);
	}

	/**
	 * Writes a VM pop command.
	 * 
	 * @param type
	 * @param index
	 * @param kind
	 */
	public void writePop(Segment type, int index, Identifier kind, String name) {
		writeLine("pop " + segmentToString(type) + " " + index + " // " + kind + " " + name);
	}

	/**
	 * Writes a VM arithmetic command.
	 * 
	 * @param type
	 */
	public void writeArithmetic(Command type) {
		switch (type) {
			case ADD:
				writeLine("add");
				break;
			case AND:
				writeLine("and");
				break;
			case EQ:
				writeLine("eq");
				break;
			case GT:
				writeLine("gt");
				break;
			case LT:
				writeLine("lt");
				break;
			case NEG:
				writeLine("neg");
				break;
			case NOT:
				writeLine("not");
				break;
			case OR:
				writeLine("or");
				break;
			case SUB:
				writeLine("sub");
				break;
			default:
				throwException("Invalid CommandType " + type + "!");
		}
	}

	/**
	 * Writes a VM label command.
	 * 
	 * @param label
	 */
	public void writeLabel(String label) {
		writeLine("label " + label);
	}

	/**
	 * Writes a VM goto command.
	 * 
	 * @param label
	 */
	public void writeGoto(String label) {
		writeLine("goto " + label);
	}

	/**
	 * Writes a VM if-goto command.
	 * 
	 * @param label
	 */
	public void writeIf(String label) {
		writeLine("if-goto " + label);
	}

	/**
	 * Writes a VM call command.
	 * 
	 * @param name
	 * @param nArgs
	 */
	public void writeCall(String name, int nArgs) {
		writeLine("call " + name + " " + nArgs);
	}

	/**
	 * Writes a VM function command.
	 * 
	 * @param name
	 * @param nLocals
	 */
	public void writeFunction(String name, int nLocals, String varType) {
		writeLine("function " + name + " " + nLocals + " // Returns " + varType);
	}

	/**
	 * Writes a VM return command.
	 */
	public void writeReturn() {
		writeLine("return");
	}

	/**
	 * Writes the line to the output file
	 * 
	 * @param line
	 */
	private void writeLine(String line) {
		try {
			bw.write(line + "\n");
		} catch (IOException ioe) {
			System.out.println("Couldn't write VM for " + line + "!");
			ioe.printStackTrace();
			System.exit(1);
		}

		flush();
	}

	/**
	 * Returns the String representation of a Segment
	 * 
	 * @param seg
	 * @return
	 */
	private String segmentToString(Segment seg) {
		String stringRep = "";
		switch (seg) {
			case ARG:
				stringRep = "argument";
				break;
			case CONST:
				stringRep = "constant";
				break;
			case LOCAL:
				stringRep = "local";
				break;
			case POINTER:
				stringRep = "pointer";
				break;
			case STATIC:
				stringRep = "static";
				break;
			case TEMP:
				stringRep = "temp";
				break;
			case THAT:
				stringRep = "that";
				break;
			case THIS:
				stringRep = "this";
				break;
			default:
				throwException("Unexpected segment " + seg + "!");
		}
		return stringRep;
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
	private void flush() {
		try {
			bw.flush();
			;
		} catch (IOException ioe) {
			System.err.println("Couldn't flush BufferedWriter!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}
}

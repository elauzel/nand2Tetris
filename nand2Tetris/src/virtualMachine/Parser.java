package virtualMachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles the parsing of a single .vm file, and encapsulates access to the input code. It reads VM commands, parses them, and provides convenient
 * access to their components. In addition, it removes all white space and comments
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class Parser {

	private String			currentLine;

	private BufferedReader	br;

	/**
	 * Constructs a new VM parser for the file
	 * 
	 * @param fileName
	 */
	public Parser(String fileName) {
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException fnfe) {
			System.err.println("Couldn't parse file " + fileName + "!");
			fnfe.printStackTrace();
			System.exit(1);
		}

		currentLine = "";

	}

	/**
	 * Returns the current line in the file
	 * 
	 * @return
	 */
	public String getCurrentLine() {
		return currentLine;
	}

	/**
	 * Determines if there are more commands in the file, which is directly correlated to it being ready for reading
	 * 
	 * @return
	 */
	public boolean hasMoreCommands() {
		boolean ready = false;
		try {
			ready = br.ready();
		} catch (IOException ioe) {
			System.err.println("The file isn't ready to be read!");
			// ioe.printStackTrace();
			// System.exit(1);
		}
		return ready;
	}

	/**
	 * Advances to the next line in the file
	 */
	public void advance() {
		if (hasMoreCommands()) {
			try {
				currentLine = br.readLine();
			} catch (IOException ioe) {
				System.err.println("Couldn't read the next line!");
				ioe.printStackTrace();
				System.exit(1);
			}
			formatLine();
		}
	}

	/**
	 * Removes spaces and comments in the current line
	 */
	private void formatLine() {
		int index = currentLine.indexOf("//");
		if ((index != -1)) currentLine = currentLine.substring(0, index);
		currentLine = currentLine.replaceAll("\\s+", " ");
		currentLine = currentLine.trim();
	}

	/**
	 * Returns the command type of the current line
	 * 
	 * @return
	 */
	public CommandType commandType() {
		String[] args = currentLine.split(" ");
		CommandType type = null;
		switch (args[0]) {
			case "add":
				type = CommandType.C_ARITHMETIC;
				break;
			case "sub":
				type = CommandType.C_ARITHMETIC;
				break;
			case "neg":
				type = CommandType.C_ARITHMETIC;
				break;
			case "eq":
				type = CommandType.C_ARITHMETIC;
				break;
			case "gt":
				type = CommandType.C_ARITHMETIC;
				break;
			case "lt":
				type = CommandType.C_ARITHMETIC;
				break;
			case "and":
				type = CommandType.C_ARITHMETIC;
				break;
			case "or":
				type = CommandType.C_ARITHMETIC;
				break;
			case "not":
				type = CommandType.C_ARITHMETIC;
				break;
			case "push":
				type = CommandType.C_PUSH;
				break;
			case "pop":
				type = CommandType.C_POP;
				break;
			case "label":
				type = CommandType.C_LABEL;
				break;
			case "goto":
				type = CommandType.C_GOTO;
				break;
			case "if-goto":
				type = CommandType.C_IF;
				break;
			case "function":
				type = CommandType.C_FUNCTION;
				break;
			case "return":
				type = CommandType.C_RETURN;
				break;
			case "call":
				type = CommandType.C_CALL;
				break;
			default:
				try {
					throw new Exception();
				} catch (Exception e) {
					System.out.println("Invalid CommandType " + args[0]);
					e.printStackTrace();
					System.exit(1);
				}
		}
		return type;
	}

	/**
	 * Returns the segment type of the current line
	 * 
	 * @param segment
	 * @return
	 */
	public static SegmentType segmentType(String segment) {
		SegmentType type = null;
		switch (segment) {
			case "argument":
				type = SegmentType.S_ARG;
				break;
			case "local":
				type = SegmentType.S_LCL;
				break;
			case "static":
				type = SegmentType.S_STAT;
				break;
			case "constant":
				type = SegmentType.S_CONST;
				break;
			case "this":
				type = SegmentType.S_THIS;
				break;
			case "that":
				type = SegmentType.S_THAT;
				break;
			case "pointer":
				type = SegmentType.S_PTR;
				break;
			case "temp":
				type = SegmentType.S_TEMP;
				break;
			default:
				try {
					throw new Exception();
				} catch (Exception e) {
					System.out.println("Invalid segment type " + segment);
					e.printStackTrace();
					System.exit(1);
				}
		}
		return type;
	}

	/**
	 * Returns the first argument in the line, if applicable
	 * 
	 * @return
	 */
	public String arg1() {
		CommandType type = commandType();
		String[] args;
		switch (type) {
			case C_ARITHMETIC:
				return currentLine;
			case C_PUSH:
				args = currentLine.split(" ");
				return args[1];
			case C_POP:
				args = currentLine.split(" ");
				return args[1];
			case C_LABEL:
				args = currentLine.split(" ");
				return args[1];
			case C_GOTO:
				args = currentLine.split(" ");
				return args[1];
			case C_IF:
				args = currentLine.split(" ");
				return args[1];
			case C_FUNCTION:
				args = currentLine.split(" ");
				return args[1];
			case C_CALL:
				args = currentLine.split(" ");
				return args[1];
			default:
				// System.out.println("arg1() should not be called on " + type);
				// System.exit(1);
				return null;
		}
	}

	/**
	 * Returns the second argument in the line, if applicable
	 * 
	 * @return
	 */
	public int arg2() {
		CommandType type = commandType();
		String[] args;
		switch (type) {
			case C_PUSH:
				args = currentLine.split(" ");
				return Integer.parseInt(args[2]);
			case C_POP:
				args = currentLine.split(" ");
				return Integer.parseInt(args[2]);
			case C_FUNCTION:
				args = currentLine.split(" ");
				return Integer.parseInt(args[2]);
			case C_CALL:
				args = currentLine.split(" ");
				return Integer.parseInt(args[2]);
			default:
				// System.out.println("arg2() should not be called on " + type);
				// System.exit(1);
				return Integer.MIN_VALUE;
		}
	}
}
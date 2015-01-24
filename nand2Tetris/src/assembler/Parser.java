package assembler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Encapsulates access to the input code. Reads an assembly language command, parses it, and provides convenient access to the command's components
 * (fields and symbols). In addition, removes all white space and comments.
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class Parser {
	// private static final String INPUT = "./06_input/";
	// private static final String OUTPUT = "./06_output/";

	private static int	lineNumber;
	private static int	romAddress;

	/**
	 * Opens the input file, parses the assembly code, and writes the binary code as follows:<br>
	 * 1) The program reads the assembly code into an ArrayList<String> and creates another for the binary output<br>
	 * 2) The program marches through each assembly instruction in the supplied Prog.asm file. First, load the symbol table<br>
	 * 3) For each C-instruction, the program concatenates the translated binary codes of the instruction fields into a single 16-bit word<br>
	 * 4) Next, the program adds this word into the ArrayList
	 * 
	 * @param fileName
	 */
	public Parser(File program) {
		lineNumber = 0;
		romAddress = 16;

		ArrayList<String> assembly = readAssembly(program);

		for (String line : assembly) {
			scanForSymbols(line);
		}
		// System.out.println("\nSymbol Table (" + SymbolTable.getTable().size() + "): " + SymbolTable.getTable() + "\n");

		// lineNumber = 0;
		ArrayList<String> binary = new ArrayList<String>(assembly.size());
		for (String line : assembly) {
			String binaryCommand = advance(line);
			// System.out.println("Binary Command after advance(): " + binaryCommand);
			if (commandType(line) != "L_COMMAND") {
				binary.add(binaryCommand);
			}
		}
		binary.trimToSize();
		// System.out.println("\nBinary:\n" + binary);
		writeBinary(binary, program.getAbsolutePath().replace(".asm", ".hack"));
	}

	/**
	 * Adds new symbols to the SymbolTable on the first pass
	 * 
	 * @param line
	 */
	private static void scanForSymbols(String line) {
		String type = commandType(line);
		if (type.equals("L_COMMAND")) {
			String symbol = labelFromAssembly(line);
			if (!SymbolTable.contains(symbol)) {
				SymbolTable.addEntry(symbol, lineNumber);
			}
		} else {
			lineNumber++;
		}
		// if (type != "L_COMMAND") lineNumber++;

	}

	/**
	 * Reads the next command from the input and makes it the current command. Should be called only if hasMoreCommands() is true. Initially there
	 * is no current command.
	 */
	private static String advance(String line) {
		// System.out.println("=======\tAssembly Command " + lineNumber++ + ": " + line);
		String binaryCommand = null;
		switch (commandType(line)) {
			case "C_COMMAND":
				binaryCommand = binaryC(line);
				break;
			case "A_COMMAND":
				binaryCommand = binaryA(line);
				break;
			default:
				binaryCommand = binaryL(line);
		}
		// if (commandType(line).equals("C_COMMAND")) {
		// binaryCommand = binaryC(line);
		// } else if (commandType(line).equals("A_COMMAND")) {
		// binaryCommand = binaryA(line);
		// } else {
		// binaryCommand = binaryL(line);
		// }
		return binaryCommand;
	}

	/**
	 * Convert Assembly to Binary for L-instructions
	 * 
	 * @param line
	 * @return
	 */
	private static String binaryL(String line) {
		String label = labelFromAssembly(line);
		return SymbolTable.binaryFromSymbol(label);
		// String binary = SymbolTable.binaryFromSymbol(label);
		// System.out.println("L - Binary Command from label: " + binary);
		// return binary;
	}

	/**
	 * Convert Assembly to Binary for C-instructions
	 * 
	 * @param line
	 * @return
	 */
	private static String binaryA(String line) {
		String variable = variableFromAssembly(line);
		String binary = null;
		try {
			String unpadded = Integer.toBinaryString(Integer.parseInt(variable));
			// it was an integer (constant)
			binary = String.format("%016d", Long.valueOf(unpadded));
		} catch (NumberFormatException nfe) {
			// it was a symbol after all
			if (!SymbolTable.contains(variable)) {
				SymbolTable.addEntry(variable, romAddress++);
			}
			binary = SymbolTable.binaryFromSymbol(variable);
		}
		// System.out.println("A - Binary Command from variable: " + binary);
		return binary;
	}

	/**
	 * Convert Assembly to Binary for C-instructions
	 * 
	 * @param line
	 * @return
	 */
	private static String binaryC(String line) {
		String compBin = Code.binaryFromComp(compFromAssembly(line));
		String destBin = Code.binaryFromDest(destFromAssembly(line));
		String jumpBin = Code.binaryFromJump(jumpFromAssembly(line));
		// String threeFields = Code.binaryFromComp(compFromAssembly(line)) + Code.binaryFromDest(destFromAssembly(line))
		// + Code.binaryFromJump(jumpFromAssembly(line));
		return "111" + compBin + destBin + jumpBin;
		// String binary = "111" + threeFields;
		// System.out.println("C - '111' + a + comp + dest + jump from assembly: " + binary);
		// return binary;
	}

	/**
	 * Returns the type of the current command: <br>
	 * A_COMMAND for @Xxx where Xxx is either a symbol or a decimal number <br>
	 * C_COMMAND for dest=comp;jump <br>
	 * L_COMMAND (actually, pseudo-command) for (Xxx) where Xxx is a symbol
	 * 
	 * @return
	 */
	private static String commandType(String assemblyCommand) {
		if (assemblyCommand.contains("@")) {
			return "A_COMMAND";
		} else if (assemblyCommand.contains("(") && assemblyCommand.contains(")")) {
			return "L_COMMAND";
		} else {
			return "C_COMMAND";
		}
	}

	/**
	 * Returns the variable of the current command @Xxx. Should be called only when commandType() is A_COMMAND.
	 * 
	 * @return
	 */
	private static String variableFromAssembly(String assemblyCommand) {
		return assemblyCommand.substring(1);
		// String variable = assemblyCommand.substring(1);
		// System.out.println("A - Symbol (variable) from assembly: " + variable);
		// return variable;
	}

	/**
	 * Returns the label of the current command (Xxx). Should be called only when commandType() is L_COMMAND.
	 * 
	 * @param assemblyCommand
	 * @return
	 */
	private static String labelFromAssembly(String assemblyCommand) {
		return assemblyCommand.substring(1, assemblyCommand.indexOf(")"));
		// String label = assemblyCommand.substring(1, assemblyCommand.indexOf(")"));
		// System.out.println("L - Symbol (label) from assembly: " + label);
		// return label;
	}

	/**
	 * Returns the dest mnemonic in the current C-command (8 possibilities). Should be called only when commandType() is C_COMMAND.
	 * 
	 * @return
	 */
	private static String destFromAssembly(String assemblyCommand) {
		String dest = null;
		if (assemblyCommand.contains("=")) {
			dest = assemblyCommand.substring(0, assemblyCommand.indexOf('='));
		}
		// System.out.println("C - destination from assembly: " + dest + (dest == null ? " (Object)" : ""));
		return dest;
	}

	/**
	 * Returns the comp mnemonic in the current C-command (28 possibilities). Should be called only when commandType() is C_COMMAND.
	 * 
	 * @return
	 */
	private static String compFromAssembly(String assemblyCommand) {
		String comp = assemblyCommand;
		if (comp.contains("=")) {
			comp = comp.substring(comp.indexOf("=") + 1);
		}
		if (comp.contains(";")) {
			comp = comp.substring(0, comp.indexOf(";"));
		}
		// System.out.println("C - computation from assembly: " + comp);
		return comp;
	}

	/**
	 * Returns the jump mnemonic in the current C-command (8 possibilities). Should be called only when commandType() is C_COMMAND.
	 * 
	 * @return
	 */
	private static String jumpFromAssembly(String assemblyCommand) {
		String jump = null;
		if (assemblyCommand.contains(";")) {
			jump = assemblyCommand.substring(assemblyCommand.indexOf(';') + 1);
		}
		// System.out.println("C - jump from assembly: " + jump + (jump == null ? " (Object)" : ""));
		return jump;
	}

	/**
	 * Loads the assembly file into an ArrayList<String> for parsing.
	 * 
	 * @param fileName
	 * @return
	 */
	private static ArrayList<String> readAssembly(File program) {
		ArrayList<String> assembly = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(program))) {
			String line = null;
			while (br.ready()) {
				// get the line without whitespace
				line = br.readLine().replaceAll("\\s+", " ");
				// remove all comments
				if (line.contains("//")) {
					line = line.substring(0, line.indexOf("//"));
				}
				// add it to assembly if it isn't null or empty
				if (!(line == null || line.isEmpty())) {
					// System.out.println("Assembly Command " + lineNum++ + ": " + line);
					assembly.add(line);
				}
			}
		} catch (IOException ioe) {
			System.err.println("File " + program.getAbsolutePath() + " couldn't be read from!");
			ioe.printStackTrace();
			System.exit(1);
		}
		return assembly;
	}

	/**
	 * Writes the ArrayList<String> of parsed assembly into a file.
	 * 
	 * @param binary
	 * @param fileName
	 */
	public static void writeBinary(ArrayList<String> binary, String fileName) {
		Path path = Paths.get(fileName);
		System.out.print("Writing " + fileName + "...");
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (String line : binary) {
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException ioe) {
			System.err.println("File " + fileName + " couldn't be written to!\n");
			ioe.printStackTrace();
			System.exit(1);
		}
		System.out.println("\n\t" + fileName + " was written!\n");
	}

}

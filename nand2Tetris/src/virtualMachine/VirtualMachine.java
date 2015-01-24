package virtualMachine;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Jeremy Wayne Gilreath
 *
 */
public class VirtualMachine {
	private static final String		INPUT			= "08_input/";
	private static final String[]	functionCalling	= { "Sys.vm", "Class1.vm", "Class2.vm" };

	/**
	 * Program entry point
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		File[] files = getFiles(args);
		for (File f : files) {
			System.out.println("Found: " + f.getAbsolutePath());
		}
		System.out.println();

		String lastPath = null;
		CodeWriter cw = null;
		for (File program : files) {
			String absPath = program.getAbsolutePath();
			System.out.println("absPath:\t" + absPath);
			String filePath = absPath.substring(0, absPath.lastIndexOf("\\") + 1);
			System.out.println("filePath:\t" + filePath);
			String fullFolder = filePath.substring(0, filePath.length() - 1);
			String folderName = fullFolder.substring(fullFolder.lastIndexOf("\\") + 1);
			System.out.println("folderName:\t" + folderName);
			String fullFile = program.getName();
			String fileName = fullFile.substring(0, fullFile.lastIndexOf("."));
			System.out.println("fileName:\t" + fileName);
			System.out.println("Creating Parser for " + fileName + ".vm");
			Parser p = new Parser(filePath + fileName + ".vm");

			if (!filePath.equals(lastPath)) { // new folder
				if (cw != null) cw.close();
				lastPath = filePath;
				System.out.println("Creating CodeWriter for " + filePath + folderName + ".asm:");
				cw = new CodeWriter(filePath + folderName + ".asm");
				System.out.println("Setting fileName for CodeWriter of " + fileName + ".asm - Parsed Assembly:");
				cw.setFileName(fileName + ".asm");
				cw.writeInit();
			} else {
				System.out.println("Setting fileName of " + fileName + ".asm for CodeWriter - Parsed Assembly:");
				cw.setFileName(fileName + ".asm");
			}

			while (p.hasMoreCommands()) {
				p.advance();

				if (p.getCurrentLine().equals("")) continue;

				CommandType type = p.commandType();

				StringBuilder line = new StringBuilder(p.getCurrentLine());
				int numSpaces = 24 - line.length();
				for (int i = 0; i < numSpaces; i++) {
					line.append(" ");
				}
				System.out.println("\t" + line.toString() + "//type:" + type + "\targ1:" + p.arg1() + "\targ2:"
						+ p.arg2());

				cw.writeComment(line.toString());
				switch (type) {
					case C_ARITHMETIC:
						cw.writeArithmetic(p.arg1());
						break;
					case C_PUSH:
						cw.WritePushPop(type, p.arg1(), p.arg2());
						break;
					case C_POP:
						cw.WritePushPop(type, p.arg1(), p.arg2());
						break;
					case C_LABEL:
						cw.writeLabel(p.arg1());
						break;
					case C_GOTO:
						cw.writeGoto(p.arg1());
						break;
					case C_IF:
						cw.writeIf(p.arg1());
						break;
					case C_FUNCTION:
						cw.writeFunction(p.arg1(), p.arg2());
						break;
					case C_RETURN:
						cw.writeReturn();
						break;
					case C_CALL:
						cw.writeCall(p.arg1(), p.arg2());
						break;
					default:
						try {
							throw new Exception();
						} catch (Exception e) {
							System.err.println("Invalid CommandType \"" + type + "\"!");
							e.printStackTrace();
							System.exit(1);
						}
				}
			}
			cw.flush();
			System.out.println();
		}
	}

	/**
	 * Returns a File[] for programs to be used in the virtual machine. If args is empty, it defaults to the test programs
	 * 
	 * @param args
	 * @return
	 */
	private static File[] getFiles(String[] args) {
		File[] files;
		if (args.length == 0) {
			files = new File[functionCalling.length];
			for (int i = 0; i < functionCalling.length; i++) {
				files[i] = new File(INPUT + functionCalling[i]);
			}
			// use supplied arguments
		} else {
			ArrayList<File> programs = new ArrayList<File>();
			for (String arg : args) {
				File f = new File(arg);
				if (f.isDirectory()) {
					for (File program : f.listFiles()) {
						programs.add(program);
					}
				} else
					programs.add(new File(arg));
			}

			int totalFiles = programs.size();
			files = new File[totalFiles];
			for (int i = 0; i < totalFiles; i++) {
				files[i] = programs.get(i);
			}
		}
		return files;
	}
}

package virtualMachine;

import java.io.File;

import static utility.Utils.*;

/**
 * Takes a .vm file as input and translates it into assembly
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class VirtualMachine {
	private static final String		INPUT		= "vm_files/";
	private static final String[]	programs	= { "Sys.vm", "Class1.vm", "Class2.vm" };

	/**
	 * Program entry point
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		File[] files = getFiles(INPUT, programs, args);

		String lastPath = null;
		CodeWriter cw = null;
		Parser p = null;
		for (File program : files) {
			String filePath = getFilePath(program);
			String folderName = program.getParent();
			System.out.println("folderName:\t" + folderName);
			String fileName = program.getName();
			System.out.println("fileName:\t" + fileName);
			String fileNameASM = fileName.replace(".vm", ".asm");

			System.out.println("Creating Parser for " + fileName);
			p = new Parser(filePath + fileName);

			if (!filePath.equals(lastPath)) { // new folder
				if (cw != null) cw.close();
				lastPath = filePath;
				System.out.println("Creating CodeWriter for " + folderName + "\\" + fileNameASM + ":");
				cw = new CodeWriter(filePath + folderName + ".asm");

				System.out.println("Setting fileName of " + fileNameASM + " for CodeWriter, Parsing Assembly:");
				cw.setFileName(fileNameASM);
				cw.writeInit();
			} else {
				System.out.println("Setting fileName of " + fileNameASM + " for CodeWriter, Parsing Assembly:");
				cw.setFileName(fileNameASM);
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
				String typeIndented = type + "";
				if (type != CommandType.C_ARITHMETIC && type != CommandType.C_FUNCTION) typeIndented += "\t";
				DEBUG_PRINT("\t" + line.toString() + "//type:" + typeIndented + "\targ1:" + p.arg1() + "\targ2:"
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
						throwException("Invalid CommandType \"" + type + "\"!");
				}
			}
			cw.flush();
			System.out.println();
		}
		cw.close();
	}

}

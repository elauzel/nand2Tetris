package assembler;

import java.io.File;
import java.util.ArrayList;

/**
 * Assembler for the HACK platform
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class Assembler {
	private static final String		INPUT		= "06_input/";
	private static final String[]	programs	= { "Add.asm", "Max.asm", "MaxL.asm", "Pong.asm", "PongL.asm",
			"Rect.asm", "RectL.asm"			};

	public static void main(String[] args) {
		new Code();
		new SymbolTable();

		File[] files = getFiles(args);
		for (File f : files) {
			System.out.println("Found: " + f.getAbsolutePath());
		}
		System.out.println();

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
			System.out.println("Creating Parser for " + filePath + fileName + ".asm");

			new Parser(program);
		}
	}

	/**
	 * Returns a File[] for programs to be used in the assembler. If args is empty, it defaults to the test programs
	 * 
	 * @param args
	 * @return
	 */
	private static File[] getFiles(String[] args) {
		File[] files;
		if (args.length == 0) {
			files = new File[programs.length];
			for (int i = 0; i < programs.length; i++) {
				files[i] = new File(INPUT + programs[i]);
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
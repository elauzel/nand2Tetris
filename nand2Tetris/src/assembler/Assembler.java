package assembler;

import java.io.File;

import static utility.Utils.*;

/**
 * Assembler for the HACK platform
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class Assembler {
	private static final String		INPUT		= "06_input/";
	private static final String[]	programs	= { "Add.asm", "Max.asm", "MaxL.asm", "Pong.asm", "PongL.asm",
			"Rect.asm", "RectL.asm",			};

	public static void main(String[] args) {
		new Code();
		new SymbolTable();

		File[] files = getFiles(INPUT, programs, args);

		for (File program : files) {
			// String filePath = getFilePath(program);
			String folderName = program.getParent();
			System.out.println("folderName:\t" + folderName);
			String fileName = program.getName();
			System.out.println("fileName:\t" + fileName);

			System.out.println("Creating Parser for " + folderName + fileName);

			new Parser(program);
		}
	}
}
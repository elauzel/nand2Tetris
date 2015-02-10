package compiler;

import java.io.File;

import static utility.Utils.*;

/**
 * The analyzer program operates on a given source, where source is either a file name in the form Xxx.jack or a directory name containing one or
 * more such files. For each source Xxx.jack file, the analyzer creates a JackTokenizer from the Xxx.jack input file, creates an output file called
 * Xxx.xml and prepares it for writing, and uses the CompilationEngine to compile the input JackTokenizer into the output file.
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class JackAnalyzer {
	private static final String		INPUT		= "10_input/";
	private static final String[]	programs	= { "Main.jack" };

	public static void main(String[] args) {
		File[] files = getFiles(INPUT, programs, args);

		// For each source Xxx.jack file, create a JackTokenizer from the Xxx.jack input file
		for (File program : files) {
			String filePath = getFilePath(program);
			String folderName = program.getParent();
			System.out.println("folderName:\t" + folderName);
			String fileName = program.getName();
			System.out.println("fileName:\t" + fileName);
			String fileNameXML = fileName.replace(".jack", ".xml");

			System.out.println("Tokenizing " + folderName + "\\" + fileName);
			JackTokenizer jt = new JackTokenizer(filePath + fileName);

			// create an output file called Xxx.xml and prepare it for writing
			System.out.println("Compiling " + folderName + "\\" + fileNameXML);
			CompilationEngine ce = new CompilationEngine(filePath + fileNameXML, jt);

			// use the CompilationEngine to compile the input JackTokenizer into the output file
			ce.compileClass();
			ce.close();
		}
	}
}

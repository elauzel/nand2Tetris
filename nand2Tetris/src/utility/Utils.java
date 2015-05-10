package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
	private static int	serial;

	/**
	 * Used for printing debug messages
	 * 
	 * @param message
	 */
	public static void DEBUG_PRINT(String message) {
		System.out.println(message);
	}

	/**
	 * Returns a File[] for programs to be used. If args is empty, it defaults to the test programs
	 * 
	 * @param args
	 * @return
	 */
	public static File[] getFiles(String folder, String[] inputs, String[] args) {
		File[] files;
		if (args.length == 0) {
			files = new File[inputs.length];
			for (int i = 0; i < inputs.length; i++) {
				files[i] = new File(folder + inputs[i]);
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

		for (File f : files) {
			System.out.println("Found: " + f.getAbsolutePath());
		}
		System.out.println();

		return files;
	}

	/**
	 * Returns the file path for a file.
	 * 
	 * @param f
	 * @return
	 */
	public static String getFilePath(File f) {
		String absPath = f.getAbsolutePath();
		System.out.println("absPath:\t" + absPath);
		String filePath = absPath.substring(0, absPath.lastIndexOf("\\") + 1);
		System.out.println("filePath:\t" + filePath);
		return filePath;
	}

	/**
	 * Throws an exception and exits the system.
	 * 
	 * @param message
	 */
	public static void throwException(String message) {
		try {
			throw new Exception();
		} catch (Exception e) {
			System.err.println(message);
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Returns a unique label for use in conditional branching
	 * 
	 * @param label
	 * @return
	 */
	public static String uniqueLabel(String label) {
		return "$" + label + serial++;
	}

	/**
	 * Removes leading/trailing/extra whitespace in a String.
	 * 
	 * @param s
	 * @return
	 */
	public static String removeWhitespace(String s) {
		s = s.replaceAll("\\s+", " ");
		// s = s.trim();
		return s.trim();
	}

	/**
	 * Returns a BufferedWriter for the given output.
	 * 
	 * @param output
	 * @return
	 */
	public static BufferedWriter bufferedWriterFor(String output) {
		File outputFile = new File(output);
		if (!outputFile.exists()) try {
			outputFile.createNewFile();
		} catch (IOException ioe) {
			System.err.println("Couldn't create output file " + output + "!");
			ioe.printStackTrace();
			System.exit(1);
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(outputFile.getAbsoluteFile());
		} catch (IOException ioe) {
			System.err.println("Couldn't create FileWriter!");
			ioe.printStackTrace();
			System.exit(1);
		}

		return new BufferedWriter(fw);
	}

}

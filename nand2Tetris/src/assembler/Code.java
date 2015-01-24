package assembler;

import java.util.HashMap;

/**
 * Translates Hack assembly language mnemonics into binary codes.
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class Code {
	private static HashMap<String, String>	computations	= new HashMap<String, String>(28);
	private static HashMap<String, String>	destinations	= new HashMap<String, String>(8);
	private static HashMap<String, String>	jumps			= new HashMap<String, String>(8);

	/**
	 * Puts the key:value pairs for the three fields comp, dest, and jump in C-instructions
	 */
	public Code() {
		computations.put("0", "101010");
		computations.put("1", "111111");
		computations.put("-1", "111010");
		computations.put("D", "001100");
		computations.put("A", "110000");
		computations.put("!D", "001101");
		computations.put("!A", "110001");
		computations.put("-D", "001111");
		computations.put("-A", "110011");
		computations.put("D+1", "011111");
		computations.put("A+1", "110111");
		computations.put("D-1", "001110");
		computations.put("A-1", "110010");
		computations.put("D+A", "000010");
		computations.put("D-A", "010011");
		computations.put("A-D", "000111");
		computations.put("D&A", "000000");
		computations.put("D|A", "010101");
		computations.put("M", "110000");
		computations.put("!M", "110001");
		computations.put("-M", "110011");
		computations.put("M+1", "110111");
		computations.put("M-1", "110010");
		computations.put("D+M", "000010");
		computations.put("D-M", "010011");
		computations.put("M-D", "000111");
		computations.put("D&M", "000000");
		computations.put("D|M", "010101");

		destinations.put(null, "000");
		destinations.put("M", "001");
		destinations.put("D", "010");
		destinations.put("MD", "011");
		destinations.put("A", "100");
		destinations.put("AM", "101");
		destinations.put("AD", "110");
		destinations.put("AMD", "111");

		jumps.put(null, "000");
		jumps.put("JGT", "001");
		jumps.put("JEQ", "010");
		jumps.put("JGE", "011");
		jumps.put("JLT", "100");
		jumps.put("JNE", "101");
		jumps.put("JLE", "110");
		jumps.put("JMP", "111");
	}

	/**
	 * Returns the binary code of the dest mnemonic
	 * 
	 * @param mnemonic
	 * @return
	 */
	public static String binaryFromDest(String mnemonic) {
		// ddd
		return destinations.get(mnemonic);
	}

	/**
	 * Returns the binary code of the comp mnemonic
	 * 
	 * @param mnemonic
	 * @return
	 */
	public static String binaryFromComp(String mnemonic) {
		// acccccc
		String aBit = null;
		if (mnemonic.equals("M") || mnemonic.equals("!M") || mnemonic.equals("-M") || mnemonic.equals("M+1")
				|| mnemonic.equals("M-1") || mnemonic.equals("D+M") || mnemonic.equals("D-M") || mnemonic.equals("M-D")
				|| mnemonic.equals("D&M") || mnemonic.equals("D|M")) {
			aBit = "1";
		} else {
			aBit = "0";
		}
		return aBit + computations.get(mnemonic);
	}

	/**
	 * Returns the binary code of the jump mnemonic
	 * 
	 * @param mnemonic
	 * @return
	 */
	public static String binaryFromJump(String mnemonic) {
		// jjj
		return jumps.get(mnemonic);
	}
}

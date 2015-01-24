package assembler;

import java.util.HashMap;

public class SymbolTable {
	private static HashMap<String, Integer>	symbols	= new HashMap<String, Integer>();

	/**
	 * Puts the key:value pairs for Predefined Symbols in A-instructions
	 */
	public SymbolTable() {
		symbols.put("SP", 0x0000);
		symbols.put("LCL", 0x0001);
		symbols.put("ARG", 0x0002);
		symbols.put("THIS", 0x0003);
		symbols.put("THAT", 0x0004);
		symbols.put("R0", 0x0000);
		symbols.put("R1", 0x0001);
		symbols.put("R2", 0x0002);
		symbols.put("R3", 0x0003);
		symbols.put("R4", 0x0004);
		symbols.put("R5", 0x0005);
		symbols.put("R6", 0x0006);
		symbols.put("R7", 0x0007);
		symbols.put("R8", 0x0008);
		symbols.put("R9", 0x0009);
		symbols.put("R10", 0x000a);
		symbols.put("R11", 0x000b);
		symbols.put("R12", 0x000c);
		symbols.put("R13", 0x000d);
		symbols.put("R14", 0x000e);
		symbols.put("R15", 0x000f);
		symbols.put("SCREEN", 0x4000);
		symbols.put("KBD", 0x6000);
	}

	/**
	 * Adds the key:value pair to the HashMap
	 * 
	 * @param symbol
	 * @param address
	 */
	public static void addEntry(String symbol, int address) {
		symbols.put(symbol, address);
	}

	/**
	 * Does the symbol table contain the given symbol?
	 * 
	 * @param symbol
	 * @return
	 */
	public static boolean contains(String symbol) {
		return symbols.get(symbol) != null;
	}

	/**
	 * Returns the address associated with the symbol
	 * 
	 * @param symbol
	 * @return
	 */
	public static String getAddress(String symbol) {
		return Integer.toBinaryString(symbols.get(symbol));
	}

	/**
	 * Returns the binary code of the predefined symbol
	 * 
	 * @param mnemonic
	 * @return
	 */
	public static String binaryFromSymbol(String mnemonic) {
		return String.format("%016d", Long.valueOf(getAddress(mnemonic)));
	}
}

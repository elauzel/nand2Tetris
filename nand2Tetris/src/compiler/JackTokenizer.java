package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static utility.Utils.*;

/**
 * Removes all comments and white space from the input stream and breaks it into Jack-language tokens, as specified by the Jack grammar
 * 
 * @author Jeremy Wayne Gilreath
 *
 */
public class JackTokenizer {
	private static final String						SYMBOLS	= "()[]{},;=.+-*/&|~<>";

	private static HashMap<String, KeyWord>			keyWords;
	private static HashMap<String, TokenType>		tokenTypes1;
	private static HashMap<Character, TokenType>	tokenTypes2;

	private BufferedReader							br;
	private String									currentLine, currentToken;
	private boolean									commentJavaDoc, partOfString;
	private int										numLines;

	/**
	 * Constructor for the Jack Tokenizer
	 * 
	 * @param fileName
	 */
	public JackTokenizer(String fileName) {
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException fnfe) {
			System.err.println("Couldn't parse file " + fileName + "!");
			fnfe.printStackTrace();
			System.exit(1);
		}

		currentLine = "";
		numLines = 0;
		partOfString = false;

		keyWords = new HashMap<String, KeyWord>();
		keyWords.put("class", KeyWord.CLASS);
		keyWords.put("method", KeyWord.METHOD);
		keyWords.put("function", KeyWord.FUNCTION);
		keyWords.put("constructor", KeyWord.CONSTRUCTOR);
		keyWords.put("int", KeyWord.INT);
		keyWords.put("boolean", KeyWord.BOOLEAN);
		keyWords.put("char", KeyWord.CHAR);
		keyWords.put("void", KeyWord.VOID);
		keyWords.put("var", KeyWord.VAR);
		keyWords.put("static", KeyWord.STATIC);
		keyWords.put("field", KeyWord.FIELD);
		keyWords.put("let", KeyWord.LET);
		keyWords.put("do", KeyWord.DO);
		keyWords.put("if", KeyWord.IF);
		keyWords.put("else", KeyWord.ELSE);
		keyWords.put("while", KeyWord.WHILE);
		keyWords.put("return", KeyWord.RETURN);
		keyWords.put("true", KeyWord.TRUE);
		keyWords.put("false", KeyWord.FALSE);
		keyWords.put("null", KeyWord.NULL);
		keyWords.put("this", KeyWord.THIS);

		tokenTypes1 = new HashMap<String, TokenType>();
		tokenTypes1.put("class", TokenType.KEYWORD);
		tokenTypes1.put("method", TokenType.KEYWORD);
		tokenTypes1.put("function", TokenType.KEYWORD);
		tokenTypes1.put("constructor", TokenType.KEYWORD);
		tokenTypes1.put("int", TokenType.KEYWORD);
		tokenTypes1.put("boolean", TokenType.KEYWORD);
		tokenTypes1.put("char", TokenType.KEYWORD);
		tokenTypes1.put("void", TokenType.KEYWORD);
		tokenTypes1.put("var", TokenType.KEYWORD);
		tokenTypes1.put("static", TokenType.KEYWORD);
		tokenTypes1.put("field", TokenType.KEYWORD);
		tokenTypes1.put("let", TokenType.KEYWORD);
		tokenTypes1.put("do", TokenType.KEYWORD);
		tokenTypes1.put("if", TokenType.KEYWORD);
		tokenTypes1.put("else", TokenType.KEYWORD);
		tokenTypes1.put("while", TokenType.KEYWORD);
		tokenTypes1.put("return", TokenType.KEYWORD);
		tokenTypes1.put("true", TokenType.KEYWORD);
		tokenTypes1.put("false", TokenType.KEYWORD);
		tokenTypes1.put("null", TokenType.KEYWORD);
		tokenTypes1.put("this", TokenType.KEYWORD);
		tokenTypes1.put("(", TokenType.SYMBOL);
		tokenTypes1.put(")", TokenType.SYMBOL);
		tokenTypes1.put("[", TokenType.SYMBOL);
		tokenTypes1.put("]", TokenType.SYMBOL);
		tokenTypes1.put("{", TokenType.SYMBOL);
		tokenTypes1.put("}", TokenType.SYMBOL);
		tokenTypes1.put(",", TokenType.SYMBOL);
		tokenTypes1.put(";", TokenType.SYMBOL);
		tokenTypes1.put("=", TokenType.SYMBOL);
		tokenTypes1.put(".", TokenType.SYMBOL);
		tokenTypes1.put("+", TokenType.SYMBOL);
		tokenTypes1.put("-", TokenType.SYMBOL);
		tokenTypes1.put("*", TokenType.SYMBOL);
		tokenTypes1.put("/", TokenType.SYMBOL);
		tokenTypes1.put("&", TokenType.SYMBOL);
		tokenTypes1.put("|", TokenType.SYMBOL);
		tokenTypes1.put("~", TokenType.SYMBOL);
		tokenTypes1.put("<", TokenType.SYMBOL);
		tokenTypes1.put(">", TokenType.SYMBOL);

		tokenTypes2 = new HashMap<Character, TokenType>();
		tokenTypes2.put('"', TokenType.STRING_CONST);
		tokenTypes2.put('0', TokenType.INT_CONST);
		tokenTypes2.put('1', TokenType.INT_CONST);
		tokenTypes2.put('2', TokenType.INT_CONST);
		tokenTypes2.put('3', TokenType.INT_CONST);
		tokenTypes2.put('4', TokenType.INT_CONST);
		tokenTypes2.put('5', TokenType.INT_CONST);
		tokenTypes2.put('6', TokenType.INT_CONST);
		tokenTypes2.put('7', TokenType.INT_CONST);
		tokenTypes2.put('8', TokenType.INT_CONST);
		tokenTypes2.put('9', TokenType.INT_CONST);
	}

	/**
	 * Do we have more tokens in the input?
	 *
	 * @return
	 */
	private boolean hasMoreTokens() {
		return !currentLine.isEmpty();
	}

	/**
	 * This gets the next token from the input and makes it the current token.
	 */
	public void advance() {
		System.out.println("\tCurrent line:\t\"" + currentLine + "\"");

		// determine if you need to advance the line first
		if (!hasMoreTokens() && hasMoreLines()) nextLine();

		// process the next token
		currentToken = "";
		boolean endToken = false;
		char currentChar;
		// for every character in the line, check to see if this character is a symbol.
		for (int i = 0; i < currentLine.length(); i++) {
			currentChar = currentLine.charAt(i);
			DEBUG_PRINT("\t\t" + i + ") Current Character:\t" + currentChar);

			// if this character marks the beginning of a String literal, suspend normal rules for the String
			if (currentChar == '\"') {
				partOfString = !partOfString;
			}

			// if this character isn't part of a String and is a space or a valid symbol, the current token should end
			if (!partOfString && (currentChar == ' ' || SYMBOLS.indexOf(currentChar) != -1)) // what about "?
			endToken = true;

			// if the token should continue or this is its first character, add it to the token
			if (!endToken || currentToken.length() == 0) currentToken += currentChar;

			// if we are ending the current token, check its type and remove it from the current line before trimming whitespace
			if (endToken) {
				TokenType type = tokenType();
				if (type == TokenType.KEYWORD) DEBUG_PRINT("\tCurrent token matched KEYWORD \"" + currentToken + "\"");
				if (type == TokenType.SYMBOL) DEBUG_PRINT("\tCurrent token matched SYMBOL \"" + currentToken + "\"");
				if (type == TokenType.INT_CONST) DEBUG_PRINT("\tCurrent token matched INT_CONST \"" + currentToken
						+ "\"");
				if (type == TokenType.STRING_CONST) DEBUG_PRINT("\tCurrent token matched STRING_CONST \""
						+ currentToken + "\"");
				if (type == TokenType.IDENTIFIER) DEBUG_PRINT("\tCurrent token matched IDENTIFIER \"" + currentToken
						+ "\"");

				currentLine = currentLine.substring(currentToken.length());
				currentLine = currentLine.trim();
				break;
			}

		}
	}

	/**
	 * Determines if there are more lines in the file, which is directly correlated to it being ready for reading
	 * 
	 * @return
	 */
	public boolean hasMoreLines() {
		boolean ready = false;
		try {
			ready = br.ready();
		} catch (IOException ioe) {
			System.err.println("The file isn't ready to be read!");
			ioe.printStackTrace();
			System.exit(1);
		}
		return ready;
	}

	/**
	 * Advances to the next line of input
	 */
	private void nextLine() {
		String formatted = String.format("%03d", ++numLines);
		try {
			currentLine = br.readLine();
			DEBUG_PRINT("    " + formatted + ") Next line:\t\"" + currentLine + "\"");
			formatLine();
			if (!hasMoreTokens()) nextLine();

		} catch (IOException ioe) {
			System.err.println("Couldn't read the next line!");
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Removes comments and extra whitespace in the current line
	 */
	private void formatLine() {
		if (commentJavaDoc) {
			if (currentLine.contains("*/")) commentJavaDoc = false;
			currentLine = "";
			return;
		}

		if (currentLine.contains("//")) {
			currentLine = currentLine.substring(0, currentLine.indexOf("//"));
		}
		if (currentLine.contains("/*")) {
			if (!currentLine.contains("*/")) commentJavaDoc = true;
			currentLine = currentLine.substring(0, currentLine.indexOf("/*"));
		}
		currentLine = removeWhitespace(currentLine);
	}

	/**
	 * Returns the type of the current token.
	 * 
	 * @return
	 */
	public TokenType tokenType() {
		TokenType type = tokenTypes1.get(currentToken);
		if (type == null) type = tokenTypes2.get(currentToken.charAt(0));
		if (type == null) type = TokenType.IDENTIFIER;

		return type;
	}

	/**
	 * Returns the keyword which is the current token. Should be called only when tokenType() is KEYWORD
	 * 
	 * @return
	 */
	public KeyWord keyWord() {
		KeyWord word = keyWords.get(currentToken);
		if (word == null) throwException("Invalid keyword type " + currentToken);

		return word;
	}

	/**
	 * Returns the String representation of the current KeyWord.
	 * 
	 * @return
	 */
	public String keyWordStr() {
		String keyString = null;
		switch (keyWord()) {
			case BOOLEAN:
				keyString = "boolean";
				break;
			case CHAR:
				keyString = "char";
				break;
			case CLASS:
				keyString = "class";
				break;
			case CONSTRUCTOR:
				keyString = "constructor";
				break;
			case DO:
				keyString = "do";
				break;
			case ELSE:
				keyString = "else";
				break;
			case FALSE:
				keyString = "false";
				break;
			case FIELD:
				keyString = "field";
				break;
			case FUNCTION:
				keyString = "function";
				break;
			case IF:
				keyString = "if";
				break;
			case INT:
				keyString = "int";
				break;
			case LET:
				keyString = "let";
				break;
			case METHOD:
				keyString = "method";
				break;
			case NULL:
				keyString = "null";
				break;
			case RETURN:
				keyString = "return";
				break;
			case STATIC:
				keyString = "static";
				break;
			case THIS:
				keyString = "this";
				break;
			case TRUE:
				keyString = "true";
				break;
			case VAR:
				keyString = "var";
				break;
			case VOID:
				keyString = "void";
				break;
			case WHILE:
				keyString = "while";
				break;
			default:
				break;
		}
		return keyString;
	}

	/**
	 * Returns the character which is the current token. Should be called only when tokenType() is SYMBOL
	 * 
	 * @return
	 */
	public char symbol() {
		return currentToken.charAt(0);
	}

	/**
	 * Returns the identifier which is the current token. Should be called only when tokenType() is IDENTIFIER.
	 * 
	 * @return
	 */
	public String identifier() {
		return currentToken;
	}

	/**
	 * Returns the integer value of the current token. Should be called only when tokenType() is INT_CONST.
	 * 
	 * @return
	 */
	public int intVal() {
		return Integer.valueOf(currentToken);
	}

	/**
	 * Returns the String value of the current token, without the double quotes. Should be called only when tokenType() is STRING_CONST.
	 * 
	 * @return
	 */
	public String stringVal() {
		return currentToken.substring(1, currentToken.length() - 1);
	}

}

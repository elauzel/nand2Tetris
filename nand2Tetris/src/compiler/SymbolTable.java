package compiler;

import static utility.Utils.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import utility.Segment;

public class SymbolTable {
	private static HashMap<String, Symbol>	symbolsSubroutine, symbolsClass;
	// private static LinkedList<HashMap<String, Symbol>> scope;
	private static int						staticCount, fieldCount, argCount, varCount;
	private static Scope					scope;

	// private static int classIndex, subroutineIndex;

	/**
	 * Creates a new empty symbol table.
	 */
	public SymbolTable() {
		scope = Scope.CLASS;

		startSubroutine();
		symbolsClass = new HashMap<String, Symbol>();
		// classIndex = 0;
		staticCount = 0;
		fieldCount = 0;
	}

	/**
	 * Starts a new subroutine scope (i.e., resets the subroutine's symbol table.
	 */
	public void startSubroutine() {
		scope = Scope.SUBROUTINE;
		symbolsSubroutine = new HashMap<String, Symbol>();
		// subroutineIndex = 0;
		argCount = 0;
		varCount = 0;
	}

	/**
	 * Defines a new identifier of a given name, type, and kind and assigns it a running index. <STATIC> and <FIELD> identifiers have a class scope,
	 * while <ARG> and <VAR> identifiers have a subroutine scope.
	 * 
	 * @param name
	 * @param type
	 * @param kind
	 */
	public void define(String name, String type, Identifier kind) {
		DEBUG_PRINT("DEFINING: " + kind + " " + type + " " + name);
		boolean exists;
		boolean sameKind;
		switch (kind) {
			case ARG:
				exists = symbolsSubroutine.get(name) != null;
				sameKind = kindOf(name) == kind;
				if (exists && sameKind) {
					printAll();
					throwException("Identifier ARG " + name + " has already been defined!");
				} else {
					Symbol s = new Symbol(type, kind, argCount++);
					symbolsSubroutine.put(name, s);
				}
				break;
			case FIELD:
				exists = symbolsClass.get(name) != null;
				sameKind = kindOf(name) == kind;
				if (exists && sameKind) {
					printAll();
					throwException("Identifier FIELD " + name + " has already been defined!");
				} else {
					Symbol s = new Symbol(type, kind, fieldCount++);
					symbolsClass.put(name, s);
				}
				break;
			case STATIC:
				exists = symbolsClass.get(name) != null;
				sameKind = kindOf(name) == kind;
				if (exists && sameKind) {
					printAll();
					throwException("Identifier STATIC " + name + " has already been defined!");
				} else {
					Symbol s = new Symbol(type, kind, staticCount++);
					symbolsClass.put(name, s);
				}
				break;
			case VAR:
				exists = symbolsSubroutine.get(name) != null;
				sameKind = kindOf(name) == kind;
				if (exists && sameKind) {
					printAll();
					throwException("Identifier VAR " + name + " has already been defined!");
				} else {
					Symbol s = new Symbol(type, kind, varCount++);
					symbolsSubroutine.put(name, s);
				}
				break;
			default:
				throwException("Invalid Identifier type " + kind + "!");
		}
	}

	/**
	 * Returns the number of variables of the given kind already defined in the current scope.
	 * 
	 * @param kind
	 * @return
	 */
	public int varCount(Identifier kind) {
		int count = 0;
		switch (kind) {
			case ARG:
				count = argCount;
				break;
			case FIELD:
				count = fieldCount;
				break;
			case STATIC:
				count = staticCount;
				break;
			case VAR:
				count = varCount;
				break;
			default:
				throwException("Invalid Identifier type " + kind + "!");
		}
		return count;
	}

	/**
	 * Returns the Segment where the variable type is stored.
	 * 
	 * @param kind
	 * @return
	 */
	public Segment kindToSegment(Identifier kind) {
		Segment seg = null;
		switch (kind) {
			case ARG:
				seg = Segment.ARG;
				break;
			case FIELD:
				seg = Segment.THIS;
				break;
			case STATIC:
				seg = Segment.STATIC;
				break;
			case VAR:
				seg = Segment.LOCAL;
				break;
			default:
				throwException("Invalid Identifier \"" + kind + "\"!");
		}
		return seg;
	}

	/**
	 * Returns the kind of the named identifier in the current scope. If the identifier is unknown in the current scope, returns NONE.
	 * 
	 * @param name
	 * @return
	 */
	public Identifier kindOf(String name) {
		Identifier id;
		Symbol sym = getSymbol(name, scope);
		if (sym == null) {
			id = Identifier.NONE;
		} else {
			id = sym.getKind();
		}
		return id;
	}

	/**
	 * Returns the type of the named identifier in the current scope.
	 * 
	 * @param name
	 * @return
	 */
	public String typeOf(String name) {
		return getSymbol(name, scope).getType();
	}

	/**
	 * Returns the index assigned to the named identifier.
	 * 
	 * @param name
	 * @return
	 */
	public int indexOf(String name) {
		return getSymbol(name, scope).getIndex();
	}

	/**
	 * Returns a String representation of the variable's scope.
	 * 
	 * @param name
	 * @return
	 */
	public Scope scopeOf(String name) {
		Scope s = null;
		if (symbolsSubroutine.containsKey(name)) s = Scope.SUBROUTINE;
		else if (symbolsClass.containsKey(name)) s = Scope.CLASS;
		return s;
	}

	/**
	 * Finds the named identifier in the specified Scope.
	 * 
	 * @param name
	 * @return
	 */
	public Symbol getSymbol(String name, Scope s) {
		Symbol sym;
		if (s == Scope.SUBROUTINE) {
			sym = symbolsSubroutine.get(name);
		} else {
			sym = symbolsClass.get(name);
		}
		return sym;
	}

	/**
	 * Returns the current Scope.
	 * 
	 * @return
	 */
	public Scope getScope() {
		return scope;
	}

	/**
	 * Sets the current Scope.
	 * 
	 * @param s
	 */
	public void setScope(Scope s) {
		scope = s;
	}

	/**
	 * Prints all key:value pairs in the class and subroutine HashMaps.
	 */
	public void printAll() {
		Iterator<Entry<String, Symbol>> iterC = symbolsClass.entrySet().iterator();
		Iterator<Entry<String, Symbol>> iterS = symbolsSubroutine.entrySet().iterator();
		while (iterC.hasNext()) {
			printAndRemove(iterC);
		}
		while (iterS.hasNext()) {
			printAndRemove(iterS);
		}
	}

	/**
	 * Prints all key:value pairs for the Iterator, removing each pair as it is printed.
	 * 
	 * @param iter
	 */
	private void printAndRemove(Iterator<Entry<String, Symbol>> iter) {
		Map.Entry<String, Symbol> pair = (Map.Entry<String, Symbol>) iter.next();
		System.out.println(pair.getValue().getKind() + " " + pair.getValue().getType() + " " + pair.getKey() + " #"
				+ pair.getValue().getIndex());
		iter.remove();
	}
}

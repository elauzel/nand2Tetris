package compiler;

public class Symbol {
	private String		type;
	private Identifier	kind;
	private int			index;

	public Symbol(String type, Identifier kind, int index) {
		this.setType(type);
		this.setKind(kind);
		this.setIndex(index);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Identifier getKind() {
		return kind;
	}

	public void setKind(Identifier kind) {
		this.kind = kind;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		return "Symbol:\tType - " + type + "\tKind - " + kind + "\tIndex - " + index;
	}

}

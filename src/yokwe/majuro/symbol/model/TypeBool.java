package yokwe.majuro.symbol.model;

public class TypeBool extends Type {
	TypeBool() {
		super("BOOL", Kind.BOOL, 1);
	}
	@Override
	public String toString() {
		return String.format("{%s %s}", name, kind);
	}
	@Override
	protected void fix() {
		this.needsFix = false;
	}
}
package yokwe.majuro.symbol.model;

public final class TypePointerLong extends TypePointer {
	public static final int    BITS = 32;
	public static final String NAME = "LONG POINTER";
	
	public TypePointerLong(String name, Type targetType) {
		super(name, BITS, NAME, targetType);
	}
	public TypePointerLong(String name) {
		this(name, null);
	}
}
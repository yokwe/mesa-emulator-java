package yokwe.majuro.symbol.model;

public final class TypePointerShort extends TypePointer {
	public static final int    BITS = 16;
	public static final String NAME = "POINTER";
	
	public TypePointerShort(String name, Type targetType) {
		super(name, BITS, NAME, targetType);
	}
	public TypePointerShort(String name) {
		this(name, null);
	}
}
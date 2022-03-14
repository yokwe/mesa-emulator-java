package yokwe.majuro.symbol.model;

public final class TypePointerLong extends TypePointer {
	public static final int    BITS  = 32;
	public static final String NAME  = "LONG POINTER";
	public static final QName  QNAME = new QName(NAME);
	
	public TypePointerLong(QName qName, Type targetType) {
		super(qName, BITS, NAME, targetType);
	}
	public TypePointerLong(QName qName) {
		this(qName, null);
	}
}
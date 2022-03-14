package yokwe.majuro.symbol.model;

public final class TypePointerShort extends TypePointer {
	public static final int    BITS = 16;
	public static final String NAME = "POINTER";
	public static final QName  QNAME = new QName(NAME);
	
	public TypePointerShort(QName qName, Type targetType) {
		super(qName, BITS, NAME, targetType);
	}
	public TypePointerShort(QName qName) {
		this(qName, null);
	}
}
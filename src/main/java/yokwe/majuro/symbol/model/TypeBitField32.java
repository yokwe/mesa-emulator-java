package yokwe.majuro.symbol.model;

import java.util.List;

public final class TypeBitField32 extends TypeRecord {
	public TypeBitField32(String name, List<Field> fieldList) {
		super(name, "RECORD32", fieldList);
	}
}
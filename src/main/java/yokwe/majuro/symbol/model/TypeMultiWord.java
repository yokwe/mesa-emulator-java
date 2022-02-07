package yokwe.majuro.symbol.model;

import java.util.List;

public final class TypeMultiWord extends TypeRecord {
	public TypeMultiWord(String name, List<Field> fieldList) {
		super(name, "RECORD", fieldList);
	}
}
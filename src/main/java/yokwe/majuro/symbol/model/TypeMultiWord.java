package yokwe.majuro.symbol.model;

import java.util.List;

public final class TypeMultiWord extends TypeRecord {
	public TypeMultiWord(QName qName, List<Field> fieldList) {
		super(qName, SubType.MULTI_WORD, fieldList);
	}
}
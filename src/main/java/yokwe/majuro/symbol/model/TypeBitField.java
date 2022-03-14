package yokwe.majuro.symbol.model;

import java.util.List;

import yokwe.majuro.UnexpectedException;

public final class TypeBitField extends TypeRecord {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	public TypeBitField(QName qName, SubType subType, List<Field> fieldList) {
		super(qName, subType, fieldList);
		
		// sanity check
		switch(subType) {
		case BIT_FIELD_16:
		case BIT_FIELD_32:
			break;
		default:
			logger.error("type    %s", this.toMesaDecl());
			logger.error("subType %s", subType);
			throw new UnexpectedException();
		}
	}
}
package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeBoolean extends Type {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	public static final String NAME  = "BOOLEAN";
	public static final QName  QNAME = new QName(NAME);

	public TypeBoolean(QName qName) {
		super(qName);
		fix();
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	@Override
	public void fix() {
		needsFix = false;
		bitSize = 1;
	}

	@Override
	public String toMesaType() {
		if (qName.equals(NAME)) return "BOOLEAN";
		
		logger.error("Unexpected");
		logger.error("  this  %s", this);
		throw new UnexpectedException("Unexpected");
	}
}

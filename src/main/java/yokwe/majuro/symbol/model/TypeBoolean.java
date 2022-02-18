package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeBoolean extends Type {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	public static final String NAME = "BOOLEAN";

	public TypeBoolean(String name) {
		super(name);
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
		if (name.equals(Type.BOOLEAN.name)) return "BOOLEAN";
		
		logger.error("Unexpected");
		logger.error("  this  %s", this);
		throw new UnexpectedException("Unexpected");
	}
}

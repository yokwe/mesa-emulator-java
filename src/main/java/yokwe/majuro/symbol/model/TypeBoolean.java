package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeBoolean extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypeBoolean.class);

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
		logger.error("  this  {}", this);
		throw new UnexpectedException("Unexpected");
	}
}

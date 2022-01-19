package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypePointer extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypePointer.class);

	public enum Size {
		SHORT,
		LONG,
	}
	public final Size  size;
	public final Type  type;
	
	public TypePointer(String name, Size size, Type type) {
		super(name, Kind.POINTER);
		
		this.size     = size;
		this.type     = type;
		
		fix();
	}
	public TypePointer(String name, Size size) {
		this(name, size, null);
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	public void checkValue(long value) {
		switch(size) {
		case SHORT:
			if (0 <= value && value <= 0x0000_FFFFL) return;
			break;
		case LONG:
			if (0 <= value && value <= 0xFFFF_FFFFL) return;
			break;
		default:
			logger.error("Unexpected");
			logger.error("  this  {}", this);
			throw new UnexpectedException("Unepxected");
		}
	}


	@Override
	public void fix() {
		if (needsFix) {
			if (type == null) {
				needsFix = false;
			} else {
				type.fix();
				if (!type.needsFix) {
					needsFix = false;
				}
			}
		}
	}

}

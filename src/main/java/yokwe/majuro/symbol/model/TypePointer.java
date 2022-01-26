package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypePointer extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypePointer.class);

	public enum PointerSize {
		SHORT,
		LONG,
	}
	public final PointerSize  pointerSize;
	public final Type  targetType;
	
	public TypePointer(String name, PointerSize pointerSize, Type targetType) {
		super(name, Kind.POINTER);
		
		this.pointerSize = pointerSize;
		this.targetType  = targetType;
		
		fix();
	}
	public TypePointer(String name, PointerSize pointerSize) {
		this(name, pointerSize, null);
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	public void checkValue(long value) {
		switch(pointerSize) {
		case SHORT:
			if (0 <= value && value <= 0x0000_FFFFL) return;
			break;
		case LONG:
			if (0 <= value && value <= 0xFFFF_FFFFL) return;
			break;
		default:
			logger.error("Unexpected");
			logger.error("  this  {}", this);
			throw new UnexpectedException("Unexpected");
		}
	}


	@Override
	public void fix() {
		if (needsFix) {
			if (targetType == null) {
				needsFix = false;
			} else {
				targetType.fix();
				if (!targetType.needsFix) {
					needsFix = false;
				}
			}
			
			switch(pointerSize) {
			case SHORT:
				bitSize = 16;
				break;
			case LONG:
				bitSize = 32;
				break;
			default:
				logger.error("Unexpected");
				logger.error("  this  {}", this);
				throw new UnexpectedException("Unexpected");
			}
		}
	}
	
	@Override
	public String toMesaType() {
		String baseType;
		switch(pointerSize) {
		case SHORT:
			baseType = "POINTER";
			break;
		case LONG:
			baseType = "LONG POINTER";
			break;
		default:
			logger.error("Unexpected");
			logger.error("  this  {}", this);
			throw new UnexpectedException("Unexpected");
		}
		
		if (targetType == null) {
			return baseType;
		} else {
			return baseType + " TO " + targetType.toMesaType();
		}
	}
	
	@Override
	public boolean container() {
		return true;
	}
}

package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypePointer extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypePointer.class);

	public enum PointerSize {
		SHORT,
		LONG,
	}
	public final PointerSize pointerSize;
	public final Type        targetType;
	
	public TypePointer(String name, PointerSize pointerSize, Type targetType) {
		super(name);
		
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
		if (shortPointer()) {
			if (0 <= value && value <= 0x0000_FFFFL) return;
		}
		if (longPointer()) {
			if (0 <= value && value <= 0xFFFF_FFFFL) return;
		}
		logger.error("Unexpected");
		logger.error("  this  {}", this);
		throw new UnexpectedException("Unexpected");
	}


	@Override
	public void fix() {
		if (needsFix) {
			if (rawPointer()) {
				needsFix = false;
			} else {
				targetType.fix();
				if (!targetType.needsFix) {
					needsFix = false;
				}
			}
			
			if (shortPointer()) {
				bitSize = 16;
			} else if (longPointer()) {
				bitSize = 32;
			} else {
				logger.error("Unexpected");
				logger.error("  this  {}", this);
				throw new UnexpectedException("Unexpected");
			}
		}
	}
	
	@Override
	public String toMesaType() {
		String baseType;
		if (shortPointer()) {
			baseType = POINTER.name;
		} else if (longPointer()) {
			baseType = LONG_POINTER.name;
		} else {
			logger.error("Unexpected");
			logger.error("  this  {}", this);
			throw new UnexpectedException("Unexpected");
		}

		if (rawPointer()) {
			return baseType;
		} else {
			return baseType + " TO " + targetType.toMesaType();
		}
	}   
}

package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public abstract class TypePointer extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypePointer.class);

	private final int    bits;
	private final long   maxValue;
	private final String typeName;
	
	public final Type   targetType;
	
	protected TypePointer(String name, int bits, String typeName, Type targetType) {
		super(name);
		
		this.bits       = bits;
		this.maxValue   = (1L << bits) - 1;
		this.typeName   = typeName;		
		this.targetType = targetType;
		fix();
	}
	protected TypePointer(String name, int bits, String typeName) {
		this(name, bits, typeName, null);
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	public void checkValue(long value) {
		if (0 <= value && value <= maxValue) return;
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
			
			bitSize = bits;
		}
	}
	
	@Override
	public String toMesaType() {
		if (rawPointer()) {
			return typeName;
		} else {
			return typeName + " TO " + targetType.toMesaType();
		}
	}
	
	public static final class Long extends TypePointer {
		public static final int    BITS = 32;
		public static final String NAME = "LONG POINTER";
		
		public Long(String name, Type targetType) {
			super(name, BITS, NAME, targetType);
		}
		public Long(String name) {
			this(name, null);
		}
	}
	
	public static final class Short extends TypePointer {
		public static final int    BITS = 16;
		public static final String NAME = "POINTER";
		
		public Short(String name, Type targetType) {
			super(name, BITS, NAME, targetType);
		}
		public Short(String name) {
			this(name, null);
		}
	}
}

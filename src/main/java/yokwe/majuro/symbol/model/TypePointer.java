package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public abstract class TypePointer extends Type {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	private final int    bits;
	private final long   maxValue;
	private final String typeName;
	
	public final Type   pointerTarget;
	
	protected TypePointer(String name, int bits, String typeName, Type pointerTarget) {
		super(name);
		
		this.bits          = bits;
		this.maxValue      = (1L << bits) - 1;
		this.typeName      = typeName;		
		this.pointerTarget = pointerTarget;
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
		logger.error("  this  ", this);
		throw new UnexpectedException("Unexpected");
	}


	@Override
	public void fix() {
		if (needsFix) {
			if (rawPointer()) {
				needsFix = false;
			} else {
				pointerTarget.fix();
				if (!pointerTarget.needsFix) {
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
			return typeName + " TO " + pointerTarget.toMesaType();
		}
	}
}

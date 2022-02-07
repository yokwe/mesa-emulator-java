package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;

public abstract class TypeArray extends Type {
	static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypeArray.class);
	
	public final Type arrayElement;
	
	public long minValue;
	public long maxValue;
	public long size;

	protected TypeArray(String name, Type arrayElement) {
		super(name);
		
		this.arrayElement = arrayElement;
		
		this.minValue = 0;
		this.maxValue = 0;
		this.size     = 0;
	}
		
	public void checkValue(long value) {
		if (!needsFix && minValue <= value && value <= maxValue) return;
		logger.error("Unexpected");
		logger.error("  value {}", value);
		logger.error("  this  {}", this);
		throw new UnexpectedException("Unexpected");
	}
	
	protected void setValue(long minValue, long maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.size     = maxValue - minValue + 1;
	}
	
	public TypeArraySub toSubrange() {
		if (this instanceof TypeArraySub) {
			return (TypeArraySub)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeArrayRef toReference() {
		if (this instanceof TypeArrayRef) {
			return (TypeArrayRef)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
}

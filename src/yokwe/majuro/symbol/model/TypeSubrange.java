package yokwe.majuro.symbol.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public abstract class TypeSubrange extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeSubrange.class);

	public final TypeReference baseType;
	public       Const         valueMin;
	public       Const         valueMax;
	public final boolean       valueMaxInclusive;
	
	public int  length;
	
	public TypeSubrange(String name, int size, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.baseType = new TypeReference(name + "#base", baseName);
		if (valueMin.matches("^-?[0-9]+$")) {
			this.valueMin = new Const(name + "#valueMin", Type.LONG_CARDINAL, Long.parseLong(valueMin));
		} else {
			this.valueMin = new Const(name + "#valueMin", Type.LONG_CARDINAL, valueMin);
		}
		if (valueMax.matches("^-?[0-9]+$")) {
			this.valueMax = new Const(name + "#valueMax", Type.LONG_CARDINAL, Long.parseLong(valueMax));
		} else {
			this.valueMax = new Const(name + "#valueMax", Type.LONG_CARDINAL, valueMax);
		}
		this.valueMaxInclusive = valueMaxInclusive;
		this.length   = 0;
		
		fix();
	}
	public TypeSubrange(String name, int size, String baseName, long valueMin, long valueMax, boolean valueMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.baseType = new TypeReference(name + "#base", baseName);
		this.valueMin = new Const(name + "#valueMinLong", Type.LONG_CARDINAL, valueMin);
		this.valueMax = new Const(name + "#valueMaxLong", Type.LONG_CARDINAL, valueMax);
		this.valueMaxInclusive = valueMaxInclusive;
		this.length   = 0;
		
		fix();
	}
	public TypeSubrange(String name, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		this(name, Type.UNKNOWN_SIZE, baseName, valueMin, valueMax, valueMaxInclusive);
	}
	
	public void checkValue(long rangeMax, long rangeMin) {
		if (!needsFix) {
			if (rangeMin < this.valueMin.numericValue) {
				logger.error("Unexpected rangeMin");
				logger.error("  rangeMin {}", rangeMin);
				logger.error("  valueMin {}", this.valueMin);
				throw new UnexpectedException("Unexpected rangeMin");
			}
			if (this.valueMax.numericValue < rangeMax) {
				logger.error("Unexpected rangeMax");
				logger.error("  rangeMax {}", rangeMax);
				logger.error("  valueMax {}", this.valueMax);
				throw new UnexpectedException("Unexpected rangeMax");
			}
		}
	}

	public void checkValue(long value) {
		if (!needsFix) {
			if (value < this.valueMin.numericValue) {
				logger.error("Unexpected value");
				logger.error("  value    {}", value);
				logger.error("  valueMin {}", this.valueMin);
				throw new UnexpectedException("Unexpected rangeMin");
			}
			if (this.valueMax.numericValue < value) {
				logger.error("Unexpected value");
				logger.error("  value    {}", value);
				logger.error("  valueMax {}", this.valueMax);
				throw new UnexpectedException("Unexpected rangeMax");
			}
		}
	}

	@Override
	public String toString() {
		return String.format("{%s %d %s %s %s %s %s}", name, size, kind, baseType, valueMin, valueMax, valueMaxInclusive);
	}
	
	@Override
	protected void fix() {
		if (needsFix) {
			baseType.fix();
			valueMin.fix();
			valueMax.fix();
			
			if (!baseType.needsFix && !valueMin.needsFix && !valueMax.needsFix) {
				if (!baseType.baseType.isSubrange()) {
					logger.error("Unexpected baseType");
					logger.error("  baseType {}", baseType);
					throw new UnexpectedException("Unexpected baseType");
				}
				
				long length = valueMax.numericValue - valueMin.numericValue;
				// sanity check
				if (length < 0) {
					logger.error("Unexpected length");
					logger.error("  valueMin {}", valueMin);
					logger.error("  valueMax {}", valueMax);
					logger.error("  length   {}", length);
					throw new UnexpectedException("Unexpected length");
				}
				
				// FIXME
				if (valueMin.typeSubrange != null && valueMax.typeSubrange != null &&
					valueMin.numericValue == valueMin.typeSubrange.valueMin.numericValue && valueMax.numericValue == valueMax.typeSubrange.valueMax.numericValue) {
					//
				} else {
					if (Integer.MAX_VALUE <= length) {
						logger.error("Unexpected length");
						logger.error("  valueMin {}", valueMin);
						logger.error("  valueMax {}", valueMax);
						logger.error("  this     {}", this);
						throw new UnexpectedException("Unexpected length");
					}		
				}
				
				this.size   = baseType.size;
				this.length = (int)length;
				this.needsFix = false;
			}
		}
	}
}

class TypeSubrangeFull extends TypeSubrange {
	private static final Logger logger = LoggerFactory.getLogger(TypeSubrangeFull.class);
	public TypeSubrangeFull(String name, String baseName) {
		super(name, baseName, "0", "0", false);
	}
	
	@Override
	protected void fix() {
		{
			baseType.fix();
			
			if (!baseType.needsFix) {
				if (!baseType.baseType.isSubrange()) {
					logger.error("Unexpected baseType");
					logger.error("  baseType {}", baseType);
					throw new UnexpectedException("Unexpected baseType");
				}
				TypeSubrange typeSubrange = (TypeSubrange)baseType.baseType;
				this.valueMin = typeSubrange.valueMin;
				this.valueMax = typeSubrange.valueMax;
			}
		}
		super.fix();
	}
}

class TypeSubrangeRange extends TypeSubrange {
	public TypeSubrangeRange(String name, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		super(name, baseName, valueMin, valueMax, valueMaxInclusive);
	}
	public TypeSubrangeRange(String name, int size, String baseName, long valueMin, long valueMax, boolean valueMaxInclusive) {
		super(name, size, baseName, valueMin, valueMax, valueMaxInclusive);
	}
}


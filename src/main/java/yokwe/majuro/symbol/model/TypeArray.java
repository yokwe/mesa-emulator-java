package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constant.WORD_BITS;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public abstract class TypeArray extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypeArray.class);
	
	public static class Subrange extends TypeArray {
		public TypeSubrange typeSubrange;
		
		public Subrange(String name, TypeSubrange typeSubrange, Type arrayElement) {
			super(name, arrayElement);
			this.typeSubrange = typeSubrange;
			
			fix();
		}
		
		@Override
		public void fix() {
			if (needsFix) {
				arrayElement.fix();
				typeSubrange.fix();
				
				if (!arrayElement.needsFix && !typeSubrange.needsFix) {
					needsFix = false;
					setValue(typeSubrange.minValue, typeSubrange.maxValue);
					
					// To calculate bitSize, use arrayElemen.wordSize() * WORD_BITS
					// Because arrayElement is aligned to WORD.
					bitSize = arrayElement.wordSize() * WORD_BITS * (int)size;
				}
			}
		}
		
		@Override
		public String toString() {
			return StringUtil.toString(this);
		}

		@Override
		public String toMesaType() {
			return String.format("ARRAY %s OF %s", typeSubrange.toMesaType(), arrayElement.toMesaType());
		}
	}
	public static class Reference extends TypeArray {
		public TypeReference typeReference;
		
		public Reference(String name, TypeReference typeReference, Type arrayElement) {
			super(name, arrayElement);
			this.typeReference = typeReference;
			
			fix();
		}
		
		@Override
		public void fix() {
			if (needsFix) {
				arrayElement.fix();
				typeReference.fix();
				
				if (!arrayElement.needsFix && !typeReference.needsFix) {
					needsFix = false;
					
					Type realType = typeReference.realType;
					if (realType instanceof TypeSubrange) {
						TypeSubrange typeSubrange = realType.toTypeSubrange();
						setValue(typeSubrange.minValue, typeSubrange.maxValue);
					} else if (realType instanceof TypeEnum) {
						TypeEnum typeEnum = realType.toTypeEnum();
						setValue(typeEnum.minValue, typeEnum.maxValue);
					} else {
						logger.error("Unexpected");
						logger.error("  this  {}", this);
						throw new UnexpectedException("Unexpected");
					}
					
					// To calculate bitSize, use arrayElemen.wordSize() * WORD_BITS
					// Because arrayElement is aligned to WORD.
					bitSize = arrayElement.wordSize() * WORD_BITS * (int)size;
				}
			}
		}
		
		@Override
		public String toString() {
			return StringUtil.toString(this);
		}

		@Override
		public String toMesaType() {
			return String.format("ARRAY %s OF %s", typeReference.toMesaType(), arrayElement.toMesaType());
		}
	}

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
	
	public TypeArray.Subrange toSubrange() {
		if (this instanceof TypeArray.Subrange) {
			return (TypeArray.Subrange)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeArray.Reference toReference() {
		if (this instanceof TypeArray.Reference) {
			return (TypeArray.Reference)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	
	@Override
	public boolean container() {
		return true;
	}
}

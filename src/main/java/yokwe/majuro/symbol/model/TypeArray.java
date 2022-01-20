package yokwe.majuro.symbol.model;

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
					
					bitSize = arrayElement.bitSize() * (int)size;
				}
			}
		}
		
		@Override
		public String toString() {
			return StringUtil.toString(this);
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
					switch(realType.kind) {
					case SUBRANGE:
						TypeSubrange typeSubrange = (TypeSubrange)realType;
						setValue(typeSubrange.minValue, typeSubrange.maxValue);
						break;
					case ENUM:
						TypeEnum typeEnum = (TypeEnum)realType;
						setValue(typeEnum.minValue, typeEnum.maxValue);
						break;
					default:
						logger.error("Unexpected");
						logger.error("  this  {}", this);
						throw new UnexpectedException("Unexpected");
					}
					
					bitSize = arrayElement.bitSize() * (int)size;
				}
			}
		}
		
		@Override
		public String toString() {
			return StringUtil.toString(this);
		}
	}

	public final Type arrayElement;
	
	public long minValue;
	public long maxValue;
	public long size;

	public TypeArray(String name, Type arrayElement) {
		super(name, Kind.ARRAY);
		
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
}

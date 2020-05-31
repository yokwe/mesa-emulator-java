package yokwe.majuro.symbol.antlr.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class TypeSubrange extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeSubrange.class);

	public final TypeReference baseType;
	public final long          valueMin;
	public final long          valueMax;
	public final int           length;
	
	public TypeSubrange(String name, int size, String baseName, long valueMin, long valueMax) {
		super(name, Kind.SUBRANGE, size);
		
		long length = valueMax - valueMin;
		// sanity check
		if (length < 0) {
			logger.error("Unexpected length");
			logger.error("  valueMin {}", valueMin);
			logger.error("  valueMax {}", valueMax);
			logger.error("  length   {}", length);
			throw new UnexpectedException("Unexpected length");
		}
		if (Integer.MAX_VALUE <= length) {
			logger.error("Unexpected length");
			logger.error("  valueMin {}", valueMin);
			logger.error("  valueMax {}", valueMax);
			logger.error("  length   {}", length);
			throw new UnexpectedException("Unexpected length");
		}

		this.baseType = new TypeReference(name + "#base", baseName);
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		this.length   = (int)length;
		
		fix();
	}
	public TypeSubrange(String name, String baseName, long valueMin, long valueMax) {
		this(name, Type.UNKNOWN_SIZE, baseName, valueMin, valueMax);
	}
	
	public void checkValue(long rangeMax, long rangeMin) {
		if (!needsFix) {
			if (rangeMin < this.valueMin) {
				logger.error("Unexpected rangeMin");
				logger.error("  rangeMin {}", rangeMin);
				logger.error("  valueMin {}", this.valueMin);
				throw new UnexpectedException("Unexpected rangeMin");
			}
			if (this.valueMax < rangeMax) {
				logger.error("Unexpected rangeMax");
				logger.error("  rangeMax {}", rangeMax);
				logger.error("  valueMax {}", this.valueMax);
				throw new UnexpectedException("Unexpected rangeMax");
			}
		}
	}

	@Override
	public String toString() {
		return String.format("{%s %s %d %d}", name, size, kind, baseType, valueMin, valueMax);
	}
	
	@Override
	protected void fix() {
		if (needsFix) {
			baseType.fix();
			if (!baseType.needsFix) {
				switch(baseType.kind) {
				case SUBRANGE:
					break;
				default:
					logger.error("Unexpected baseType");
					logger.error("  baseType {}", baseType);
					throw new UnexpectedException("Unexpected baseType");
				}
				
				size     = baseType.size;
				needsFix = false;
			}
		}
	}
}
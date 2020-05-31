package yokwe.majuro.symbol.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class TypeEnum extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeEnum.class);

	public static class Element {
		public final String name;
		public final int    value;
		Element(String name, int value) {
			this.name  = name;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.format("{%s %d}", name, value);
		}
	}
	
	public final List<Element> elementList;
	public final long          valueMin;
	public final long          valueMax;
	public final int           length;
	
	TypeEnum(String name, List<Element> elementList) {
		super(name, Kind.ENUM, 1);
				
		long valueMin = elementList.stream().mapToInt(o -> o.value).min().getAsInt();
		long valueMax = elementList.stream().mapToInt(o -> o.value).max().getAsInt();
		long length = valueMax - valueMin;
		// sanity check
		{
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
		}
		
		this.elementList = elementList;
		this.valueMin    = valueMin;
		this.valueMax    = valueMax;
		this.length      = (int)length;
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
		return String.format("{%s %s %d %d %d %s}", name, kind, size, valueMin, valueMax, elementList);
//		return String.format("{%s %s %d %s}", name, kind, size, elementList);
	}
	
	@Override
	protected void fix() {
		this.needsFix = false;
	}
}
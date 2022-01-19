package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeSubrange extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypeSubrange.class);
	
	public static final String CLOSE_CHAR_INCLUSIVE = "]";
	public static final String CLOSE_CHAR_EXCLUSIVE = ")";
	
	public final String minString;
	public final String maxString;
	public final String closeChar;
	
	public long minValue;
	public long maxValue;
	public long size;

	public TypeSubrange(String name, String minString, String maxString, String closeChar) {
		super(name, Kind.SUBRANGE);
		
		this.minString = minString;
		this.maxString = maxString;
		this.closeChar = closeChar;
		
		fix();
	}
	public TypeSubrange(String name, long minValue, long maxValue) {
		super(name, Kind.SUBRANGE);
		
		this.minString = Long.toString(minValue);
		this.maxString = Long.toString(maxValue);
		this.closeChar = CLOSE_CHAR_INCLUSIVE;
		
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.size     = this.maxValue - this.minValue + 1;
		
		this.needsFix = false;
	}

	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	public void checkValue(long value) {
		if (minValue <= value && value <= maxValue) return;
		logger.error("Unexpected");
		logger.error("  value {}", value);
		logger.error("  this  {}", this);
		throw new UnexpectedException("Unepxected");
	}
	
	public void fix() {
		if (needsFix) {
			Long minNumeric = Constant.getNumericValue(minString);
			Long maxNumeric = Constant.getNumericValue(maxString);
			
			if (minNumeric != null && maxNumeric != null) {
				needsFix = false;
				minValue = minNumeric;
				maxValue = maxNumeric;
				if (closeChar.equals(CLOSE_CHAR_EXCLUSIVE)) maxValue--;
				size = maxValue - minValue + 1;

				// sanity check
				if (minString.equals(maxString) && closeChar.equals(CLOSE_CHAR_EXCLUSIVE)) {
					// case of open array
				} else {
					if (maxValue < minValue) {
						logger.error("Unexpected");
						logger.error("  {}", this);
						throw new UnexpectedException("Unepxected");
					}
				}
			}
		}
	}
}

package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeSubrange extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypeSubrange.class);
	
	public static final String CLOSE_CHAR_INCLUSIVE = "]";
	public static final String CLOSE_CHAR_EXCLUSIVE = ")";
	
	public final String  minString;
	public final String  maxString;
	public final String  closeChar;
	
	public long minValue;
	public long maxValue;
	public long size;

	public TypeSubrange(String name, String minString, String maxString, String closeChar) {
		super(name, Kind.SUBRANGE);
		
		this.minString       = minString;
		this.maxString       = maxString;
		this.closeChar       = closeChar;
		
		fix();
	}
	public TypeSubrange(String name, long minValue, long maxValue) {
		this(name, Long.toString(minValue), Long.toString(maxValue), CLOSE_CHAR_INCLUSIVE);
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
		throw new UnexpectedException("Unexpected");
	}
	
	@Override
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
				
				if (size == 0) {
					bitSize = 0;
				} else {
					bitSize = Type.bitSize(size - 1);
				}

				// sanity check
				if (minString.equals(maxString) && closeChar.equals(CLOSE_CHAR_EXCLUSIVE)) {
					// case of open array
				} else {
					if (maxValue < minValue) {
						logger.error("Unexpected");
						logger.error("  {}", this);
						throw new UnexpectedException("Unexpected");
					}
				}
			}
		}
	}
	
	@Override
	public String toMesaType() {
		return String.format("[%s..%s%s", minString, maxString, closeChar);
	}
	
	@Override
	public boolean container() {
		return false;
	}
}

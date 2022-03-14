package yokwe.majuro.symbol.model;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeSubrange extends Type {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	public static final String NAME_INTEGER          = "INTEGER";
	public static final String NAME_CARDINAL         = "CARDINAL";
	public static final String NAME_LONG_CARDINAL    = "LONG_CARDINAL";
	public static final String NAME_UNSPECIFIED      = "UNSPECIFIED";
	public static final String NAME_LONG_UNSPECIFIED = "LONG UNSPECIFIED";
	
	public static final QName QNAME_INTEGER          = new QName(NAME_INTEGER);
	
	public static final QName QNAME_CARDINAL         = new QName(NAME_CARDINAL);
	public static final QName QNAME_LONG_CARDINAL    = new QName(NAME_LONG_CARDINAL);
	
	public static final QName QNAME_UNSPECIFIED      = new QName(NAME_UNSPECIFIED);
	public static final QName QNAME_LONG_UNSPECIFIED = new QName(NAME_LONG_UNSPECIFIED);
	

	public static final String CLOSE_CHAR_INCLUSIVE = "]";
	public static final String CLOSE_CHAR_EXCLUSIVE = ")";
		
	public final String  minString;
	public final String  maxString;
	public final String  closeChar;
	
	public long minValue;
	public long maxValue;
	public long size;

	public TypeSubrange(QName qName, String minString, String maxString, String closeChar) {
		super(qName);
		
		this.minString       = minString;
		this.maxString       = maxString;
		this.closeChar       = closeChar;
		
		fix();
	}
	public TypeSubrange(QName qName, long minValue, long maxValue) {
		this(qName, Long.toString(minValue), Long.toString(maxValue), CLOSE_CHAR_INCLUSIVE);
	}

	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	public void checkValue(long value) {
		if (minValue <= value && value <= maxValue) return;
		logger.error("Unexpected");
		logger.error("  value %s", value);
		logger.error("  this  %s", this);
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
						logger.error("  %s", this);
						throw new UnexpectedException("Unexpected");
					}
				}
			}
		}
	}
	
	@Override
	public String toMesaType() {
		// special for predefined class
		if (isPredefiled()) {
			return qName.qName;
		} else {
			return String.format("[%s..%s%s", minString, maxString, closeChar);
		}
	}
}

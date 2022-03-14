package yokwe.majuro.symbol.model;

import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.ClassUtil;
import yokwe.majuro.util.StringUtil;

public class Constant implements Comparable<Constant> {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	public static Map<String, Constant> map = new TreeMap<>();
	//                qName.qName
	private static void add(Constant cons) {
		String key = cons.qName.qName;
		// add to map
		if (map.containsKey(key)) {
			logger.error("duplicate key");
			logger.error("  key  %s", key);
			logger.error("  old  %s", map.get(key));
			logger.error("  new  %s", cons);
			throw new UnexpectedException("duplicate key");
		} else {
			map.put(key, cons);
		}
	}
	public static int fixAll() {
		int countNeedsFixLast = -1;
		int countNeedsFix     = -1;
		for(;;) {
			countNeedsFix = 0;
			for(var value: map.values()) {					
				if (value.needsFix) {
					value.fix();
					if (value.needsFix) countNeedsFix++;
				}
			}
			if (countNeedsFix == 0) break;
			if (countNeedsFix == countNeedsFixLast) break;
			countNeedsFixLast = countNeedsFix;
		}
		return countNeedsFix;
	}
	
	
	//
	// parse mesa string
	//
	public static long parseLong(String text) {
		long numberSign;
		String numberString;
		if (text.startsWith("-")) {
			numberSign = -1;
			numberString = text.substring(1);
		} else {
			numberSign = +1;
			numberString = text;
		}
		
		int length = numberString.length();
		try {
			switch(numberString.charAt(length - 1)) {
			case 'b':
			case 'B':
				return Long.parseUnsignedLong(numberString.substring(0, length - 1), 8) * numberSign;
			case 'x':
			case 'X':
				return Long.parseUnsignedLong(numberString.substring(0, length - 1), 16) * numberSign;
			case 'd':
			case 'D':
				return Long.parseUnsignedLong(numberString.substring(0, length - 1), 10) * numberSign;
			default:
				return Long.parseUnsignedLong(numberString, 10) * numberSign;
			}
		} catch (NumberFormatException e) {
			logger.error("Unexpected number format");
			logger.error("  text %s!", text);
			String exceptionName = e.getClass().getSimpleName();
			logger.error("%s %s", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	public static Long getNumericValue(String string) {
		if (string.matches("^(\\+|-)?\\d+[bBxDdD]?$")) {
			// mesa style numeric constant
			return parseLong(string);
		} else if (string.startsWith("0x")) {
			// java style numeric constant
			return Long.parseLong(string.substring(2).replaceAll("_", ""), 16);
		} else {
			if (map.containsKey(string)) {
				// constant is defined in type file
				Constant cons = map.get(string);
				if (cons.needsFix) {
					logger.error("cons needs fix");
					logger.error("  cons %s", cons);
					throw new UnexpectedException("cons needs fix");
				}
				return getNumericValue(cons.valueString);
			} else {
				// constant is defined in java class file
				return ClassUtil.getStaticNumericValue(string);
			}
		}
	}

	public final QName  qName;
	public final Type   type;
	public final String valueString;

	public boolean needsFix;
	public void fix() {
		if (needsFix) {
			type.fix();
			if (!type.needsFix) {
				needsFix = false;
				
				Type realType = type.realType();
				if (realType instanceof TypeSubrange)     return;
				if (realType instanceof TypePointerShort) return;
				if (realType instanceof TypePointerLong)  return;
				
				logger.error("Unexpected");
				logger.error("  this      %s", this);
				logger.error("  realType  %s", realType.getClass().getSimpleName());
				throw new UnexpectedException("Unexpected");
			}
		}
	}
	
	public Constant(QName qName, Type type, String valueString) {
		this.qName       = qName;
		this.type        = type;
		this.valueString = valueString;
		
		this.needsFix = true;
		
		add(this);
		
		fix();
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	@Override
	public int compareTo(Constant that) {
		return this.qName.compareTo(that.qName);
	}
	
	public String toMesaDecl() {
		return String.format("%s: %s = %s;", qName, type.toMesaType(), valueString);
	}
}

package yokwe.majuro.symbol.model;

import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.ClassUtil;
import yokwe.majuro.util.StringUtil;

public class Constant implements Comparable<Constant> {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Constant.class);
	
	public static Map<String, Constant> map = new TreeMap<>();
	//                name
	private static void add(Constant cons) {
		String name = cons.name;
		// add to map
		if (map.containsKey(name)) {
			logger.error("duplicate name");
			logger.error("  old  {}", map.get(name));
			logger.error("  new  {}", cons);
			throw new UnexpectedException("duplicate name");
		} else {
			map.put(name, cons);
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
			logger.error("  text {}!", text);
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
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
					logger.error("  cons {}", cons);
					throw new UnexpectedException("cons needs fix");
				}
				return cons.numericValue;
			} else {
				// constant is defined in java class file
				return ClassUtil.getStaticNumericValue(string);
			}
		}
	}

	public final String name;
	public final Type   type;
	public final String valueString;
	
	public long numericValue;

	public boolean needsFix;
	public void fix() {
		if (needsFix) {
			type.fix();
			Long value = Constant.getNumericValue(valueString);
			if (!type.needsFix && value != null) {
				numericValue = value.intValue();
				needsFix = false;
				
				// sanity check
				if (type instanceof TypeSubrange) {
					TypeSubrange typeSubrange = type.toTypeSubrange();
					typeSubrange.checkValue(numericValue);
				} else if (type instanceof TypePointer) {
					TypePointer typePointer = type.toTypePointer();
					typePointer.checkValue(numericValue);
				} else {
					logger.error("Unexpected");
					logger.error("  this  {}", this);
					throw new UnexpectedException("Unexpected");
				}
			}
		}

	}
	
	public Constant(String name, Type type, String valueString) {
		this.name        = name;
		this.type        = type;
		this.valueString = valueString;
		
		this.numericValue = 0;
		
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
		return this.name.compareTo(that.name);
	}
	
	public String toMesaType() {
		return String.format("%s: %s = %s", name, type.toMesaType(), valueString);
	}

}

package yokwe.majuro.symbol.model;

import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type.Kind;
import yokwe.majuro.util.ClassUtil;
import yokwe.majuro.util.StringUtil;

public class Constant {
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
	
	public static Long getNumericValue(String string) {
		if (string.matches("^(\\+|-)?\\d+$")) {
			return Long.parseLong(string);
		} else {
			if (map.containsKey(string)) {
				Constant cons = map.get(string);
				if (cons.needsFix) {
					logger.error("cons needs fix");
					logger.error("  cons {}", cons);
					throw new UnexpectedException("cons needs fix");
				}
				return cons.numericValue;
			} else {
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
				if (type.kind == Kind.SUBRANGE) {
					TypeSubrange typeSubrange = (TypeSubrange)type;
					typeSubrange.checkValue(numericValue);
				} else if (type.kind == Kind.POINTER) {
					TypePointer typePointer = (TypePointer)type;
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

}
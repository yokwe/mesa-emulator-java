package yokwe.majuro.symbol.model;

import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public abstract class Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Type.class);

	public static Type getRealType(String name) {
		if (map.containsKey(name)) {
			Type realType = map.get(name);
			while(realType instanceof TypeReference) {
				TypeReference typeReference = (TypeReference)realType;
				String typeString = typeReference.typeString;
				if (map.containsKey(typeString)) {
					realType = map.get(typeString);
				} else {
					return null;
				}
			}
			return realType;
		} else {
			return null;
		}
	}

	public static Map<String, Type> map = new TreeMap<>();
	//                name
	private static void add(Type type) {
		String name = type.name;
		// add to map
		if (map.containsKey(name)) {
			logger.error("duplicate name");
			logger.error("  old  {}", map.get(name));
			logger.error("  new  {}", type);
			throw new UnexpectedException("duplicate name");
		} else {
			map.put(name, type);
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

	public enum Kind {
		// boolean
		BOOLEAN,
		// [MIN_VALUE .. MAX_VALUE] of CARDINAL, INTEGER
		SUBRANGE,
		// array subrange of numeric type
		ARRAY,
		// enum list of name-value pair
		ENUM,
		// record list of field (name-type pair)
		RECORD,
		// long or short pointer to type
		POINTER,
		// name of other type
		REFERENCE,
	}
	
	//
	// Define simple type
	//
	public static final Type BOOLELAN         = new TypeBoolean ("BOOLEAN");
	public static final Type INTEGER          = new TypeSubrange("INTEGER", Short.MIN_VALUE, Short.MAX_VALUE);
	
	public static final Type CARDINAL         = new TypeSubrange("CARDINAL",         0, 0xFFFF);
	public static final Type UNSPECIFIED      = new TypeSubrange("UNSPECIFIED",      0, 0xFFFF);
	
	public static final Type LONG_CARDINAL    = new TypeSubrange("LONG CARDINAL",    0, 0xFFFF_FFFFL);
	public static final Type LONG_UNSPECIFIED = new TypeSubrange("LONG UNSPECIFIED", 0, 0xFFFF_FFFFL);
	
	public static final Type POINTER          = new TypePointer ("POINTER",      TypePointer.Size.SHORT);
	public static final Type LONG_POINTER     = new TypePointer ("LONG POINTER", TypePointer.Size.LONG);

	
	public final String name;
	public final Kind   kind;
	
	public boolean needsFix;
	abstract public void fix();

	protected Type(String name, Kind kind) {
		this.name = name;
		this.kind = kind;
		
		this.needsFix = true;
		
		add(this);
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

}

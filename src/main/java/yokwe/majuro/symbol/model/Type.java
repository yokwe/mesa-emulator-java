package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constant.WORD_BITS;

import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public abstract class Type implements Comparable<Type> {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Type.class);

	public static Type findRealType(String name) {
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

	public static int bitSize(long value) {
		if (value == 0) return 0;
		if (0 < value) {
			for(int i = 1; i <= 32; i++) {
				long n = 1L << i;
				if (value == n) return i + 1;
				if (value < n)  return i;
			}
		}
		logger.error("Unexpected");
		logger.error("  value dec {}", Long.toString(value));
		logger.error("  value hex {}", Long.toHexString(value));
		logger.error("  value bin {}", Long.toBinaryString(value));
		throw new UnexpectedException("Unexpected");
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
	
	public String getJavaName() {
		return StringUtil.toJavaName(name);
	}
	
	public boolean needsFix;
	abstract public void fix();
	
	abstract public String toMesaType();
	abstract public String toJavaType();
	
	private static final int NO_VALUE = -1;
	public int bitSize = NO_VALUE;
	public int bitSize() {
		if (needsFix) throw new UnexpectedException("Unexpected");
		if (bitSize == NO_VALUE) {
			logger.error("bitSize == NO_VALUE");
			logger.error("name  {}", name);
			logger.error("type  {}", this);
			throw new UnexpectedException("Unexpected");
		}
		return bitSize;
	}
	
	public int wordSize() {
		return (bitSize + WORD_BITS - 1) / WORD_BITS;
	}
	
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
	
	@Override
	public int compareTo(Type that) {
		return this.name.compareTo(that.name);
	}

	public Type getRealType() {
		if (needsFix) throw new UnexpectedException("Unexpected");
		if (kind == Kind.REFERENCE) {
			return ((TypeReference)this).realType;
		} else {
			return this;
		}
	}
}

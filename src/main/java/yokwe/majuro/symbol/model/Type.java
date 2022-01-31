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

	//
	// Define simple type
	//
	public static final Type BOOLEAN          = new TypeBoolean ("BOOLEAN");
	public static final Type INTEGER          = new TypeSubrange("INTEGER", Short.MIN_VALUE, Short.MAX_VALUE);
	
	public static final Type CARDINAL         = new TypeSubrange("CARDINAL",         0, 0xFFFF);
	public static final Type UNSPECIFIED      = new TypeSubrange("UNSPECIFIED",      0, 0xFFFF);
	
	public static final Type LONG_CARDINAL    = new TypeSubrange("LONG CARDINAL",    0, 0xFFFF_FFFFL);
	public static final Type LONG_UNSPECIFIED = new TypeSubrange("LONG UNSPECIFIED", 0, 0xFFFF_FFFFL);
	
	public static final Type POINTER          = new TypePointer ("POINTER",      TypePointer.PointerSize.SHORT);
	public static final Type LONG_POINTER     = new TypePointer ("LONG POINTER", TypePointer.PointerSize.LONG);

	
	public final String name;
	
	protected Type(String name) {
		this.name = name;
		
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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Type) {
			Type that = (Type)o;
			return compareTo(that) == 0;
		} else {
			return false;
		}
	}

	// fix for bitSize
	protected boolean needsFix;
	abstract public void fix();

	// bitSize and wordSIze
	private static final int NO_VALUE = -1;
	protected int bitSize = NO_VALUE;
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
		return (bitSize() + WORD_BITS - 1) / WORD_BITS;
	}
	
	abstract public String toMesaType();
	
	abstract public boolean container(); // if type hold other type object, it is container
	
	
	//
	// Convenience method
	//
	public Type getRealType() {
		if (needsFix) throw new UnexpectedException("Unexpected");
		if (this instanceof TypeReference) {
			return toTypeReference().realType;
		} else {
			return this;
		}
	}
	
	public boolean simpleType() {
		if (this.equals(Type.BOOLEAN))          return true;
		if (this.equals(Type.INTEGER))          return true;
		if (this.equals(Type.CARDINAL))         return true;
		if (this.equals(Type.UNSPECIFIED))      return true;
		if (this.equals(Type.LONG_CARDINAL))    return true;
		if (this.equals(Type.LONG_UNSPECIFIED)) return true;
		
		if (this instanceof TypePointer) {
			TypePointer typePointer = this.toTypePointer();
			if (typePointer.rawPointer()) return true;
		}
		if (this instanceof TypeRecord) {
			TypeRecord typeRecord = this.toTypeRecord();
			if (typeRecord.isBitField16()) return true;
			if (typeRecord.isBitField32()) return true;
		}
		
		return false;
	}
	
	public TypeArray toTypeArray() {
		if (this instanceof TypeArray) {
			return (TypeArray)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeBoolean toTypeBoolean() {
		if (this instanceof TypeBoolean) {
			return (TypeBoolean)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeEnum toTypeEnum() {
		if (this instanceof TypeEnum) {
			return (TypeEnum)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypePointer toTypePointer() {
		if (this instanceof TypePointer) {
			return (TypePointer)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeRecord toTypeRecord() {
		if (this instanceof TypeRecord) {
			return (TypeRecord)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeReference toTypeReference() {
		if (this instanceof TypeReference) {
			return (TypeReference)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeSubrange toTypeSubrange() {
		if (this instanceof TypeSubrange) {
			return (TypeSubrange)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
}

package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constants.WORD_BITS;

import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public abstract class Type implements Comparable<Type> {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

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
		logger.error("  value dec %s", Long.toString(value));
		logger.error("  value hex %s", Long.toHexString(value));
		logger.error("  value bin %s", Long.toBinaryString(value));
		throw new UnexpectedException("Unexpected");
	}
	
	public static Map<String, Type> map = new TreeMap<>();
	//                name
	private static void add(Type type) {
		String name = type.name;
		// add to map
		if (map.containsKey(name)) {
			logger.error("duplicate name");
			logger.error("  old  %s", map.get(name));
			logger.error("  new  %s", type);
			throw new UnexpectedException("duplicate name");
		} else {
			map.put(name, type);
		}
	}
	public static void clearMap() {
		map.clear();
		// add predefined type
		add(Type.BOOLEAN);
		add(Type.INTEGER);
		add(Type.CARDINAL);
		add(Type.UNSPECIFIED);
		add(Type.LONG_CARDINAL);
		add(Type.LONG_UNSPECIFIED);
		add(Type.POINTER);
		add(Type.LONG_POINTER);
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

	public static Type findType(String name) {
		return map.containsKey(name) ? map.get(name) : null;
	}
	public static void dumpReference(Type type) {
		logger.info("dumpReference START");
		for(int i = 0; i < 99; i++) {
			if (type == null) break;
			logger.info("%d %s", i, type.toMesaDecl());
			if (type instanceof TypeReference) {
				type = findType(type.toTypeReference().typeString);
				continue;
			}
			break;
		}
		logger.info("dumpReference STOP");
	}
	
	//
	// Define predefined type
	//
	public static final Type BOOLEAN          = new TypeBoolean (TypeBoolean.NAME);
	public static final Type INTEGER          = new TypeSubrange(TypeSubrange.NAME_INTEGER, Short.MIN_VALUE, Short.MAX_VALUE);
	
	public static final Type CARDINAL         = new TypeSubrange(TypeSubrange.NAME_CARDINAL,         0, 0xFFFF);
	public static final Type UNSPECIFIED      = new TypeSubrange(TypeSubrange.NAME_UNSPECIFIED,      0, 0xFFFF);
	
	public static final Type LONG_CARDINAL    = new TypeSubrange(TypeSubrange.NAME_LONG_CARDINAL,    0, 0xFFFF_FFFFL);
	public static final Type LONG_UNSPECIFIED = new TypeSubrange(TypeSubrange.NAME_LONG_UNSPECIFIED, 0, 0xFFFF_FFFFL);
	
	public static final Type POINTER          = new TypePointerShort(TypePointerShort.NAME);
	public static final Type LONG_POINTER     = new TypePointerLong (TypePointerLong.NAME);
	
	public boolean isPredefiled() {
		if (this.equals(BOOLEAN))          return true;
		if (this.equals(INTEGER))          return true;
		if (this.equals(CARDINAL))         return true;
		if (this.equals(UNSPECIFIED))      return true;
		if (this.equals(LONG_CARDINAL))    return true;
		if (this.equals(LONG_UNSPECIFIED)) return true;
		if (this.equals(POINTER))          return true;
		if (this.equals(LONG_POINTER))     return true;
		return false;
	}
	
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
			logger.error("name  %s", name);
			logger.error("type  %s", this);
			throw new UnexpectedException("Unexpected");
		}
		return bitSize;
	}
	public int wordSize() {
		return (bitSize() + WORD_BITS - 1) / WORD_BITS;
	}
	
	abstract public String toMesaType();	
	
	public String toMesaDecl() {
		if (isPredefiled()) {
			return name;
		} else {
			return String.format("%s: TYPE = %s;", name, toMesaType());
		}
	}
	
	//
	// Convenience method
	//
	public Type realType() {
		if (needsFix) throw new UnexpectedException("Unexpected");
		if (this instanceof TypeReference) {
			return toTypeReference().realType.realType(); // return realType() of realType
		} else {
			return this;
		}
	}
	
	public boolean rawPointer() {
		if (this instanceof TypePointerLong) {
			return toTypePointerLong().pointerTarget == null;
		} else if (this instanceof TypePointerShort) {
			return toTypePointerShort().pointerTarget == null;
		} else {
			return false;
		}
	}
	public boolean openSubrange() {
		if (this instanceof TypeSubrange) {
			TypeSubrange typeSubrange = toTypeSubrange();
			return (typeSubrange.minString.equals(typeSubrange.maxString) &&
				typeSubrange.closeChar.equals(TypeSubrange.CLOSE_CHAR_EXCLUSIVE));
		} else {
			return false;
		}
	}
	public boolean needsRangeCheck() {
		if (this instanceof TypeSubrange) {
			return 1 <= bitSize() && bitSize() <= 31;
		} else if (this instanceof TypeEnum) {
			return true;
		} else {
			return false;
		}
	}
	
	// field access
	public Type pointerTarget() {
		if (this instanceof TypePointerShort) {
			return toTypePointerShort().pointerTarget;
		}
		if (this instanceof TypePointerLong) {
			return toTypePointerLong().pointerTarget;
		}
		throw new UnexpectedException("Unexpected");	
	}
	public Type arrayElement() {
		if (this instanceof TypeArrayRef) {
			return toTypeArrayRef().arrayElement;
		}
		if (this instanceof TypeArraySub) {
			return toTypeArraySub().arrayElement;
		}
		throw new UnexpectedException("Unexpected");	
	}

	// type cast
	public TypeArrayRef toTypeArrayRef() {
		if (this instanceof TypeArrayRef) {
			return (TypeArrayRef)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeArraySub toTypeArraySub() {
		if (this instanceof TypeArraySub) {
			return (TypeArraySub)this;
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
	public TypePointerShort toTypePointerShort() {
		if (this instanceof TypePointerShort) {
			return (TypePointerShort)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypePointerLong toTypePointerLong() {
		if (this instanceof TypePointerLong) {
			return (TypePointerLong)this;
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
	public TypeBitField16 toTypeBitField16() {
		if (this instanceof TypeBitField16) {
			return (TypeBitField16)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeBitField32 toTypeBitField32() {
		if (this instanceof TypeBitField32) {
			return (TypeBitField32)this;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	public TypeMultiWord toTypeMultiWord() {
		if (this instanceof TypeMultiWord) {
			return (TypeMultiWord)this;
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

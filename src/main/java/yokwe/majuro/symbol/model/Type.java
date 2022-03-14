package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constants.WORD_BITS;

import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public abstract class Type implements Comparable<Type> {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	public static Type findRealType(String module, String name) {
		String key = QName.getQName(module, name);
		if (map.containsKey(key)) {
			Type realType = map.get(key);
			while(realType instanceof TypeReference) {
				TypeReference typeReference = (TypeReference)realType;
				key = QName.getQName(typeReference, typeReference.typeString);
				if (map.containsKey(key)) {
					realType = map.get(key);
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
	//                name.qName
	private static void add(Type type) {
		String key = type.qName.qName;
		// add to map
		if (map.containsKey(key)) {
			logger.error("duplicate key");
			logger.error("  key  %s", key);
			logger.error("  old  %s", map.get(key));
			logger.error("  new  %s", type);
			throw new UnexpectedException("duplicate key");
		} else {
			map.put(key, type);
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
	public static final Type BOOLEAN          = new TypeBoolean (TypeBoolean.QNAME);
	public static final Type INTEGER          = new TypeSubrange(TypeSubrange.QNAME_INTEGER, Short.MIN_VALUE, Short.MAX_VALUE);
	
	public static final Type CARDINAL         = new TypeSubrange(TypeSubrange.QNAME_CARDINAL,         0, 0xFFFF);
	public static final Type UNSPECIFIED      = new TypeSubrange(TypeSubrange.QNAME_UNSPECIFIED,      0, 0xFFFF);
	
	public static final Type LONG_CARDINAL    = new TypeSubrange(TypeSubrange.QNAME_LONG_CARDINAL,    0, 0xFFFF_FFFFL);
	public static final Type LONG_UNSPECIFIED = new TypeSubrange(TypeSubrange.QNAME_LONG_UNSPECIFIED, 0, 0xFFFF_FFFFL);
	
	public static final Type POINTER          = new TypePointerShort(TypePointerShort.QNAME);
	public static final Type LONG_POINTER     = new TypePointerLong (TypePointerLong.QNAME);
	
	public boolean isPredefiled() {
		return isPredefined(qName.qName);
	}
	public static boolean isPredefined(String name) {
		if (name.equals(TypeBoolean.NAME))                   return true;
		if (name.equals(TypeSubrange.NAME_INTEGER))          return true;
		if (name.equals(TypeSubrange.NAME_CARDINAL))         return true;
		if (name.equals(TypeSubrange.NAME_UNSPECIFIED))      return true;
		if (name.equals(TypeSubrange.NAME_LONG_CARDINAL))    return true;
		if (name.equals(TypeSubrange.NAME_LONG_UNSPECIFIED)) return true;
		if (name.equals(TypePointerShort.NAME))              return true;
		if (name.equals(TypePointerLong.NAME))               return true;
		return false;
	}
	
	public final QName qName;
	
	protected Type(QName qName) {
		this.qName    = qName;
		this.needsFix = true;
		
		add(this);
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	@Override
	public int compareTo(Type that) {
		return this.qName.compareTo(that.qName);
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
			logger.error("name  %s", qName);
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
			return qName.qName;
		} else {
			return String.format("%s: TYPE = %s;", qName.name, toMesaType());
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
	public TypeBitField toTypeBitField() {
		if (this instanceof TypeBitField) {
			return (TypeBitField)this;
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

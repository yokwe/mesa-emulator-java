package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constants.WORD_BITS;

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

	public static Type findType(String name) {
		return map.containsKey(name) ? map.get(name) : null;
	}
	public static void dumpReference(Type type) {
		logger.info("dumpReference START");
		for(int i = 0; i < 99; i++) {
			if (type == null) break;
			logger.info("{} {}", i, type.toMesaDecl());
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
	
	public String toMesaDecl() {
		return String.format("%s: TYPE = %s;", name, toMesaType());
	}
	
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
	
	// check bitSize for empty
	public boolean empty() {
		return getRealType().bitSize() == 0;
	}
	
	public boolean bitField16() {
		if (this instanceof TypeRecord) {
			TypeRecord typeRecord = toTypeRecord();
			if (typeRecord.align == TypeRecord.Align.BIT_16) {
				if (typeRecord.bitSize() <= 16) {
					for(var e: typeRecord.fieldList) {
						// if record has empty field return false
						if (e.type.empty()) return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	public boolean bitField32() {
		if (this instanceof TypeRecord) {
			TypeRecord typeRecord = toTypeRecord();
			return typeRecord.align == TypeRecord.Align.BIT_32 && typeRecord.bitSize == 32;
		}
		return false;
	}
	public boolean rawPointer() {
		if (this instanceof TypePointer) {
			TypePointer typePointer = toTypePointer();
			return typePointer.targetType == null;
		} else {
			return false;
		}
	}
	public boolean shortPointer() {
		if (this instanceof TypePointer) {
			TypePointer typePointer = toTypePointer();
			return typePointer.pointerSize == TypePointer.PointerSize.SHORT;
		} else {
			return false;
		}
	}
	public boolean longPointer() {
		if (this instanceof TypePointer) {
			TypePointer typePointer = toTypePointer();
			return typePointer.pointerSize == TypePointer.PointerSize.LONG;
		} else {
			return false;
		}
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

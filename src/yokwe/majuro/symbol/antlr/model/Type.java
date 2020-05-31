package yokwe.majuro.symbol.antlr.model;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public abstract class Type {
	private static final Logger logger = LoggerFactory.getLogger(Type.class);

	public enum Kind {
		// predefined
		BOOL,
		// subrange of CARDINAL, INTEGER
		SUBRANGE,
		// constructed
		ARRAY, ENUM, RECORD,
		// reference
		REFERENCE,
	}
	
	public final String name;
	public final Kind   kind;
	public final int    originalSize;
	
	public boolean needsFix;
	public int     size;
	
	public static final int UNKNOWN_SIZE = -1;
	
	protected Type(String name, Kind kind, int size) {
		this.name         = name;
		this.kind         = kind;
		this.originalSize = size;
		
		if (0 <= size) {
			this.needsFix = false;
			this.size     = size;
		} else {
			this.needsFix = true;
			this.size     = UNKNOWN_SIZE;
		}
		
		register(this);
	}
	protected Type(String name, Kind kind) {
		this(name, kind, UNKNOWN_SIZE);
	}
	
	protected abstract void fix();
	
	public static Map<String, Type> map = new TreeMap<>();
	
	protected static void register(Type type) {
		String name = type.name;
		
		if (map.containsKey(name)) {
			Type old = map.get(name);
			
			logger.error("Duplicate type name");
			logger.error("  new {} {}", name, type);
			logger.error("  old {} {}", old.name,  old);
			throw new UnexpectedException("Duplicate type name");
		} else {
			map.put(name, type);
		}
	}
	
	public static void stats() {
		int needsFix = 0;
		Map<Type.Kind, Integer> kindMap = new TreeMap<>();
		for(Type.Kind e: Type.Kind.values()) {
			kindMap.put(e, 0);
		}
		
		for(Type type: map.values()) {
			if (type.needsFix) needsFix++;
			kindMap.put(type.kind, kindMap.get(type.kind) + 1);
		}
		
		logger.info("stats");
		logger.info("  {}", String.format("%-9s  %3d", "all", map.size()));
		logger.info("  {}", String.format("%-9s  %3d", "needsFix", needsFix));
		logger.info("  ==");
		for(Type.Kind e: Type.Kind.values()) {
			logger.info("  {}", String.format("%-9s  %3d", e, kindMap.get(e)));
		}
		
		logger.info("  ==");
		for(Type type: map.values()) {
			if (type.needsFix) {
				logger.info("needsFix {}", type);
			}
		}
	}
	
	public boolean isReference() {
		return kind == Kind.REFERENCE;
	}
	
	public static final long CARDINAL_MIN = 0;
	public static final long CARDINAL_MAX = (1L << 16) - 1;
	
	public static final long LONG_CARDINAL_MIN = 0;
	public static final long LONG_CARDINAL_MAX = (1L << 32) - 1;

	public static final long INTEGER_MIN = Integer.MIN_VALUE;
	public static final long INTEGER_MAX = Integer.MIN_VALUE;

	public static final long LONG_INTEGER_MIN = Long.MIN_VALUE;
	public static final long LONG_INTEGER_MAX = Long.MIN_VALUE;

	// Define predefined class
	public static final Type BOOL             = new TypeBool();
	public static final Type CARDINAL         = new TypeSubrangeRange("CARDINAL",         1, "CARDINAL",         CARDINAL_MIN,      CARDINAL_MAX);
	public static final Type LONG_CARDINAL    = new TypeSubrangeRange("LONG_CARDINAL",    2, "LONG_CARDINAL",    LONG_CARDINAL_MIN, LONG_CARDINAL_MAX);
	public static final Type INTEGER          = new TypeSubrangeRange("INTEGER",          1, "INTEGER",          INTEGER_MIN,       INTEGER_MAX);
	public static final Type LONG_INTEGER     = new TypeSubrangeRange("LONG_INTEGER",     2, "LONG_INTEGER",     LONG_INTEGER_MIN,  LONG_INTEGER_MAX);
	public static final Type UNSPECIFIED      = new TypeSubrangeRange("UNSPECIFIED",      1, "UNSPECIFIED",      CARDINAL_MIN,      CARDINAL_MAX);
	public static final Type LONG_UNSPECIFIED = new TypeSubrangeRange("LONG_UNSPECIFIED", 2, "LONG_UNSPECIFIED", LONG_CARDINAL_MIN, LONG_CARDINAL_MAX);

}

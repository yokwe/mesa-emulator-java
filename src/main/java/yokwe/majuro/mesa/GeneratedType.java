package yokwe.majuro.mesa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import yokwe.majuro.UnexpectedException;

public final class GeneratedType {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GeneratedType.class);
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface BitField16 {
		int offset()   default 0;
		int startPos() default 0;
		int stopPos()  default 15;
	}
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface BitField32 {
		int offset()   default 0;
		int startPos() default 0;
		int stopPos()  default 31;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface Record {
		int elementSize() default 16;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Record32 {
		int elementSize() default 32;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Getter {}
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Setter {}
	
	
	//
	// Subrange2
	//
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface Subrange2 {
		long minValue() default 0;
		long maxValue() default 0;
	}

	@Subrange2(minValue = 0, maxValue = 0x0001) class BIT {}
	@Subrange2(minValue = 0, maxValue = 0x000F) class NIBBLE {}
	@Subrange2(minValue = 0, maxValue = 0x00FF) class BYTE {}
	@Subrange2(minValue = 0, maxValue = 0xFFFF) class CARD16 {}
	@Subrange2(minValue = 0, maxValue = 0xFFFF_FFFF) class CARD32 {}
	
	
	//NibblePair: TYPE = RECORD[
	//    left  (0: 0..3): NIBBLE,
	//    right (0: 4..7): NIBBLE];
	public static final class NibblePair {
		public static final int SIZE_BITS = 8;
		
		public static final int LEFT_START = 0;
		public static final int LEFT_STOP  = 3;
		
		public static final int RIGHT_START = 4;
		public static final int RIGHT_STOP  = 7;
		
		private static final int LEFT_BITS = LEFT_STOP - LEFT_START + 1;
		private static final int LEFT_PAT = (1 << LEFT_BITS) - 1;
		private static final int LEFT_SHIFT = SIZE_BITS - LEFT_STOP - 1;
		private static final int LEFT_MASK = LEFT_PAT << LEFT_SHIFT;
		
		private static final int RIGHT_BITS = RIGHT_STOP - RIGHT_START + 1;
		private static final int RIGHT_PAT = (1 << RIGHT_BITS) - 1;
		private static final int RIGHT_SHIFT = SIZE_BITS - RIGHT_STOP - 1;
		private static final int RIGHT_MASK = RIGHT_PAT << RIGHT_SHIFT;
		
		private int rawValue;
		
		public NibblePair(int newValue) {
			rawValue = newValue;
		}
		
		public int left;
		
		// left
		// static
		public static int leftValue(int value) {
			return (value & LEFT_MASK) >>> LEFT_SHIFT; 
		}
		public static int leftValue(int value, int newValue) {
			return (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
		}
		// instance
		public int left() {
			return (rawValue & LEFT_MASK) >>> LEFT_SHIFT; 
		}
		public void left(int newValue) {
			rawValue = (rawValue & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
		}
		
		// right
		// static
		public static int rightValue(int value) {
			return (value & RIGHT_MASK) >>> RIGHT_SHIFT; 
		}
		public static int rightValue(int value, int newValue) {
			return (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
		}
		// instance
		public int rightValues(int newValue) {
			return (newValue & RIGHT_MASK) >>> RIGHT_SHIFT; 
		}
		public int right() {
			return (rawValue & RIGHT_MASK) >>> RIGHT_SHIFT; 
		}
		public void right(int newValue) {
			rawValue = (rawValue & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
		}
		
	}

	@Record
	public static class NibblePair2 {
		@BitField16(startPos = 0, stopPos = 3) public int left;
		@BitField16(startPos = 4, stopPos = 7) public int right;
	}
	@Record
	public static final class NibblePair3 {
		@BitField16(startPos =  0, stopPos =  3) public NIBBLE left;
		@BitField16(startPos =  4, stopPos =  7) public NIBBLE right;
		
		@Getter @Setter
		private int value;
		
		public static final int SIZE_BIT = 8;
		
		public NibblePair3(int newValue) {
			value = newValue;
		}
		public NibblePair3() {
			this(0);
		}
		public int get() {
			return value;
		}
		public void set(int newValue) {
			value = newValue;
		}
		
		private static final int LEFT_MASK = 0xF0;
		private static final int LEFT_SHIFT = 4;
		private static final int RIGHT_MASK = 0x0F;
		private static final int RIGHT_SHIFT = 0;
		
		
		public int left() {
			return (value & LEFT_MASK) >>> LEFT_SHIFT; 
		}
		public void left(int newValue) {
			value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
		}
		public int right() {
			return (value & RIGHT_MASK) >>> RIGHT_SHIFT; 
		}
		public void right(int newValue) {
			value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
		}
		
		
	}
	

	
	public static final class Subrange {
		public static final Subrange BIT      = new Subrange(0, 0x0001);
		public static final Subrange NIBBLE   = new Subrange(0, 0x000F);
		public static final Subrange BYTE     = new Subrange(0, 0x00FF);
		public static final Subrange CARDINAL = new Subrange(0, 0xFFFF);
		
		public final long minValue;
		public final long maxValue;
		
		public Subrange(long minValue, long maxValue) {
			this.minValue = minValue;
			this.maxValue = maxValue;
		}
		
		public void check(long value) {
			if (Debug.ENABLE_SUBRANGE_CHECK) {
				if (minValue <= value && value <= maxValue) {
					//
				} else {
					logger.error("Subrange check error");
					logger.error("  min   {}", minValue);
					logger.error("  max   {}", maxValue);
					logger.error("  value {}", value);
					throw new UnexpectedException("Subrange check error");
				}
			}
		}
		
		@Override
		public String toString() {
			return String.format("[%d .. %d]", minValue, maxValue);
		}
	}

	// BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
	public static final class BLOCK {
		public static final int BIT_SIZE = 0;
		public static final int ELEMENT_SIZE = 1;
		
		public static final Subrange subrange = Subrange.CARDINAL;
		
		public static int offset(int index) {
			subrange.check(index);
			return index * ELEMENT_SIZE;
		}
		
		public static char read(int va, int index) {
			return Mesa.read16(va + offset(index));
		}
		public static void write(int va, int index, char newValue) {
			Mesa.write16(va + offset(index), newValue);
		}
	}



}

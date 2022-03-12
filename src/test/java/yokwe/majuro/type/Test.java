package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;
import yokwe.majuro.type.PrincOps.CARDINAL;
import yokwe.majuro.type.PrincOps.LONG_POINTER;
import yokwe.majuro.type.PrincOps.POINTER;
import yokwe.majuro.type.PrincOps.UNSPECIFIED;

public final class Test {
    
    //
    // Sub: TYPE = [0..4);
    //
    public static final class Sub extends MemoryData16 {
        public static final String NAME = "Sub";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 2;
                                              
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 3;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final Sub value(@Mesa.CARD16 int value) {
            return new Sub(value);
        }
        public static final Sub value() {
            return new Sub(0);
        }
        public static final Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Sub(base, access);
        }
        public static final Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Sub(Memory.lengthenMDS(base), access);
        }
        
        private Sub(@Mesa.CARD16 int value) {
            super(value);
        }
        private Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // BIT4: TYPE = [0..16);
    //
    public static final class BIT4 extends MemoryData16 {
        public static final String NAME = "BIT4";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  =  4;
                                               
        public static final int MIN_VALUE =  0;
        public static final int MAX_VALUE = 15;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final BIT4 value(@Mesa.CARD16 int value) {
            return new BIT4(value);
        }
        public static final BIT4 value() {
            return new BIT4(0);
        }
        public static final BIT4 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BIT4(base, access);
        }
        public static final BIT4 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BIT4(Memory.lengthenMDS(base), access);
        }
        
        private BIT4(@Mesa.CARD16 int value) {
            super(value);
        }
        private BIT4(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // BIT8: TYPE = [0..256);
    //
    public static final class BIT8 extends MemoryData16 {
        public static final String NAME = "BIT8";
        
        public static final int WORD_SIZE =    1;
        public static final int BIT_SIZE  =    8;
                                                 
        public static final int MIN_VALUE =    0;
        public static final int MAX_VALUE = 0xFF;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final BIT8 value(@Mesa.CARD16 int value) {
            return new BIT8(value);
        }
        public static final BIT8 value() {
            return new BIT8(0);
        }
        public static final BIT8 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BIT8(base, access);
        }
        public static final BIT8 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BIT8(Memory.lengthenMDS(base), access);
        }
        
        private BIT8(@Mesa.CARD16 int value) {
            super(value);
        }
        private BIT8(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // Enum: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
    //
    public static final class Enum extends MemoryData16 {
        public static final String NAME = "Enum";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 2;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int FRAME         = 0;
        public static final @Mesa.ENUM int OLD_PROCEDURE = 1;
        public static final @Mesa.ENUM int INDIRECT      = 2;
        public static final @Mesa.ENUM int NEW_PROCEDURE = 3;
        
        private static final int[] values = {
            FRAME, OLD_PROCEDURE, INDIRECT, NEW_PROCEDURE
        };
        private static final String[] names = {
            "FRAME", "OLD_PROCEDURE", "INDIRECT", "NEW_PROCEDURE"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final Enum value(@Mesa.CARD16 int value) {
            return new Enum(value);
        }
        public static final Enum value() {
            return new Enum(0);
        }
        public static final Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Enum(base, access);
        }
        public static final Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Enum(Memory.lengthenMDS(base), access);
        }
        
        private Enum(@Mesa.CARD16 int value) {
            super(value);
        }
        private Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // BitField8: TYPE = RECORD[left (0:0..3): BIT4, right (0:4..7): BIT4];
    //
    public static final class BitField8 extends MemoryData16 {
        public static final String NAME = "BitField8";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 8;
        
        // Constructor
        public static final BitField8 value(@Mesa.CARD16 int value) {
            return new BitField8(value);
        }
        public static final BitField8 value() {
            return new BitField8(0);
        }
        public static final BitField8 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BitField8(base, access);
        }
        public static final BitField8 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BitField8(Memory.lengthenMDS(base), access);
        }
        
        private BitField8(@Mesa.CARD16 int value) {
            super(value);
        }
        private BitField8(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // left  (0:0..3): BIT4
        // right (0:4..7): BIT4
        
        private static final int LEFT_MASK   = 0b1111_0000;
        private static final int LEFT_SHIFT  =           4;
        private static final int RIGHT_MASK  = 0b0000_1111;
        private static final int RIGHT_SHIFT =           0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is BIT4
        public final @Mesa.CARD8 int left() {
            return (value & LEFT_MASK) >>> LEFT_SHIFT;
        }
        public final BitField8 left(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BIT4.checkValue(newValue);
            value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is BIT4
        public final @Mesa.CARD8 int right() {
            return (value & RIGHT_MASK) >>> RIGHT_SHIFT;
        }
        public final BitField8 right(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BIT4.checkValue(newValue);
            value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
            return this;
        }
        
    }
    
    //
    // BitField16: TYPE = RECORD[left (0:0..7): BIT8, right (0:8..15): BIT8];
    //
    public static final class BitField16 extends MemoryData16 {
        public static final String NAME = "BitField16";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final BitField16 value(@Mesa.CARD16 int value) {
            return new BitField16(value);
        }
        public static final BitField16 value() {
            return new BitField16(0);
        }
        public static final BitField16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BitField16(base, access);
        }
        public static final BitField16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BitField16(Memory.lengthenMDS(base), access);
        }
        
        private BitField16(@Mesa.CARD16 int value) {
            super(value);
        }
        private BitField16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // left  (0:0..7):  BIT8
        // right (0:8..15): BIT8
        
        private static final int LEFT_MASK   = 0b1111_1111_0000_0000;
        private static final int LEFT_SHIFT  =                     8;
        private static final int RIGHT_MASK  = 0b0000_0000_1111_1111;
        private static final int RIGHT_SHIFT =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is BIT8
        public final @Mesa.CARD8 int left() {
            return (value & LEFT_MASK) >>> LEFT_SHIFT;
        }
        public final BitField16 left(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BIT8.checkValue(newValue);
            value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is BIT8
        public final @Mesa.CARD8 int right() {
            return (value & RIGHT_MASK) >>> RIGHT_SHIFT;
        }
        public final BitField16 right(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BIT8.checkValue(newValue);
            value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
            return this;
        }
        
    }
    
    //
    // BitField32: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): Enum, fill (0:16..31): UNSPECIFIED];
    //
    public static final class BitField32 extends MemoryData32 {
        public static final String NAME = "BitField32";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final BitField32 value(@Mesa.CARD32 int value) {
            return new BitField32(value);
        }
        public static final BitField32 value() {
            return new BitField32(0);
        }
        public static final BitField32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BitField32(base, access);
        }
        public static final BitField32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BitField32(Memory.lengthenMDS(base), access);
        }
        
        private BitField32(@Mesa.CARD32 int value) {
            super(value);
        }
        private BitField32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // data (0:0..13):  UNSPECIFIED
        // tag  (0:14..15): Enum
        // fill (0:16..31): UNSPECIFIED
        
        private static final int DATA_MASK  = 0b1111_1111_1111_1100_0000_0000_0000_0000;
        private static final int DATA_SHIFT =                                        18;
        private static final int TAG_MASK   = 0b0000_0000_0000_0011_0000_0000_0000_0000;
        private static final int TAG_SHIFT  =                                        16;
        private static final int FILL_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
        private static final int FILL_SHIFT =                                         0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int data() {
            return (value & DATA_MASK) >>> DATA_SHIFT;
        }
        public final BitField32 data(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~DATA_MASK) | ((newValue << DATA_SHIFT) & DATA_MASK);
            return this;
        }
        
        // @Mesa.ENUM is Enum
        public final @Mesa.ENUM int tag() {
            return Types.toCARD16((value & TAG_MASK) >>> TAG_SHIFT);
        }
        public final BitField32 tag(@Mesa.ENUM int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Enum.checkValue(newValue);
            value = Types.toCARD16((value & ~TAG_MASK) | ((newValue << TAG_SHIFT) & TAG_MASK));
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int fill() {
            return (value & FILL_MASK) >>> FILL_SHIFT;
        }
        public final BitField32 fill(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~FILL_MASK) | ((newValue << FILL_SHIFT) & FILL_MASK);
            return this;
        }
        
    }
    
    //
    // ArraySubOpen: TYPE = ARRAY [0..0) OF UNSPECIFIED;
    //
    public static final class ArraySubOpen extends MemoryBase {
        public static final String NAME = "ArraySubOpen";
        
        public static final int WORD_SIZE = 0;
        public static final int BIT_SIZE  = 0;
        
        // Constructor
        public static final ArraySubOpen longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArraySubOpen(base, access);
        }
        public static final ArraySubOpen pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArraySubOpen(Memory.lengthenMDS(base), access);
        }
        
        private ArraySubOpen(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final UNSPECIFIED get(int index) {
            int longPointer = base + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // ArraySubFixed: TYPE = ARRAY [0..4) OF UNSPECIFIED;
    //
    public static final class ArraySubFixed extends MemoryBase {
        public static final String NAME = "ArraySubFixed";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArraySubFixed longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArraySubFixed(base, access);
        }
        public static final ArraySubFixed pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArraySubFixed(Memory.lengthenMDS(base), access);
        }
        
        private ArraySubFixed(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        private static final class ArrayIndex {
            private static final ContextSubrange context = new ContextSubrange(NAME, 0, 3);
            private static final void checkValue(int value) {
                context.check(value);
            }
        }
        public final UNSPECIFIED get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) ArrayIndex.checkValue(index);
            int longPointer = base + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // ArrayRefEnum: TYPE = ARRAY Enum OF UNSPECIFIED;
    //
    public static final class ArrayRefEnum extends MemoryBase {
        public static final String NAME = "ArrayRefEnum";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefEnum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefEnum(base, access);
        }
        public static final ArrayRefEnum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefEnum(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefEnum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final UNSPECIFIED get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Enum.checkValue(index);
            int longPointer = base + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // ArrayRefSub: TYPE = ARRAY Sub OF UNSPECIFIED;
    //
    public static final class ArrayRefSub extends MemoryBase {
        public static final String NAME = "ArrayRefSub";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSub(base, access);
        }
        public static final ArrayRefSub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSub(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final UNSPECIFIED get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // ArrayRefSubSub: TYPE = ARRAY Sub OF Sub;
    //
    public static final class ArrayRefSubSub extends MemoryBase {
        public static final String NAME = "ArrayRefSubSub";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubSub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubSub(base, access);
        }
        public static final ArrayRefSubSub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubSub(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubSub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Sub get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + (Sub.WORD_SIZE * index);
            return Sub.longPointer(longPointer, access);
        }
    }
    
    //
    // ArrayRefSubEnum: TYPE = ARRAY Sub OF Enum;
    //
    public static final class ArrayRefSubEnum extends MemoryBase {
        public static final String NAME = "ArrayRefSubEnum";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubEnum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubEnum(base, access);
        }
        public static final ArrayRefSubEnum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubEnum(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubEnum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Enum get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + (Enum.WORD_SIZE * index);
            return Enum.longPointer(longPointer, access);
        }
    }
    
    //
    // ArrayRefSubBit16: TYPE = ARRAY Sub OF BitField16;
    //
    public static final class ArrayRefSubBit16 extends MemoryBase {
        public static final String NAME = "ArrayRefSubBit16";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubBit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubBit16(base, access);
        }
        public static final ArrayRefSubBit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubBit16(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubBit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final BitField16 get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + (BitField16.WORD_SIZE * index);
            return BitField16.longPointer(longPointer, access);
        }
    }
    
    //
    // ArrayRefSubBit32: TYPE = ARRAY Sub OF BitField32;
    //
    public static final class ArrayRefSubBit32 extends MemoryBase {
        public static final String NAME = "ArrayRefSubBit32";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ArrayRefSubBit32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubBit32(base, access);
        }
        public static final ArrayRefSubBit32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubBit32(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubBit32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final BitField32 get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + (BitField32.WORD_SIZE * index);
            return BitField32.longPointer(longPointer, access);
        }
    }
    
    //
    // ArrayRefSubPtr16: TYPE = ARRAY Sub OF POINTER;
    //
    public static final class ArrayRefSubPtr16 extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr16";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubPtr16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16(base, access);
        }
        public static final ArrayRefSubPtr16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final POINTER get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
            return POINTER.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read16(base + (POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr16 getValue(int index, @Mesa.SHORT_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr16Sub: TYPE = ARRAY Sub OF POINTER TO Sub;
    //
    public static final class ArrayRefSubPtr16Sub extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr16Sub";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubPtr16Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Sub(base, access);
        }
        public static final ArrayRefSubPtr16Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Sub(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr16Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Sub get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
            return Sub.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read16(base + (POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr16Sub getValue(int index, @Mesa.SHORT_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr16Enum: TYPE = ARRAY Sub OF POINTER TO Enum;
    //
    public static final class ArrayRefSubPtr16Enum extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr16Enum";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubPtr16Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Enum(base, access);
        }
        public static final ArrayRefSubPtr16Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Enum(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr16Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Enum get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
            return Enum.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read16(base + (POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr16Enum getValue(int index, @Mesa.SHORT_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr16Bit16: TYPE = ARRAY Sub OF POINTER TO BitField16;
    //
    public static final class ArrayRefSubPtr16Bit16 extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr16Bit16";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubPtr16Bit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Bit16(base, access);
        }
        public static final ArrayRefSubPtr16Bit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Bit16(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr16Bit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final BitField16 get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
            return BitField16.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read16(base + (POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr16Bit16 getValue(int index, @Mesa.SHORT_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr16Bit32: TYPE = ARRAY Sub OF POINTER TO BitField32;
    //
    public static final class ArrayRefSubPtr16Bit32 extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr16Bit32";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubPtr16Bit32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Bit32(base, access);
        }
        public static final ArrayRefSubPtr16Bit32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Bit32(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr16Bit32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final BitField32 get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
            return BitField32.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read16(base + (POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr16Bit32 getValue(int index, @Mesa.SHORT_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr16Rec: TYPE = ARRAY Sub OF POINTER TO Rec;
    //
    public static final class ArrayRefSubPtr16Rec extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr16Rec";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final ArrayRefSubPtr16Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Rec(base, access);
        }
        public static final ArrayRefSubPtr16Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr16Rec(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr16Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Rec get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
            return Rec.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read16(base + (POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr16Rec getValue(int index, @Mesa.SHORT_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr32: TYPE = ARRAY Sub OF LONG POINTER;
    //
    public static final class ArrayRefSubPtr32 extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr32";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ArrayRefSubPtr32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32(base, access);
        }
        public static final ArrayRefSubPtr32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final LONG_POINTER get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
            return LONG_POINTER.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr32 getValue(int index, @Mesa.LONG_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write32(base + (LONG_POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr32Sub: TYPE = ARRAY Sub OF LONG POINTER TO Sub;
    //
    public static final class ArrayRefSubPtr32Sub extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr32Sub";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ArrayRefSubPtr32Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Sub(base, access);
        }
        public static final ArrayRefSubPtr32Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Sub(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr32Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Sub get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
            return Sub.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr32Sub getValue(int index, @Mesa.LONG_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write32(base + (LONG_POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr32Enum: TYPE = ARRAY Sub OF LONG POINTER TO Enum;
    //
    public static final class ArrayRefSubPtr32Enum extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr32Enum";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ArrayRefSubPtr32Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Enum(base, access);
        }
        public static final ArrayRefSubPtr32Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Enum(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr32Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Enum get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
            return Enum.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr32Enum getValue(int index, @Mesa.LONG_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write32(base + (LONG_POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr32Bit16: TYPE = ARRAY Sub OF LONG POINTER TO BitField16;
    //
    public static final class ArrayRefSubPtr32Bit16 extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr32Bit16";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ArrayRefSubPtr32Bit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Bit16(base, access);
        }
        public static final ArrayRefSubPtr32Bit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Bit16(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr32Bit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final BitField16 get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
            return BitField16.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr32Bit16 getValue(int index, @Mesa.LONG_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write32(base + (LONG_POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr32Bit32: TYPE = ARRAY Sub OF LONG POINTER TO BitField32;
    //
    public static final class ArrayRefSubPtr32Bit32 extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr32Bit32";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ArrayRefSubPtr32Bit32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Bit32(base, access);
        }
        public static final ArrayRefSubPtr32Bit32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Bit32(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr32Bit32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final BitField32 get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
            return BitField32.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr32Bit32 getValue(int index, @Mesa.LONG_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write32(base + (LONG_POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // ArrayRefSubPtr32Rec: TYPE = ARRAY Sub OF LONG POINTER TO Rec;
    //
    public static final class ArrayRefSubPtr32Rec extends MemoryBase {
        public static final String NAME = "ArrayRefSubPtr32Rec";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ArrayRefSubPtr32Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Rec(base, access);
        }
        public static final ArrayRefSubPtr32Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ArrayRefSubPtr32Rec(Memory.lengthenMDS(base), access);
        }
        
        private ArrayRefSubPtr32Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final Rec get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
            return Rec.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            return Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        }
        public final ArrayRefSubPtr32Rec getValue(int index, @Mesa.LONG_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            Memory.write32(base + (LONG_POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    // IGNORE Ptr16Sub: TYPE = POINTER TO Sub;
    // IGNORE Ptr16Enum: TYPE = POINTER TO Enum;
    // IGNORE Ptr16Bit16: TYPE = POINTER TO BitField16;
    // IGNORE Ptr16Bit32: TYPE = POINTER TO BitField32;
    // IGNORE Ptr16Rec: TYPE = POINTER TO Rec;
    // IGNORE Ptr32Sub: TYPE = LONG POINTER TO Sub;
    // IGNORE Ptr32Enum: TYPE = LONG POINTER TO Enum;
    // IGNORE Ptr32Bit16: TYPE = LONG POINTER TO BitField16;
    // IGNORE Ptr32Bit32: TYPE = LONG POINTER TO BitField32;
    // IGNORE Ptr32Rec: TYPE = LONG POINTER TO Rec;
    
    //
    // Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): CARDINAL];
    //
    public static final class Rec extends MemoryBase {
        public static final String NAME = "Rec";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Rec(base, access);
        }
        public static final Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Rec(Memory.lengthenMDS(base), access);
        }
        
        private Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): CARDINAL
        private static final int OFFSET_CARD_1 = 1;
        public CARDINAL card1() {
            int longPointer = base + OFFSET_CARD_1;
            return CARDINAL.longPointer(longPointer, access);
        }
    }
    
    //
    // RecSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): Sub];
    //
    public static final class RecSub extends MemoryBase {
        public static final String NAME = "RecSub";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecSub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecSub(base, access);
        }
        public static final RecSub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecSub(Memory.lengthenMDS(base), access);
        }
        
        private RecSub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): Sub
        private static final int OFFSET_CARD_1 = 1;
        public Sub card1() {
            int longPointer = base + OFFSET_CARD_1;
            return Sub.longPointer(longPointer, access);
        }
    }
    
    //
    // RecEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): Enum];
    //
    public static final class RecEnum extends MemoryBase {
        public static final String NAME = "RecEnum";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecEnum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecEnum(base, access);
        }
        public static final RecEnum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecEnum(Memory.lengthenMDS(base), access);
        }
        
        private RecEnum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): Enum
        private static final int OFFSET_CARD_1 = 1;
        public Enum card1() {
            int longPointer = base + OFFSET_CARD_1;
            return Enum.longPointer(longPointer, access);
        }
    }
    
    //
    // RecBit16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): BitField16];
    //
    public static final class RecBit16 extends MemoryBase {
        public static final String NAME = "RecBit16";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecBit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecBit16(base, access);
        }
        public static final RecBit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecBit16(Memory.lengthenMDS(base), access);
        }
        
        private RecBit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): BitField16
        private static final int OFFSET_CARD_1 = 1;
        public BitField16 card1() {
            int longPointer = base + OFFSET_CARD_1;
            return BitField16.longPointer(longPointer, access);
        }
    }
    
    //
    // RecBit32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): BitField32];
    //
    public static final class RecBit32 extends MemoryBase {
        public static final String NAME = "RecBit32";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecBit32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecBit32(base, access);
        }
        public static final RecBit32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecBit32(Memory.lengthenMDS(base), access);
        }
        
        private RecBit32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): BitField32
        private static final int OFFSET_CARD_1 = 1;
        public BitField32 card1() {
            int longPointer = base + OFFSET_CARD_1;
            return BitField32.longPointer(longPointer, access);
        }
    }
    
    //
    // RecRec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): Rec];
    //
    public static final class RecRec extends MemoryBase {
        public static final String NAME = "RecRec";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecRec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecRec(base, access);
        }
        public static final RecRec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecRec(Memory.lengthenMDS(base), access);
        }
        
        private RecRec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): Rec
        private static final int OFFSET_CARD_1 = 1;
        public Rec card1() {
            int longPointer = base + OFFSET_CARD_1;
            return Rec.longPointer(longPointer, access);
        }
    }
    
    //
    // RecPtr16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER];
    //
    public static final class RecPtr16 extends MemoryBase {
        public static final String NAME = "RecPtr16";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecPtr16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr16(base, access);
        }
        public static final RecPtr16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr16(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): POINTER
        private static final int OFFSET_CARD_1 = 1;
        public POINTER card1() {
            int longPointer = base + OFFSET_CARD_1;
            return POINTER.longPointer(longPointer, access);
        }
    }
    
    //
    // RecPtr16Sub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO Sub];
    //
    public static final class RecPtr16Sub extends MemoryBase {
        public static final String NAME = "RecPtr16Sub";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecPtr16Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr16Sub(base, access);
        }
        public static final RecPtr16Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr16Sub(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr16Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): POINTER TO Sub
        private static final int OFFSET_CARD_1 = 1;
        public Sub card1() {
            int pointer = Memory.read16(base + OFFSET_CARD_1);
            return Sub.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int card1Value() {
            return Memory.read16(base + OFFSET_CARD_1);
        }
        public final RecPtr16Sub card1Value(@Mesa.SHORT_POINTER int newValue) {
            Memory.write16(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr16Enum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO Enum];
    //
    public static final class RecPtr16Enum extends MemoryBase {
        public static final String NAME = "RecPtr16Enum";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecPtr16Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr16Enum(base, access);
        }
        public static final RecPtr16Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr16Enum(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr16Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): POINTER TO Enum
        private static final int OFFSET_CARD_1 = 1;
        public Enum card1() {
            int pointer = Memory.read16(base + OFFSET_CARD_1);
            return Enum.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int card1Value() {
            return Memory.read16(base + OFFSET_CARD_1);
        }
        public final RecPtr16Enum card1Value(@Mesa.SHORT_POINTER int newValue) {
            Memory.write16(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr16Bit16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO BitField16];
    //
    public static final class RecPtr16Bit16 extends MemoryBase {
        public static final String NAME = "RecPtr16Bit16";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecPtr16Bit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr16Bit16(base, access);
        }
        public static final RecPtr16Bit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr16Bit16(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr16Bit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): POINTER TO BitField16
        private static final int OFFSET_CARD_1 = 1;
        public BitField16 card1() {
            int pointer = Memory.read16(base + OFFSET_CARD_1);
            return BitField16.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int card1Value() {
            return Memory.read16(base + OFFSET_CARD_1);
        }
        public final RecPtr16Bit16 card1Value(@Mesa.SHORT_POINTER int newValue) {
            Memory.write16(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr16Bit32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO BitField32];
    //
    public static final class RecPtr16Bit32 extends MemoryBase {
        public static final String NAME = "RecPtr16Bit32";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecPtr16Bit32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr16Bit32(base, access);
        }
        public static final RecPtr16Bit32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr16Bit32(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr16Bit32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): POINTER TO BitField32
        private static final int OFFSET_CARD_1 = 1;
        public BitField32 card1() {
            int pointer = Memory.read16(base + OFFSET_CARD_1);
            return BitField32.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int card1Value() {
            return Memory.read16(base + OFFSET_CARD_1);
        }
        public final RecPtr16Bit32 card1Value(@Mesa.SHORT_POINTER int newValue) {
            Memory.write16(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr16Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO Rec];
    //
    public static final class RecPtr16Rec extends MemoryBase {
        public static final String NAME = "RecPtr16Rec";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final RecPtr16Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr16Rec(base, access);
        }
        public static final RecPtr16Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr16Rec(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr16Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..15): POINTER TO Rec
        private static final int OFFSET_CARD_1 = 1;
        public Rec card1() {
            int pointer = Memory.read16(base + OFFSET_CARD_1);
            return Rec.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int card1Value() {
            return Memory.read16(base + OFFSET_CARD_1);
        }
        public final RecPtr16Rec card1Value(@Mesa.SHORT_POINTER int newValue) {
            Memory.write16(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER];
    //
    public static final class RecPtr32 extends MemoryBase {
        public static final String NAME = "RecPtr32";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecPtr32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr32(base, access);
        }
        public static final RecPtr32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr32(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): LONG POINTER
        private static final int OFFSET_CARD_1 = 1;
        public LONG_POINTER card1() {
            int longPointer = base + OFFSET_CARD_1;
            return LONG_POINTER.longPointer(longPointer, access);
        }
    }
    
    //
    // RecPtr32Sub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Sub];
    //
    public static final class RecPtr32Sub extends MemoryBase {
        public static final String NAME = "RecPtr32Sub";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecPtr32Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr32Sub(base, access);
        }
        public static final RecPtr32Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr32Sub(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr32Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): LONG POINTER TO Sub
        private static final int OFFSET_CARD_1 = 1;
        public Sub card1() {
            int longPointer = Memory.read32(base + OFFSET_CARD_1);
            return Sub.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int card1Value() {
            return Memory.read32(base + OFFSET_CARD_1);
        }
        public final RecPtr32Sub card1Value(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr32Enum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Enum];
    //
    public static final class RecPtr32Enum extends MemoryBase {
        public static final String NAME = "RecPtr32Enum";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecPtr32Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr32Enum(base, access);
        }
        public static final RecPtr32Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr32Enum(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr32Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): LONG POINTER TO Enum
        private static final int OFFSET_CARD_1 = 1;
        public Enum card1() {
            int longPointer = Memory.read32(base + OFFSET_CARD_1);
            return Enum.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int card1Value() {
            return Memory.read32(base + OFFSET_CARD_1);
        }
        public final RecPtr32Enum card1Value(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr32Bit16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO BitField16];
    //
    public static final class RecPtr32Bit16 extends MemoryBase {
        public static final String NAME = "RecPtr32Bit16";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecPtr32Bit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr32Bit16(base, access);
        }
        public static final RecPtr32Bit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr32Bit16(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr32Bit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): LONG POINTER TO BitField16
        private static final int OFFSET_CARD_1 = 1;
        public BitField16 card1() {
            int longPointer = Memory.read32(base + OFFSET_CARD_1);
            return BitField16.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int card1Value() {
            return Memory.read32(base + OFFSET_CARD_1);
        }
        public final RecPtr32Bit16 card1Value(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr32BIt32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO BitField32];
    //
    public static final class RecPtr32BIt32 extends MemoryBase {
        public static final String NAME = "RecPtr32BIt32";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecPtr32BIt32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr32BIt32(base, access);
        }
        public static final RecPtr32BIt32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr32BIt32(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr32BIt32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): LONG POINTER TO BitField32
        private static final int OFFSET_CARD_1 = 1;
        public BitField32 card1() {
            int longPointer = Memory.read32(base + OFFSET_CARD_1);
            return BitField32.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int card1Value() {
            return Memory.read32(base + OFFSET_CARD_1);
        }
        public final RecPtr32BIt32 card1Value(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecPtr32Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Rec];
    //
    public static final class RecPtr32Rec extends MemoryBase {
        public static final String NAME = "RecPtr32Rec";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final RecPtr32Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecPtr32Rec(base, access);
        }
        public static final RecPtr32Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecPtr32Rec(Memory.lengthenMDS(base), access);
        }
        
        private RecPtr32Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..31): LONG POINTER TO Rec
        private static final int OFFSET_CARD_1 = 1;
        public Rec card1() {
            int longPointer = Memory.read32(base + OFFSET_CARD_1);
            return Rec.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int card1Value() {
            return Memory.read32(base + OFFSET_CARD_1);
        }
        public final RecPtr32Rec card1Value(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_CARD_1, newValue);
            return this;
        }
    }
    
    //
    // RecArraySubOpen: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1): ARRAY [0..0) OF UNSPECIFIED];
    //
    public static final class RecArraySubOpen extends MemoryBase {
        public static final String NAME = "RecArraySubOpen";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final RecArraySubOpen longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArraySubOpen(base, access);
        }
        public static final RecArraySubOpen pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArraySubOpen(Memory.lengthenMDS(base), access);
        }
        
        private RecArraySubOpen(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1): ARRAY [0..0) OF UNSPECIFIED
        private static final int OFFSET_CARD_1 = 1;
        public final UNSPECIFIED card1(int index) {
            int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArraySubFixed: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY [0..4) OF UNSPECIFIED];
    //
    public static final class RecArraySubFixed extends MemoryBase {
        public static final String NAME = "RecArraySubFixed";
        
        public static final int WORD_SIZE =  5;
        public static final int BIT_SIZE  = 80;
        
        // Constructor
        public static final RecArraySubFixed longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArraySubFixed(base, access);
        }
        public static final RecArraySubFixed pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArraySubFixed(Memory.lengthenMDS(base), access);
        }
        
        private RecArraySubFixed(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..63): ARRAY [0..4) OF UNSPECIFIED
        private static final int OFFSET_CARD_1 = 1;
        private static final class Card1Index {
            private static final ContextSubrange context = new ContextSubrange("RecArraySubFixed", 0, 3);
            private static final void checkValue(int value) {
                context.check(value);
            }
        }
        public final UNSPECIFIED card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Card1Index.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Enum OF UNSPECIFIED];
    //
    public static final class RecArrayRefEnum extends MemoryBase {
        public static final String NAME = "RecArrayRefEnum";
        
        public static final int WORD_SIZE =  5;
        public static final int BIT_SIZE  = 80;
        
        // Constructor
        public static final RecArrayRefEnum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefEnum(base, access);
        }
        public static final RecArrayRefEnum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefEnum(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefEnum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..63): ARRAY Enum OF UNSPECIFIED
        private static final int OFFSET_CARD_1 = 1;
        public final UNSPECIFIED card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Enum.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF UNSPECIFIED];
    //
    public static final class RecArrayRefSub extends MemoryBase {
        public static final String NAME = "RecArrayRefSub";
        
        public static final int WORD_SIZE =  5;
        public static final int BIT_SIZE  = 80;
        
        // Constructor
        public static final RecArrayRefSub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSub(base, access);
        }
        public static final RecArrayRefSub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSub(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefSub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..63): ARRAY Sub OF UNSPECIFIED
        private static final int OFFSET_CARD_1 = 1;
        public final UNSPECIFIED card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefSubCARDINAL: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF CARDINAL];
    //
    public static final class RecArrayRefSubCARDINAL extends MemoryBase {
        public static final String NAME = "RecArrayRefSubCARDINAL";
        
        public static final int WORD_SIZE =  5;
        public static final int BIT_SIZE  = 80;
        
        // Constructor
        public static final RecArrayRefSubCARDINAL longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubCARDINAL(base, access);
        }
        public static final RecArrayRefSubCARDINAL pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubCARDINAL(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefSubCARDINAL(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..63): ARRAY Sub OF CARDINAL
        private static final int OFFSET_CARD_1 = 1;
        public final CARDINAL card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (CARDINAL.WORD_SIZE * index);
            return CARDINAL.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefSubSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF Sub];
    //
    public static final class RecArrayRefSubSub extends MemoryBase {
        public static final String NAME = "RecArrayRefSubSub";
        
        public static final int WORD_SIZE =  5;
        public static final int BIT_SIZE  = 80;
        
        // Constructor
        public static final RecArrayRefSubSub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubSub(base, access);
        }
        public static final RecArrayRefSubSub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubSub(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefSubSub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..63): ARRAY Sub OF Sub
        private static final int OFFSET_CARD_1 = 1;
        public final Sub card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (Sub.WORD_SIZE * index);
            return Sub.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefSubEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF Enum];
    //
    public static final class RecArrayRefSubEnum extends MemoryBase {
        public static final String NAME = "RecArrayRefSubEnum";
        
        public static final int WORD_SIZE =  5;
        public static final int BIT_SIZE  = 80;
        
        // Constructor
        public static final RecArrayRefSubEnum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubEnum(base, access);
        }
        public static final RecArrayRefSubEnum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubEnum(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefSubEnum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..63): ARRAY Sub OF Enum
        private static final int OFFSET_CARD_1 = 1;
        public final Enum card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (Enum.WORD_SIZE * index);
            return Enum.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefSubBit16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF BitField16];
    //
    public static final class RecArrayRefSubBit16 extends MemoryBase {
        public static final String NAME = "RecArrayRefSubBit16";
        
        public static final int WORD_SIZE =  5;
        public static final int BIT_SIZE  = 80;
        
        // Constructor
        public static final RecArrayRefSubBit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubBit16(base, access);
        }
        public static final RecArrayRefSubBit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubBit16(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefSubBit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..63): ARRAY Sub OF BitField16
        private static final int OFFSET_CARD_1 = 1;
        public final BitField16 card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (BitField16.WORD_SIZE * index);
            return BitField16.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefSubBit32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..127): ARRAY Sub OF BitField32];
    //
    public static final class RecArrayRefSubBit32 extends MemoryBase {
        public static final String NAME = "RecArrayRefSubBit32";
        
        public static final int WORD_SIZE =   9;
        public static final int BIT_SIZE  = 144;
        
        // Constructor
        public static final RecArrayRefSubBit32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubBit32(base, access);
        }
        public static final RecArrayRefSubBit32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubBit32(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefSubBit32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..127): ARRAY Sub OF BitField32
        private static final int OFFSET_CARD_1 = 1;
        public final BitField32 card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (BitField32.WORD_SIZE * index);
            return BitField32.longPointer(longPointer, access);
        }
    }
    
    //
    // RecArrayRefSubRec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..127): ARRAY Sub OF Rec];
    //
    public static final class RecArrayRefSubRec extends MemoryBase {
        public static final String NAME = "RecArrayRefSubRec";
        
        public static final int WORD_SIZE =   9;
        public static final int BIT_SIZE  = 144;
        
        // Constructor
        public static final RecArrayRefSubRec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubRec(base, access);
        }
        public static final RecArrayRefSubRec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new RecArrayRefSubRec(Memory.lengthenMDS(base), access);
        }
        
        private RecArrayRefSubRec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // card0 (0:0..15): CARDINAL
        private static final int OFFSET_CARD_0 = 0;
        public CARDINAL card0() {
            int longPointer = base + OFFSET_CARD_0;
            return CARDINAL.longPointer(longPointer, access);
        }
        // card1 (1:0..127): ARRAY Sub OF Rec
        private static final int OFFSET_CARD_1 = 1;
        public final Rec card1(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
            int longPointer = base + OFFSET_CARD_1 + (Rec.WORD_SIZE * index);
            return Rec.longPointer(longPointer, access);
        }
    }
}

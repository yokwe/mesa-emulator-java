package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

public final class PrincOps {
    
    //
    // BOOLEAN: TYPE = BOOLEAN;
    //
    public static final class BOOLEAN extends MemoryData16 {
        public static final String NAME = "BOOLEAN";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 1;
        
        // Constructor
        public static final BOOLEAN value(@Mesa.CARD16 int value) {
            return new BOOLEAN(value);
        }
        public static final BOOLEAN value() {
            return new BOOLEAN(0);
        }
        public static final BOOLEAN longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BOOLEAN(base, access);
        }
        public static final BOOLEAN pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BOOLEAN(Memory.lengthenMDS(base), access);
        }
        
        private BOOLEAN(@Mesa.CARD16 int value) {
            super(value);
        }
        private BOOLEAN(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // CARDINAL: TYPE = CARDINAL;
    //
    public static final class CARDINAL extends MemoryData16 {
        public static final String NAME = "CARDINAL";
        
        public static final int WORD_SIZE =      1;
        public static final int BIT_SIZE  =     16;
                                                   
        public static final int MIN_VALUE =      0;
        public static final int MAX_VALUE = 0xFFFF;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final CARDINAL value(@Mesa.CARD16 int value) {
            return new CARDINAL(value);
        }
        public static final CARDINAL value() {
            return new CARDINAL(0);
        }
        public static final CARDINAL longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new CARDINAL(base, access);
        }
        public static final CARDINAL pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new CARDINAL(Memory.lengthenMDS(base), access);
        }
        
        private CARDINAL(@Mesa.CARD16 int value) {
            super(value);
        }
        private CARDINAL(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // INTEGER: TYPE = INTEGER;
    //
    public static final class INTEGER extends MemoryData16 {
        public static final String NAME = "INTEGER";
        
        public static final int WORD_SIZE =               1;
        public static final int BIT_SIZE  =              16;
                                                            
        public static final int MIN_VALUE = Short.MIN_VALUE;
        public static final int MAX_VALUE = Short.MAX_VALUE;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final INTEGER value(@Mesa.CARD16 int value) {
            return new INTEGER(value);
        }
        public static final INTEGER value() {
            return new INTEGER(0);
        }
        public static final INTEGER longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new INTEGER(base, access);
        }
        public static final INTEGER pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new INTEGER(Memory.lengthenMDS(base), access);
        }
        
        private INTEGER(@Mesa.CARD16 int value) {
            super(value);
        }
        private INTEGER(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // LONG CARDINAL: TYPE = LONG CARDINAL;
    //
    public static final class LONG_CARDINAL extends MemoryData32 {
        public static final String NAME = "LONG_CARDINAL";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final LONG_CARDINAL value(@Mesa.CARD32 int value) {
            return new LONG_CARDINAL(value);
        }
        public static final LONG_CARDINAL value() {
            return new LONG_CARDINAL(0);
        }
        public static final LONG_CARDINAL longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new LONG_CARDINAL(base, access);
        }
        public static final LONG_CARDINAL pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new LONG_CARDINAL(Memory.lengthenMDS(base), access);
        }
        
        private LONG_CARDINAL(@Mesa.CARD32 int value) {
            super(value);
        }
        private LONG_CARDINAL(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // LONG POINTER: TYPE = LONG POINTER;
    //
    public static final class LONG_POINTER extends MemoryBase {
        public static final String NAME = "LONG_POINTER";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final LONG_POINTER longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new LONG_POINTER(base, access);
        }
        public static final LONG_POINTER pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new LONG_POINTER(Memory.lengthenMDS(base), access);
        }
        
        private LONG_POINTER(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // LONG UNSPECIFIED: TYPE = LONG UNSPECIFIED;
    //
    public static final class LONG_UNSPECIFIED extends MemoryData32 {
        public static final String NAME = "LONG_UNSPECIFIED";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final LONG_UNSPECIFIED value(@Mesa.CARD32 int value) {
            return new LONG_UNSPECIFIED(value);
        }
        public static final LONG_UNSPECIFIED value() {
            return new LONG_UNSPECIFIED(0);
        }
        public static final LONG_UNSPECIFIED longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new LONG_UNSPECIFIED(base, access);
        }
        public static final LONG_UNSPECIFIED pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new LONG_UNSPECIFIED(Memory.lengthenMDS(base), access);
        }
        
        private LONG_UNSPECIFIED(@Mesa.CARD32 int value) {
            super(value);
        }
        private LONG_UNSPECIFIED(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // POINTER: TYPE = POINTER;
    //
    public static final class POINTER extends MemoryBase {
        public static final String NAME = "POINTER";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final POINTER longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new POINTER(base, access);
        }
        public static final POINTER pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new POINTER(Memory.lengthenMDS(base), access);
        }
        
        private POINTER(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // UNSPECIFIED: TYPE = UNSPECIFIED;
    //
    public static final class UNSPECIFIED extends MemoryData16 {
        public static final String NAME = "UNSPECIFIED";
        
        public static final int WORD_SIZE =      1;
        public static final int BIT_SIZE  =     16;
                                                   
        public static final int MIN_VALUE =      0;
        public static final int MAX_VALUE = 0xFFFF;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final UNSPECIFIED value(@Mesa.CARD16 int value) {
            return new UNSPECIFIED(value);
        }
        public static final UNSPECIFIED value() {
            return new UNSPECIFIED(0);
        }
        public static final UNSPECIFIED longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new UNSPECIFIED(base, access);
        }
        public static final UNSPECIFIED pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new UNSPECIFIED(Memory.lengthenMDS(base), access);
        }
        
        private UNSPECIFIED(@Mesa.CARD16 int value) {
            super(value);
        }
        private UNSPECIFIED(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
    //
    public static final class BLOCK extends MemoryBase {
        public static final String NAME = "BLOCK";
        
        public static final int WORD_SIZE = 0;
        public static final int BIT_SIZE  = 0;
        
        // Constructor
        public static final BLOCK longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BLOCK(base, access);
        }
        public static final BLOCK pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BLOCK(Memory.lengthenMDS(base), access);
        }
        
        private BLOCK(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // BIT: TYPE = [0..2);
    //
    public static final class BIT extends MemoryData16 {
        public static final String NAME = "BIT";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 1;
                                              
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 1;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final BIT value(@Mesa.CARD16 int value) {
            return new BIT(value);
        }
        public static final BIT value() {
            return new BIT(0);
        }
        public static final BIT longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BIT(base, access);
        }
        public static final BIT pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BIT(Memory.lengthenMDS(base), access);
        }
        
        private BIT(@Mesa.CARD16 int value) {
            super(value);
        }
        private BIT(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // NIBBLE: TYPE = [0..16);
    //
    public static final class NIBBLE extends MemoryData16 {
        public static final String NAME = "NIBBLE";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  =  4;
                                               
        public static final int MIN_VALUE =  0;
        public static final int MAX_VALUE = 15;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final NIBBLE value(@Mesa.CARD16 int value) {
            return new NIBBLE(value);
        }
        public static final NIBBLE value() {
            return new NIBBLE(0);
        }
        public static final NIBBLE longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new NIBBLE(base, access);
        }
        public static final NIBBLE pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new NIBBLE(Memory.lengthenMDS(base), access);
        }
        
        private NIBBLE(@Mesa.CARD16 int value) {
            super(value);
        }
        private NIBBLE(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // BYTE: TYPE = [0..256);
    //
    public static final class BYTE extends MemoryData16 {
        public static final String NAME = "BYTE";
        
        public static final int WORD_SIZE =    1;
        public static final int BIT_SIZE  =    8;
                                                 
        public static final int MIN_VALUE =    0;
        public static final int MAX_VALUE = 0xFF;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final BYTE value(@Mesa.CARD16 int value) {
            return new BYTE(value);
        }
        public static final BYTE value() {
            return new BYTE(0);
        }
        public static final BYTE longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BYTE(base, access);
        }
        public static final BYTE pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BYTE(Memory.lengthenMDS(base), access);
        }
        
        private BYTE(@Mesa.CARD16 int value) {
            super(value);
        }
        private BYTE(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // NibblePair: TYPE = RECORD[left (0:0..3): NIBBLE, right (0:4..7): NIBBLE];
    //
    public static final class NibblePair extends MemoryData16 {
        public static final String NAME = "NibblePair";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 8;
        
        // Constructor
        public static final NibblePair value(@Mesa.CARD16 int value) {
            return new NibblePair(value);
        }
        public static final NibblePair value() {
            return new NibblePair(0);
        }
        public static final NibblePair longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new NibblePair(base, access);
        }
        public static final NibblePair pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new NibblePair(Memory.lengthenMDS(base), access);
        }
        
        private NibblePair(@Mesa.CARD16 int value) {
            super(value);
        }
        private NibblePair(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // left  (0:0..3): NIBBLE
        // right (0:4..7): NIBBLE
        
        private static final int LEFT_MASK   = 0b1111_0000;
        private static final int LEFT_SHIFT  =           4;
        private static final int RIGHT_MASK  = 0b0000_1111;
        private static final int RIGHT_SHIFT =           0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int left() {
            return (value & LEFT_MASK) >>> LEFT_SHIFT;
        }
        public final NibblePair left(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int right() {
            return (value & RIGHT_MASK) >>> RIGHT_SHIFT;
        }
        public final NibblePair right(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
            return this;
        }
        
    }
    
    //
    // BytePair: TYPE = RECORD[left (0:0..7): BYTE, right (0:8..15): BYTE];
    //
    public static final class BytePair extends MemoryData16 {
        public static final String NAME = "BytePair";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final BytePair value(@Mesa.CARD16 int value) {
            return new BytePair(value);
        }
        public static final BytePair value() {
            return new BytePair(0);
        }
        public static final BytePair longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BytePair(base, access);
        }
        public static final BytePair pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BytePair(Memory.lengthenMDS(base), access);
        }
        
        private BytePair(@Mesa.CARD16 int value) {
            super(value);
        }
        private BytePair(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // left  (0:0..7):  BYTE
        // right (0:8..15): BYTE
        
        private static final int LEFT_MASK   = 0b1111_1111_0000_0000;
        private static final int LEFT_SHIFT  =                     8;
        private static final int RIGHT_MASK  = 0b0000_0000_1111_1111;
        private static final int RIGHT_SHIFT =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is BYTE
        public final @Mesa.CARD8 int left() {
            return (value & LEFT_MASK) >>> LEFT_SHIFT;
        }
        public final BytePair left(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
            value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is BYTE
        public final @Mesa.CARD8 int right() {
            return (value & RIGHT_MASK) >>> RIGHT_SHIFT;
        }
        public final BytePair right(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
            value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
            return this;
        }
        
    }
    
    //
    // Long: TYPE = RECORD[high (0:0..15): UNSPECIFIED, low (0:16..31): UNSPECIFIED];
    //
    public static final class Long extends MemoryData32 {
        public static final String NAME = "Long";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final Long value(@Mesa.CARD32 int value) {
            return new Long(value);
        }
        public static final Long value() {
            return new Long(0);
        }
        public static final Long longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Long(base, access);
        }
        public static final Long pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Long(Memory.lengthenMDS(base), access);
        }
        
        private Long(@Mesa.CARD32 int value) {
            super(value);
        }
        private Long(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // high (0:0..15):  UNSPECIFIED
        // low  (0:16..31): UNSPECIFIED
        
        private static final int HIGH_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
        private static final int HIGH_SHIFT =                                        16;
        private static final int LOW_MASK   = 0b0000_0000_0000_0000_1111_1111_1111_1111;
        private static final int LOW_SHIFT  =                                         0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int high() {
            return (value & HIGH_MASK) >>> HIGH_SHIFT;
        }
        public final Long high(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~HIGH_MASK) | ((newValue << HIGH_SHIFT) & HIGH_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int low() {
            return (value & LOW_MASK) >>> LOW_SHIFT;
        }
        public final Long low(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~LOW_MASK) | ((newValue << LOW_SHIFT) & LOW_MASK);
            return this;
        }
        
    }
    
    //
    // CodeSegment: TYPE = RECORD[available (0:0..63): ARRAY [0..4) OF UNSPECIFIED, code (4): BLOCK];
    //
    public static final class CodeSegment extends MemoryBase {
        public static final String NAME = "CodeSegment";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final CodeSegment longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new CodeSegment(base, access);
        }
        public static final CodeSegment pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new CodeSegment(Memory.lengthenMDS(base), access);
        }
        
        private CodeSegment(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // available (0:0..63): ARRAY [0..4) OF UNSPECIFIED
        private static final int OFFSET_AVAILABLE = 0;
        private static final class AvailableIndex {
            private static final ContextSubrange context = new ContextSubrange("CodeSegment", 0, 3);
            private static final void checkValue(int value) {
                context.check(value);
            }
        }
        public final UNSPECIFIED available(int index) {
            if (Debug.ENABLE_CHECK_VALUE) AvailableIndex.checkValue(index);
            int longPointer = base + OFFSET_AVAILABLE + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // code (4): BLOCK
        private static final int OFFSET_CODE = 4;
        public BLOCK code() {
            int longPointer = base + OFFSET_CODE;
            return BLOCK.longPointer(longPointer, access);
        }
    }
    // IGNORE GlobalVariables: TYPE = BLOCK;
    // IGNORE GlobalFrameHandle: TYPE = LONG POINTER TO GlobalVariables;
    
    //
    // GlobalWord: TYPE = RECORD[gfi (0:0..13): GFTIndex, trapxfers (0:14..14): BOOLEAN, codelinks (0:15..15): BOOLEAN];
    //
    public static final class GlobalWord extends MemoryData16 {
        public static final String NAME = "GlobalWord";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final GlobalWord value(@Mesa.CARD16 int value) {
            return new GlobalWord(value);
        }
        public static final GlobalWord value() {
            return new GlobalWord(0);
        }
        public static final GlobalWord longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new GlobalWord(base, access);
        }
        public static final GlobalWord pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new GlobalWord(Memory.lengthenMDS(base), access);
        }
        
        private GlobalWord(@Mesa.CARD16 int value) {
            super(value);
        }
        private GlobalWord(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // gfi       (0:0..13):  GFTIndex
        // trapxfers (0:14..14): BOOLEAN
        // codelinks (0:15..15): BOOLEAN
        
        private static final int GFI_MASK        = 0b1111_1111_1111_1100;
        private static final int GFI_SHIFT       =                     2;
        private static final int TRAPXFERS_MASK  = 0b0000_0000_0000_0010;
        private static final int TRAPXFERS_SHIFT =                     1;
        private static final int CODELINKS_MASK  = 0b0000_0000_0000_0001;
        private static final int CODELINKS_SHIFT =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is GFTIndex
        public final @Mesa.CARD16 int gfi() {
            return (value & GFI_MASK) >>> GFI_SHIFT;
        }
        public final GlobalWord gfi(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) GFTIndex.checkValue(newValue);
            value = (value & ~GFI_MASK) | ((newValue << GFI_SHIFT) & GFI_MASK);
            return this;
        }
        
        public final boolean trapxfers() {
            return ((value & TRAPXFERS_MASK) >>> TRAPXFERS_SHIFT) != 0;
        }
        public final GlobalWord trapxfers(boolean newValue) {
            value = Types.toCARD16((value & ~TRAPXFERS_MASK) | (((newValue ? 1 : 0) << TRAPXFERS_SHIFT) & TRAPXFERS_MASK));
            return this;
        }
        
        public final boolean codelinks() {
            return ((value & CODELINKS_MASK) >>> CODELINKS_SHIFT) != 0;
        }
        public final GlobalWord codelinks(boolean newValue) {
            value = Types.toCARD16((value & ~CODELINKS_MASK) | (((newValue ? 1 : 0) << CODELINKS_SHIFT) & CODELINKS_MASK));
            return this;
        }
        
    }
    
    //
    // GlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, codebase (2:0..31): LONG POINTER TO CodeSegment, global (4): GlobalVariables];
    //
    public static final class GlobalOverhead extends MemoryBase {
        public static final String NAME = "GlobalOverhead";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final GlobalOverhead longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new GlobalOverhead(base, access);
        }
        public static final GlobalOverhead pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new GlobalOverhead(Memory.lengthenMDS(base), access);
        }
        
        private GlobalOverhead(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // available (0:0..15): UNSPECIFIED
        private static final int OFFSET_AVAILABLE = 0;
        public UNSPECIFIED available() {
            int longPointer = base + OFFSET_AVAILABLE;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // word (1:0..15): GlobalWord
        private static final int OFFSET_WORD = 1;
        public GlobalWord word() {
            int longPointer = base + OFFSET_WORD;
            return GlobalWord.longPointer(longPointer, access);
        }
        // codebase (2:0..31): LONG POINTER TO CodeSegment
        private static final int OFFSET_CODEBASE = 2;
        public CodeSegment codebase() {
            int longPointer = Memory.read32(base + OFFSET_CODEBASE);
            return CodeSegment.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int codebaseValue() {
            return Memory.read32(base + OFFSET_CODEBASE);
        }
        public final GlobalOverhead codebaseValue(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_CODEBASE, newValue);
            return this;
        }
        // global (4): GlobalVariables
        private static final int OFFSET_GLOBAL = 4;
        public BLOCK global() {
            int longPointer = base + OFFSET_GLOBAL;
            return BLOCK.longPointer(longPointer, access);
        }
    }
    
    //
    // NewGlobalOverhead: TYPE = RECORD[available (0:0..15): UNSPECIFIED, word (1:0..15): GlobalWord, global (2): GlobalVariables];
    //
    public static final class NewGlobalOverhead extends MemoryBase {
        public static final String NAME = "NewGlobalOverhead";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final NewGlobalOverhead longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new NewGlobalOverhead(base, access);
        }
        public static final NewGlobalOverhead pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new NewGlobalOverhead(Memory.lengthenMDS(base), access);
        }
        
        private NewGlobalOverhead(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // available (0:0..15): UNSPECIFIED
        private static final int OFFSET_AVAILABLE = 0;
        public UNSPECIFIED available() {
            int longPointer = base + OFFSET_AVAILABLE;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // word (1:0..15): GlobalWord
        private static final int OFFSET_WORD = 1;
        public GlobalWord word() {
            int longPointer = base + OFFSET_WORD;
            return GlobalWord.longPointer(longPointer, access);
        }
        // global (2): GlobalVariables
        private static final int OFFSET_GLOBAL = 2;
        public BLOCK global() {
            int longPointer = base + OFFSET_GLOBAL;
            return BLOCK.longPointer(longPointer, access);
        }
    }
    
    //
    // GFT: LONG POINTER TO GlobalFrameTable = yokwe.majuro.mesa.Constants.mGFT;
    //
    public static final GlobalFrameTable GFT(MemoryAccess access) {
        return GlobalFrameTable.longPointer(yokwe.majuro.mesa.Constants.mGFT, access);
    }
    
    //
    // GlobalFrameTable: TYPE = ARRAY GFTIndex OF GFTItem;
    //
    public static final class GlobalFrameTable extends MemoryBase {
        public static final String NAME = "GlobalFrameTable";
        
        public static final int WORD_SIZE =   65536;
        public static final int BIT_SIZE  = 1048576;
        
        // Constructor
        public static final GlobalFrameTable longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new GlobalFrameTable(base, access);
        }
        public static final GlobalFrameTable pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new GlobalFrameTable(Memory.lengthenMDS(base), access);
        }
        
        private GlobalFrameTable(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final GFTItem get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) GFTIndex.checkValue(index);
            int longPointer = base + (GFTItem.WORD_SIZE * index);
            return GFTItem.longPointer(longPointer, access);
        }
    }
    // IGNORE GFTHandle: TYPE = CARDINAL;
    
    //
    // GFTIndex: TYPE = [0..16384);
    //
    public static final class GFTIndex extends MemoryData16 {
        public static final String NAME = "GFTIndex";
        
        public static final int WORD_SIZE =     1;
        public static final int BIT_SIZE  =    14;
                                                  
        public static final int MIN_VALUE =     0;
        public static final int MAX_VALUE = 16383;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final GFTIndex value(@Mesa.CARD16 int value) {
            return new GFTIndex(value);
        }
        public static final GFTIndex value() {
            return new GFTIndex(0);
        }
        public static final GFTIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new GFTIndex(base, access);
        }
        public static final GFTIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new GFTIndex(Memory.lengthenMDS(base), access);
        }
        
        private GFTIndex(@Mesa.CARD16 int value) {
            super(value);
        }
        private GFTIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // GFTItem: TYPE = RECORD[globalFrame (0:0..31): GlobalFrameHandle, codebase (2:0..31): LONG POINTER TO CodeSegment];
    //
    public static final class GFTItem extends MemoryBase {
        public static final String NAME = "GFTItem";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final GFTItem longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new GFTItem(base, access);
        }
        public static final GFTItem pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new GFTItem(Memory.lengthenMDS(base), access);
        }
        
        private GFTItem(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // globalFrame (0:0..31): GlobalFrameHandle
        private static final int OFFSET_GLOBAL_FRAME = 0;
        public BLOCK globalFrame() {
            int longPointer = Memory.read32(base + OFFSET_GLOBAL_FRAME);
            return BLOCK.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int globalFrameValue() {
            return Memory.read32(base + OFFSET_GLOBAL_FRAME);
        }
        public final GFTItem globalFrameValue(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_GLOBAL_FRAME, newValue);
            return this;
        }
        // codebase (2:0..31): LONG POINTER TO CodeSegment
        private static final int OFFSET_CODEBASE = 2;
        public CodeSegment codebase() {
            int longPointer = Memory.read32(base + OFFSET_CODEBASE);
            return CodeSegment.longPointer(longPointer, access);
        }
        public final @Mesa.LONG_POINTER int codebaseValue() {
            return Memory.read32(base + OFFSET_CODEBASE);
        }
        public final GFTItem codebaseValue(@Mesa.LONG_POINTER int newValue) {
            Memory.write32(base + OFFSET_CODEBASE, newValue);
            return this;
        }
    }
    // IGNORE LocalFrameHandle: TYPE = POINTER TO LocalVariables;
    // IGNORE LocalVariables: TYPE = BLOCK;
    
    //
    // LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
    //
    public static final class LocalWord extends MemoryData16 {
        public static final String NAME = "LocalWord";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final LocalWord value(@Mesa.CARD16 int value) {
            return new LocalWord(value);
        }
        public static final LocalWord value() {
            return new LocalWord(0);
        }
        public static final LocalWord longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new LocalWord(base, access);
        }
        public static final LocalWord pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new LocalWord(Memory.lengthenMDS(base), access);
        }
        
        private LocalWord(@Mesa.CARD16 int value) {
            super(value);
        }
        private LocalWord(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // available (0:0..7):  BYTE
        // fsi       (0:8..15): FSIndex
        
        private static final int AVAILABLE_MASK  = 0b1111_1111_0000_0000;
        private static final int AVAILABLE_SHIFT =                     8;
        private static final int FSI_MASK        = 0b0000_0000_1111_1111;
        private static final int FSI_SHIFT       =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is BYTE
        public final @Mesa.CARD8 int available() {
            return (value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT;
        }
        public final LocalWord available(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
            value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is FSIndex
        public final @Mesa.CARD8 int fsi() {
            return (value & FSI_MASK) >>> FSI_SHIFT;
        }
        public final LocalWord fsi(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(newValue);
            value = (value & ~FSI_MASK) | ((newValue << FSI_SHIFT) & FSI_MASK);
            return this;
        }
        
    }
    
    //
    // LocalOverhead: TYPE = RECORD[word (0:0..15): LocalWord, returnlink (1:0..15): ShortControlLink, globallink (2:0..15): GFTHandle, pc (3:0..15): CARDINAL, local (4): LocalVariables];
    //
    public static final class LocalOverhead extends MemoryBase {
        public static final String NAME = "LocalOverhead";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final LocalOverhead longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new LocalOverhead(base, access);
        }
        public static final LocalOverhead pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new LocalOverhead(Memory.lengthenMDS(base), access);
        }
        
        private LocalOverhead(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // word (0:0..15): LocalWord
        private static final int OFFSET_WORD = 0;
        public LocalWord word() {
            int longPointer = base + OFFSET_WORD;
            return LocalWord.longPointer(longPointer, access);
        }
        // returnlink (1:0..15): ShortControlLink
        private static final int OFFSET_RETURNLINK = 1;
        public UNSPECIFIED returnlink() {
            int longPointer = base + OFFSET_RETURNLINK;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // globallink (2:0..15): GFTHandle
        private static final int OFFSET_GLOBALLINK = 2;
        public CARDINAL globallink() {
            int longPointer = base + OFFSET_GLOBALLINK;
            return CARDINAL.longPointer(longPointer, access);
        }
        // pc (3:0..15): CARDINAL
        private static final int OFFSET_PC = 3;
        public CARDINAL pc() {
            int longPointer = base + OFFSET_PC;
            return CARDINAL.longPointer(longPointer, access);
        }
        // local (4): LocalVariables
        private static final int OFFSET_LOCAL = 4;
        public BLOCK local() {
            int longPointer = base + OFFSET_LOCAL;
            return BLOCK.longPointer(longPointer, access);
        }
    }
    
    //
    // FieldSpec: TYPE = RECORD[pos (0:0..3): NIBBLE, size (0:4..7): NIBBLE];
    //
    public static final class FieldSpec extends MemoryData16 {
        public static final String NAME = "FieldSpec";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 8;
        
        // Constructor
        public static final FieldSpec value(@Mesa.CARD16 int value) {
            return new FieldSpec(value);
        }
        public static final FieldSpec value() {
            return new FieldSpec(0);
        }
        public static final FieldSpec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new FieldSpec(base, access);
        }
        public static final FieldSpec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new FieldSpec(Memory.lengthenMDS(base), access);
        }
        
        private FieldSpec(@Mesa.CARD16 int value) {
            super(value);
        }
        private FieldSpec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // pos  (0:0..3): NIBBLE
        // size (0:4..7): NIBBLE
        
        private static final int POS_MASK   = 0b1111_0000;
        private static final int POS_SHIFT  =           4;
        private static final int SIZE_MASK  = 0b0000_1111;
        private static final int SIZE_SHIFT =           0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int pos() {
            return (value & POS_MASK) >>> POS_SHIFT;
        }
        public final FieldSpec pos(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~POS_MASK) | ((newValue << POS_SHIFT) & POS_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int size() {
            return (value & SIZE_MASK) >>> SIZE_SHIFT;
        }
        public final FieldSpec size(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~SIZE_MASK) | ((newValue << SIZE_SHIFT) & SIZE_MASK);
            return this;
        }
        
    }
    
    //
    // FieldDesc: TYPE = RECORD[offset (0:0..7): BYTE, field (0:8..15): FieldSpec];
    //
    public static final class FieldDesc extends MemoryData16 {
        public static final String NAME = "FieldDesc";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final FieldDesc value(@Mesa.CARD16 int value) {
            return new FieldDesc(value);
        }
        public static final FieldDesc value() {
            return new FieldDesc(0);
        }
        public static final FieldDesc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new FieldDesc(base, access);
        }
        public static final FieldDesc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new FieldDesc(Memory.lengthenMDS(base), access);
        }
        
        private FieldDesc(@Mesa.CARD16 int value) {
            super(value);
        }
        private FieldDesc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // offset (0:0..7):  BYTE
        // field  (0:8..15): FieldSpec
        
        private static final int OFFSET_MASK  = 0b1111_1111_0000_0000;
        private static final int OFFSET_SHIFT =                     8;
        private static final int FIELD_MASK   = 0b0000_0000_1111_1111;
        private static final int FIELD_SHIFT  =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is BYTE
        public final @Mesa.CARD8 int offset() {
            return (value & OFFSET_MASK) >>> OFFSET_SHIFT;
        }
        public final FieldDesc offset(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
            value = (value & ~OFFSET_MASK) | ((newValue << OFFSET_SHIFT) & OFFSET_MASK);
            return this;
        }
        
        // FieldSpec is BIT FIELD 16
        public final FieldSpec field() {
            return FieldSpec.value(Types.toCARD16((value & FIELD_MASK) >>> FIELD_SHIFT));
        }
        public final FieldDesc field(FieldSpec newValue) {
            value = Types.toCARD16((value & ~FIELD_MASK) | ((newValue.value << FIELD_SHIFT) & FIELD_MASK));
            return this;
        }
        
    }
    
    //
    // BitAddress: TYPE = RECORD[word (0:0..31): LONG POINTER, reserved (2:0..11): UNSPECIFIED, bit (2:12..15): CARDINAL];
    //
    public static final class BitAddress extends MemoryBase {
        public static final String NAME = "BitAddress";
        
        public static final int WORD_SIZE =  3;
        public static final int BIT_SIZE  = 48;
        
        // Constructor
        public static final BitAddress longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BitAddress(base, access);
        }
        public static final BitAddress pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BitAddress(Memory.lengthenMDS(base), access);
        }
        
        private BitAddress(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // word (0:0..31): LONG POINTER
        private static final int OFFSET_WORD = 0;
        public LONG_POINTER word() {
            int longPointer = base + OFFSET_WORD;
            return LONG_POINTER.longPointer(longPointer, access);
        }
        // reserved (2:0..11): UNSPECIFIED
        // FIXME  Field is not aligned
        // bit (2:12..15): CARDINAL
        // FIXME  Field is not aligned
    }
    
    //
    // BitBltFlags: TYPE = RECORD[direction (0:0..0): Direction, disjoint (0:1..1): BOOLEAN, disjointItems (0:2..2): BOOLEAN, gray (0:3..3): BOOLEAN, srcFunc (0:4..4): SrcFunc, dstFunc (0:5..6): DstFunc, reserved (0:7..15): UNSPECIFIED];
    //
    public static final class BitBltFlags extends MemoryData16 {
        public static final String NAME = "BitBltFlags";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final BitBltFlags value(@Mesa.CARD16 int value) {
            return new BitBltFlags(value);
        }
        public static final BitBltFlags value() {
            return new BitBltFlags(0);
        }
        public static final BitBltFlags longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BitBltFlags(base, access);
        }
        public static final BitBltFlags pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BitBltFlags(Memory.lengthenMDS(base), access);
        }
        
        private BitBltFlags(@Mesa.CARD16 int value) {
            super(value);
        }
        private BitBltFlags(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // direction     (0:0..0):  Direction
        // disjoint      (0:1..1):  BOOLEAN
        // disjointItems (0:2..2):  BOOLEAN
        // gray          (0:3..3):  BOOLEAN
        // srcFunc       (0:4..4):  SrcFunc
        // dstFunc       (0:5..6):  DstFunc
        // reserved      (0:7..15): UNSPECIFIED
        
        private static final int DIRECTION_MASK       = 0b1000_0000_0000_0000;
        private static final int DIRECTION_SHIFT      =                    15;
        private static final int DISJOINT_MASK        = 0b0100_0000_0000_0000;
        private static final int DISJOINT_SHIFT       =                    14;
        private static final int DISJOINT_ITEMS_MASK  = 0b0010_0000_0000_0000;
        private static final int DISJOINT_ITEMS_SHIFT =                    13;
        private static final int GRAY_MASK            = 0b0001_0000_0000_0000;
        private static final int GRAY_SHIFT           =                    12;
        private static final int SRC_FUNC_MASK        = 0b0000_1000_0000_0000;
        private static final int SRC_FUNC_SHIFT       =                    11;
        private static final int DST_FUNC_MASK        = 0b0000_0110_0000_0000;
        private static final int DST_FUNC_SHIFT       =                     9;
        private static final int RESERVED_MASK        = 0b0000_0001_1111_1111;
        private static final int RESERVED_SHIFT       =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.ENUM is Direction
        public final @Mesa.ENUM int direction() {
            return Types.toCARD16((value & DIRECTION_MASK) >>> DIRECTION_SHIFT);
        }
        public final BitBltFlags direction(@Mesa.ENUM int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Direction.checkValue(newValue);
            value = Types.toCARD16((value & ~DIRECTION_MASK) | ((newValue << DIRECTION_SHIFT) & DIRECTION_MASK));
            return this;
        }
        
        public final boolean disjoint() {
            return ((value & DISJOINT_MASK) >>> DISJOINT_SHIFT) != 0;
        }
        public final BitBltFlags disjoint(boolean newValue) {
            value = Types.toCARD16((value & ~DISJOINT_MASK) | (((newValue ? 1 : 0) << DISJOINT_SHIFT) & DISJOINT_MASK));
            return this;
        }
        
        public final boolean disjointItems() {
            return ((value & DISJOINT_ITEMS_MASK) >>> DISJOINT_ITEMS_SHIFT) != 0;
        }
        public final BitBltFlags disjointItems(boolean newValue) {
            value = Types.toCARD16((value & ~DISJOINT_ITEMS_MASK) | (((newValue ? 1 : 0) << DISJOINT_ITEMS_SHIFT) & DISJOINT_ITEMS_MASK));
            return this;
        }
        
        public final boolean gray() {
            return ((value & GRAY_MASK) >>> GRAY_SHIFT) != 0;
        }
        public final BitBltFlags gray(boolean newValue) {
            value = Types.toCARD16((value & ~GRAY_MASK) | (((newValue ? 1 : 0) << GRAY_SHIFT) & GRAY_MASK));
            return this;
        }
        
        // @Mesa.ENUM is SrcFunc
        public final @Mesa.ENUM int srcFunc() {
            return Types.toCARD16((value & SRC_FUNC_MASK) >>> SRC_FUNC_SHIFT);
        }
        public final BitBltFlags srcFunc(@Mesa.ENUM int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) SrcFunc.checkValue(newValue);
            value = Types.toCARD16((value & ~SRC_FUNC_MASK) | ((newValue << SRC_FUNC_SHIFT) & SRC_FUNC_MASK));
            return this;
        }
        
        // @Mesa.ENUM is DstFunc
        public final @Mesa.ENUM int dstFunc() {
            return Types.toCARD16((value & DST_FUNC_MASK) >>> DST_FUNC_SHIFT);
        }
        public final BitBltFlags dstFunc(@Mesa.ENUM int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) DstFunc.checkValue(newValue);
            value = Types.toCARD16((value & ~DST_FUNC_MASK) | ((newValue << DST_FUNC_SHIFT) & DST_FUNC_MASK));
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int reserved() {
            return (value & RESERVED_MASK) >>> RESERVED_SHIFT;
        }
        public final BitBltFlags reserved(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
            return this;
        }
        
    }
    
    //
    // SrcFunc: TYPE = {null(0), complement(1)};
    //
    public static final class SrcFunc extends MemoryData16 {
        public static final String NAME = "SrcFunc";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 1;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int NULL       = 0;
        public static final @Mesa.ENUM int COMPLEMENT = 1;
        
        private static final int[] values = {
            NULL, COMPLEMENT
        };
        private static final String[] names = {
            "NULL", "COMPLEMENT"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final SrcFunc value(@Mesa.CARD16 int value) {
            return new SrcFunc(value);
        }
        public static final SrcFunc value() {
            return new SrcFunc(0);
        }
        public static final SrcFunc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new SrcFunc(base, access);
        }
        public static final SrcFunc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new SrcFunc(Memory.lengthenMDS(base), access);
        }
        
        private SrcFunc(@Mesa.CARD16 int value) {
            super(value);
        }
        private SrcFunc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // DstFunc: TYPE = {null(0), and(1), or(2), xor(3)};
    //
    public static final class DstFunc extends MemoryData16 {
        public static final String NAME = "DstFunc";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 2;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int NULL = 0;
        public static final @Mesa.ENUM int AND  = 1;
        public static final @Mesa.ENUM int OR   = 2;
        public static final @Mesa.ENUM int XOR  = 3;
        
        private static final int[] values = {
            NULL, AND, OR, XOR
        };
        private static final String[] names = {
            "NULL", "AND", "OR", "XOR"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final DstFunc value(@Mesa.CARD16 int value) {
            return new DstFunc(value);
        }
        public static final DstFunc value() {
            return new DstFunc(0);
        }
        public static final DstFunc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new DstFunc(base, access);
        }
        public static final DstFunc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new DstFunc(Memory.lengthenMDS(base), access);
        }
        
        private DstFunc(@Mesa.CARD16 int value) {
            super(value);
        }
        private DstFunc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // Direction: TYPE = {forward(0), backward(1)};
    //
    public static final class Direction extends MemoryData16 {
        public static final String NAME = "Direction";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 1;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int FORWARD  = 0;
        public static final @Mesa.ENUM int BACKWARD = 1;
        
        private static final int[] values = {
            FORWARD, BACKWARD
        };
        private static final String[] names = {
            "FORWARD", "BACKWARD"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final Direction value(@Mesa.CARD16 int value) {
            return new Direction(value);
        }
        public static final Direction value() {
            return new Direction(0);
        }
        public static final Direction longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Direction(base, access);
        }
        public static final Direction pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Direction(Memory.lengthenMDS(base), access);
        }
        
        private Direction(@Mesa.CARD16 int value) {
            super(value);
        }
        private Direction(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // BitBltArg: TYPE = RECORD[dst (0:0..47): BitAddress, dstBpl (3:0..15): INTEGER, src (4:0..47): BitAddress, srcBpl (7:0..15): INTEGER, width (8:0..15): CARDINAL, height (9:0..15): CARDINAL, flags (10:0..15): BitBltFlags, reserved (11:0..15): UNSPECIFIED];
    //
    public static final class BitBltArg extends MemoryBase {
        public static final String NAME = "BitBltArg";
        
        public static final int WORD_SIZE =  12;
        public static final int BIT_SIZE  = 192;
        
        // Constructor
        public static final BitBltArg longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new BitBltArg(base, access);
        }
        public static final BitBltArg pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new BitBltArg(Memory.lengthenMDS(base), access);
        }
        
        private BitBltArg(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // dst (0:0..47): BitAddress
        private static final int OFFSET_DST = 0;
        public BitAddress dst() {
            int longPointer = base + OFFSET_DST;
            return BitAddress.longPointer(longPointer, access);
        }
        // dstBpl (3:0..15): INTEGER
        private static final int OFFSET_DST_BPL = 3;
        public INTEGER dstBpl() {
            int longPointer = base + OFFSET_DST_BPL;
            return INTEGER.longPointer(longPointer, access);
        }
        // src (4:0..47): BitAddress
        private static final int OFFSET_SRC = 4;
        public BitAddress src() {
            int longPointer = base + OFFSET_SRC;
            return BitAddress.longPointer(longPointer, access);
        }
        // srcBpl (7:0..15): INTEGER
        private static final int OFFSET_SRC_BPL = 7;
        public INTEGER srcBpl() {
            int longPointer = base + OFFSET_SRC_BPL;
            return INTEGER.longPointer(longPointer, access);
        }
        // width (8:0..15): CARDINAL
        private static final int OFFSET_WIDTH = 8;
        public CARDINAL width() {
            int longPointer = base + OFFSET_WIDTH;
            return CARDINAL.longPointer(longPointer, access);
        }
        // height (9:0..15): CARDINAL
        private static final int OFFSET_HEIGHT = 9;
        public CARDINAL height() {
            int longPointer = base + OFFSET_HEIGHT;
            return CARDINAL.longPointer(longPointer, access);
        }
        // flags (10:0..15): BitBltFlags
        private static final int OFFSET_FLAGS = 10;
        public BitBltFlags flags() {
            int longPointer = base + OFFSET_FLAGS;
            return BitBltFlags.longPointer(longPointer, access);
        }
        // reserved (11:0..15): UNSPECIFIED
        private static final int OFFSET_RESERVED = 11;
        public UNSPECIFIED reserved() {
            int longPointer = base + OFFSET_RESERVED;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // GrayParm: TYPE = RECORD[reserved (0:0..3): NIBBLE, yOffset (0:4..7): NIBBLE, widthMinusOne (0:8..11): NIBBLE, heightMinusOne (0:12..15): NIBBLE];
    //
    public static final class GrayParm extends MemoryData16 {
        public static final String NAME = "GrayParm";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final GrayParm value(@Mesa.CARD16 int value) {
            return new GrayParm(value);
        }
        public static final GrayParm value() {
            return new GrayParm(0);
        }
        public static final GrayParm longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new GrayParm(base, access);
        }
        public static final GrayParm pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new GrayParm(Memory.lengthenMDS(base), access);
        }
        
        private GrayParm(@Mesa.CARD16 int value) {
            super(value);
        }
        private GrayParm(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // reserved       (0:0..3):   NIBBLE
        // yOffset        (0:4..7):   NIBBLE
        // widthMinusOne  (0:8..11):  NIBBLE
        // heightMinusOne (0:12..15): NIBBLE
        
        private static final int RESERVED_MASK          = 0b1111_0000_0000_0000;
        private static final int RESERVED_SHIFT         =                    12;
        private static final int Y_OFFSET_MASK          = 0b0000_1111_0000_0000;
        private static final int Y_OFFSET_SHIFT         =                     8;
        private static final int WIDTH_MINUS_ONE_MASK   = 0b0000_0000_1111_0000;
        private static final int WIDTH_MINUS_ONE_SHIFT  =                     4;
        private static final int HEIGHT_MINUS_ONE_MASK  = 0b0000_0000_0000_1111;
        private static final int HEIGHT_MINUS_ONE_SHIFT =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int reserved() {
            return (value & RESERVED_MASK) >>> RESERVED_SHIFT;
        }
        public final GrayParm reserved(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int yOffset() {
            return (value & Y_OFFSET_MASK) >>> Y_OFFSET_SHIFT;
        }
        public final GrayParm yOffset(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~Y_OFFSET_MASK) | ((newValue << Y_OFFSET_SHIFT) & Y_OFFSET_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int widthMinusOne() {
            return (value & WIDTH_MINUS_ONE_MASK) >>> WIDTH_MINUS_ONE_SHIFT;
        }
        public final GrayParm widthMinusOne(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~WIDTH_MINUS_ONE_MASK) | ((newValue << WIDTH_MINUS_ONE_SHIFT) & WIDTH_MINUS_ONE_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is NIBBLE
        public final @Mesa.CARD8 int heightMinusOne() {
            return (value & HEIGHT_MINUS_ONE_MASK) >>> HEIGHT_MINUS_ONE_SHIFT;
        }
        public final GrayParm heightMinusOne(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) NIBBLE.checkValue(newValue);
            value = (value & ~HEIGHT_MINUS_ONE_MASK) | ((newValue << HEIGHT_MINUS_ONE_SHIFT) & HEIGHT_MINUS_ONE_MASK);
            return this;
        }
        
    }
    // IGNORE ControlLink: TYPE = LONG UNSPECIFIED;
    // IGNORE ShortControlLink: TYPE = UNSPECIFIED;
    
    //
    // LinkType: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
    //
    public static final class LinkType extends MemoryData16 {
        public static final String NAME = "LinkType";
        
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
        public static final LinkType value(@Mesa.CARD16 int value) {
            return new LinkType(value);
        }
        public static final LinkType value() {
            return new LinkType(0);
        }
        public static final LinkType longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new LinkType(base, access);
        }
        public static final LinkType pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new LinkType(Memory.lengthenMDS(base), access);
        }
        
        private LinkType(@Mesa.CARD16 int value) {
            super(value);
        }
        private LinkType(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // TaggedControlLink: TYPE = RECORD[fill (0:0..15): UNSPECIFIED, data (0:16..29): UNSPECIFIED, tag (0:30..31): LinkType];
    //
    public static final class TaggedControlLink extends MemoryData32 {
        public static final String NAME = "TaggedControlLink";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final TaggedControlLink value(@Mesa.CARD32 int value) {
            return new TaggedControlLink(value);
        }
        public static final TaggedControlLink value() {
            return new TaggedControlLink(0);
        }
        public static final TaggedControlLink longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new TaggedControlLink(base, access);
        }
        public static final TaggedControlLink pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new TaggedControlLink(Memory.lengthenMDS(base), access);
        }
        
        private TaggedControlLink(@Mesa.CARD32 int value) {
            super(value);
        }
        private TaggedControlLink(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // fill (0:0..15):  UNSPECIFIED
        // data (0:16..29): UNSPECIFIED
        // tag  (0:30..31): LinkType
        
        private static final int FILL_MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
        private static final int FILL_SHIFT =                                        16;
        private static final int DATA_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1100;
        private static final int DATA_SHIFT =                                         2;
        private static final int TAG_MASK   = 0b0000_0000_0000_0000_0000_0000_0000_0011;
        private static final int TAG_SHIFT  =                                         0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int fill() {
            return (value & FILL_MASK) >>> FILL_SHIFT;
        }
        public final TaggedControlLink fill(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~FILL_MASK) | ((newValue << FILL_SHIFT) & FILL_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int data() {
            return (value & DATA_MASK) >>> DATA_SHIFT;
        }
        public final TaggedControlLink data(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~DATA_MASK) | ((newValue << DATA_SHIFT) & DATA_MASK);
            return this;
        }
        
        // @Mesa.ENUM is LinkType
        public final @Mesa.ENUM int tag() {
            return Types.toCARD16((value & TAG_MASK) >>> TAG_SHIFT);
        }
        public final TaggedControlLink tag(@Mesa.ENUM int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) LinkType.checkValue(newValue);
            value = Types.toCARD16((value & ~TAG_MASK) | ((newValue << TAG_SHIFT) & TAG_MASK));
            return this;
        }
        
    }
    // IGNORE FrameLink: TYPE = LocalFrameHandle;
    
    //
    // ProcDesc: TYPE = RECORD[pc (0:0..15): CARDINAL, taggedGF (0:16..31): UNSPECIFIED];
    //
    public static final class ProcDesc extends MemoryData32 {
        public static final String NAME = "ProcDesc";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final ProcDesc value(@Mesa.CARD32 int value) {
            return new ProcDesc(value);
        }
        public static final ProcDesc value() {
            return new ProcDesc(0);
        }
        public static final ProcDesc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ProcDesc(base, access);
        }
        public static final ProcDesc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ProcDesc(Memory.lengthenMDS(base), access);
        }
        
        private ProcDesc(@Mesa.CARD32 int value) {
            super(value);
        }
        private ProcDesc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // pc       (0:0..15):  CARDINAL
        // taggedGF (0:16..31): UNSPECIFIED
        
        private static final int PC_MASK         = 0b1111_1111_1111_1111_0000_0000_0000_0000;
        private static final int PC_SHIFT        =                                        16;
        private static final int TAGGED_GF_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
        private static final int TAGGED_GF_SHIFT =                                         0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is CARDINAL
        public final @Mesa.CARD16 int pc() {
            return (value & PC_MASK) >>> PC_SHIFT;
        }
        public final ProcDesc pc(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) CARDINAL.checkValue(newValue);
            value = (value & ~PC_MASK) | ((newValue << PC_SHIFT) & PC_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int taggedGF() {
            return (value & TAGGED_GF_MASK) >>> TAGGED_GF_SHIFT;
        }
        public final ProcDesc taggedGF(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~TAGGED_GF_MASK) | ((newValue << TAGGED_GF_SHIFT) & TAGGED_GF_MASK);
            return this;
        }
        
    }
    
    //
    // NewProcDesc: TYPE = RECORD[pc (0:0..15): CARDINAL, taggedGFI (0:16..31): UNSPECIFIED];
    //
    public static final class NewProcDesc extends MemoryData32 {
        public static final String NAME = "NewProcDesc";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final NewProcDesc value(@Mesa.CARD32 int value) {
            return new NewProcDesc(value);
        }
        public static final NewProcDesc value() {
            return new NewProcDesc(0);
        }
        public static final NewProcDesc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new NewProcDesc(base, access);
        }
        public static final NewProcDesc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new NewProcDesc(Memory.lengthenMDS(base), access);
        }
        
        private NewProcDesc(@Mesa.CARD32 int value) {
            super(value);
        }
        private NewProcDesc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // pc        (0:0..15):  CARDINAL
        // taggedGFI (0:16..31): UNSPECIFIED
        
        private static final int PC_MASK          = 0b1111_1111_1111_1111_0000_0000_0000_0000;
        private static final int PC_SHIFT         =                                        16;
        private static final int TAGGED_GFI_MASK  = 0b0000_0000_0000_0000_1111_1111_1111_1111;
        private static final int TAGGED_GFI_SHIFT =                                         0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is CARDINAL
        public final @Mesa.CARD16 int pc() {
            return (value & PC_MASK) >>> PC_SHIFT;
        }
        public final NewProcDesc pc(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) CARDINAL.checkValue(newValue);
            value = (value & ~PC_MASK) | ((newValue << PC_SHIFT) & PC_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int taggedGFI() {
            return (value & TAGGED_GFI_MASK) >>> TAGGED_GFI_SHIFT;
        }
        public final NewProcDesc taggedGFI(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~TAGGED_GFI_MASK) | ((newValue << TAGGED_GFI_SHIFT) & TAGGED_GFI_MASK);
            return this;
        }
        
    }
    
    //
    // AV: POINTER TO AllocationVector = yokwe.majuro.mesa.Constants.mAV;
    //
    public static final AllocationVector AV(MemoryAccess access) {
        return AllocationVector.pointer(yokwe.majuro.mesa.Constants.mAV, access);
    }
    
    //
    // AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
    //
    public static final class AllocationVector extends MemoryBase {
        public static final String NAME = "AllocationVector";
        
        public static final int WORD_SIZE =  256;
        public static final int BIT_SIZE  = 4096;
        
        // Constructor
        public static final AllocationVector longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new AllocationVector(base, access);
        }
        public static final AllocationVector pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new AllocationVector(Memory.lengthenMDS(base), access);
        }
        
        private AllocationVector(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final AVItem get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(index);
            int longPointer = base + (AVItem.WORD_SIZE * index);
            return AVItem.longPointer(longPointer, access);
        }
    }
    
    //
    // FSIndex: TYPE = [0..256);
    //
    public static final class FSIndex extends MemoryData16 {
        public static final String NAME = "FSIndex";
        
        public static final int WORD_SIZE =    1;
        public static final int BIT_SIZE  =    8;
                                                 
        public static final int MIN_VALUE =    0;
        public static final int MAX_VALUE = 0xFF;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final FSIndex value(@Mesa.CARD16 int value) {
            return new FSIndex(value);
        }
        public static final FSIndex value() {
            return new FSIndex(0);
        }
        public static final FSIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new FSIndex(base, access);
        }
        public static final FSIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new FSIndex(Memory.lengthenMDS(base), access);
        }
        
        private FSIndex(@Mesa.CARD16 int value) {
            super(value);
        }
        private FSIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
    //
    public static final class AVItem extends MemoryData16 {
        public static final String NAME = "AVItem";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final AVItem value(@Mesa.CARD16 int value) {
            return new AVItem(value);
        }
        public static final AVItem value() {
            return new AVItem(0);
        }
        public static final AVItem longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new AVItem(base, access);
        }
        public static final AVItem pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new AVItem(Memory.lengthenMDS(base), access);
        }
        
        private AVItem(@Mesa.CARD16 int value) {
            super(value);
        }
        private AVItem(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // data (0:0..13):  UNSPECIFIED
        // tag  (0:14..15): AVItemType
        
        private static final int DATA_MASK  = 0b1111_1111_1111_1100;
        private static final int DATA_SHIFT =                     2;
        private static final int TAG_MASK   = 0b0000_0000_0000_0011;
        private static final int TAG_SHIFT  =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int data() {
            return (value & DATA_MASK) >>> DATA_SHIFT;
        }
        public final AVItem data(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~DATA_MASK) | ((newValue << DATA_SHIFT) & DATA_MASK);
            return this;
        }
        
        // @Mesa.ENUM is AVItemType
        public final @Mesa.ENUM int tag() {
            return Types.toCARD16((value & TAG_MASK) >>> TAG_SHIFT);
        }
        public final AVItem tag(@Mesa.ENUM int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) AVItemType.checkValue(newValue);
            value = Types.toCARD16((value & ~TAG_MASK) | ((newValue << TAG_SHIFT) & TAG_MASK));
            return this;
        }
        
    }
    
    //
    // AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
    //
    public static final class AVItemType extends MemoryData16 {
        public static final String NAME = "AVItemType";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 2;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int FRAME    = 0;
        public static final @Mesa.ENUM int EMPTY    = 1;
        public static final @Mesa.ENUM int INDIRECT = 2;
        public static final @Mesa.ENUM int UNUSED   = 3;
        
        private static final int[] values = {
            FRAME, EMPTY, INDIRECT, UNUSED
        };
        private static final String[] names = {
            "FRAME", "EMPTY", "INDIRECT", "UNUSED"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final AVItemType value(@Mesa.CARD16 int value) {
            return new AVItemType(value);
        }
        public static final AVItemType value() {
            return new AVItemType(0);
        }
        public static final AVItemType longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new AVItemType(base, access);
        }
        public static final AVItemType pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new AVItemType(Memory.lengthenMDS(base), access);
        }
        
        private AVItemType(@Mesa.CARD16 int value) {
            super(value);
        }
        private AVItemType(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // XferType: TYPE = {return(0), call(1), localCall(2), part(3), xfer(4), trap(5), processSwitch(6), unused(7)};
    //
    public static final class XferType extends MemoryData16 {
        public static final String NAME = "XferType";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 3;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int RETURN         = 0;
        public static final @Mesa.ENUM int CALL           = 1;
        public static final @Mesa.ENUM int LOCAL_CALL     = 2;
        public static final @Mesa.ENUM int PART           = 3;
        public static final @Mesa.ENUM int XFER           = 4;
        public static final @Mesa.ENUM int TRAP           = 5;
        public static final @Mesa.ENUM int PROCESS_SWITCH = 6;
        public static final @Mesa.ENUM int UNUSED         = 7;
        
        private static final int[] values = {
            RETURN, CALL, LOCAL_CALL, PART, XFER, TRAP, PROCESS_SWITCH, UNUSED
        };
        private static final String[] names = {
            "RETURN", "CALL", "LOCAL_CALL", "PART", "XFER", "TRAP", "PROCESS_SWITCH", "UNUSED"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final XferType value(@Mesa.CARD16 int value) {
            return new XferType(value);
        }
        public static final XferType value() {
            return new XferType(0);
        }
        public static final XferType longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new XferType(base, access);
        }
        public static final XferType pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new XferType(Memory.lengthenMDS(base), access);
        }
        
        private XferType(@Mesa.CARD16 int value) {
            super(value);
        }
        private XferType(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
    
    //
    // Port: TYPE = RECORD[inport (0:0..15): FrameLink, unused (1:0..15): UNSPECIFIED, outport (2:0..31): ControlLink];
    //
    public static final class Port extends MemoryBase {
        public static final String NAME = "Port";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final Port longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Port(base, access);
        }
        public static final Port pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Port(Memory.lengthenMDS(base), access);
        }
        
        private Port(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // inport (0:0..15): FrameLink
        private static final int OFFSET_INPORT = 0;
        public BLOCK inport() {
            int pointer = Memory.read16(base + OFFSET_INPORT);
            return BLOCK.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int inportValue() {
            return Memory.read16(base + OFFSET_INPORT);
        }
        public final Port inportValue(@Mesa.SHORT_POINTER int newValue) {
            Memory.write16(base + OFFSET_INPORT, newValue);
            return this;
        }
        // unused (1:0..15): UNSPECIFIED
        private static final int OFFSET_UNUSED = 1;
        public UNSPECIFIED unused() {
            int longPointer = base + OFFSET_UNUSED;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // outport (2:0..31): ControlLink
        private static final int OFFSET_OUTPORT = 2;
        public LONG_UNSPECIFIED outport() {
            int longPointer = base + OFFSET_OUTPORT;
            return LONG_UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // SD: POINTER TO SystemData = yokwe.majuro.mesa.Constants.mSD;
    //
    public static final SystemData SD(MemoryAccess access) {
        return SystemData.pointer(yokwe.majuro.mesa.Constants.mSD, access);
    }
    
    //
    // SystemData: TYPE = ARRAY SDIndex OF ControlLink;
    //
    public static final class SystemData extends MemoryBase {
        public static final String NAME = "SystemData";
        
        public static final int WORD_SIZE =  512;
        public static final int BIT_SIZE  = 8192;
        
        // Constructor
        public static final SystemData longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new SystemData(base, access);
        }
        public static final SystemData pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new SystemData(Memory.lengthenMDS(base), access);
        }
        
        private SystemData(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final LONG_UNSPECIFIED get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) SDIndex.checkValue(index);
            int longPointer = base + (LONG_UNSPECIFIED.WORD_SIZE * index);
            return LONG_UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // SDIndex: TYPE = [0..256);
    //
    public static final class SDIndex extends MemoryData16 {
        public static final String NAME = "SDIndex";
        
        public static final int WORD_SIZE =    1;
        public static final int BIT_SIZE  =    8;
                                                 
        public static final int MIN_VALUE =    0;
        public static final int MAX_VALUE = 0xFF;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final SDIndex value(@Mesa.CARD16 int value) {
            return new SDIndex(value);
        }
        public static final SDIndex value() {
            return new SDIndex(0);
        }
        public static final SDIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new SDIndex(base, access);
        }
        public static final SDIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new SDIndex(Memory.lengthenMDS(base), access);
        }
        
        private SDIndex(@Mesa.CARD16 int value) {
            super(value);
        }
        private SDIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // ETT: POINTER TO EscTrapTable = yokwe.majuro.mesa.Constants.mETT;
    //
    public static final EscTrapTable ETT(MemoryAccess access) {
        return EscTrapTable.pointer(yokwe.majuro.mesa.Constants.mETT, access);
    }
    
    //
    // EscTrapTable: TYPE = ARRAY BYTE OF ControlLink;
    //
    public static final class EscTrapTable extends MemoryBase {
        public static final String NAME = "EscTrapTable";
        
        public static final int WORD_SIZE =  512;
        public static final int BIT_SIZE  = 8192;
        
        // Constructor
        public static final EscTrapTable longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new EscTrapTable(base, access);
        }
        public static final EscTrapTable pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new EscTrapTable(Memory.lengthenMDS(base), access);
        }
        
        private EscTrapTable(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final LONG_UNSPECIFIED get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(index);
            int longPointer = base + (LONG_UNSPECIFIED.WORD_SIZE * index);
            return LONG_UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    // IGNORE StateHandle: TYPE = LONG POINTER TO StateVector;
    
    //
    // StateWord: TYPE = RECORD[instByte (0:0..7): BYTE, stkPtr (0:8..15): BYTE];
    //
    public static final class StateWord extends MemoryData16 {
        public static final String NAME = "StateWord";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final StateWord value(@Mesa.CARD16 int value) {
            return new StateWord(value);
        }
        public static final StateWord value() {
            return new StateWord(0);
        }
        public static final StateWord longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new StateWord(base, access);
        }
        public static final StateWord pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new StateWord(Memory.lengthenMDS(base), access);
        }
        
        private StateWord(@Mesa.CARD16 int value) {
            super(value);
        }
        private StateWord(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // instByte (0:0..7):  BYTE
        // stkPtr   (0:8..15): BYTE
        
        private static final int INST_BYTE_MASK  = 0b1111_1111_0000_0000;
        private static final int INST_BYTE_SHIFT =                     8;
        private static final int STK_PTR_MASK    = 0b0000_0000_1111_1111;
        private static final int STK_PTR_SHIFT   =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is BYTE
        public final @Mesa.CARD8 int instByte() {
            return (value & INST_BYTE_MASK) >>> INST_BYTE_SHIFT;
        }
        public final StateWord instByte(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
            value = (value & ~INST_BYTE_MASK) | ((newValue << INST_BYTE_SHIFT) & INST_BYTE_MASK);
            return this;
        }
        
        // @Mesa.CARD8 is BYTE
        public final @Mesa.CARD8 int stkPtr() {
            return (value & STK_PTR_MASK) >>> STK_PTR_SHIFT;
        }
        public final StateWord stkPtr(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(newValue);
            value = (value & ~STK_PTR_MASK) | ((newValue << STK_PTR_SHIFT) & STK_PTR_MASK);
            return this;
        }
        
    }
    
    //
    // StackDepth: CARDINAL = yokwe.majuro.mesa.Constants.cSS;
    //
    public static final @Mesa.CARD16 int StackDepth = yokwe.majuro.mesa.Constants.cSS;
    
    //
    // StateVector: TYPE = RECORD[stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED, word (14:0..15): StateWord, frame (15:0..15): LocalFrameHandle, data (16): BLOCK];
    //
    public static final class StateVector extends MemoryBase {
        public static final String NAME = "StateVector";
        
        public static final int WORD_SIZE =  16;
        public static final int BIT_SIZE  = 256;
        
        // Constructor
        public static final StateVector longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new StateVector(base, access);
        }
        public static final StateVector pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new StateVector(Memory.lengthenMDS(base), access);
        }
        
        private StateVector(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // stack (0:0..223): ARRAY [0..StackDepth) OF UNSPECIFIED
        private static final int OFFSET_STACK = 0;
        private static final class StackIndex {
            private static final ContextSubrange context = new ContextSubrange("StateVector", 0, 13);
            private static final void checkValue(int value) {
                context.check(value);
            }
        }
        public final UNSPECIFIED stack(int index) {
            if (Debug.ENABLE_CHECK_VALUE) StackIndex.checkValue(index);
            int longPointer = base + OFFSET_STACK + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // word (14:0..15): StateWord
        private static final int OFFSET_WORD = 14;
        public StateWord word() {
            int longPointer = base + OFFSET_WORD;
            return StateWord.longPointer(longPointer, access);
        }
        // frame (15:0..15): LocalFrameHandle
        private static final int OFFSET_FRAME = 15;
        public BLOCK frame() {
            int pointer = Memory.read16(base + OFFSET_FRAME);
            return BLOCK.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int frameValue() {
            return Memory.read16(base + OFFSET_FRAME);
        }
        public final StateVector frameValue(@Mesa.SHORT_POINTER int newValue) {
            Memory.write16(base + OFFSET_FRAME, newValue);
            return this;
        }
        // data (16): BLOCK
        private static final int OFFSET_DATA = 16;
        public BLOCK data() {
            int longPointer = base + OFFSET_DATA;
            return BLOCK.longPointer(longPointer, access);
        }
    }
    
    //
    // TransferDescriptor: TYPE = RECORD[src (0:0..15): ShortControlLink, reserved (1:0..15): UNSPECIFIED, dst (2:0..31): ControlLink];
    //
    public static final class TransferDescriptor extends MemoryBase {
        public static final String NAME = "TransferDescriptor";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final TransferDescriptor longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new TransferDescriptor(base, access);
        }
        public static final TransferDescriptor pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new TransferDescriptor(Memory.lengthenMDS(base), access);
        }
        
        private TransferDescriptor(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // src (0:0..15): ShortControlLink
        private static final int OFFSET_SRC = 0;
        public UNSPECIFIED src() {
            int longPointer = base + OFFSET_SRC;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // reserved (1:0..15): UNSPECIFIED
        private static final int OFFSET_RESERVED = 1;
        public UNSPECIFIED reserved() {
            int longPointer = base + OFFSET_RESERVED;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // dst (2:0..31): ControlLink
        private static final int OFFSET_DST = 2;
        public LONG_UNSPECIFIED dst() {
            int longPointer = base + OFFSET_DST;
            return LONG_UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // PDA: LONG POINTER TO ProcessDataArea = yokwe.majuro.mesa.Constants.mPDA;
    //
    public static final ProcessDataArea PDA(MemoryAccess access) {
        return ProcessDataArea.longPointer(yokwe.majuro.mesa.Constants.mPDA, access);
    }
    
    //
    // ProcessDataArea: TYPE = RECORD[ready (0:0..15): Queue, count (1:0..15): CARDINAL, unused (2:0..15): UNSPECIFIED, available (3:0..79): ARRAY [0..4] OF UNSPECIFIED, state (8:0..127): StateAllocationTable, interrupt (16:0..511): InterruptVector, fault (48:0..255): FaultVector, block (0): ARRAY PsbIndex OF ProcessStateBlock];
    //
    public static final class ProcessDataArea extends MemoryBase {
        public static final String NAME = "ProcessDataArea";
        
        public static final int WORD_SIZE =   64;
        public static final int BIT_SIZE  = 1024;
        
        // Constructor
        public static final ProcessDataArea longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ProcessDataArea(base, access);
        }
        public static final ProcessDataArea pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ProcessDataArea(Memory.lengthenMDS(base), access);
        }
        
        private ProcessDataArea(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // ready (0:0..15): Queue
        private static final int OFFSET_READY = 0;
        public Queue ready() {
            int longPointer = base + OFFSET_READY;
            return Queue.longPointer(longPointer, access);
        }
        // count (1:0..15): CARDINAL
        private static final int OFFSET_COUNT = 1;
        public CARDINAL count() {
            int longPointer = base + OFFSET_COUNT;
            return CARDINAL.longPointer(longPointer, access);
        }
        // unused (2:0..15): UNSPECIFIED
        private static final int OFFSET_UNUSED = 2;
        public UNSPECIFIED unused() {
            int longPointer = base + OFFSET_UNUSED;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // available (3:0..79): ARRAY [0..4] OF UNSPECIFIED
        private static final int OFFSET_AVAILABLE = 3;
        private static final class AvailableIndex {
            private static final ContextSubrange context = new ContextSubrange("ProcessDataArea", 0, 4);
            private static final void checkValue(int value) {
                context.check(value);
            }
        }
        public final UNSPECIFIED available(int index) {
            if (Debug.ENABLE_CHECK_VALUE) AvailableIndex.checkValue(index);
            int longPointer = base + OFFSET_AVAILABLE + (UNSPECIFIED.WORD_SIZE * index);
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // state (8:0..127): StateAllocationTable
        private static final int OFFSET_STATE = 8;
        public StateAllocationTable state() {
            int longPointer = base + OFFSET_STATE;
            return StateAllocationTable.longPointer(longPointer, access);
        }
        // interrupt (16:0..511): InterruptVector
        private static final int OFFSET_INTERRUPT = 16;
        public InterruptVector interrupt() {
            int longPointer = base + OFFSET_INTERRUPT;
            return InterruptVector.longPointer(longPointer, access);
        }
        // fault (48:0..255): FaultVector
        private static final int OFFSET_FAULT = 48;
        public FaultVector fault() {
            int longPointer = base + OFFSET_FAULT;
            return FaultVector.longPointer(longPointer, access);
        }
        // block (0): ARRAY PsbIndex OF ProcessStateBlock
        private static final int OFFSET_BLOCK = 0;
        public final ProcessStateBlock block(int index) {
            if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(index);
            int longPointer = base + OFFSET_BLOCK + (ProcessStateBlock.WORD_SIZE * index);
            return ProcessStateBlock.longPointer(longPointer, access);
        }
    }
    
    //
    // PsbIndex: TYPE = [0..1024);
    //
    public static final class PsbIndex extends MemoryData16 {
        public static final String NAME = "PsbIndex";
        
        public static final int WORD_SIZE =    1;
        public static final int BIT_SIZE  =   10;
                                                 
        public static final int MIN_VALUE =    0;
        public static final int MAX_VALUE = 1023;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final PsbIndex value(@Mesa.CARD16 int value) {
            return new PsbIndex(value);
        }
        public static final PsbIndex value() {
            return new PsbIndex(0);
        }
        public static final PsbIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new PsbIndex(base, access);
        }
        public static final PsbIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new PsbIndex(Memory.lengthenMDS(base), access);
        }
        
        private PsbIndex(@Mesa.CARD16 int value) {
            super(value);
        }
        private PsbIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): Ticks, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, sticky (6:0..31): LONG UNSPECIFIED];
    //
    public static final class ProcessStateBlock extends MemoryBase {
        public static final String NAME = "ProcessStateBlock";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final ProcessStateBlock longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new ProcessStateBlock(base, access);
        }
        public static final ProcessStateBlock pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new ProcessStateBlock(Memory.lengthenMDS(base), access);
        }
        
        private ProcessStateBlock(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // link (0:0..15): PsbLink
        private static final int OFFSET_LINK = 0;
        public PsbLink link() {
            int longPointer = base + OFFSET_LINK;
            return PsbLink.longPointer(longPointer, access);
        }
        // flags (1:0..15): PsbFlags
        private static final int OFFSET_FLAGS = 1;
        public PsbFlags flags() {
            int longPointer = base + OFFSET_FLAGS;
            return PsbFlags.longPointer(longPointer, access);
        }
        // context (2:0..15): POINTER
        private static final int OFFSET_CONTEXT = 2;
        public POINTER context() {
            int longPointer = base + OFFSET_CONTEXT;
            return POINTER.longPointer(longPointer, access);
        }
        // timeout (3:0..15): Ticks
        private static final int OFFSET_TIMEOUT = 3;
        public CARDINAL timeout() {
            int longPointer = base + OFFSET_TIMEOUT;
            return CARDINAL.longPointer(longPointer, access);
        }
        // mds (4:0..15): CARDINAL
        private static final int OFFSET_MDS = 4;
        public CARDINAL mds() {
            int longPointer = base + OFFSET_MDS;
            return CARDINAL.longPointer(longPointer, access);
        }
        // available (5:0..15): UNSPECIFIED
        private static final int OFFSET_AVAILABLE = 5;
        public UNSPECIFIED available() {
            int longPointer = base + OFFSET_AVAILABLE;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
        // sticky (6:0..31): LONG UNSPECIFIED
        private static final int OFFSET_STICKY = 6;
        public LONG_UNSPECIFIED sticky() {
            int longPointer = base + OFFSET_STICKY;
            return LONG_UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    
    //
    // Priority: TYPE = [0..7];
    //
    public static final class Priority extends MemoryData16 {
        public static final String NAME = "Priority";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 3;
                                              
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 7;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final Priority value(@Mesa.CARD16 int value) {
            return new Priority(value);
        }
        public static final Priority value() {
            return new Priority(0);
        }
        public static final Priority longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Priority(base, access);
        }
        public static final Priority pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Priority(Memory.lengthenMDS(base), access);
        }
        
        private Priority(@Mesa.CARD16 int value) {
            super(value);
        }
        private Priority(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // PsbLink: TYPE = RECORD[priority (0:0..2): Priority, next (0:3..12): PsbIndex, failed (0:13..13): BOOLEAN, permanent (0:14..14): BOOLEAN, preempted (0:15..15): BOOLEAN];
    //
    public static final class PsbLink extends MemoryData16 {
        public static final String NAME = "PsbLink";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final PsbLink value(@Mesa.CARD16 int value) {
            return new PsbLink(value);
        }
        public static final PsbLink value() {
            return new PsbLink(0);
        }
        public static final PsbLink longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new PsbLink(base, access);
        }
        public static final PsbLink pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new PsbLink(Memory.lengthenMDS(base), access);
        }
        
        private PsbLink(@Mesa.CARD16 int value) {
            super(value);
        }
        private PsbLink(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // priority  (0:0..2):   Priority
        // next      (0:3..12):  PsbIndex
        // failed    (0:13..13): BOOLEAN
        // permanent (0:14..14): BOOLEAN
        // preempted (0:15..15): BOOLEAN
        
        private static final int PRIORITY_MASK   = 0b1110_0000_0000_0000;
        private static final int PRIORITY_SHIFT  =                    13;
        private static final int NEXT_MASK       = 0b0001_1111_1111_1000;
        private static final int NEXT_SHIFT      =                     3;
        private static final int FAILED_MASK     = 0b0000_0000_0000_0100;
        private static final int FAILED_SHIFT    =                     2;
        private static final int PERMANENT_MASK  = 0b0000_0000_0000_0010;
        private static final int PERMANENT_SHIFT =                     1;
        private static final int PREEMPTED_MASK  = 0b0000_0000_0000_0001;
        private static final int PREEMPTED_SHIFT =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD8 is Priority
        public final @Mesa.CARD8 int priority() {
            return (value & PRIORITY_MASK) >>> PRIORITY_SHIFT;
        }
        public final PsbLink priority(@Mesa.CARD8 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Priority.checkValue(newValue);
            value = (value & ~PRIORITY_MASK) | ((newValue << PRIORITY_SHIFT) & PRIORITY_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is PsbIndex
        public final @Mesa.CARD16 int next() {
            return (value & NEXT_MASK) >>> NEXT_SHIFT;
        }
        public final PsbLink next(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(newValue);
            value = (value & ~NEXT_MASK) | ((newValue << NEXT_SHIFT) & NEXT_MASK);
            return this;
        }
        
        public final boolean failed() {
            return ((value & FAILED_MASK) >>> FAILED_SHIFT) != 0;
        }
        public final PsbLink failed(boolean newValue) {
            value = Types.toCARD16((value & ~FAILED_MASK) | (((newValue ? 1 : 0) << FAILED_SHIFT) & FAILED_MASK));
            return this;
        }
        
        public final boolean permanent() {
            return ((value & PERMANENT_MASK) >>> PERMANENT_SHIFT) != 0;
        }
        public final PsbLink permanent(boolean newValue) {
            value = Types.toCARD16((value & ~PERMANENT_MASK) | (((newValue ? 1 : 0) << PERMANENT_SHIFT) & PERMANENT_MASK));
            return this;
        }
        
        public final boolean preempted() {
            return ((value & PREEMPTED_MASK) >>> PREEMPTED_SHIFT) != 0;
        }
        public final PsbLink preempted(boolean newValue) {
            value = Types.toCARD16((value & ~PREEMPTED_MASK) | (((newValue ? 1 : 0) << PREEMPTED_SHIFT) & PREEMPTED_MASK));
            return this;
        }
        
    }
    
    //
    // PsbFlags: TYPE = RECORD[available (0:0..2): UNSPECIFIED, cleanup (0:3..12): PsbIndex, reserved (0:13..13): UNSPECIFIED, waiting (0:14..14): BOOLEAN, abort (0:15..15): BOOLEAN];
    //
    public static final class PsbFlags extends MemoryData16 {
        public static final String NAME = "PsbFlags";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final PsbFlags value(@Mesa.CARD16 int value) {
            return new PsbFlags(value);
        }
        public static final PsbFlags value() {
            return new PsbFlags(0);
        }
        public static final PsbFlags longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new PsbFlags(base, access);
        }
        public static final PsbFlags pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new PsbFlags(Memory.lengthenMDS(base), access);
        }
        
        private PsbFlags(@Mesa.CARD16 int value) {
            super(value);
        }
        private PsbFlags(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // available (0:0..2):   UNSPECIFIED
        // cleanup   (0:3..12):  PsbIndex
        // reserved  (0:13..13): UNSPECIFIED
        // waiting   (0:14..14): BOOLEAN
        // abort     (0:15..15): BOOLEAN
        
        private static final int AVAILABLE_MASK  = 0b1110_0000_0000_0000;
        private static final int AVAILABLE_SHIFT =                    13;
        private static final int CLEANUP_MASK    = 0b0001_1111_1111_1000;
        private static final int CLEANUP_SHIFT   =                     3;
        private static final int RESERVED_MASK   = 0b0000_0000_0000_0100;
        private static final int RESERVED_SHIFT  =                     2;
        private static final int WAITING_MASK    = 0b0000_0000_0000_0010;
        private static final int WAITING_SHIFT   =                     1;
        private static final int ABORT_MASK      = 0b0000_0000_0000_0001;
        private static final int ABORT_SHIFT     =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int available() {
            return (value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT;
        }
        public final PsbFlags available(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is PsbIndex
        public final @Mesa.CARD16 int cleanup() {
            return (value & CLEANUP_MASK) >>> CLEANUP_SHIFT;
        }
        public final PsbFlags cleanup(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(newValue);
            value = (value & ~CLEANUP_MASK) | ((newValue << CLEANUP_SHIFT) & CLEANUP_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int reserved() {
            return (value & RESERVED_MASK) >>> RESERVED_SHIFT;
        }
        public final PsbFlags reserved(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
            return this;
        }
        
        public final boolean waiting() {
            return ((value & WAITING_MASK) >>> WAITING_SHIFT) != 0;
        }
        public final PsbFlags waiting(boolean newValue) {
            value = Types.toCARD16((value & ~WAITING_MASK) | (((newValue ? 1 : 0) << WAITING_SHIFT) & WAITING_MASK));
            return this;
        }
        
        public final boolean abort() {
            return ((value & ABORT_MASK) >>> ABORT_SHIFT) != 0;
        }
        public final PsbFlags abort(boolean newValue) {
            value = Types.toCARD16((value & ~ABORT_MASK) | (((newValue ? 1 : 0) << ABORT_SHIFT) & ABORT_MASK));
            return this;
        }
        
    }
    
    //
    // Monitor: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..14): UNSPECIFIED, locked (0:15..15): BOOLEAN];
    //
    public static final class Monitor extends MemoryData16 {
        public static final String NAME = "Monitor";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final Monitor value(@Mesa.CARD16 int value) {
            return new Monitor(value);
        }
        public static final Monitor value() {
            return new Monitor(0);
        }
        public static final Monitor longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Monitor(base, access);
        }
        public static final Monitor pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Monitor(Memory.lengthenMDS(base), access);
        }
        
        private Monitor(@Mesa.CARD16 int value) {
            super(value);
        }
        private Monitor(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // reserved  (0:0..2):   UNSPECIFIED
        // tail      (0:3..12):  PsbIndex
        // available (0:13..14): UNSPECIFIED
        // locked    (0:15..15): BOOLEAN
        
        private static final int RESERVED_MASK   = 0b1110_0000_0000_0000;
        private static final int RESERVED_SHIFT  =                    13;
        private static final int TAIL_MASK       = 0b0001_1111_1111_1000;
        private static final int TAIL_SHIFT      =                     3;
        private static final int AVAILABLE_MASK  = 0b0000_0000_0000_0110;
        private static final int AVAILABLE_SHIFT =                     1;
        private static final int LOCKED_MASK     = 0b0000_0000_0000_0001;
        private static final int LOCKED_SHIFT    =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int reserved() {
            return (value & RESERVED_MASK) >>> RESERVED_SHIFT;
        }
        public final Monitor reserved(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is PsbIndex
        public final @Mesa.CARD16 int tail() {
            return (value & TAIL_MASK) >>> TAIL_SHIFT;
        }
        public final Monitor tail(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(newValue);
            value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int available() {
            return (value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT;
        }
        public final Monitor available(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
            return this;
        }
        
        public final boolean locked() {
            return ((value & LOCKED_MASK) >>> LOCKED_SHIFT) != 0;
        }
        public final Monitor locked(boolean newValue) {
            value = Types.toCARD16((value & ~LOCKED_MASK) | (((newValue ? 1 : 0) << LOCKED_SHIFT) & LOCKED_MASK));
            return this;
        }
        
    }
    
    //
    // Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOLEAN, wakeup (0:15..15): BOOLEAN];
    //
    public static final class Condition extends MemoryData16 {
        public static final String NAME = "Condition";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final Condition value(@Mesa.CARD16 int value) {
            return new Condition(value);
        }
        public static final Condition value() {
            return new Condition(0);
        }
        public static final Condition longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Condition(base, access);
        }
        public static final Condition pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Condition(Memory.lengthenMDS(base), access);
        }
        
        private Condition(@Mesa.CARD16 int value) {
            super(value);
        }
        private Condition(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // reserved  (0:0..2):   UNSPECIFIED
        // tail      (0:3..12):  PsbIndex
        // available (0:13..13): UNSPECIFIED
        // abortable (0:14..14): BOOLEAN
        // wakeup    (0:15..15): BOOLEAN
        
        private static final int RESERVED_MASK   = 0b1110_0000_0000_0000;
        private static final int RESERVED_SHIFT  =                    13;
        private static final int TAIL_MASK       = 0b0001_1111_1111_1000;
        private static final int TAIL_SHIFT      =                     3;
        private static final int AVAILABLE_MASK  = 0b0000_0000_0000_0100;
        private static final int AVAILABLE_SHIFT =                     2;
        private static final int ABORTABLE_MASK  = 0b0000_0000_0000_0010;
        private static final int ABORTABLE_SHIFT =                     1;
        private static final int WAKEUP_MASK     = 0b0000_0000_0000_0001;
        private static final int WAKEUP_SHIFT    =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int reserved() {
            return (value & RESERVED_MASK) >>> RESERVED_SHIFT;
        }
        public final Condition reserved(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is PsbIndex
        public final @Mesa.CARD16 int tail() {
            return (value & TAIL_MASK) >>> TAIL_SHIFT;
        }
        public final Condition tail(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(newValue);
            value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int available() {
            return (value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT;
        }
        public final Condition available(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
            return this;
        }
        
        public final boolean abortable() {
            return ((value & ABORTABLE_MASK) >>> ABORTABLE_SHIFT) != 0;
        }
        public final Condition abortable(boolean newValue) {
            value = Types.toCARD16((value & ~ABORTABLE_MASK) | (((newValue ? 1 : 0) << ABORTABLE_SHIFT) & ABORTABLE_MASK));
            return this;
        }
        
        public final boolean wakeup() {
            return ((value & WAKEUP_MASK) >>> WAKEUP_SHIFT) != 0;
        }
        public final Condition wakeup(boolean newValue) {
            value = Types.toCARD16((value & ~WAKEUP_MASK) | (((newValue ? 1 : 0) << WAKEUP_SHIFT) & WAKEUP_MASK));
            return this;
        }
        
    }
    
    //
    // Queue: TYPE = RECORD[reserved1 (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, reserved2 (0:13..15): UNSPECIFIED];
    //
    public static final class Queue extends MemoryData16 {
        public static final String NAME = "Queue";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  = 16;
        
        // Constructor
        public static final Queue value(@Mesa.CARD16 int value) {
            return new Queue(value);
        }
        public static final Queue value() {
            return new Queue(0);
        }
        public static final Queue longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Queue(base, access);
        }
        public static final Queue pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Queue(Memory.lengthenMDS(base), access);
        }
        
        private Queue(@Mesa.CARD16 int value) {
            super(value);
        }
        private Queue(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Bit Field
        //
        
        // reserved1 (0:0..2):   UNSPECIFIED
        // tail      (0:3..12):  PsbIndex
        // reserved2 (0:13..15): UNSPECIFIED
        
        private static final int RESERVED_1_MASK  = 0b1110_0000_0000_0000;
        private static final int RESERVED_1_SHIFT =                    13;
        private static final int TAIL_MASK        = 0b0001_1111_1111_1000;
        private static final int TAIL_SHIFT       =                     3;
        private static final int RESERVED_2_MASK  = 0b0000_0000_0000_0111;
        private static final int RESERVED_2_SHIFT =                     0;
        
        //
        // Bit Field Access Methods
        //
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int reserved1() {
            return (value & RESERVED_1_MASK) >>> RESERVED_1_SHIFT;
        }
        public final Queue reserved1(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~RESERVED_1_MASK) | ((newValue << RESERVED_1_SHIFT) & RESERVED_1_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is PsbIndex
        public final @Mesa.CARD16 int tail() {
            return (value & TAIL_MASK) >>> TAIL_SHIFT;
        }
        public final Queue tail(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) PsbIndex.checkValue(newValue);
            value = (value & ~TAIL_MASK) | ((newValue << TAIL_SHIFT) & TAIL_MASK);
            return this;
        }
        
        // @Mesa.CARD16 is UNSPECIFIED
        public final @Mesa.CARD16 int reserved2() {
            return (value & RESERVED_2_MASK) >>> RESERVED_2_SHIFT;
        }
        public final Queue reserved2(@Mesa.CARD16 int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) UNSPECIFIED.checkValue(newValue);
            value = (value & ~RESERVED_2_MASK) | ((newValue << RESERVED_2_SHIFT) & RESERVED_2_MASK);
            return this;
        }
        
    }
    
    //
    // StateAllocationTable: TYPE = ARRAY Priority OF POINTER TO StateVector;
    //
    public static final class StateAllocationTable extends MemoryBase {
        public static final String NAME = "StateAllocationTable";
        
        public static final int WORD_SIZE =   8;
        public static final int BIT_SIZE  = 128;
        
        // Constructor
        public static final StateAllocationTable longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new StateAllocationTable(base, access);
        }
        public static final StateAllocationTable pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new StateAllocationTable(Memory.lengthenMDS(base), access);
        }
        
        private StateAllocationTable(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final StateVector get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Priority.checkValue(index);
            int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
            return StateVector.pointer(pointer, access);
        }
        public final @Mesa.SHORT_POINTER int getValue(int index) {
            if (Debug.ENABLE_CHECK_VALUE) Priority.checkValue(index);
            return Memory.read16(base + (POINTER.WORD_SIZE * index));
        }
        public final StateAllocationTable getValue(int index, @Mesa.SHORT_POINTER int newValue) {
            if (Debug.ENABLE_CHECK_VALUE) Priority.checkValue(index);
            Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
            return this;
        }
    }
    
    //
    // FaultVector: TYPE = ARRAY FaultIndex OF FaultQueue;
    //
    public static final class FaultVector extends MemoryBase {
        public static final String NAME = "FaultVector";
        
        public static final int WORD_SIZE =  16;
        public static final int BIT_SIZE  = 256;
        
        // Constructor
        public static final FaultVector longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new FaultVector(base, access);
        }
        public static final FaultVector pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new FaultVector(Memory.lengthenMDS(base), access);
        }
        
        private FaultVector(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final FaultQueue get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) FaultIndex.checkValue(index);
            int longPointer = base + (FaultQueue.WORD_SIZE * index);
            return FaultQueue.longPointer(longPointer, access);
        }
    }
    
    //
    // FaultIndex: TYPE = [0..8);
    //
    public static final class FaultIndex extends MemoryData16 {
        public static final String NAME = "FaultIndex";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 3;
                                              
        public static final int MIN_VALUE = 0;
        public static final int MAX_VALUE = 7;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final FaultIndex value(@Mesa.CARD16 int value) {
            return new FaultIndex(value);
        }
        public static final FaultIndex value() {
            return new FaultIndex(0);
        }
        public static final FaultIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new FaultIndex(base, access);
        }
        public static final FaultIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new FaultIndex(Memory.lengthenMDS(base), access);
        }
        
        private FaultIndex(@Mesa.CARD16 int value) {
            super(value);
        }
        private FaultIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // FaultQueue: TYPE = RECORD[queue (0:0..15): Queue, condition (1:0..15): Condition];
    //
    public static final class FaultQueue extends MemoryBase {
        public static final String NAME = "FaultQueue";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final FaultQueue longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new FaultQueue(base, access);
        }
        public static final FaultQueue pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new FaultQueue(Memory.lengthenMDS(base), access);
        }
        
        private FaultQueue(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // queue (0:0..15): Queue
        private static final int OFFSET_QUEUE = 0;
        public Queue queue() {
            int longPointer = base + OFFSET_QUEUE;
            return Queue.longPointer(longPointer, access);
        }
        // condition (1:0..15): Condition
        private static final int OFFSET_CONDITION = 1;
        public Condition condition() {
            int longPointer = base + OFFSET_CONDITION;
            return Condition.longPointer(longPointer, access);
        }
    }
    
    //
    // InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
    //
    public static final class InterruptVector extends MemoryBase {
        public static final String NAME = "InterruptVector";
        
        public static final int WORD_SIZE =  32;
        public static final int BIT_SIZE  = 512;
        
        // Constructor
        public static final InterruptVector longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new InterruptVector(base, access);
        }
        public static final InterruptVector pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new InterruptVector(Memory.lengthenMDS(base), access);
        }
        
        private InterruptVector(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        public final InterruptItem get(int index) {
            if (Debug.ENABLE_CHECK_VALUE) InterruptLevel.checkValue(index);
            int longPointer = base + (InterruptItem.WORD_SIZE * index);
            return InterruptItem.longPointer(longPointer, access);
        }
    }
    
    //
    // InterruptLevel: TYPE = [0..16);
    //
    public static final class InterruptLevel extends MemoryData16 {
        public static final String NAME = "InterruptLevel";
        
        public static final int WORD_SIZE =  1;
        public static final int BIT_SIZE  =  4;
                                               
        public static final int MIN_VALUE =  0;
        public static final int MAX_VALUE = 15;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final InterruptLevel value(@Mesa.CARD16 int value) {
            return new InterruptLevel(value);
        }
        public static final InterruptLevel value() {
            return new InterruptLevel(0);
        }
        public static final InterruptLevel longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new InterruptLevel(base, access);
        }
        public static final InterruptLevel pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new InterruptLevel(Memory.lengthenMDS(base), access);
        }
        
        private InterruptLevel(@Mesa.CARD16 int value) {
            super(value);
        }
        private InterruptLevel(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED];
    //
    public static final class InterruptItem extends MemoryBase {
        public static final String NAME = "InterruptItem";
        
        public static final int WORD_SIZE =  2;
        public static final int BIT_SIZE  = 32;
        
        // Constructor
        public static final InterruptItem longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new InterruptItem(base, access);
        }
        public static final InterruptItem pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new InterruptItem(Memory.lengthenMDS(base), access);
        }
        
        private InterruptItem(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // condition (0:0..15): Condition
        private static final int OFFSET_CONDITION = 0;
        public Condition condition() {
            int longPointer = base + OFFSET_CONDITION;
            return Condition.longPointer(longPointer, access);
        }
        // available (1:0..15): UNSPECIFIED
        private static final int OFFSET_AVAILABLE = 1;
        public UNSPECIFIED available() {
            int longPointer = base + OFFSET_AVAILABLE;
            return UNSPECIFIED.longPointer(longPointer, access);
        }
    }
    // IGNORE Ticks: TYPE = CARDINAL;
}

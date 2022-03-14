package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

public final class Space {
    
    //
    // Usage: TYPE = [0..2048);
    //
    public static final class Usage extends MemoryData16 {
        public static final String NAME = "Usage";
        
        public static final int WORD_SIZE =    1;
        public static final int BIT_SIZE  =   11;
                                                 
        public static final int MIN_VALUE =    0;
        public static final int MAX_VALUE = 2047;
        
        private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final Usage value(@Mesa.CARD16 int value) {
            return new Usage(value);
        }
        public static final Usage value() {
            return new Usage(0);
        }
        public static final Usage longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Usage(base, access);
        }
        public static final Usage pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Usage(Memory.lengthenMDS(base), access);
        }
        
        private Usage(@Mesa.CARD16 int value) {
            super(value);
        }
        private Usage(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
    }
    
    //
    // Class: TYPE = {unknown(0), code(1), globalFrame(2), localFrame(3), zone(4), file(5), data(6), spareA(7), spareB(8), pilotResident(31)};
    //
    public static final class Class extends MemoryData16 {
        public static final String NAME = "Class";
        
        public static final int WORD_SIZE = 1;
        public static final int BIT_SIZE  = 5;
        
        //
        // Enum Value Constants
        //
        public static final @Mesa.ENUM int UNKNOWN         = 0;
        public static final @Mesa.ENUM int CODE            = 1;
        public static final @Mesa.ENUM int GLOBAL_FRAME    = 2;
        public static final @Mesa.ENUM int LOCAL_FRAME     = 3;
        public static final @Mesa.ENUM int ZONE            = 4;
        public static final @Mesa.ENUM int FILE            = 5;
        public static final @Mesa.ENUM int DATA            = 6;
        public static final @Mesa.ENUM int SPARE_A         = 7;
        public static final @Mesa.ENUM int SPARE_B         = 8;
        public static final @Mesa.ENUM int PILOT_RESIDENT = 31;
        
        private static final int[] values = {
            UNKNOWN, CODE, GLOBAL_FRAME, LOCAL_FRAME, ZONE, FILE, DATA, SPARE_A, SPARE_B, PILOT_RESIDENT
        };
        private static final String[] names = {
            "UNKNOWN", "CODE", "GLOBAL_FRAME", "LOCAL_FRAME", "ZONE", "FILE", "DATA", "SPARE_A", "SPARE_B", "PILOT_RESIDENT"
        };
        private static final ContextEnum context = new ContextEnum(NAME, values, names);
        
        public static final void checkValue(int value) {
            if (Debug.ENABLE_CHECK_VALUE) context.check(value);
        }
        
        // Constructor
        public static final Class value(@Mesa.CARD16 int value) {
            return new Class(value);
        }
        public static final Class value() {
            return new Class(0);
        }
        public static final Class longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Class(base, access);
        }
        public static final Class pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Class(Memory.lengthenMDS(base), access);
        }
        
        private Class(@Mesa.CARD16 int value) {
            super(value);
        }
        private Class(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        public final String toString(int value) {
            return context.toString(value);
        }
    }
}

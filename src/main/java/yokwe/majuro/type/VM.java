package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.type.PrincOps.LONG_CARDINAL;

public final class VM {
    
    //
    // VM.NULL_PAGE: LONG_CARDINAL = 4294967295;
    //
    public static final @Mesa.CARD32 int NULL_PAGE = 0xFFFF_FFFF;
    // IGNORE PageCount: TYPE = Environment.PageCount;
    // IGNORE PageOffset: TYPE = Environment.PageOffset;
    
    //
    // Interval: TYPE = RECORD[page (0:0..31): Environment.PageNumber, count (2:0..31): PageCount];
    //
    public static final class Interval extends MemoryBase {
        public static final String NAME = "Interval";
        
        public static final int WORD_SIZE =  4;
        public static final int BIT_SIZE  = 64;
        
        // Constructor
        public static final Interval longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Interval(base, access);
        }
        public static final Interval pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Interval(Memory.lengthenMDS(base), access);
        }
        
        private Interval(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        
        //
        // Access to Field of Record
        //
        // page (0:0..31): Environment.PageNumber
        private static final int OFFSET_PAGE = 0;
        public LONG_CARDINAL page() {
            int longPointer = base + OFFSET_PAGE;
            return LONG_CARDINAL.longPointer(longPointer, access);
        }
        // count (2:0..31): PageCount
        private static final int OFFSET_COUNT = 2;
        public LONG_CARDINAL count() {
            int longPointer = base + OFFSET_COUNT;
            return LONG_CARDINAL.longPointer(longPointer, access);
        }
    }
}

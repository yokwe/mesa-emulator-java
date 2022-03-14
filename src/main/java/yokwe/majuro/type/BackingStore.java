package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.type.PrincOps.UNSPECIFIED;

public final class BackingStore {
    
    //
    // Data: TYPE = ARRAY [0..7) OF UNSPECIFIED;
    //
    public static final class Data extends MemoryBase {
        public static final String NAME = "Data";
        
        public static final int WORD_SIZE =   7;
        public static final int BIT_SIZE  = 112;
        
        // Constructor
        public static final Data longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            return new Data(base, access);
        }
        public static final Data pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
            return new Data(Memory.lengthenMDS(base), access);
        }
        
        private Data(@Mesa.LONG_POINTER int base, MemoryAccess access) {
            super(base, access);
        }
        //
        // Access to Element of Array
        //
        private static final class ArrayIndex {
            private static final ContextSubrange context = new ContextSubrange(NAME, 0, 6);
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
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
public final class BLOCK extends MemoryBase {
    public static final String NAME = "BLOCK";
    
    public static final int WORD_SIZE = 0;
    public static final int BIT_SIZE  = 0;
    
    //
    // Constructor
    //
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

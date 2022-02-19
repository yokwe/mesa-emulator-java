package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArraySubOpen: TYPE = ARRAY [0..0) OF UNSPECIFIED;
public final class ArraySubOpen extends MemoryBase {
    public static final String NAME = "ArraySubOpen";
    
    public static final int WORD_SIZE = 0;
    public static final int BIT_SIZE  = 0;
    
    //
    // Constructor
    //
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

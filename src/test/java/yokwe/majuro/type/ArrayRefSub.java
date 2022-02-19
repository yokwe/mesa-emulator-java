package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSub: TYPE = ARRAY Sub OF UNSPECIFIED;
public final class ArrayRefSub extends MemoryBase {
    public static final String NAME = "ArrayRefSub";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
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

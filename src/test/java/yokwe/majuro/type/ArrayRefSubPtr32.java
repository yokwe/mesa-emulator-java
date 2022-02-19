package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr32: TYPE = ARRAY Sub OF LONG POINTER;
public final class ArrayRefSubPtr32 extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr32";
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
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
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubBit32: TYPE = ARRAY Sub OF BitField32;
public final class ArrayRefSubBit32 extends MemoryBase {
    public static final String NAME = "ArrayRefSubBit32";
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
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

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubBit16: TYPE = ARRAY Sub OF BitField16;
public final class ArrayRefSubBit16 extends MemoryBase {
    public static final String NAME = "ArrayRefSubBit16";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
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

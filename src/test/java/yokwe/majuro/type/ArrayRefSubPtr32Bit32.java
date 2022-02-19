package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr32Bit32: TYPE = ARRAY Sub OF LONG POINTER TO BitField32;
public final class ArrayRefSubPtr32Bit32 extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr32Bit32";
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr32Bit32 longPointer(@Mesa.POINTER int base) {
        return new ArrayRefSubPtr32Bit32(base);
    }
    public static final ArrayRefSubPtr32Bit32 pointer(@Mesa.SHORT_POINTER int base) {
        return new ArrayRefSubPtr32Bit32(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr32Bit32(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final BitField32 get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        return BitField32.longPointer(longPointer, access);
    }
}

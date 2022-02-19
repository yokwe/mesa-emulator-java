package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16Bit32: TYPE = ARRAY Sub OF POINTER TO BitField32;
public final class ArrayRefSubPtr16Bit32 extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr16Bit32";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Bit32 longPointer(@Mesa.POINTER int base) {
        return new ArrayRefSubPtr16Bit32(base);
    }
    public static final ArrayRefSubPtr16Bit32 pointer(@Mesa.SHORT_POINTER int base) {
        return new ArrayRefSubPtr16Bit32(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Bit32(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final BitField32 get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return BitField32.pointer(pointer, access);
    }
}

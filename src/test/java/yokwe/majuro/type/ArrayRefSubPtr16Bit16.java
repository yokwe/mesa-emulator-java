package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16Bit16: TYPE = ARRAY Sub OF POINTER TO BitField16;
public final class ArrayRefSubPtr16Bit16 extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr16Bit16";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Bit16 longPointer(@Mesa.LONG_POINTER int base) {
        return new ArrayRefSubPtr16Bit16(base);
    }
    public static final ArrayRefSubPtr16Bit16 pointer(@Mesa.SHORT_POINTER int base) {
        return new ArrayRefSubPtr16Bit16(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Bit16(@Mesa.LONG_POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final BitField16 get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return BitField16.pointer(pointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16Enum: TYPE = ARRAY Sub OF POINTER TO Enum;
public final class ArrayRefSubPtr16Enum extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr16Enum";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr16Enum(base, access);
    }
    public static final ArrayRefSubPtr16Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr16Enum(Memory.lengthenMDS(base), access);
    }
    
    private ArrayRefSubPtr16Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final Enum get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return Enum.pointer(pointer, access);
    }
}

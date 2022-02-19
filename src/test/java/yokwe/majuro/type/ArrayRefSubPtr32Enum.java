package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr32Enum: TYPE = ARRAY Sub OF LONG POINTER TO Enum;
public final class ArrayRefSubPtr32Enum extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr32Enum";
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr32Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr32Enum(base, access);
    }
    public static final ArrayRefSubPtr32Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr32Enum(Memory.lengthenMDS(base), access);
    }
    
    private ArrayRefSubPtr32Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final Enum get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        return Enum.longPointer(longPointer, access);
    }
}

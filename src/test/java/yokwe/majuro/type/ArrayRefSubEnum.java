package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubEnum: TYPE = ARRAY Sub OF Enum;
public final class ArrayRefSubEnum extends MemoryBase {
    public static final String NAME = "ArrayRefSubEnum";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubEnum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubEnum(base, access);
    }
    public static final ArrayRefSubEnum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubEnum(Memory.lengthenMDS(base), access);
    }
    
    private ArrayRefSubEnum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final Enum get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + (Enum.WORD_SIZE * index);
        return Enum.longPointer(longPointer, access);
    }
}

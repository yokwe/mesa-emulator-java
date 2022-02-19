package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefEnum: TYPE = ARRAY Enum OF UNSPECIFIED;
public final class ArrayRefEnum extends MemoryBase {
    public static final String NAME = "ArrayRefEnum";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefEnum longPointer(@Mesa.POINTER int base) {
        return new ArrayRefEnum(base);
    }
    public static final ArrayRefEnum pointer(@Mesa.SHORT_POINTER int base) {
        return new ArrayRefEnum(Memory.lengthenMDS(base));
    }
    
    private ArrayRefEnum(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final UNSPECIFIED get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Enum.checkValue(index);
        int longPointer = base + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

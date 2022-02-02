package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefEnum: TYPE = ARRAY Enum OF UNSPECIFIED;
public final class ArrayRefEnum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) Enum.checkValue(value);
    }
    //
    // Constructor
    //
    public static final ArrayRefEnum longPointer(int base) {
        return new ArrayRefEnum(base);
    }
    public static final ArrayRefEnum pointer(char base) {
        return new ArrayRefEnum(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefEnum(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final UNSPECIFIED get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return UNSPECIFIED.longPointer(base + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}

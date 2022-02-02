package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr32Enum: TYPE = ARRAY Sub OF LONG POINTER TO Enum;
public final class ArrayRefSubPtr32Enum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(value);
    }
    //
    // Constructor
    //
    public static final ArrayRefSubPtr32Enum longPointer(int base) {
        return new ArrayRefSubPtr32Enum(base);
    }
    public static final ArrayRefSubPtr32Enum pointer(char base) {
        return new ArrayRefSubPtr32Enum(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr32Enum(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Enum get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return Enum.longPointer(base + (LONG_POINTER.WORD_SIZE * index), access);
    }
}

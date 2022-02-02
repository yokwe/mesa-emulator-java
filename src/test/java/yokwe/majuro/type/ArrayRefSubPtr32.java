package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr32: TYPE = ARRAY Sub OF LONG POINTER;
public final class ArrayRefSubPtr32 extends MemoryBase {
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
    public static final ArrayRefSubPtr32 longPointer(int base) {
        return new ArrayRefSubPtr32(base);
    }
    public static final ArrayRefSubPtr32 pointer(char base) {
        return new ArrayRefSubPtr32(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr32(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final LONG_POINTER get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return LONG_POINTER.longPointer(base + (LONG_POINTER.WORD_SIZE * index));
    }
}

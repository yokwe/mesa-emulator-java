package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr32Sub: TYPE = ARRAY Sub OF LONG POINTER TO Sub;
public final class ArrayRefSubPtr32Sub extends MemoryBase {
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
    public static final ArrayRefSubPtr32Sub longPointer(int base) {
        return new ArrayRefSubPtr32Sub(base);
    }
    public static final ArrayRefSubPtr32Sub pointer(char base) {
        return new ArrayRefSubPtr32Sub(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr32Sub(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Sub get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return Sub.longPointer(base + (LONG_POINTER.WORD_SIZE * index), access);
    }
}

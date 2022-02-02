package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubSub: TYPE = ARRAY Sub OF Sub;
public final class ArrayRefSubSub extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(value);
    }
    //
    // Constructor
    //
    public static final ArrayRefSubSub longPointer(int base) {
        return new ArrayRefSubSub(base);
    }
    public static final ArrayRefSubSub pointer(char base) {
        return new ArrayRefSubSub(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubSub(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Sub get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return Sub.longPointer(base + (Sub.WORD_SIZE * index), access);
    }
}

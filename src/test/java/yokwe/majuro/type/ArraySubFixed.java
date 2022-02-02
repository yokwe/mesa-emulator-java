package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArraySubFixed: TYPE = ARRAY [0..4) OF UNSPECIFIED;
public final class ArraySubFixed extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Check range of index
    //
    private static final ContextSubrange contextIndex = new ContextSubrange(NAME + "#index", 0, 3);
    public static final void checkIndex(long value) {
        if (Debug.ENABLE_CHECK_VALUE) contextIndex.check(value);
    }
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) contextIndex.check(Integer.toUnsignedLong(value));
    }
    //
    // Constructor
    //
    public static final ArraySubFixed longPointer(int base) {
        return new ArraySubFixed(base);
    }
    public static final ArraySubFixed pointer(char base) {
        return new ArraySubFixed(Mesa.lengthenMDS(base));
    }
    
    private ArraySubFixed(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final UNSPECIFIED get(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}

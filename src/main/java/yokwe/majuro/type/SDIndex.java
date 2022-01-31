package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// SDIndex: TYPE = [0..256);
public final class SDIndex extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int  WORD_SIZE  =    1;
    public static final int  BIT_SIZE   =    8;
                                               
    public static final long MIN_VALUE  =    0;
    public static final long MAX_VALUE  = 0xFF;
    public static final long SIZE_VALUE =  256;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    public static void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(Integer.toUnsignedLong(value));
    }
    
    //
    // Constructor
    //
    public static final SDIndex value(char value) {
        return new SDIndex(value);
    }
    public static final SDIndex longPointer(int base, MemoryAccess access) {
        return new SDIndex(base, access);
    }
    public static final SDIndex pointer(char base, MemoryAccess access) {
        return new SDIndex(Mesa.lengthenMDS(base), access);
    }
    
    private SDIndex(char value) {
        super(value);
    }
    private SDIndex(int base, MemoryAccess access) {
        super(base, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// Sub: TYPE = [0..4);
public final class Sub extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int  WORD_SIZE  = 1;
    public static final int  BIT_SIZE   = 2;
                                            
    public static final long MIN_VALUE  = 0;
    public static final long MAX_VALUE  = 3;
    public static final long SIZE_VALUE = 4;
    
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
    public static final Sub value(char value) {
        return new Sub(value);
    }
    public static final Sub longPointer(int base, MemoryAccess access) {
        return new Sub(base, access);
    }
    public static final Sub pointer(char base, MemoryAccess access) {
        return new Sub(Mesa.lengthenMDS(base), access);
    }
    
    private Sub(char value) {
        super(value);
    }
    private Sub(int base, MemoryAccess access) {
        super(base, access);
    }
}
package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// INTEGER: TYPE = [-32768..32767];
public final class INTEGER extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int  WORD_SIZE  =               1;
    public static final int  BIT_SIZE   =              16;
                                                          
    public static final long MIN_VALUE  = Short.MIN_VALUE;
    public static final long MAX_VALUE  = Short.MAX_VALUE;
    public static final long SIZE_VALUE =           65536;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(long value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final INTEGER value(char value) {
        return new INTEGER(value);
    }
    public static final INTEGER longPointer(int base, MemoryAccess access) {
        return new INTEGER(base, access);
    }
    public static final INTEGER pointer(char base, MemoryAccess access) {
        return new INTEGER(Mesa.lengthenMDS(base), access);
    }
    
    private INTEGER(char value) {
        super(value);
    }
    private INTEGER(int base, MemoryAccess access) {
        super(base, access);
    }
}

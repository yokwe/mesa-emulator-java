package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// PsbIndex: TYPE = [0..1024);
public class PsbIndex extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int  WORD_SIZE  =    1;
    public static final int  BIT_SIZE   =   10;
                                               
    public static final long MIN_VALUE  =    0;
    public static final long MAX_VALUE  = 1023;
    public static final long SIZE_VALUE = 1024;
    
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
    public PsbIndex(char value) {
        super(value);
    }
    public PsbIndex(int base, MemoryAccess access) {
        super(base, access);
    }
}

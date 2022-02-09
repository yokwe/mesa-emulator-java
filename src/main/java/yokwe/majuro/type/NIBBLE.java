package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// NIBBLE: TYPE = [0..16);
public final class NIBBLE extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int  WORD_SIZE =  1;
    public static final int  BIT_SIZE  =  4;
                                            
    public static final long MIN_VALUE =  0;
    public static final long MAX_VALUE = 15;
    
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
    public static final NIBBLE value(char value) {
        return new NIBBLE(value);
    }
    public static final NIBBLE longPointer(int base, MemoryAccess access) {
        return new NIBBLE(base, access);
    }
    public static final NIBBLE pointer(char base, MemoryAccess access) {
        return new NIBBLE(Mesa.lengthenMDS(base), access);
    }
    
    private NIBBLE(char value) {
        super(value);
    }
    private NIBBLE(int base, MemoryAccess access) {
        super(base, access);
    }
}

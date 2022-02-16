package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// BIT8: TYPE = [0..256);
public final class BIT8 extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =    1;
    public static final int BIT_SIZE  =    8;
                                             
    public static final int MIN_VALUE =    0;
    public static final int MAX_VALUE = 0xFF;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final BIT8 value(char value) {
        return new BIT8(value);
    }
    public static final BIT8 longPointer(int base, MemoryAccess access) {
        return new BIT8(base, access);
    }
    public static final BIT8 pointer(char base, MemoryAccess access) {
        return new BIT8(Memory.lengthenMDS(base), access);
    }
    
    private BIT8(char value) {
        super(value);
    }
    private BIT8(int base, MemoryAccess access) {
        super(base, access);
    }
}

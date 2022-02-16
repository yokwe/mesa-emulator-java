package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// BIT: TYPE = [0..2);
public final class BIT extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 1;
                                          
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 1;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final BIT value(char value) {
        return new BIT(value);
    }
    public static final BIT longPointer(int base, MemoryAccess access) {
        return new BIT(base, access);
    }
    public static final BIT pointer(char base, MemoryAccess access) {
        return new BIT(Memory.lengthenMDS(base), access);
    }
    
    private BIT(char value) {
        super(value);
    }
    private BIT(int base, MemoryAccess access) {
        super(base, access);
    }
}

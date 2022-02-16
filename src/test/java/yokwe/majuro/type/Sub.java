package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// Sub: TYPE = [0..4);
public final class Sub extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 2;
                                          
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 3;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
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
        return new Sub(Memory.lengthenMDS(base), access);
    }
    
    private Sub(char value) {
        super(value);
    }
    private Sub(int base, MemoryAccess access) {
        super(base, access);
    }
}

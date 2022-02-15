package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// UNSPECIFIED: TYPE = UNSPECIFIED;
public final class UNSPECIFIED extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =      1;
    public static final int BIT_SIZE  =     16;
                                               
    public static final int MIN_VALUE =      0;
    public static final int MAX_VALUE = 0xFFFF;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final UNSPECIFIED value(char value) {
        return new UNSPECIFIED(value);
    }
    public static final UNSPECIFIED longPointer(int base, MemoryAccess access) {
        return new UNSPECIFIED(base, access);
    }
    public static final UNSPECIFIED pointer(char base, MemoryAccess access) {
        return new UNSPECIFIED(Memory.instance.lengthenMDS(base), access);
    }
    
    private UNSPECIFIED(char value) {
        super(value);
    }
    private UNSPECIFIED(int base, MemoryAccess access) {
        super(base, access);
    }
}

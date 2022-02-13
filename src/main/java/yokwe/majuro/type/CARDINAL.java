package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// CARDINAL: TYPE = CARDINAL;
public final class CARDINAL extends MemoryData16 {
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
    public static final CARDINAL value(char value) {
        return new CARDINAL(value);
    }
    public static final CARDINAL longPointer(int base, MemoryAccess access) {
        return new CARDINAL(base, access);
    }
    public static final CARDINAL pointer(char base, MemoryAccess access) {
        return new CARDINAL(Mesa.lengthenMDS(base), access);
    }
    
    private CARDINAL(char value) {
        super(value);
    }
    private CARDINAL(int base, MemoryAccess access) {
        super(base, access);
    }
}

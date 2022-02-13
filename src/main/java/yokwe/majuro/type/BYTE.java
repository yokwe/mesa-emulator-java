package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// BYTE: TYPE = [0..256);
public final class BYTE extends MemoryData16 {
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
    public static final BYTE value(char value) {
        return new BYTE(value);
    }
    public static final BYTE longPointer(int base, MemoryAccess access) {
        return new BYTE(base, access);
    }
    public static final BYTE pointer(char base, MemoryAccess access) {
        return new BYTE(Mesa.lengthenMDS(base), access);
    }
    
    private BYTE(char value) {
        super(value);
    }
    private BYTE(int base, MemoryAccess access) {
        super(base, access);
    }
}

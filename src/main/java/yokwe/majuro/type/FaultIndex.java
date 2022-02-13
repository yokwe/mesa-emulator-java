package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// FaultIndex: TYPE = [0..8);
public final class FaultIndex extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 3;
                                          
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 7;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final FaultIndex value(char value) {
        return new FaultIndex(value);
    }
    public static final FaultIndex longPointer(int base, MemoryAccess access) {
        return new FaultIndex(base, access);
    }
    public static final FaultIndex pointer(char base, MemoryAccess access) {
        return new FaultIndex(Mesa.lengthenMDS(base), access);
    }
    
    private FaultIndex(char value) {
        super(value);
    }
    private FaultIndex(int base, MemoryAccess access) {
        super(base, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// LONG POINTER: TYPE = LONG POINTER;
public final class LONG_POINTER extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final LONG_POINTER longPointer(int base) {
        return new LONG_POINTER(base);
    }
    public static final LONG_POINTER pointer(char base) {
        return new LONG_POINTER(Memory.instance.lengthenMDS(base));
    }
    
    private LONG_POINTER(int base) {
        super(base);
    }
}

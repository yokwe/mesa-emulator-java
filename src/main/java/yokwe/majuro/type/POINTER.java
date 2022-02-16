package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// POINTER: TYPE = POINTER;
public final class POINTER extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final POINTER longPointer(int base) {
        return new POINTER(base);
    }
    public static final POINTER pointer(char base) {
        return new POINTER(Memory.lengthenMDS(base));
    }
    
    private POINTER(int base) {
        super(base);
    }
}

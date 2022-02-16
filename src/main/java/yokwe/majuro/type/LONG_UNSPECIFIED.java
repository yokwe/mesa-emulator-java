package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// LONG UNSPECIFIED: TYPE = LONG UNSPECIFIED;
public final class LONG_UNSPECIFIED extends MemoryData32 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final LONG_UNSPECIFIED value(int value) {
        return new LONG_UNSPECIFIED(value);
    }
    public static final LONG_UNSPECIFIED longPointer(int base, MemoryAccess access) {
        return new LONG_UNSPECIFIED(base, access);
    }
    public static final LONG_UNSPECIFIED pointer(char base, MemoryAccess access) {
        return new LONG_UNSPECIFIED(Memory.lengthenMDS(base), access);
    }
    
    private LONG_UNSPECIFIED(int value) {
        super(value);
    }
    private LONG_UNSPECIFIED(int base, MemoryAccess access) {
        super(base, access);
    }
}

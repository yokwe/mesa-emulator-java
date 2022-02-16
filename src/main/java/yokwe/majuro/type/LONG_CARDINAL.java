package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// LONG CARDINAL: TYPE = LONG CARDINAL;
public final class LONG_CARDINAL extends MemoryData32 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final LONG_CARDINAL value(int value) {
        return new LONG_CARDINAL(value);
    }
    public static final LONG_CARDINAL longPointer(int base, MemoryAccess access) {
        return new LONG_CARDINAL(base, access);
    }
    public static final LONG_CARDINAL pointer(char base, MemoryAccess access) {
        return new LONG_CARDINAL(Memory.lengthenMDS(base), access);
    }
    
    private LONG_CARDINAL(int value) {
        super(value);
    }
    private LONG_CARDINAL(int base, MemoryAccess access) {
        super(base, access);
    }
}

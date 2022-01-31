package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// BOOLEAN: TYPE = BOOLEAN;
public final class BOOLEAN extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 1;
    
    //
    // Constructor
    //
    public static final BOOLEAN value(char value) {
        return new BOOLEAN(value);
    }
    public static final BOOLEAN longPointer(int base, MemoryAccess access) {
        return new BOOLEAN(base, access);
    }
    public static final BOOLEAN pointer(char base, MemoryAccess access) {
        return new BOOLEAN(Mesa.lengthenMDS(base), access);
    }
    
    private BOOLEAN(char value) {
        super(value);
    }
    private BOOLEAN(int base, MemoryAccess access) {
        super(base, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
public final class BLOCK extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 0;
    public static final int BIT_SIZE  = 0;
    
    //
    // Constructor
    //
    public static final BLOCK longPointer(int base) {
        return new BLOCK(base);
    }
    public static final BLOCK pointer(char base) {
        return new BLOCK(Mesa.lengthenMDS(base));
    }
    
    private BLOCK(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final UNSPECIFIED get(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}

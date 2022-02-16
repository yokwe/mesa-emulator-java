package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// SrcFunc: TYPE = {null(0), complement(1)};
public final class SrcFunc extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 1;
    
    //
    // Enum Value Constants
    //
    public static final char NULL       = 0;
    public static final char COMPLEMENT = 1;
    
    private static final int[] values = {
        NULL, COMPLEMENT
    };
    private static final String[] names = {
        "NULL", "COMPLEMENT"
    };
    private static final ContextEnum context = new ContextEnum(NAME, values, names);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final SrcFunc value(char value) {
        return new SrcFunc(value);
    }
    public static final SrcFunc longPointer(int base, MemoryAccess access) {
        return new SrcFunc(base, access);
    }
    public static final SrcFunc pointer(char base, MemoryAccess access) {
        return new SrcFunc(Memory.lengthenMDS(base), access);
    }
    
    private SrcFunc(char value) {
        super(value);
    }
    private SrcFunc(int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(int value) {
        return context.toString(value);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
public final class AVItemType extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 2;
    
    //
    // Enum Value Constants
    //
    public static final char FRAME    = 0;
    public static final char EMPTY    = 1;
    public static final char INDIRECT = 2;
    public static final char UNUSED   = 3;
    
    private static final int[] values = {
        FRAME, EMPTY, INDIRECT, UNUSED
    };
    private static final String[] names = {
        "FRAME", "EMPTY", "INDIRECT", "UNUSED"
    };
    private static final ContextEnum context = new ContextEnum(NAME, values, names);
    
    public static final void checkValue(char value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final AVItemType value(char value) {
        return new AVItemType(value);
    }
    public static final AVItemType longPointer(int base, MemoryAccess access) {
        return new AVItemType(base, access);
    }
    public static final AVItemType pointer(char base, MemoryAccess access) {
        return new AVItemType(Mesa.lengthenMDS(base), access);
    }
    
    private AVItemType(char value) {
        super(value);
    }
    private AVItemType(int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(char value) {
        return context.toString(value);
    }
}

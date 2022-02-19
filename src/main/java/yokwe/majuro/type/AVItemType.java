package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
public final class AVItemType extends MemoryData16 {
    public static final String NAME = "AVItemType";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 2;
    
    //
    // Enum Value Constants
    //
    public static final @Mesa.ENUM int FRAME    = 0;
    public static final @Mesa.ENUM int EMPTY    = 1;
    public static final @Mesa.ENUM int INDIRECT = 2;
    public static final @Mesa.ENUM int UNUSED   = 3;
    
    private static final int[] values = {
        FRAME, EMPTY, INDIRECT, UNUSED
    };
    private static final String[] names = {
        "FRAME", "EMPTY", "INDIRECT", "UNUSED"
    };
    private static final ContextEnum context = new ContextEnum(NAME, values, names);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final AVItemType value(@Mesa.CARD16 int value) {
        return new AVItemType(value);
    }
    public static final AVItemType longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new AVItemType(base, access);
    }
    public static final AVItemType pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new AVItemType(Memory.lengthenMDS(base), access);
    }
    
    private AVItemType(@Mesa.CARD16 int value) {
        super(value);
    }
    private AVItemType(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(int value) {
        return context.toString(value);
    }
}

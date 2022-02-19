package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Enum: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
public final class Enum extends MemoryData16 {
    public static final String NAME = "Enum";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 2;
    
    //
    // Enum Value Constants
    //
    public static final @Mesa.ENUM int FRAME         = 0;
    public static final @Mesa.ENUM int OLD_PROCEDURE = 1;
    public static final @Mesa.ENUM int INDIRECT      = 2;
    public static final @Mesa.ENUM int NEW_PROCEDURE = 3;
    
    private static final int[] values = {
        FRAME, OLD_PROCEDURE, INDIRECT, NEW_PROCEDURE
    };
    private static final String[] names = {
        "FRAME", "OLD_PROCEDURE", "INDIRECT", "NEW_PROCEDURE"
    };
    private static final ContextEnum context = new ContextEnum(NAME, values, names);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final Enum value(@Mesa.CARD16 int value) {
        return new Enum(value);
    }
    public static final Enum longPointer(@Mesa.POINTER int base, MemoryAccess access) {
        return new Enum(base, access);
    }
    public static final Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Enum(Memory.lengthenMDS(base), access);
    }
    
    private Enum(@Mesa.CARD16 int value) {
        super(value);
    }
    private Enum(@Mesa.POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(int value) {
        return context.toString(value);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// LinkType: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
public final class LinkType extends MemoryData16 {
    public static final String NAME = "LinkType";
    
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
    public static final LinkType value(@Mesa.CARD16 int value) {
        return new LinkType(value);
    }
    public static final LinkType longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new LinkType(base, access);
    }
    public static final LinkType pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new LinkType(Memory.lengthenMDS(base), access);
    }
    
    private LinkType(@Mesa.CARD16 int value) {
        super(value);
    }
    private LinkType(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(int value) {
        return context.toString(value);
    }
}

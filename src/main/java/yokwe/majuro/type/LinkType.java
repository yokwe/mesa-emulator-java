package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// LinkType: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
public final class LinkType extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 2;
    
    //
    // Enum Value Constants
    //
    public static final char FRAME         = 0;
    public static final char OLD_PROCEDURE = 1;
    public static final char INDIRECT      = 2;
    public static final char NEW_PROCEDURE = 3;
    
    private static final int[] values = {
        FRAME, OLD_PROCEDURE, INDIRECT, NEW_PROCEDURE
    };
    private static final String[] names = {
        "FRAME", "OLD_PROCEDURE", "INDIRECT", "NEW_PROCEDURE"
    };
    private static final ContextEnum context = new ContextEnum(NAME, values, names);
    
    public static final void checkValue(char value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final LinkType value(char value) {
        return new LinkType(value);
    }
    public static final LinkType longPointer(int base, MemoryAccess access) {
        return new LinkType(base, access);
    }
    public static final LinkType pointer(char base, MemoryAccess access) {
        return new LinkType(Mesa.lengthenMDS(base), access);
    }
    
    private LinkType(char value) {
        super(value);
    }
    private LinkType(int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(char value) {
        return context.toString(value);
    }
}

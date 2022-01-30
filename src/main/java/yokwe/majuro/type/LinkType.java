package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// LinkType: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
public class LinkType extends MemoryData16 {
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
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public LinkType(char value) {
        super(value);
    }
    public LinkType(int base, MemoryAccess access) {
        super(base, access);
    }
    
    @Override
    public String toString() {
        return context.toString(value);
    }
}

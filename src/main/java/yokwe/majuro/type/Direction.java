package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// Direction: TYPE = {forward(0), backward(1)};
public final class Direction extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 1;
    
    //
    // Enum Value Constants
    //
    public static final char FORWARD  = 0;
    public static final char BACKWARD = 1;
    
    private static final int[] values = {
        FORWARD, BACKWARD
    };
    private static final String[] names = {
        "FORWARD", "BACKWARD"
    };
    private static final ContextEnum context = new ContextEnum(NAME, values, names);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final Direction value(char value) {
        return new Direction(value);
    }
    public static final Direction longPointer(int base, MemoryAccess access) {
        return new Direction(base, access);
    }
    public static final Direction pointer(char base, MemoryAccess access) {
        return new Direction(Mesa.lengthenMDS(base), access);
    }
    
    private Direction(char value) {
        super(value);
    }
    private Direction(int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(int value) {
        return context.toString(value);
    }
}

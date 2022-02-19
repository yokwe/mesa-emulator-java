package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Direction: TYPE = {forward(0), backward(1)};
public final class Direction extends MemoryData16 {
    public static final String NAME = "Direction";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 1;
    
    //
    // Enum Value Constants
    //
    public static final @Mesa.ENUM int FORWARD  = 0;
    public static final @Mesa.ENUM int BACKWARD = 1;
    
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
    public static final Direction value(@Mesa.CARD16 int value) {
        return new Direction(value);
    }
    public static final Direction longPointer(@Mesa.POINTER int base, MemoryAccess access) {
        return new Direction(base, access);
    }
    public static final Direction pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Direction(Memory.lengthenMDS(base), access);
    }
    
    private Direction(@Mesa.CARD16 int value) {
        super(value);
    }
    private Direction(@Mesa.POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(int value) {
        return context.toString(value);
    }
}

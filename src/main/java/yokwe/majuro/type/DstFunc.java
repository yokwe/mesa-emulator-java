package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// DstFunc: TYPE = {null(0), and(1), or(2), xor(3)};
public final class DstFunc extends MemoryData16 {
    public static final String NAME = "DstFunc";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 2;
    
    //
    // Enum Value Constants
    //
    public static final @Mesa.ENUM int NULL = 0;
    public static final @Mesa.ENUM int AND  = 1;
    public static final @Mesa.ENUM int OR   = 2;
    public static final @Mesa.ENUM int XOR  = 3;
    
    private static final int[] values = {
        NULL, AND, OR, XOR
    };
    private static final String[] names = {
        "NULL", "AND", "OR", "XOR"
    };
    private static final ContextEnum context = new ContextEnum(NAME, values, names);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final DstFunc value(@Mesa.CARD16 int value) {
        return new DstFunc(value);
    }
    public static final DstFunc value() {
        return new DstFunc(0);
    }
    public static final DstFunc longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new DstFunc(base, access);
    }
    public static final DstFunc pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new DstFunc(Memory.lengthenMDS(base), access);
    }
    
    private DstFunc(@Mesa.CARD16 int value) {
        super(value);
    }
    private DstFunc(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    public final String toString(int value) {
        return context.toString(value);
    }
}

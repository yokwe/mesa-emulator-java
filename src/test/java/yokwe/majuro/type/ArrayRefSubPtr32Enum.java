package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArrayRefSubPtr32Enum: TYPE = ARRAY Sub OF LONG POINTER TO Enum;
public final class ArrayRefSubPtr32Enum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr32Enum longPointer(int base) {
        return new ArrayRefSubPtr32Enum(base);
    }
    public static final ArrayRefSubPtr32Enum pointer(char base) {
        return new ArrayRefSubPtr32Enum(Memory.instance.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr32Enum(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Enum get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = Memory.instance.read32(base + (LONG_POINTER.WORD_SIZE * index));
        return Enum.longPointer(longPointer, access);
    }
}

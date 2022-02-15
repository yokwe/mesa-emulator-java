package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArrayRefSubPtr16Enum: TYPE = ARRAY Sub OF POINTER TO Enum;
public final class ArrayRefSubPtr16Enum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Enum longPointer(int base) {
        return new ArrayRefSubPtr16Enum(base);
    }
    public static final ArrayRefSubPtr16Enum pointer(char base) {
        return new ArrayRefSubPtr16Enum(Memory.instance.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Enum(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Enum get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        char pointer = Memory.instance.read16(base + (POINTER.WORD_SIZE * index));
        return Enum.pointer(pointer, access);
    }
}

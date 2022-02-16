package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArrayRefSubPtr16Sub: TYPE = ARRAY Sub OF POINTER TO Sub;
public final class ArrayRefSubPtr16Sub extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Sub longPointer(int base) {
        return new ArrayRefSubPtr16Sub(base);
    }
    public static final ArrayRefSubPtr16Sub pointer(char base) {
        return new ArrayRefSubPtr16Sub(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Sub(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Sub get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        char pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return Sub.pointer(pointer, access);
    }
}

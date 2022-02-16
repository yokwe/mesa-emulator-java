package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArrayRefSubPtr16Bit32: TYPE = ARRAY Sub OF POINTER TO BitField32;
public final class ArrayRefSubPtr16Bit32 extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Bit32 longPointer(int base) {
        return new ArrayRefSubPtr16Bit32(base);
    }
    public static final ArrayRefSubPtr16Bit32 pointer(char base) {
        return new ArrayRefSubPtr16Bit32(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Bit32(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final BitField32 get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        char pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return BitField32.pointer(pointer, access);
    }
}

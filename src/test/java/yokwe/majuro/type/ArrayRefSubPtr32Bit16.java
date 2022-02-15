package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArrayRefSubPtr32Bit16: TYPE = ARRAY Sub OF LONG POINTER TO BitField16;
public final class ArrayRefSubPtr32Bit16 extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr32Bit16 longPointer(int base) {
        return new ArrayRefSubPtr32Bit16(base);
    }
    public static final ArrayRefSubPtr32Bit16 pointer(char base) {
        return new ArrayRefSubPtr32Bit16(Memory.instance.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr32Bit16(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final BitField16 get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = Memory.instance.read32(base + (LONG_POINTER.WORD_SIZE * index));
        return BitField16.longPointer(longPointer, access);
    }
}

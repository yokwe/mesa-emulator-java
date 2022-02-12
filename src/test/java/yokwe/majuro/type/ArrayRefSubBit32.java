package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubBit32: TYPE = ARRAY Sub OF BitField32;
public final class ArrayRefSubBit32 extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ArrayRefSubBit32 longPointer(int base) {
        return new ArrayRefSubBit32(base);
    }
    public static final ArrayRefSubBit32 pointer(char base) {
        return new ArrayRefSubBit32(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubBit32(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final BitField32 get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + (BitField32.WORD_SIZE * index);
        return BitField32.longPointer(longPointer, access);
    }
}

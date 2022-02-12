package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubBit16: TYPE = ARRAY Sub OF BitField16;
public final class ArrayRefSubBit16 extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubBit16 longPointer(int base) {
        return new ArrayRefSubBit16(base);
    }
    public static final ArrayRefSubBit16 pointer(char base) {
        return new ArrayRefSubBit16(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubBit16(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final BitField16 get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + (BitField16.WORD_SIZE * index);
        return BitField16.longPointer(longPointer, access);
    }
}

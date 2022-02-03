package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16: TYPE = ARRAY Sub OF POINTER;
public final class ArrayRefSubPtr16 extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(value);
    }
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16 longPointer(int base) {
        return new ArrayRefSubPtr16(base);
    }
    public static final ArrayRefSubPtr16 pointer(char base) {
        return new ArrayRefSubPtr16(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final POINTER get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return POINTER.longPointer(base + (POINTER.WORD_SIZE * index));
    }
}
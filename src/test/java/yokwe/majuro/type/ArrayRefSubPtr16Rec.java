package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16Rec: TYPE = ARRAY Sub OF POINTER TO Rec;
public final class ArrayRefSubPtr16Rec extends MemoryBase {
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
    public static final ArrayRefSubPtr16Rec longPointer(int base) {
        return new ArrayRefSubPtr16Rec(base);
    }
    public static final ArrayRefSubPtr16Rec pointer(char base) {
        return new ArrayRefSubPtr16Rec(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Rec(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Rec get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return Rec.longPointer(base + (POINTER.WORD_SIZE * index));
    }
}

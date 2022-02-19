package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArraySubFixed: TYPE = ARRAY [0..4) OF UNSPECIFIED;
public final class ArraySubFixed extends MemoryBase {
    public static final String NAME = "ArraySubFixed";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArraySubFixed longPointer(@Mesa.POINTER int base) {
        return new ArraySubFixed(base);
    }
    public static final ArraySubFixed pointer(@Mesa.SHORT_POINTER int base) {
        return new ArraySubFixed(Memory.lengthenMDS(base));
    }
    
    private ArraySubFixed(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    private static final class ArrayIndex {
        private static final ContextSubrange context = new ContextSubrange("ArraySubFixed", 0, 3);
        private static final void checkValue(int value) {
            context.check(value);
        }
    }
    public final UNSPECIFIED get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) ArrayIndex.checkValue(index);
        int longPointer = base + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

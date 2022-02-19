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
    public static final ArraySubFixed longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ArraySubFixed(base, access);
    }
    public static final ArraySubFixed pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ArraySubFixed(Memory.lengthenMDS(base), access);
    }
    
    private ArraySubFixed(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
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
    public final UNSPECIFIED get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) ArrayIndex.checkValue(index);
        int longPointer = base + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArraySubFixed: TYPE = ARRAY [0..4) OF UNSPECIFIED;
public final class ArraySubFixed extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArraySubFixed longPointer(int base) {
        return new ArraySubFixed(base);
    }
    public static final ArraySubFixed pointer(char base) {
        return new ArraySubFixed(Memory.instance.lengthenMDS(base));
    }
    
    private ArraySubFixed(int base) {
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

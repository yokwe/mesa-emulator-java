package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Sub: TYPE = [0..4);
public final class Sub extends MemoryData16 {
    public static final String NAME = "Sub";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 2;
                                          
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 3;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final Sub value(@Mesa.CARD16 int value) {
        return new Sub(value);
    }
    public static final Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new Sub(base, access);
    }
    public static final Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Sub(Memory.lengthenMDS(base), access);
    }
    
    private Sub(@Mesa.CARD16 int value) {
        super(value);
    }
    private Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

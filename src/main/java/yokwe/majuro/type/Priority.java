package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Priority: TYPE = [0..7];
public final class Priority extends MemoryData16 {
    public static final String NAME = "Priority";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 3;
                                          
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 7;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final Priority value(@Mesa.CARD16 int value) {
        return new Priority(value);
    }
    public static final Priority value() {
        return new Priority(0);
    }
    public static final Priority longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new Priority(base, access);
    }
    public static final Priority pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Priority(Memory.lengthenMDS(base), access);
    }
    
    private Priority(@Mesa.CARD16 int value) {
        super(value);
    }
    private Priority(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

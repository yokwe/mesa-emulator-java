package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// INTEGER: TYPE = INTEGER;
public final class INTEGER extends MemoryData16 {
    public static final String NAME = "INTEGER";
    
    public static final int WORD_SIZE =               1;
    public static final int BIT_SIZE  =              16;
                                                        
    public static final int MIN_VALUE = Short.MIN_VALUE;
    public static final int MAX_VALUE = Short.MAX_VALUE;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final INTEGER value(@Mesa.CARD16 int value) {
        return new INTEGER(value);
    }
    public static final INTEGER value() {
        return new INTEGER(0);
    }
    public static final INTEGER longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new INTEGER(base, access);
    }
    public static final INTEGER pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new INTEGER(Memory.lengthenMDS(base), access);
    }
    
    private INTEGER(@Mesa.CARD16 int value) {
        super(value);
    }
    private INTEGER(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

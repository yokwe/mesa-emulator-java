package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// BIT8: TYPE = [0..256);
public final class BIT8 extends MemoryData16 {
    public static final String NAME = "BIT8";
    
    public static final int WORD_SIZE =    1;
    public static final int BIT_SIZE  =    8;
                                             
    public static final int MIN_VALUE =    0;
    public static final int MAX_VALUE = 0xFF;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final BIT8 value(@Mesa.CARD16 int value) {
        return new BIT8(value);
    }
    public static final BIT8 value() {
        return new BIT8(0);
    }
    public static final BIT8 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new BIT8(base, access);
    }
    public static final BIT8 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BIT8(Memory.lengthenMDS(base), access);
    }
    
    private BIT8(@Mesa.CARD16 int value) {
        super(value);
    }
    private BIT8(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

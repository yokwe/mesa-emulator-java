package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// BIT4: TYPE = [0..16);
public final class BIT4 extends MemoryData16 {
    public static final String NAME = "BIT4";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  =  4;
                                           
    public static final int MIN_VALUE =  0;
    public static final int MAX_VALUE = 15;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final BIT4 value(@Mesa.CARD16 int value) {
        return new BIT4(value);
    }
    public static final BIT4 longPointer(@Mesa.POINTER int base, MemoryAccess access) {
        return new BIT4(base, access);
    }
    public static final BIT4 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BIT4(Memory.lengthenMDS(base), access);
    }
    
    private BIT4(@Mesa.CARD16 int value) {
        super(value);
    }
    private BIT4(@Mesa.POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

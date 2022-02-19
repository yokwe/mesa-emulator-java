package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// SDIndex: TYPE = [0..256);
public final class SDIndex extends MemoryData16 {
    public static final String NAME = "SDIndex";
    
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
    public static final SDIndex value(@Mesa.CARD16 int value) {
        return new SDIndex(value);
    }
    public static final SDIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new SDIndex(base, access);
    }
    public static final SDIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new SDIndex(Memory.lengthenMDS(base), access);
    }
    
    private SDIndex(@Mesa.CARD16 int value) {
        super(value);
    }
    private SDIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

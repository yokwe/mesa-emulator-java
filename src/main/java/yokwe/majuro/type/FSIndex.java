package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// FSIndex: TYPE = [0..256);
public final class FSIndex extends MemoryData16 {
    public static final String NAME = "FSIndex";
    
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
    public static final FSIndex value(@Mesa.CARD16 int value) {
        return new FSIndex(value);
    }
    public static final FSIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new FSIndex(base, access);
    }
    public static final FSIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new FSIndex(Memory.lengthenMDS(base), access);
    }
    
    private FSIndex(@Mesa.CARD16 int value) {
        super(value);
    }
    private FSIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

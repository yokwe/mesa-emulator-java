package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// FaultIndex: TYPE = [0..8);
public final class FaultIndex extends MemoryData16 {
    public static final String NAME = "FaultIndex";
    
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
    public static final FaultIndex value(@Mesa.CARD16 int value) {
        return new FaultIndex(value);
    }
    public static final FaultIndex longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new FaultIndex(base, access);
    }
    public static final FaultIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new FaultIndex(Memory.lengthenMDS(base), access);
    }
    
    private FaultIndex(@Mesa.CARD16 int value) {
        super(value);
    }
    private FaultIndex(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

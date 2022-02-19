package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// PsbIndex: TYPE = [0..1024);
public final class PsbIndex extends MemoryData16 {
    public static final String NAME = "PsbIndex";
    
    public static final int WORD_SIZE =    1;
    public static final int BIT_SIZE  =   10;
                                             
    public static final int MIN_VALUE =    0;
    public static final int MAX_VALUE = 1023;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final PsbIndex value(@Mesa.CARD16 int value) {
        return new PsbIndex(value);
    }
    public static final PsbIndex longPointer(@Mesa.POINTER int base, MemoryAccess access) {
        return new PsbIndex(base, access);
    }
    public static final PsbIndex pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new PsbIndex(Memory.lengthenMDS(base), access);
    }
    
    private PsbIndex(@Mesa.CARD16 int value) {
        super(value);
    }
    private PsbIndex(@Mesa.POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

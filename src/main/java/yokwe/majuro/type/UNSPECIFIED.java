package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// UNSPECIFIED: TYPE = UNSPECIFIED;
public final class UNSPECIFIED extends MemoryData16 {
    public static final String NAME = "UNSPECIFIED";
    
    public static final int WORD_SIZE =      1;
    public static final int BIT_SIZE  =     16;
                                               
    public static final int MIN_VALUE =      0;
    public static final int MAX_VALUE = 0xFFFF;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final UNSPECIFIED value(@Mesa.CARD16 int value) {
        return new UNSPECIFIED(value);
    }
    public static final UNSPECIFIED longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new UNSPECIFIED(base, access);
    }
    public static final UNSPECIFIED pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new UNSPECIFIED(Memory.lengthenMDS(base), access);
    }
    
    private UNSPECIFIED(@Mesa.CARD16 int value) {
        super(value);
    }
    private UNSPECIFIED(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// NIBBLE: TYPE = [0..16);
public final class NIBBLE extends MemoryData16 {
    public static final String NAME = "NIBBLE";
    
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
    public static final NIBBLE value(@Mesa.CARD16 int value) {
        return new NIBBLE(value);
    }
    public static final NIBBLE longPointer(@Mesa.POINTER int base, MemoryAccess access) {
        return new NIBBLE(base, access);
    }
    public static final NIBBLE pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new NIBBLE(Memory.lengthenMDS(base), access);
    }
    
    private NIBBLE(@Mesa.CARD16 int value) {
        super(value);
    }
    private NIBBLE(@Mesa.POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

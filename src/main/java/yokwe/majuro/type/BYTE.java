package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// BYTE: TYPE = [0..256);
public final class BYTE extends MemoryData16 {
    public static final String NAME = "BYTE";
    
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
    public static final BYTE value(@Mesa.CARD16 int value) {
        return new BYTE(value);
    }
    public static final BYTE longPointer(@Mesa.POINTER int base, MemoryAccess access) {
        return new BYTE(base, access);
    }
    public static final BYTE pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BYTE(Memory.lengthenMDS(base), access);
    }
    
    private BYTE(@Mesa.CARD16 int value) {
        super(value);
    }
    private BYTE(@Mesa.POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// LONG POINTER: TYPE = LONG POINTER;
public final class LONG_POINTER extends MemoryBase {
    public static final String NAME = "LONG_POINTER";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final LONG_POINTER longPointer(@Mesa.LONG_POINTER int base) {
        return new LONG_POINTER(base);
    }
    public static final LONG_POINTER pointer(@Mesa.SHORT_POINTER int base) {
        return new LONG_POINTER(Memory.lengthenMDS(base));
    }
    
    private LONG_POINTER(@Mesa.LONG_POINTER int base) {
        super(base);
    }
}

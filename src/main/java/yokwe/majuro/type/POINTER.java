package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// POINTER: TYPE = POINTER;
public final class POINTER extends MemoryBase {
    public static final String NAME = "POINTER";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final POINTER longPointer(@Mesa.LONG_POINTER int base) {
        return new POINTER(base);
    }
    public static final POINTER pointer(@Mesa.SHORT_POINTER int base) {
        return new POINTER(Memory.lengthenMDS(base));
    }
    
    private POINTER(@Mesa.LONG_POINTER int base) {
        super(base);
    }
}

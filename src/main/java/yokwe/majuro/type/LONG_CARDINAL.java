package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// LONG CARDINAL: TYPE = LONG CARDINAL;
public final class LONG_CARDINAL extends MemoryData32 {
    public static final String NAME = "LONG_CARDINAL";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final LONG_CARDINAL value(@Mesa.CARD32 int value) {
        return new LONG_CARDINAL(value);
    }
    public static final LONG_CARDINAL value() {
        return new LONG_CARDINAL(0);
    }
    public static final LONG_CARDINAL longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new LONG_CARDINAL(base, access);
    }
    public static final LONG_CARDINAL pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new LONG_CARDINAL(Memory.lengthenMDS(base), access);
    }
    
    private LONG_CARDINAL(@Mesa.CARD32 int value) {
        super(value);
    }
    private LONG_CARDINAL(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

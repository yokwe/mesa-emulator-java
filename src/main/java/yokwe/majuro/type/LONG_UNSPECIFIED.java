package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// LONG UNSPECIFIED: TYPE = LONG UNSPECIFIED;
public final class LONG_UNSPECIFIED extends MemoryData32 {
    public static final String NAME = "LONG_UNSPECIFIED";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final LONG_UNSPECIFIED value(@Mesa.CARD32 int value) {
        return new LONG_UNSPECIFIED(value);
    }
    public static final LONG_UNSPECIFIED value() {
        return new LONG_UNSPECIFIED(0);
    }
    public static final LONG_UNSPECIFIED longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new LONG_UNSPECIFIED(base, access);
    }
    public static final LONG_UNSPECIFIED pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new LONG_UNSPECIFIED(Memory.lengthenMDS(base), access);
    }
    
    private LONG_UNSPECIFIED(@Mesa.CARD32 int value) {
        super(value);
    }
    private LONG_UNSPECIFIED(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

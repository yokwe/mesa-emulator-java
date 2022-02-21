package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// BOOLEAN: TYPE = BOOLEAN;
public final class BOOLEAN extends MemoryData16 {
    public static final String NAME = "BOOLEAN";
    
    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 1;
    
    //
    // Constructor
    //
    public static final BOOLEAN value(@Mesa.CARD16 int value) {
        return new BOOLEAN(value);
    }
    public static final BOOLEAN value() {
        return new BOOLEAN(0);
    }
    public static final BOOLEAN longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new BOOLEAN(base, access);
    }
    public static final BOOLEAN pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BOOLEAN(Memory.lengthenMDS(base), access);
    }
    
    private BOOLEAN(@Mesa.CARD16 int value) {
        super(value);
    }
    private BOOLEAN(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
}

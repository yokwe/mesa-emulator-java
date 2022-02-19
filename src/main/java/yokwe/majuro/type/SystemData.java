package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// SystemData: TYPE = ARRAY SDIndex OF ControlLink;
public final class SystemData extends MemoryBase {
    public static final String NAME = "SystemData";
    
    public static final int WORD_SIZE =  512;
    public static final int BIT_SIZE  = 8192;
    
    //
    // Constructor
    //
    public static final SystemData longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new SystemData(base, access);
    }
    public static final SystemData pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new SystemData(Memory.lengthenMDS(base), access);
    }
    
    private SystemData(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final LONG_UNSPECIFIED get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) SDIndex.checkValue(index);
        int longPointer = base + (LONG_UNSPECIFIED.WORD_SIZE * index);
        return LONG_UNSPECIFIED.longPointer(longPointer, access);
    }
}

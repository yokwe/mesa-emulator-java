package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// SystemData: TYPE = ARRAY SDIndex OF ControlLink;
public final class SystemData extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  512;
    public static final int BIT_SIZE  = 8192;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) SDIndex.checkValue(value);
    }
    //
    // Constructor
    //
    public static final SystemData longPointer(int base) {
        return new SystemData(base);
    }
    public static final SystemData pointer(char base) {
        return new SystemData(Mesa.lengthenMDS(base));
    }
    
    private SystemData(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final LONG_UNSPECIFIED get(int index, MemoryAccess access) {
        return LONG_UNSPECIFIED.longPointer(base + (LONG_UNSPECIFIED.WORD_SIZE * index), access);
    }
}

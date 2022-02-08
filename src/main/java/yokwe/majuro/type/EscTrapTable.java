package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// EscTrapTable: TYPE = ARRAY BYTE OF ControlLink;
public final class EscTrapTable extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  512;
    public static final int BIT_SIZE  = 8192;
    
    //
    // Constructor
    //
    public static final EscTrapTable longPointer(int base) {
        return new EscTrapTable(base);
    }
    public static final EscTrapTable pointer(char base) {
        return new EscTrapTable(Mesa.lengthenMDS(base));
    }
    
    private EscTrapTable(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final LONG_UNSPECIFIED get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(index);
        int longPointer = base + (LONG_UNSPECIFIED.WORD_SIZE * index);
        return LONG_UNSPECIFIED.longPointer(longPointer, access);
    }
}

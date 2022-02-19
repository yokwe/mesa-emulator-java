package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// EscTrapTable: TYPE = ARRAY BYTE OF ControlLink;
public final class EscTrapTable extends MemoryBase {
    public static final String NAME = "EscTrapTable";
    
    public static final int WORD_SIZE =  512;
    public static final int BIT_SIZE  = 8192;
    
    //
    // Constructor
    //
    public static final EscTrapTable longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new EscTrapTable(base, access);
    }
    public static final EscTrapTable pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new EscTrapTable(Memory.lengthenMDS(base), access);
    }
    
    private EscTrapTable(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final LONG_UNSPECIFIED get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(index);
        int longPointer = base + (LONG_UNSPECIFIED.WORD_SIZE * index);
        return LONG_UNSPECIFIED.longPointer(longPointer, access);
    }
}

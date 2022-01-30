package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// EscTrapTable: TYPE = ARRAY BYTE OF ControlLink;
public final class EscTrapTable extends MemoryBase {
    public static final String NAME      = "EscTrapTable";
    public static final int    WORD_SIZE =            512;
    public static final int    BIT_SIZE  =           8192;

    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) BYTE.checkValue(value);
    }
    //
    // Constructor
    //
    public EscTrapTable(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public LONG_UNSPECIFIED element(int index, MemoryAccess memoryAccess) {
        return new LONG_UNSPECIFIED(base + (LONG_UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

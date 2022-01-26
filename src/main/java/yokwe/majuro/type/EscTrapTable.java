package yokwe.majuro.type;

// EscTrapTable: TYPE = ARRAY BYTE OF ControlLink;
public final class EscTrapTable extends MemoryBase {
    public static final String NAME      = "EscTrapTable";
    public static final int    WORD_SIZE =            512;
    public static final int    BIT_SIZE  =           8192;

    public static final int INDEX_MIN_VALUE   =        (int)BYTE.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   =        (int)BYTE.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE = LONG_UNSPECIFIED.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("EscTrapTable#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
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
        return new LONG_UNSPECIFIED(base + (ELEMENT_WORD_SIZE * index), memoryAccess);
    }
}

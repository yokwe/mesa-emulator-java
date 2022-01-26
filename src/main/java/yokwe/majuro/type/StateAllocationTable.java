package yokwe.majuro.type;

// StateAllocationTable: TYPE = ARRAY Priority OF POINTER TO StateVector;
public final class StateAllocationTable extends MemoryBase {
    public static final String NAME      = "StateAllocationTable";
    public static final int    WORD_SIZE =                      8;
    public static final int    BIT_SIZE  =                    128;

    public static final int INDEX_MIN_VALUE   = (int)Priority.MIN_VALUE;
    public static final int INDEX_MAX_VALUE   = (int)Priority.MAX_VALUE;
    public static final int ELEMENT_WORD_SIZE =       POINTER.WORD_SIZE;

    public static final ContextSubrange context = new ContextSubrange("StateAllocationTable#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public StateAllocationTable(int base) {
        super(base);
    }

    //
    // Access to Element of Array
    //
    public POINTER element(int index) {
        return new POINTER(base + (ELEMENT_WORD_SIZE * index));
    }
}

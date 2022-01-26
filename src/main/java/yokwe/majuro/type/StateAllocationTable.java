package yokwe.majuro.type;

// StateAllocationTable: TYPE = ARRAY Priority OF POINTER TO StateVector;
public final class StateAllocationTable extends MemoryBase {
    public static final String NAME      = "StateAllocationTable";
    public static final int    WORD_SIZE =                      8;
    public static final int    BIT_SIZE  =                    128;

    public static final int INDEX_MIN_VALUE   = 0;
    public static final int INDEX_MAX_VALUE   = 7;
    public static final int ELEMENT_WORD_SIZE = 1;

    public static final ContextSubrange context = new ContextSubrange("StateAllocationTable#index", INDEX_MIN_VALUE, INDEX_MAX_VALUE);
    //
    // Constructor
    //
    public StateAllocationTable(int base) {
        super(base);
    }
    public StateAllocationTable(int base, int index) {
        super(base + (ELEMENT_WORD_SIZE * index));
    }

}

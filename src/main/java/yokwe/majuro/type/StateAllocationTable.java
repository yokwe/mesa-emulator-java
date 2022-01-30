package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// StateAllocationTable: TYPE = ARRAY Priority OF POINTER TO StateVector;
public final class StateAllocationTable extends MemoryBase {
    public static final String NAME      = "StateAllocationTable";
    public static final int    WORD_SIZE =                      8;
    public static final int    BIT_SIZE  =                    128;

    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) Priority.checkValue(value);
    }
    //
    // Constructor
    //
    public StateAllocationTable(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public StateVector element(int index) {
        return new StateVector(base + (POINTER.WORD_SIZE * index));
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// StateAllocationTable: TYPE = ARRAY Priority OF POINTER TO StateVector;
public final class StateAllocationTable extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) Priority.checkValue(value);
    }
    //
    // Constructor
    //
    public static final StateAllocationTable longPointer(int base) {
        return new StateAllocationTable(base);
    }
    public static final StateAllocationTable pointer(char base) {
        return new StateAllocationTable(Mesa.lengthenMDS(base));
    }
    
    private StateAllocationTable(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final StateVector get(int index) {
        return StateVector.longPointer(base + (POINTER.WORD_SIZE * index));
    }
}

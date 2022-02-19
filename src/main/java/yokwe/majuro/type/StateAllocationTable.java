package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// StateAllocationTable: TYPE = ARRAY Priority OF POINTER TO StateVector;
public final class StateAllocationTable extends MemoryBase {
    public static final String NAME = "StateAllocationTable";
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final StateAllocationTable longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new StateAllocationTable(base, access);
    }
    public static final StateAllocationTable pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new StateAllocationTable(Memory.lengthenMDS(base), access);
    }
    
    private StateAllocationTable(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final StateVector get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Priority.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return StateVector.pointer(pointer, access);
    }
}

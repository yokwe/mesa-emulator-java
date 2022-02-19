package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
public final class AllocationVector extends MemoryBase {
    public static final String NAME = "AllocationVector";
    
    public static final int WORD_SIZE =  256;
    public static final int BIT_SIZE  = 4096;
    
    //
    // Constructor
    //
    public static final AllocationVector longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new AllocationVector(base, access);
    }
    public static final AllocationVector pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new AllocationVector(Memory.lengthenMDS(base), access);
    }
    
    private AllocationVector(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final AVItem get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(index);
        int longPointer = base + (AVItem.WORD_SIZE * index);
        return AVItem.longPointer(longPointer, access);
    }
}

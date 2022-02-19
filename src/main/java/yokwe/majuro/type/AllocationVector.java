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
    public static final AllocationVector longPointer(@Mesa.LONG_POINTER int base) {
        return new AllocationVector(base);
    }
    public static final AllocationVector pointer(@Mesa.SHORT_POINTER int base) {
        return new AllocationVector(Memory.lengthenMDS(base));
    }
    
    private AllocationVector(@Mesa.LONG_POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final AVItem get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(index);
        int longPointer = base + (AVItem.WORD_SIZE * index);
        return AVItem.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
public final class AllocationVector extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  256;
    public static final int BIT_SIZE  = 4096;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(value);
    }
    //
    // Constructor
    //
    public static final AllocationVector longPointer(int base) {
        return new AllocationVector(base);
    }
    public static final AllocationVector pointer(char base) {
        return new AllocationVector(Mesa.lengthenMDS(base));
    }
    
    private AllocationVector(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final AVItem get(int index, MemoryAccess access) {
        return AVItem.longPointer(base + (AVItem.WORD_SIZE * index), access);
    }
}

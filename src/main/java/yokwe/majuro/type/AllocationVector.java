package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// AllocationVector: TYPE = ARRAY FSIndex OF AVItem;
public final class AllocationVector extends MemoryBase {
    public static final String NAME      = "AllocationVector";
    public static final int    WORD_SIZE =                256;
    public static final int    BIT_SIZE  =               4096;

    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) FSIndex.checkValue(value);
    }
    //
    // Constructor
    //
    public AllocationVector(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public AVItem element(int index, MemoryAccess memoryAccess) {
        return new AVItem(base + (AVItem.WORD_SIZE * index), memoryAccess);
    }
}

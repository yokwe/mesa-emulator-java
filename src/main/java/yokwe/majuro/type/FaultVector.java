package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// FaultVector: TYPE = ARRAY FaultIndex OF FaultQueue;
public final class FaultVector extends MemoryBase {
    public static final String NAME      = "FaultVector";
    public static final int    WORD_SIZE =            16;
    public static final int    BIT_SIZE  =           256;

    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) FaultIndex.checkValue(value);
    }
    //
    // Constructor
    //
    public FaultVector(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public FaultQueue element(int index) {
        return new FaultQueue(base + (FaultQueue.WORD_SIZE * index));
    }
}

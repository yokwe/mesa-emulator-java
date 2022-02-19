package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// FaultVector: TYPE = ARRAY FaultIndex OF FaultQueue;
public final class FaultVector extends MemoryBase {
    public static final String NAME = "FaultVector";
    
    public static final int WORD_SIZE =  16;
    public static final int BIT_SIZE  = 256;
    
    //
    // Constructor
    //
    public static final FaultVector longPointer(@Mesa.LONG_POINTER int base) {
        return new FaultVector(base);
    }
    public static final FaultVector pointer(@Mesa.SHORT_POINTER int base) {
        return new FaultVector(Memory.lengthenMDS(base));
    }
    
    private FaultVector(@Mesa.LONG_POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final FaultQueue get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) FaultIndex.checkValue(index);
        int longPointer = base + (FaultQueue.WORD_SIZE * index);
        return FaultQueue.longPointer(longPointer);
    }
}

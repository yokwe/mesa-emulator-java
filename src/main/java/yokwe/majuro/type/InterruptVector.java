package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
public final class InterruptVector extends MemoryBase {
    public static final String NAME = "InterruptVector";
    
    public static final int WORD_SIZE =  32;
    public static final int BIT_SIZE  = 512;
    
    //
    // Constructor
    //
    public static final InterruptVector longPointer(@Mesa.POINTER int base) {
        return new InterruptVector(base);
    }
    public static final InterruptVector pointer(@Mesa.SHORT_POINTER int base) {
        return new InterruptVector(Memory.lengthenMDS(base));
    }
    
    private InterruptVector(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final InterruptItem get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) InterruptLevel.checkValue(index);
        int longPointer = base + (InterruptItem.WORD_SIZE * index);
        return InterruptItem.longPointer(longPointer);
    }
}

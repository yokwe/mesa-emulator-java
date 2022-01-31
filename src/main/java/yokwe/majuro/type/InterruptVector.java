package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
public final class InterruptVector extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  32;
    public static final int BIT_SIZE  = 512;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) InterruptLevel.checkValue(value);
    }
    //
    // Constructor
    //
    public static final InterruptVector longPointer(int base) {
        return new InterruptVector(base);
    }
    public static final InterruptVector pointer(char base) {
        return new InterruptVector(Mesa.lengthenMDS(base));
    }
    
    private InterruptVector(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final InterruptItem get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);
        return InterruptItem.longPointer(base + (InterruptItem.WORD_SIZE * index));
    }
}

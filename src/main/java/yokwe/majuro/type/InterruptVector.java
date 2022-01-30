package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// InterruptVector: TYPE = ARRAY InterruptLevel OF InterruptItem;
public class InterruptVector extends MemoryBase {
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
    public InterruptVector(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public InterruptItem element(int index) {
        return new InterruptItem(base + (InterruptItem.WORD_SIZE * index));
    }
}

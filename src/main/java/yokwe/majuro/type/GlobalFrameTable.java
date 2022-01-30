package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;

// GlobalFrameTable: TYPE = ARRAY GFTIndex OF GFTItem;
public class GlobalFrameTable extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   65536;
    public static final int BIT_SIZE  = 1048576;
    
    //
    // Check range of index
    //
    public static final void checkIndex(int value) {
        if (Debug.ENABLE_CHECK_VALUE) GFTIndex.checkValue(value);
    }
    //
    // Constructor
    //
    public GlobalFrameTable(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public GFTItem element(int index) {
        return new GFTItem(base + (GFTItem.WORD_SIZE * index));
    }
}

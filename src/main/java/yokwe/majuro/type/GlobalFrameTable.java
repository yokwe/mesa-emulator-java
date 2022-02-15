package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// GlobalFrameTable: TYPE = ARRAY GFTIndex OF GFTItem;
public final class GlobalFrameTable extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   65536;
    public static final int BIT_SIZE  = 1048576;
    
    //
    // Constructor
    //
    public static final GlobalFrameTable longPointer(int base) {
        return new GlobalFrameTable(base);
    }
    public static final GlobalFrameTable pointer(char base) {
        return new GlobalFrameTable(Memory.instance.lengthenMDS(base));
    }
    
    private GlobalFrameTable(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final GFTItem get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) GFTIndex.checkValue(index);
        int longPointer = base + (GFTItem.WORD_SIZE * index);
        return GFTItem.longPointer(longPointer);
    }
}

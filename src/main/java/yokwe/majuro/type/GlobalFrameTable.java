package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// GlobalFrameTable: TYPE = ARRAY GFTIndex OF GFTItem;
public final class GlobalFrameTable extends MemoryBase {
    public static final String NAME = "GlobalFrameTable";
    
    public static final int WORD_SIZE =   65536;
    public static final int BIT_SIZE  = 1048576;
    
    //
    // Constructor
    //
    public static final GlobalFrameTable longPointer(@Mesa.LONG_POINTER int base) {
        return new GlobalFrameTable(base);
    }
    public static final GlobalFrameTable pointer(@Mesa.SHORT_POINTER int base) {
        return new GlobalFrameTable(Memory.lengthenMDS(base));
    }
    
    private GlobalFrameTable(@Mesa.LONG_POINTER int base) {
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

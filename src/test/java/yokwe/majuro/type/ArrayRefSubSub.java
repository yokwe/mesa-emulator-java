package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubSub: TYPE = ARRAY Sub OF Sub;
public final class ArrayRefSubSub extends MemoryBase {
    public static final String NAME = "ArrayRefSubSub";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubSub longPointer(@Mesa.POINTER int base) {
        return new ArrayRefSubSub(base);
    }
    public static final ArrayRefSubSub pointer(@Mesa.SHORT_POINTER int base) {
        return new ArrayRefSubSub(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubSub(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Sub get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + (Sub.WORD_SIZE * index);
        return Sub.longPointer(longPointer, access);
    }
}

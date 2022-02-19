package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16Sub: TYPE = ARRAY Sub OF POINTER TO Sub;
public final class ArrayRefSubPtr16Sub extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr16Sub";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Sub longPointer(@Mesa.POINTER int base) {
        return new ArrayRefSubPtr16Sub(base);
    }
    public static final ArrayRefSubPtr16Sub pointer(@Mesa.SHORT_POINTER int base) {
        return new ArrayRefSubPtr16Sub(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Sub(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Sub get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return Sub.pointer(pointer, access);
    }
}

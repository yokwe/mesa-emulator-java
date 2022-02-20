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
    public static final ArrayRefSubPtr16Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr16Sub(base, access);
    }
    public static final ArrayRefSubPtr16Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr16Sub(Memory.lengthenMDS(base), access);
    }
    
    private ArrayRefSubPtr16Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final Sub get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return Sub.pointer(pointer, access);
    }
    public final @Mesa.SHORT_POINTER int getValue(int index) {
        return Memory.read16(base + (POINTER.WORD_SIZE * index));
    }
    public final void getValue(int index, @Mesa.SHORT_POINTER int newValue) {
        Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16: TYPE = ARRAY Sub OF POINTER;
public final class ArrayRefSubPtr16 extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr16";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr16(base, access);
    }
    public static final ArrayRefSubPtr16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr16(Memory.lengthenMDS(base), access);
    }
    
    private ArrayRefSubPtr16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final POINTER get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return POINTER.pointer(pointer, access);
    }
    public final @Mesa.SHORT_POINTER int getValue(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        return Memory.read16(base + (POINTER.WORD_SIZE * index));
    }
    public final ArrayRefSubPtr16 getValue(int index, @Mesa.SHORT_POINTER int newValue) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        Memory.write16(base + (POINTER.WORD_SIZE * index), newValue);
        return this;
    }
}

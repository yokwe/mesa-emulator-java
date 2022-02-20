package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr32Rec: TYPE = ARRAY Sub OF LONG POINTER TO Rec;
public final class ArrayRefSubPtr32Rec extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr32Rec";
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr32Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr32Rec(base, access);
    }
    public static final ArrayRefSubPtr32Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new ArrayRefSubPtr32Rec(Memory.lengthenMDS(base), access);
    }
    
    private ArrayRefSubPtr32Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    //
    // Access to Element of Array
    //
    public final Rec get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
        return Rec.longPointer(longPointer, access);
    }
    public final @Mesa.LONG_POINTER int getValue(int index) {
        return Memory.read32(base + (LONG_POINTER.WORD_SIZE * index));
    }
    public final void getValue(int index, @Mesa.LONG_POINTER int newValue) {
        Memory.write32(base + (LONG_POINTER.WORD_SIZE * index), newValue);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubPtr16Rec: TYPE = ARRAY Sub OF POINTER TO Rec;
public final class ArrayRefSubPtr16Rec extends MemoryBase {
    public static final String NAME = "ArrayRefSubPtr16Rec";
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Rec longPointer(@Mesa.POINTER int base) {
        return new ArrayRefSubPtr16Rec(base);
    }
    public static final ArrayRefSubPtr16Rec pointer(@Mesa.SHORT_POINTER int base) {
        return new ArrayRefSubPtr16Rec(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Rec(@Mesa.POINTER int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Rec get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return Rec.pointer(pointer);
    }
}

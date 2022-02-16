package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArrayRefSubPtr16Rec: TYPE = ARRAY Sub OF POINTER TO Rec;
public final class ArrayRefSubPtr16Rec extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr16Rec longPointer(int base) {
        return new ArrayRefSubPtr16Rec(base);
    }
    public static final ArrayRefSubPtr16Rec pointer(char base) {
        return new ArrayRefSubPtr16Rec(Memory.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr16Rec(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Rec get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        char pointer = Memory.read16(base + (POINTER.WORD_SIZE * index));
        return Rec.pointer(pointer);
    }
}

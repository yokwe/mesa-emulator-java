package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// ArrayRefSubPtr32Rec: TYPE = ARRAY Sub OF LONG POINTER TO Rec;
public final class ArrayRefSubPtr32Rec extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   8;
    public static final int BIT_SIZE  = 128;
    
    //
    // Constructor
    //
    public static final ArrayRefSubPtr32Rec longPointer(int base) {
        return new ArrayRefSubPtr32Rec(base);
    }
    public static final ArrayRefSubPtr32Rec pointer(char base) {
        return new ArrayRefSubPtr32Rec(Memory.instance.lengthenMDS(base));
    }
    
    private ArrayRefSubPtr32Rec(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Rec get(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = Memory.instance.read32(base + (LONG_POINTER.WORD_SIZE * index));
        return Rec.longPointer(longPointer);
    }
}

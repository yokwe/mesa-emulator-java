package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// ArrayRefSubEnum: TYPE = ARRAY Sub OF Enum;
public final class ArrayRefSubEnum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final ArrayRefSubEnum longPointer(int base) {
        return new ArrayRefSubEnum(base);
    }
    public static final ArrayRefSubEnum pointer(char base) {
        return new ArrayRefSubEnum(Mesa.lengthenMDS(base));
    }
    
    private ArrayRefSubEnum(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public final Enum get(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + (Enum.WORD_SIZE * index);
        return Enum.longPointer(longPointer, access);
    }
}

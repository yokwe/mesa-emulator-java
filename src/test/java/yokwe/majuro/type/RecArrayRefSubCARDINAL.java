package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// RecArrayRefSubCARDINAL: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF CARDINAL];
public final class RecArrayRefSubCARDINAL extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubCARDINAL longPointer(int base) {
        return new RecArrayRefSubCARDINAL(base);
    }
    public static final RecArrayRefSubCARDINAL pointer(char base) {
        return new RecArrayRefSubCARDINAL(Memory.instance.lengthenMDS(base));
    }
    
    private RecArrayRefSubCARDINAL(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // card0 (0:0..15): CARDINAL
    private static final int OFFSET_CARD_0 = 0;
    public CARDINAL card0(MemoryAccess access) {
        int longPointer = base + OFFSET_CARD_0;
        return CARDINAL.longPointer(longPointer, access);
    }
    // card1 (1:0..63): ARRAY Sub OF CARDINAL
    private static final int OFFSET_CARD_1 = 1;
    public final CARDINAL card1(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (CARDINAL.WORD_SIZE * index);
        return CARDINAL.longPointer(longPointer, access);
    }
}

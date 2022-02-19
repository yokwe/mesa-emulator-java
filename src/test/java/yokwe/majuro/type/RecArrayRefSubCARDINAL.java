package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubCARDINAL: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF CARDINAL];
public final class RecArrayRefSubCARDINAL extends MemoryBase {
    public static final String NAME = "RecArrayRefSubCARDINAL";
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubCARDINAL longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubCARDINAL(base, access);
    }
    public static final RecArrayRefSubCARDINAL pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubCARDINAL(Memory.lengthenMDS(base), access);
    }
    
    private RecArrayRefSubCARDINAL(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // card0 (0:0..15): CARDINAL
    private static final int OFFSET_CARD_0 = 0;
    public CARDINAL card0() {
        int longPointer = base + OFFSET_CARD_0;
        return CARDINAL.longPointer(longPointer, access);
    }
    // card1 (1:0..63): ARRAY Sub OF CARDINAL
    private static final int OFFSET_CARD_1 = 1;
    public final CARDINAL card1(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (CARDINAL.WORD_SIZE * index);
        return CARDINAL.longPointer(longPointer, access);
    }
}

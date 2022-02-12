package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// RecArrayRefEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Enum OF UNSPECIFIED];
public final class RecArrayRefEnum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefEnum longPointer(int base) {
        return new RecArrayRefEnum(base);
    }
    public static final RecArrayRefEnum pointer(char base) {
        return new RecArrayRefEnum(Mesa.lengthenMDS(base));
    }
    
    private RecArrayRefEnum(int base) {
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
    // card1 (1:0..63): ARRAY Enum OF UNSPECIFIED
    private static final int OFFSET_CARD_1 = 1;
    public final UNSPECIFIED card1(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Enum.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

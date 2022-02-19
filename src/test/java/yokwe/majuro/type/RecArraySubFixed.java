package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArraySubFixed: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY [0..4) OF UNSPECIFIED];
public final class RecArraySubFixed extends MemoryBase {
    public static final String NAME = "RecArraySubFixed";
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArraySubFixed longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecArraySubFixed(base, access);
    }
    public static final RecArraySubFixed pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecArraySubFixed(Memory.lengthenMDS(base), access);
    }
    
    private RecArraySubFixed(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..63): ARRAY [0..4) OF UNSPECIFIED
    private static final int OFFSET_CARD_1 = 1;
    private static final class Card1Index {
        private static final ContextSubrange context = new ContextSubrange("RecArraySubFixed", 0, 3);
        private static final void checkValue(int value) {
            context.check(value);
        }
    }
    public final UNSPECIFIED card1(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Card1Index.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArrayRefSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF UNSPECIFIED];
public final class RecArrayRefSub extends MemoryBase {
    public static final String NAME = "RecArrayRefSub";
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSub longPointer(@Mesa.LONG_POINTER int base) {
        return new RecArrayRefSub(base);
    }
    public static final RecArrayRefSub pointer(@Mesa.SHORT_POINTER int base) {
        return new RecArrayRefSub(Memory.lengthenMDS(base));
    }
    
    private RecArrayRefSub(@Mesa.LONG_POINTER int base) {
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
    // card1 (1:0..63): ARRAY Sub OF UNSPECIFIED
    private static final int OFFSET_CARD_1 = 1;
    public final UNSPECIFIED card1(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

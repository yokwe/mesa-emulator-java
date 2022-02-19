package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF Sub];
public final class RecArrayRefSubSub extends MemoryBase {
    public static final String NAME = "RecArrayRefSubSub";
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubSub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubSub(base, access);
    }
    public static final RecArrayRefSubSub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubSub(Memory.lengthenMDS(base), access);
    }
    
    private RecArrayRefSubSub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..63): ARRAY Sub OF Sub
    private static final int OFFSET_CARD_1 = 1;
    public final Sub card1(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (Sub.WORD_SIZE * index);
        return Sub.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): Sub];
public final class RecSub extends MemoryBase {
    public static final String NAME = "RecSub";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecSub longPointer(@Mesa.LONG_POINTER int base) {
        return new RecSub(base);
    }
    public static final RecSub pointer(@Mesa.SHORT_POINTER int base) {
        return new RecSub(Memory.lengthenMDS(base));
    }
    
    private RecSub(@Mesa.LONG_POINTER int base) {
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
    // card1 (1:0..15): Sub
    private static final int OFFSET_CARD_1 = 1;
    public Sub card1(MemoryAccess access) {
        int longPointer = base + OFFSET_CARD_1;
        return Sub.longPointer(longPointer, access);
    }
}

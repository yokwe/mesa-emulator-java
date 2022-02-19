package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubRec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..127): ARRAY Sub OF Rec];
public final class RecArrayRefSubRec extends MemoryBase {
    public static final String NAME = "RecArrayRefSubRec";
    
    public static final int WORD_SIZE =   9;
    public static final int BIT_SIZE  = 144;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubRec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubRec(base, access);
    }
    public static final RecArrayRefSubRec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubRec(Memory.lengthenMDS(base), access);
    }
    
    private RecArrayRefSubRec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..127): ARRAY Sub OF Rec
    private static final int OFFSET_CARD_1 = 1;
    public final Rec card1(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (Rec.WORD_SIZE * index);
        return Rec.longPointer(longPointer, access);
    }
}

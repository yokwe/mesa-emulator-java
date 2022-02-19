package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArraySubOpen: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1): ARRAY [0..0) OF UNSPECIFIED];
public final class RecArraySubOpen extends MemoryBase {
    public static final String NAME = "RecArraySubOpen";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final RecArraySubOpen longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecArraySubOpen(base, access);
    }
    public static final RecArraySubOpen pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecArraySubOpen(Memory.lengthenMDS(base), access);
    }
    
    private RecArraySubOpen(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1): ARRAY [0..0) OF UNSPECIFIED
    private static final int OFFSET_CARD_1 = 1;
    public final UNSPECIFIED card1(int index) {
        int longPointer = base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
}

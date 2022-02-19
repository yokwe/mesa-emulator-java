package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecBit32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): BitField32];
public final class RecBit32 extends MemoryBase {
    public static final String NAME = "RecBit32";
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecBit32 longPointer(@Mesa.LONG_POINTER int base) {
        return new RecBit32(base);
    }
    public static final RecBit32 pointer(@Mesa.SHORT_POINTER int base) {
        return new RecBit32(Memory.lengthenMDS(base));
    }
    
    private RecBit32(@Mesa.LONG_POINTER int base) {
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
    // card1 (1:0..31): BitField32
    private static final int OFFSET_CARD_1 = 1;
    public BitField32 card1(MemoryAccess access) {
        int longPointer = base + OFFSET_CARD_1;
        return BitField32.longPointer(longPointer, access);
    }
}

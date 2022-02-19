package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecBit16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): BitField16];
public final class RecBit16 extends MemoryBase {
    public static final String NAME = "RecBit16";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecBit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecBit16(base, access);
    }
    public static final RecBit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecBit16(Memory.lengthenMDS(base), access);
    }
    
    private RecBit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..15): BitField16
    private static final int OFFSET_CARD_1 = 1;
    public BitField16 card1() {
        int longPointer = base + OFFSET_CARD_1;
        return BitField16.longPointer(longPointer, access);
    }
}

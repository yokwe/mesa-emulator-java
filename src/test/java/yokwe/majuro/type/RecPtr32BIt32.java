package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr32BIt32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO BitField32];
public final class RecPtr32BIt32 extends MemoryBase {
    public static final String NAME = "RecPtr32BIt32";
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecPtr32BIt32 longPointer(@Mesa.POINTER int base) {
        return new RecPtr32BIt32(base);
    }
    public static final RecPtr32BIt32 pointer(@Mesa.SHORT_POINTER int base) {
        return new RecPtr32BIt32(Memory.lengthenMDS(base));
    }
    
    private RecPtr32BIt32(@Mesa.POINTER int base) {
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
    // card1 (1:0..31): LONG POINTER TO BitField32
    private static final int OFFSET_CARD_1 = 1;
    public BitField32 card1(MemoryAccess access) {
        int longPointer = Memory.read32(base + OFFSET_CARD_1);
        return BitField32.longPointer(longPointer, access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): Enum];
public final class RecEnum extends MemoryBase {
    public static final String NAME = "RecEnum";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecEnum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecEnum(base, access);
    }
    public static final RecEnum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecEnum(Memory.lengthenMDS(base), access);
    }
    
    private RecEnum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..15): Enum
    private static final int OFFSET_CARD_1 = 1;
    public Enum card1() {
        int longPointer = base + OFFSET_CARD_1;
        return Enum.longPointer(longPointer, access);
    }
}

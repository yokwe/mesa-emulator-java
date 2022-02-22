package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr32Enum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Enum];
public final class RecPtr32Enum extends MemoryBase {
    public static final String NAME = "RecPtr32Enum";
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecPtr32Enum longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecPtr32Enum(base, access);
    }
    public static final RecPtr32Enum pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecPtr32Enum(Memory.lengthenMDS(base), access);
    }
    
    private RecPtr32Enum(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..31): LONG POINTER TO Enum
    private static final int OFFSET_CARD_1 = 1;
    public Enum card1() {
        int longPointer = Memory.read32(base + OFFSET_CARD_1);
        return Enum.longPointer(longPointer, access);
    }
    public final @Mesa.LONG_POINTER int card1Value() {
        return Memory.read32(base + OFFSET_CARD_1);
    }
    public final RecPtr32Enum card1Value(@Mesa.LONG_POINTER int newValue) {
        Memory.write32(base + OFFSET_CARD_1, newValue);
        return this;
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr32Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Rec];
public final class RecPtr32Rec extends MemoryBase {
    public static final String NAME = "RecPtr32Rec";
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecPtr32Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecPtr32Rec(base, access);
    }
    public static final RecPtr32Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecPtr32Rec(Memory.lengthenMDS(base), access);
    }
    
    private RecPtr32Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..31): LONG POINTER TO Rec
    private static final int OFFSET_CARD_1 = 1;
    public Rec card1() {
        int longPointer = Memory.read32(base + OFFSET_CARD_1);
        return Rec.longPointer(longPointer, access);
    }
    public final @Mesa.LONG_POINTER int card1Value() {
        return Memory.read32(base + OFFSET_CARD_1);
    }
    public final RecPtr32Rec card1Value(@Mesa.LONG_POINTER int newValue) {
        Memory.write32(base + OFFSET_CARD_1, newValue);
        return this;
    }
}

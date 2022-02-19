package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr16Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO Rec];
public final class RecPtr16Rec extends MemoryBase {
    public static final String NAME = "RecPtr16Rec";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecPtr16Rec longPointer(@Mesa.POINTER int base) {
        return new RecPtr16Rec(base);
    }
    public static final RecPtr16Rec pointer(@Mesa.SHORT_POINTER int base) {
        return new RecPtr16Rec(Memory.lengthenMDS(base));
    }
    
    private RecPtr16Rec(@Mesa.POINTER int base) {
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
    // card1 (1:0..15): POINTER TO Rec
    private static final int OFFSET_CARD_1 = 1;
    public Rec card1() {
        int pointer = Memory.read16(base + OFFSET_CARD_1);
        return Rec.pointer(pointer);
    }
}

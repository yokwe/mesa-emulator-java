package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): CARDINAL];
public final class Rec extends MemoryBase {
    public static final String NAME = "Rec";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final Rec longPointer(@Mesa.POINTER int base) {
        return new Rec(base);
    }
    public static final Rec pointer(@Mesa.SHORT_POINTER int base) {
        return new Rec(Memory.lengthenMDS(base));
    }
    
    private Rec(@Mesa.POINTER int base) {
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
    // card1 (1:0..15): CARDINAL
    private static final int OFFSET_CARD_1 = 1;
    public CARDINAL card1(MemoryAccess access) {
        int longPointer = base + OFFSET_CARD_1;
        return CARDINAL.longPointer(longPointer, access);
    }
}

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
    public static final Rec longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new Rec(base, access);
    }
    public static final Rec pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new Rec(Memory.lengthenMDS(base), access);
    }
    
    private Rec(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..15): CARDINAL
    private static final int OFFSET_CARD_1 = 1;
    public CARDINAL card1() {
        int longPointer = base + OFFSET_CARD_1;
        return CARDINAL.longPointer(longPointer, access);
    }
}

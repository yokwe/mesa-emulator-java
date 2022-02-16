package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// RecRec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): Rec];
public final class RecRec extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecRec longPointer(int base) {
        return new RecRec(base);
    }
    public static final RecRec pointer(char base) {
        return new RecRec(Memory.lengthenMDS(base));
    }
    
    private RecRec(int base) {
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
    // card1 (1:0..31): Rec
    private static final int OFFSET_CARD_1 = 1;
    public Rec card1() {
        int longPointer = base + OFFSET_CARD_1;
        return Rec.longPointer(longPointer);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecPtr32Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Rec];
public final class RecPtr32Rec extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecPtr32Rec longPointer(int base) {
        return new RecPtr32Rec(base);
    }
    public static final RecPtr32Rec pointer(char base) {
        return new RecPtr32Rec(Mesa.lengthenMDS(base));
    }
    
    private RecPtr32Rec(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // card0 (0:0..15): CARDINAL
    private static final int OFFSET_CARD_0 = 0;
    public CARDINAL card0(MemoryAccess access) {
        return CARDINAL.longPointer(base + OFFSET_CARD_0, access);
    }
    // card1 (1:0..31): LONG POINTER TO Rec
    private static final int OFFSET_CARD_1 = 1;
    public Rec card1() {
        return Rec.longPointer(Mesa.read32(base + OFFSET_CARD_1));
    }
}

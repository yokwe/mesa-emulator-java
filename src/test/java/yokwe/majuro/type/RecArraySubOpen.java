package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecArraySubOpen: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1): ARRAY [0..0) OF UNSPECIFIED];
public final class RecArraySubOpen extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final RecArraySubOpen longPointer(int base) {
        return new RecArraySubOpen(base);
    }
    public static final RecArraySubOpen pointer(char base) {
        return new RecArraySubOpen(Mesa.lengthenMDS(base));
    }
    
    private RecArraySubOpen(int base) {
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
    // card1 (1): ARRAY [0..0) OF UNSPECIFIED
    private static final int OFFSET_CARD_1 = 1;
    public UNSPECIFIED card1(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}
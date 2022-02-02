package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecArraySubFixed: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY [0..4) OF UNSPECIFIED];
public final class RecArraySubFixed extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArraySubFixed longPointer(int base) {
        return new RecArraySubFixed(base);
    }
    public static final RecArraySubFixed pointer(char base) {
        return new RecArraySubFixed(Mesa.lengthenMDS(base));
    }
    
    private RecArraySubFixed(int base) {
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
    // card1 (1:0..63): ARRAY [0..4) OF UNSPECIFIED
    private static final int OFFSET_CARD_1 = 1;
    public UNSPECIFIED card1(int index, MemoryAccess access) {
        return UNSPECIFIED.longPointer(base + OFFSET_CARD_1 + (UNSPECIFIED.WORD_SIZE * index), access);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): Enum];
public final class RecEnum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecEnum longPointer(int base) {
        return new RecEnum(base);
    }
    public static final RecEnum pointer(char base) {
        return new RecEnum(Mesa.lengthenMDS(base));
    }
    
    private RecEnum(int base) {
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
    // card1 (1:0..15): Enum
    private static final int OFFSET_CARD_1 = 1;
    public Enum card1(MemoryAccess access) {
        return Enum.longPointer(base + OFFSET_CARD_1, access);
    }
}
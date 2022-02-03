package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF Enum];
public final class RecArrayRefSubEnum extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubEnum longPointer(int base) {
        return new RecArrayRefSubEnum(base);
    }
    public static final RecArrayRefSubEnum pointer(char base) {
        return new RecArrayRefSubEnum(Mesa.lengthenMDS(base));
    }
    
    private RecArrayRefSubEnum(int base) {
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
    // card1 (1:0..63): ARRAY Sub OF Enum
    private static final int OFFSET_CARD_1 = 1;
    public Enum card1(int index, MemoryAccess access) {
        return Enum.longPointer(base + OFFSET_CARD_1 + (Enum.WORD_SIZE * index), access);
    }
}
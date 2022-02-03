package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF Sub];
public final class RecArrayRefSubSub extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubSub longPointer(int base) {
        return new RecArrayRefSubSub(base);
    }
    public static final RecArrayRefSubSub pointer(char base) {
        return new RecArrayRefSubSub(Mesa.lengthenMDS(base));
    }
    
    private RecArrayRefSubSub(int base) {
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
    // card1 (1:0..63): ARRAY Sub OF Sub
    private static final int OFFSET_CARD_1 = 1;
    public Sub card1(int index, MemoryAccess access) {
        return Sub.longPointer(base + OFFSET_CARD_1 + (Sub.WORD_SIZE * index), access);
    }
}
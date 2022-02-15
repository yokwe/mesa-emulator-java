package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// RecSub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): Sub];
public final class RecSub extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecSub longPointer(int base) {
        return new RecSub(base);
    }
    public static final RecSub pointer(char base) {
        return new RecSub(Memory.instance.lengthenMDS(base));
    }
    
    private RecSub(int base) {
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
    // card1 (1:0..15): Sub
    private static final int OFFSET_CARD_1 = 1;
    public Sub card1(MemoryAccess access) {
        int longPointer = base + OFFSET_CARD_1;
        return Sub.longPointer(longPointer, access);
    }
}

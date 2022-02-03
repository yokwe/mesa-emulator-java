package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecPtr32Sub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Sub];
public final class RecPtr32Sub extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecPtr32Sub longPointer(int base) {
        return new RecPtr32Sub(base);
    }
    public static final RecPtr32Sub pointer(char base) {
        return new RecPtr32Sub(Mesa.lengthenMDS(base));
    }
    
    private RecPtr32Sub(int base) {
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
    // card1 (1:0..31): LONG POINTER TO Sub
    private static final int OFFSET_CARD_1 = 1;
    public Sub card1(MemoryAccess access) {
        return Sub.longPointer(Mesa.read32(base + OFFSET_CARD_1), access);
    }
}
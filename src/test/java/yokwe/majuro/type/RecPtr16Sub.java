package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecPtr16Sub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO Sub];
public final class RecPtr16Sub extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecPtr16Sub longPointer(int base) {
        return new RecPtr16Sub(base);
    }
    public static final RecPtr16Sub pointer(char base) {
        return new RecPtr16Sub(Mesa.lengthenMDS(base));
    }
    
    private RecPtr16Sub(int base) {
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
    // card1 (1:0..15): POINTER TO Sub
    private static final int OFFSET_CARD_1 = 1;
    public Sub card1(MemoryAccess access) {
        return Sub.pointer(Mesa.read16(base + OFFSET_CARD_1), access);
    }
}
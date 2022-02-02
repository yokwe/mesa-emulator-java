package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubBit16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF BitField16];
public final class RecArrayRefSubBit16 extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubBit16 longPointer(int base) {
        return new RecArrayRefSubBit16(base);
    }
    public static final RecArrayRefSubBit16 pointer(char base) {
        return new RecArrayRefSubBit16(Mesa.lengthenMDS(base));
    }
    
    private RecArrayRefSubBit16(int base) {
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
    // card1 (1:0..63): ARRAY Sub OF BitField16
    private static final int OFFSET_CARD_1 = 1;
    public BitField16 card1(int index, MemoryAccess access) {
        return BitField16.longPointer(base + OFFSET_CARD_1 + (BitField16.WORD_SIZE * index), access);
    }
}

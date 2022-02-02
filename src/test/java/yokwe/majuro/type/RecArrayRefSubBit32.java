package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubBit32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..127): ARRAY Sub OF BitField32];
public final class RecArrayRefSubBit32 extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   9;
    public static final int BIT_SIZE  = 144;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubBit32 longPointer(int base) {
        return new RecArrayRefSubBit32(base);
    }
    public static final RecArrayRefSubBit32 pointer(char base) {
        return new RecArrayRefSubBit32(Mesa.lengthenMDS(base));
    }
    
    private RecArrayRefSubBit32(int base) {
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
    // card1 (1:0..127): ARRAY Sub OF BitField32
    private static final int OFFSET_CARD_1 = 1;
    public BitField32 card1(int index, MemoryAccess access) {
        return BitField32.longPointer(base + OFFSET_CARD_1 + (BitField32.WORD_SIZE * index), access);
    }
}

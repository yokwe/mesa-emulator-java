package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubBit32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..127): ARRAY Sub OF BitField32];
public final class RecArrayRefSubBit32 extends MemoryBase {
    public static final String NAME = "RecArrayRefSubBit32";
    
    public static final int WORD_SIZE =   9;
    public static final int BIT_SIZE  = 144;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubBit32 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubBit32(base, access);
    }
    public static final RecArrayRefSubBit32 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecArrayRefSubBit32(Memory.lengthenMDS(base), access);
    }
    
    private RecArrayRefSubBit32(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Access to Field of Record
    //
    // card0 (0:0..15): CARDINAL
    private static final int OFFSET_CARD_0 = 0;
    public CARDINAL card0() {
        int longPointer = base + OFFSET_CARD_0;
        return CARDINAL.longPointer(longPointer, access);
    }
    // card1 (1:0..127): ARRAY Sub OF BitField32
    private static final int OFFSET_CARD_1 = 1;
    public final BitField32 card1(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (BitField32.WORD_SIZE * index);
        return BitField32.longPointer(longPointer, access);
    }
}

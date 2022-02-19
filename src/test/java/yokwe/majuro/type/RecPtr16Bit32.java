package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr16Bit32: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO BitField32];
public final class RecPtr16Bit32 extends MemoryBase {
    public static final String NAME = "RecPtr16Bit32";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecPtr16Bit32 longPointer(@Mesa.LONG_POINTER int base) {
        return new RecPtr16Bit32(base);
    }
    public static final RecPtr16Bit32 pointer(@Mesa.SHORT_POINTER int base) {
        return new RecPtr16Bit32(Memory.lengthenMDS(base));
    }
    
    private RecPtr16Bit32(@Mesa.LONG_POINTER int base) {
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
    // card1 (1:0..15): POINTER TO BitField32
    private static final int OFFSET_CARD_1 = 1;
    public BitField32 card1(MemoryAccess access) {
        int pointer = Memory.read16(base + OFFSET_CARD_1);
        return BitField32.pointer(pointer, access);
    }
}

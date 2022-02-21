package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr16Bit16: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO BitField16];
public final class RecPtr16Bit16 extends MemoryBase {
    public static final String NAME = "RecPtr16Bit16";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecPtr16Bit16 longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecPtr16Bit16(base, access);
    }
    public static final RecPtr16Bit16 pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecPtr16Bit16(Memory.lengthenMDS(base), access);
    }
    
    private RecPtr16Bit16(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..15): POINTER TO BitField16
    private static final int OFFSET_CARD_1 = 1;
    public BitField16 card1() {
        int pointer = Memory.read16(base + OFFSET_CARD_1);
        return BitField16.pointer(pointer, access);
    }
    public final @Mesa.SHORT_POINTER int card1Value() {
        return Memory.read16(base + OFFSET_CARD_1);
    }
    public final void card1Value(@Mesa.SHORT_POINTER int newValue) {
        Memory.write16(base + OFFSET_CARD_1, newValue);
    }
}

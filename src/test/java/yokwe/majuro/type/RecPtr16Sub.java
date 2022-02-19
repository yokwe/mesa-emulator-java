package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr16Sub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): POINTER TO Sub];
public final class RecPtr16Sub extends MemoryBase {
    public static final String NAME = "RecPtr16Sub";
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final RecPtr16Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecPtr16Sub(base, access);
    }
    public static final RecPtr16Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecPtr16Sub(Memory.lengthenMDS(base), access);
    }
    
    private RecPtr16Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..15): POINTER TO Sub
    private static final int OFFSET_CARD_1 = 1;
    public Sub card1() {
        int pointer = Memory.read16(base + OFFSET_CARD_1);
        return Sub.pointer(pointer, access);
    }
}

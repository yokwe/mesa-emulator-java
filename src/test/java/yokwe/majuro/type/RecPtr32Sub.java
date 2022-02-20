package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecPtr32Sub: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..31): LONG POINTER TO Sub];
public final class RecPtr32Sub extends MemoryBase {
    public static final String NAME = "RecPtr32Sub";
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final RecPtr32Sub longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new RecPtr32Sub(base, access);
    }
    public static final RecPtr32Sub pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new RecPtr32Sub(Memory.lengthenMDS(base), access);
    }
    
    private RecPtr32Sub(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    // card1 (1:0..31): LONG POINTER TO Sub
    private static final int OFFSET_CARD_1 = 1;
    public Sub card1() {
        int longPointer = Memory.read32(base + OFFSET_CARD_1);
        return Sub.longPointer(longPointer, access);
    }
    public final @Mesa.LONG_POINTER int card1Value(int index) {
        return Memory.read32(base + OFFSET_CARD_1);
    }
    public final void card1Value(int index, @Mesa.LONG_POINTER int newValue) {
        Memory.write32(base + OFFSET_CARD_1, newValue);
    }
}

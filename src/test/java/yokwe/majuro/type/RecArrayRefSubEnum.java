package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;

// RecArrayRefSubEnum: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..63): ARRAY Sub OF Enum];
public final class RecArrayRefSubEnum extends MemoryBase {
    public static final String NAME = "RecArrayRefSubEnum";
    
    public static final int WORD_SIZE =  5;
    public static final int BIT_SIZE  = 80;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubEnum longPointer(@Mesa.POINTER int base) {
        return new RecArrayRefSubEnum(base);
    }
    public static final RecArrayRefSubEnum pointer(@Mesa.SHORT_POINTER int base) {
        return new RecArrayRefSubEnum(Memory.lengthenMDS(base));
    }
    
    private RecArrayRefSubEnum(@Mesa.POINTER int base) {
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
    // card1 (1:0..63): ARRAY Sub OF Enum
    private static final int OFFSET_CARD_1 = 1;
    public final Enum card1(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (Enum.WORD_SIZE * index);
        return Enum.longPointer(longPointer, access);
    }
}

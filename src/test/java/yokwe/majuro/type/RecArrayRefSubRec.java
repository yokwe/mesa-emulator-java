package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// RecArrayRefSubRec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..127): ARRAY Sub OF Rec];
public final class RecArrayRefSubRec extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =   9;
    public static final int BIT_SIZE  = 144;
    
    //
    // Constructor
    //
    public static final RecArrayRefSubRec longPointer(int base) {
        return new RecArrayRefSubRec(base);
    }
    public static final RecArrayRefSubRec pointer(char base) {
        return new RecArrayRefSubRec(Memory.instance.lengthenMDS(base));
    }
    
    private RecArrayRefSubRec(int base) {
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
    // card1 (1:0..127): ARRAY Sub OF Rec
    private static final int OFFSET_CARD_1 = 1;
    public final Rec card1(int index) {
        if (Debug.ENABLE_CHECK_VALUE) Sub.checkValue(index);
        int longPointer = base + OFFSET_CARD_1 + (Rec.WORD_SIZE * index);
        return Rec.longPointer(longPointer);
    }
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// Rec: TYPE = RECORD[card0 (0:0..15): CARDINAL, card1 (1:0..15): CARDINAL];
public final class Rec extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public static final Rec longPointer(int base) {
        return new Rec(base);
    }
    public static final Rec pointer(char base) {
        return new Rec(Memory.instance.lengthenMDS(base));
    }
    
    private Rec(int base) {
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
    // card1 (1:0..15): CARDINAL
    private static final int OFFSET_CARD_1 = 1;
    public CARDINAL card1(MemoryAccess access) {
        int longPointer = base + OFFSET_CARD_1;
        return CARDINAL.longPointer(longPointer, access);
    }
}

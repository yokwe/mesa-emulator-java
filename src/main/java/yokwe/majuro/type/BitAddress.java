package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// BitAddress: TYPE = RECORD[word (0:0..31): LONG POINTER, reserved (2:0..11): UNSPECIFIED, bit (2:12..15): CARDINAL];
public final class BitAddress extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  3;
    public static final int BIT_SIZE  = 48;
    
    //
    // Constructor
    //
    public static final BitAddress longPointer(int base) {
        return new BitAddress(base);
    }
    public static final BitAddress pointer(char base) {
        return new BitAddress(Memory.instance.lengthenMDS(base));
    }
    
    private BitAddress(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // word (0:0..31): LONG POINTER
    private static final int OFFSET_WORD = 0;
    public LONG_POINTER word() {
        int longPointer = base + OFFSET_WORD;
        return LONG_POINTER.longPointer(longPointer);
    }
    // reserved (2:0..11): UNSPECIFIED
    // FIXME  Field is not aligned
    // bit (2:12..15): CARDINAL
    // FIXME  Field is not aligned
}

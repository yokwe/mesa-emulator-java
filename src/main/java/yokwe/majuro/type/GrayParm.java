package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// GrayParm: TYPE = RECORD[reserved (0:0..3): NIBBLE, yOffset (0:4..7): NIBBLE, widthMinusOne (0:8..11): NIBBLE, heightMinusOne (0:12..15): NIBBLE];
public final class GrayParm extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final GrayParm value(char value) {
        return new GrayParm(value);
    }
    public static final GrayParm longPointer(int base, MemoryAccess access) {
        return new GrayParm(base, access);
    }
    public static final GrayParm pointer(char base, MemoryAccess access) {
        return new GrayParm(Memory.lengthenMDS(base), access);
    }
    
    private GrayParm(char value) {
        super(value);
    }
    private GrayParm(int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // reserved       (0:0..3):   NIBBLE
    // yOffset        (0:4..7):   NIBBLE
    // widthMinusOne  (0:8..11):  NIBBLE
    // heightMinusOne (0:12..15): NIBBLE
    
    private static final int RESERVED_MASK          = 0b1111_0000_0000_0000;
    private static final int RESERVED_SHIFT         =                    12;
    private static final int Y_OFFSET_MASK          = 0b0000_1111_0000_0000;
    private static final int Y_OFFSET_SHIFT         =                     8;
    private static final int WIDTH_MINUS_ONE_MASK   = 0b0000_0000_1111_0000;
    private static final int WIDTH_MINUS_ONE_SHIFT  =                     4;
    private static final int HEIGHT_MINUS_ONE_MASK  = 0b0000_0000_0000_1111;
    private static final int HEIGHT_MINUS_ONE_SHIFT =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final char reserved() {
        return (char)((value & RESERVED_MASK) >>> RESERVED_SHIFT);
    }
    public final void reserved(char newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }
    
    public final char yOffset() {
        return (char)((value & Y_OFFSET_MASK) >>> Y_OFFSET_SHIFT);
    }
    public final void yOffset(char newValue) {
        value = (value & ~Y_OFFSET_MASK) | ((newValue << Y_OFFSET_SHIFT) & Y_OFFSET_MASK);
    }
    
    public final char widthMinusOne() {
        return (char)((value & WIDTH_MINUS_ONE_MASK) >>> WIDTH_MINUS_ONE_SHIFT);
    }
    public final void widthMinusOne(char newValue) {
        value = (value & ~WIDTH_MINUS_ONE_MASK) | ((newValue << WIDTH_MINUS_ONE_SHIFT) & WIDTH_MINUS_ONE_MASK);
    }
    
    public final char heightMinusOne() {
        return (char)((value & HEIGHT_MINUS_ONE_MASK) >>> HEIGHT_MINUS_ONE_SHIFT);
    }
    public final void heightMinusOne(char newValue) {
        value = (value & ~HEIGHT_MINUS_ONE_MASK) | ((newValue << HEIGHT_MINUS_ONE_SHIFT) & HEIGHT_MINUS_ONE_MASK);
    }
    
}

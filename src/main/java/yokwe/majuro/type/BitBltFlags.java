package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;

// BitBltFlags: TYPE = RECORD[direction (0:0..0): Direction, disjoint (0:1..1): BOOLEAN, disjointItems (0:2..2): BOOLEAN, gray (0:3..3): BOOLEAN, srcFunc (0:4..4): SrcFunc, dstFunc (0:5..6): DstFunc, reserved (0:7..15): UNSPECIFIED];
public final class BitBltFlags extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final BitBltFlags value(char value) {
        return new BitBltFlags(value);
    }
    public static final BitBltFlags longPointer(int base, MemoryAccess access) {
        return new BitBltFlags(base, access);
    }
    public static final BitBltFlags pointer(char base, MemoryAccess access) {
        return new BitBltFlags(Memory.lengthenMDS(base), access);
    }
    
    private BitBltFlags(char value) {
        super(value);
    }
    private BitBltFlags(int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // direction     (0:0..0):  Direction
    // disjoint      (0:1..1):  BOOLEAN
    // disjointItems (0:2..2):  BOOLEAN
    // gray          (0:3..3):  BOOLEAN
    // srcFunc       (0:4..4):  SrcFunc
    // dstFunc       (0:5..6):  DstFunc
    // reserved      (0:7..15): UNSPECIFIED
    
    private static final int DIRECTION_MASK       = 0b1000_0000_0000_0000;
    private static final int DIRECTION_SHIFT      =                    15;
    private static final int DISJOINT_MASK        = 0b0100_0000_0000_0000;
    private static final int DISJOINT_SHIFT       =                    14;
    private static final int DISJOINT_ITEMS_MASK  = 0b0010_0000_0000_0000;
    private static final int DISJOINT_ITEMS_SHIFT =                    13;
    private static final int GRAY_MASK            = 0b0001_0000_0000_0000;
    private static final int GRAY_SHIFT           =                    12;
    private static final int SRC_FUNC_MASK        = 0b0000_1000_0000_0000;
    private static final int SRC_FUNC_SHIFT       =                    11;
    private static final int DST_FUNC_MASK        = 0b0000_0110_0000_0000;
    private static final int DST_FUNC_SHIFT       =                     9;
    private static final int RESERVED_MASK        = 0b0000_0001_1111_1111;
    private static final int RESERVED_SHIFT       =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final char direction() {
        return (char)((value & DIRECTION_MASK) >>> DIRECTION_SHIFT);
    }
    public final void direction(char newValue) {
        value = (value & ~DIRECTION_MASK) | ((newValue << DIRECTION_SHIFT) & DIRECTION_MASK);
    }
    
    public final char disjoint() {
        return (char)((value & DISJOINT_MASK) >>> DISJOINT_SHIFT);
    }
    public final void disjoint(char newValue) {
        value = (value & ~DISJOINT_MASK) | ((newValue << DISJOINT_SHIFT) & DISJOINT_MASK);
    }
    
    public final char disjointItems() {
        return (char)((value & DISJOINT_ITEMS_MASK) >>> DISJOINT_ITEMS_SHIFT);
    }
    public final void disjointItems(char newValue) {
        value = (value & ~DISJOINT_ITEMS_MASK) | ((newValue << DISJOINT_ITEMS_SHIFT) & DISJOINT_ITEMS_MASK);
    }
    
    public final char gray() {
        return (char)((value & GRAY_MASK) >>> GRAY_SHIFT);
    }
    public final void gray(char newValue) {
        value = (value & ~GRAY_MASK) | ((newValue << GRAY_SHIFT) & GRAY_MASK);
    }
    
    public final char srcFunc() {
        return (char)((value & SRC_FUNC_MASK) >>> SRC_FUNC_SHIFT);
    }
    public final void srcFunc(char newValue) {
        value = (value & ~SRC_FUNC_MASK) | ((newValue << SRC_FUNC_SHIFT) & SRC_FUNC_MASK);
    }
    
    public final char dstFunc() {
        return (char)((value & DST_FUNC_MASK) >>> DST_FUNC_SHIFT);
    }
    public final void dstFunc(char newValue) {
        value = (value & ~DST_FUNC_MASK) | ((newValue << DST_FUNC_SHIFT) & DST_FUNC_MASK);
    }
    
    public final char reserved() {
        return (char)((value & RESERVED_MASK) >>> RESERVED_SHIFT);
    }
    public final void reserved(char newValue) {
        value = (value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK);
    }
    
}

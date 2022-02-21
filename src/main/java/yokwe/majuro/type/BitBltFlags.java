package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// BitBltFlags: TYPE = RECORD[direction (0:0..0): Direction, disjoint (0:1..1): BOOLEAN, disjointItems (0:2..2): BOOLEAN, gray (0:3..3): BOOLEAN, srcFunc (0:4..4): SrcFunc, dstFunc (0:5..6): DstFunc, reserved (0:7..15): UNSPECIFIED];
public final class BitBltFlags extends MemoryData16 {
    public static final String NAME = "BitBltFlags";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final BitBltFlags value(@Mesa.CARD16 int value) {
        return new BitBltFlags(value);
    }
    public static final BitBltFlags value() {
        return new BitBltFlags(0);
    }
    public static final BitBltFlags longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new BitBltFlags(base, access);
    }
    public static final BitBltFlags pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BitBltFlags(Memory.lengthenMDS(base), access);
    }
    
    private BitBltFlags(@Mesa.CARD16 int value) {
        super(value);
    }
    private BitBltFlags(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    public final @Mesa.CARD16 int direction() {
        return Types.toCARD16((value & DIRECTION_MASK) >>> DIRECTION_SHIFT);
    }
    public final BitBltFlags direction(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~DIRECTION_MASK) | ((newValue << DIRECTION_SHIFT) & DIRECTION_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int disjoint() {
        return Types.toCARD16((value & DISJOINT_MASK) >>> DISJOINT_SHIFT);
    }
    public final BitBltFlags disjoint(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~DISJOINT_MASK) | ((newValue << DISJOINT_SHIFT) & DISJOINT_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int disjointItems() {
        return Types.toCARD16((value & DISJOINT_ITEMS_MASK) >>> DISJOINT_ITEMS_SHIFT);
    }
    public final BitBltFlags disjointItems(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~DISJOINT_ITEMS_MASK) | ((newValue << DISJOINT_ITEMS_SHIFT) & DISJOINT_ITEMS_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int gray() {
        return Types.toCARD16((value & GRAY_MASK) >>> GRAY_SHIFT);
    }
    public final BitBltFlags gray(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~GRAY_MASK) | ((newValue << GRAY_SHIFT) & GRAY_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int srcFunc() {
        return Types.toCARD16((value & SRC_FUNC_MASK) >>> SRC_FUNC_SHIFT);
    }
    public final BitBltFlags srcFunc(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~SRC_FUNC_MASK) | ((newValue << SRC_FUNC_SHIFT) & SRC_FUNC_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int dstFunc() {
        return Types.toCARD16((value & DST_FUNC_MASK) >>> DST_FUNC_SHIFT);
    }
    public final BitBltFlags dstFunc(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~DST_FUNC_MASK) | ((newValue << DST_FUNC_SHIFT) & DST_FUNC_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int reserved() {
        return Types.toCARD16((value & RESERVED_MASK) >>> RESERVED_SHIFT);
    }
    public final BitBltFlags reserved(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RESERVED_MASK) | ((newValue << RESERVED_SHIFT) & RESERVED_MASK));
        return this;
    }
    
}

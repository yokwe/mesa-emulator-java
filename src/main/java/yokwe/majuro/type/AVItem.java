package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
public final class AVItem extends MemoryData16 {
    public static final String NAME = "AVItem";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final AVItem value(@Mesa.CARD16 int value) {
        return new AVItem(value);
    }
    public static final AVItem longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new AVItem(base, access);
    }
    public static final AVItem pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new AVItem(Memory.lengthenMDS(base), access);
    }
    
    private AVItem(@Mesa.CARD16 int value) {
        super(value);
    }
    private AVItem(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // data (0:0..13):  UNSPECIFIED
    // tag  (0:14..15): AVItemType
    
    private static final int DATA_MASK  = 0b1111_1111_1111_1100;
    private static final int DATA_SHIFT =                     2;
    private static final int TAG_MASK   = 0b0000_0000_0000_0011;
    private static final int TAG_SHIFT  =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final @Mesa.CARD16 int data() {
        return Types.toCARD16((value & DATA_MASK) >>> DATA_SHIFT);
    }
    public final void data(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~DATA_MASK) | ((newValue << DATA_SHIFT) & DATA_MASK));
    }
    
    public final @Mesa.CARD16 int tag() {
        return Types.toCARD16((value & TAG_MASK) >>> TAG_SHIFT);
    }
    public final void tag(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~TAG_MASK) | ((newValue << TAG_SHIFT) & TAG_MASK));
    }
    
}

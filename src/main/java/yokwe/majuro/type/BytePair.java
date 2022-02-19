package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// BytePair: TYPE = RECORD[left (0:0..7): BYTE, right (0:8..15): BYTE];
public final class BytePair extends MemoryData16 {
    public static final String NAME = "BytePair";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final BytePair value(@Mesa.CARD16 int value) {
        return new BytePair(value);
    }
    public static final BytePair longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new BytePair(base, access);
    }
    public static final BytePair pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new BytePair(Memory.lengthenMDS(base), access);
    }
    
    private BytePair(@Mesa.CARD16 int value) {
        super(value);
    }
    private BytePair(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // left  (0:0..7):  BYTE
    // right (0:8..15): BYTE
    
    private static final int LEFT_MASK   = 0b1111_1111_0000_0000;
    private static final int LEFT_SHIFT  =                     8;
    private static final int RIGHT_MASK  = 0b0000_0000_1111_1111;
    private static final int RIGHT_SHIFT =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final @Mesa.CARD16 int left() {
        return Types.toCARD16((value & LEFT_MASK) >>> LEFT_SHIFT);
    }
    public final void left(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK));
    }
    
    public final @Mesa.CARD16 int right() {
        return Types.toCARD16((value & RIGHT_MASK) >>> RIGHT_SHIFT);
    }
    public final void right(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK));
    }
    
}

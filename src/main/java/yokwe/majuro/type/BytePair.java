package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// BytePair: TYPE = RECORD[left (0:0..7): BYTE, right (0:8..15): BYTE];
public final class BytePair extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final BytePair value(char value) {
        return new BytePair(value);
    }
    public static final BytePair longPointer(int base, MemoryAccess access) {
        return new BytePair(base, access);
    }
    public static final BytePair pointer(char base, MemoryAccess access) {
        return new BytePair(Mesa.lengthenMDS(base), access);
    }
    
    private BytePair(char value) {
        super(value);
    }
    private BytePair(int base, MemoryAccess access) {
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
    public final char left() {
        return (char)((value & LEFT_MASK) >>> LEFT_SHIFT);
    }
    public final void left(char newValue) {
        value = (value & ~LEFT_MASK) | ((newValue << LEFT_SHIFT) & LEFT_MASK);
    }
    
    public final char right() {
        return (char)((value & RIGHT_MASK) >>> RIGHT_SHIFT);
    }
    public final void right(char newValue) {
        value = (value & ~RIGHT_MASK) | ((newValue << RIGHT_SHIFT) & RIGHT_MASK);
    }
    
}

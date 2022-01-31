package yokwe.majuro.type;

import yokwe.majuro.mesa.Mesa;

// LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
public final class LocalWord extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final LocalWord value(char value) {
        return new LocalWord(value);
    }
    public static final LocalWord longPointer(int base, MemoryAccess access) {
        return new LocalWord(base, access);
    }
    public static final LocalWord pointer(char base, MemoryAccess access) {
        return new LocalWord(Mesa.lengthenMDS(base), access);
    }
    
    private LocalWord(char value) {
        super(value);
    }
    private LocalWord(int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // available (0:0..7):  BYTE
    // fsi       (0:8..15): FSIndex
    
    private static final int AVAILABLE_MASK  = 0b1111_1111_0000_0000;
    private static final int AVAILABLE_SHIFT =                     8;
    private static final int FSI_MASK        = 0b0000_0000_1111_1111;
    private static final int FSI_SHIFT       =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final char available() {
        return (char)((value & AVAILABLE_MASK) >> AVAILABLE_SHIFT);
    }
    public final void available(char newValue) {
        value = (value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK);
    }
    
    public final char fsi() {
        return (char)((value & FSI_MASK) >> FSI_SHIFT);
    }
    public final void fsi(char newValue) {
        value = (value & ~FSI_MASK) | ((newValue << FSI_SHIFT) & FSI_MASK);
    }
    
}

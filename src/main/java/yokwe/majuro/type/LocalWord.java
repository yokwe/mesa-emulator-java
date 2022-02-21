package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// LocalWord: TYPE = RECORD[available (0:0..7): BYTE, fsi (0:8..15): FSIndex];
public final class LocalWord extends MemoryData16 {
    public static final String NAME = "LocalWord";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final LocalWord value(@Mesa.CARD16 int value) {
        return new LocalWord(value);
    }
    public static final LocalWord value() {
        return new LocalWord(0);
    }
    public static final LocalWord longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new LocalWord(base, access);
    }
    public static final LocalWord pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new LocalWord(Memory.lengthenMDS(base), access);
    }
    
    private LocalWord(@Mesa.CARD16 int value) {
        super(value);
    }
    private LocalWord(@Mesa.LONG_POINTER int base, MemoryAccess access) {
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
    public final @Mesa.CARD16 int available() {
        return Types.toCARD16((value & AVAILABLE_MASK) >>> AVAILABLE_SHIFT);
    }
    public final LocalWord available(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~AVAILABLE_MASK) | ((newValue << AVAILABLE_SHIFT) & AVAILABLE_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int fsi() {
        return Types.toCARD16((value & FSI_MASK) >>> FSI_SHIFT);
    }
    public final LocalWord fsi(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~FSI_MASK) | ((newValue << FSI_SHIFT) & FSI_MASK));
        return this;
    }
    
}

package yokwe.majuro.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Mesa;
import yokwe.majuro.mesa.Types;

// GlobalWord: TYPE = RECORD[gfi (0:0..13): GFTIndex, trapxfers (0:14..14): BOOLEAN, codelinks (0:15..15): BOOLEAN];
public final class GlobalWord extends MemoryData16 {
    public static final String NAME = "GlobalWord";
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public static final GlobalWord value(@Mesa.CARD16 int value) {
        return new GlobalWord(value);
    }
    public static final GlobalWord value() {
        return new GlobalWord(0);
    }
    public static final GlobalWord longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        return new GlobalWord(base, access);
    }
    public static final GlobalWord pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {
        return new GlobalWord(Memory.lengthenMDS(base), access);
    }
    
    private GlobalWord(@Mesa.CARD16 int value) {
        super(value);
    }
    private GlobalWord(@Mesa.LONG_POINTER int base, MemoryAccess access) {
        super(base, access);
    }
    
    //
    // Bit Field
    //
    
    // gfi       (0:0..13):  GFTIndex
    // trapxfers (0:14..14): BOOLEAN
    // codelinks (0:15..15): BOOLEAN
    
    private static final int GFI_MASK        = 0b1111_1111_1111_1100;
    private static final int GFI_SHIFT       =                     2;
    private static final int TRAPXFERS_MASK  = 0b0000_0000_0000_0010;
    private static final int TRAPXFERS_SHIFT =                     1;
    private static final int CODELINKS_MASK  = 0b0000_0000_0000_0001;
    private static final int CODELINKS_SHIFT =                     0;
    
    //
    // Bit Field Access Methods
    //
    public final @Mesa.CARD16 int gfi() {
        return Types.toCARD16((value & GFI_MASK) >>> GFI_SHIFT);
    }
    public final GlobalWord gfi(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~GFI_MASK) | ((newValue << GFI_SHIFT) & GFI_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int trapxfers() {
        return Types.toCARD16((value & TRAPXFERS_MASK) >>> TRAPXFERS_SHIFT);
    }
    public final GlobalWord trapxfers(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~TRAPXFERS_MASK) | ((newValue << TRAPXFERS_SHIFT) & TRAPXFERS_MASK));
        return this;
    }
    
    public final @Mesa.CARD16 int codelinks() {
        return Types.toCARD16((value & CODELINKS_MASK) >>> CODELINKS_SHIFT);
    }
    public final GlobalWord codelinks(@Mesa.CARD16 int newValue) {
        value = Types.toCARD16((value & ~CODELINKS_MASK) | ((newValue << CODELINKS_SHIFT) & CODELINKS_MASK));
        return this;
    }
    
}

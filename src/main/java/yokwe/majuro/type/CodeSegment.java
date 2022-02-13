package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Mesa;

// CodeSegment: TYPE = RECORD[available (0:0..63): ARRAY [0..4) OF UNSPECIFIED, code (4): BLOCK];
public final class CodeSegment extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  4;
    public static final int BIT_SIZE  = 64;
    
    //
    // Constructor
    //
    public static final CodeSegment longPointer(int base) {
        return new CodeSegment(base);
    }
    public static final CodeSegment pointer(char base) {
        return new CodeSegment(Mesa.lengthenMDS(base));
    }
    
    private CodeSegment(int base) {
        super(base);
    }
    
    //
    // Access to Field of Record
    //
    // available (0:0..63): ARRAY [0..4) OF UNSPECIFIED
    private static final int OFFSET_AVAILABLE = 0;
    private static final class AvailableIndex {
        private static final ContextSubrange context = new ContextSubrange("CodeSegment", 0, 3);
        private static final void checkValue(int value) {
            context.check(value);
        }
    }
    public final UNSPECIFIED available(int index, MemoryAccess access) {
        if (Debug.ENABLE_CHECK_VALUE) AvailableIndex.checkValue(index);
        int longPointer = base + OFFSET_AVAILABLE + (UNSPECIFIED.WORD_SIZE * index);
        return UNSPECIFIED.longPointer(longPointer, access);
    }
    // code (4): BLOCK
    private static final int OFFSET_CODE = 4;
    public BLOCK code() {
        int longPointer = base + OFFSET_CODE;
        return BLOCK.longPointer(longPointer);
    }
}

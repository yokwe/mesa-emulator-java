package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// GFTIndex: TYPE = [0..16384);
public final class GFTIndex extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =     1;
    public static final int BIT_SIZE  =    14;
                                              
    public static final int MIN_VALUE =     0;
    public static final int MAX_VALUE = 16383;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final GFTIndex value(char value) {
        return new GFTIndex(value);
    }
    public static final GFTIndex longPointer(int base, MemoryAccess access) {
        return new GFTIndex(base, access);
    }
    public static final GFTIndex pointer(char base, MemoryAccess access) {
        return new GFTIndex(Memory.instance.lengthenMDS(base), access);
    }
    
    private GFTIndex(char value) {
        super(value);
    }
    private GFTIndex(int base, MemoryAccess access) {
        super(base, access);
    }
}

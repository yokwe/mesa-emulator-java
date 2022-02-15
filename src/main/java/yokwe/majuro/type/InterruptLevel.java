package yokwe.majuro.type;

import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

// InterruptLevel: TYPE = [0..16);
public final class InterruptLevel extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  =  4;
                                           
    public static final int MIN_VALUE =  0;
    public static final int MAX_VALUE = 15;
    
    private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);
    
    public static final void checkValue(int value) {
        if (Debug.ENABLE_CHECK_VALUE) context.check(value);
    }
    
    //
    // Constructor
    //
    public static final InterruptLevel value(char value) {
        return new InterruptLevel(value);
    }
    public static final InterruptLevel longPointer(int base, MemoryAccess access) {
        return new InterruptLevel(base, access);
    }
    public static final InterruptLevel pointer(char base, MemoryAccess access) {
        return new InterruptLevel(Memory.instance.lengthenMDS(base), access);
    }
    
    private InterruptLevel(char value) {
        super(value);
    }
    private InterruptLevel(int base, MemoryAccess access) {
        super(base, access);
    }
}

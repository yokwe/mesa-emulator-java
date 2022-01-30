package yokwe.majuro.type;

// LONG POINTER: TYPE = LONG POINTER;
public class LONG_POINTER extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public LONG_POINTER(int base) {
        super(base);
    }
}

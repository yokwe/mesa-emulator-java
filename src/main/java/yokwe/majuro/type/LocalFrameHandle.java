package yokwe.majuro.type;

// LocalFrameHandle: TYPE = POINTER TO LocalVariables;
public class LocalFrameHandle extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  1;
    public static final int BIT_SIZE  = 16;
    
    //
    // Constructor
    //
    public LocalFrameHandle(int base) {
        super(base);
    }
}

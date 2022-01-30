package yokwe.majuro.type;

// GlobalFrameHandle: TYPE = LONG POINTER TO GlobalVariables;
public class GlobalFrameHandle extends BLOCK {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;

    //
    // Constructor
    //
    public GlobalFrameHandle(int base) {
        super(base);
    }
}

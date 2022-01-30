package yokwe.majuro.type;

// StateHandle: TYPE = LONG POINTER TO StateVector;
public class StateHandle extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();
    
    public static final int WORD_SIZE =  2;
    public static final int BIT_SIZE  = 32;
    
    //
    // Constructor
    //
    public StateHandle(int base) {
        super(base);
    }
}

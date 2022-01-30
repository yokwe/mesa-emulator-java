package yokwe.majuro.type;

// BOOLEAN: TYPE = BOOLEAN;
public class BOOLEAN extends MemoryData16 {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE = 1;
    public static final int BIT_SIZE  = 1;

    //
    // Constructor
    //
    public BOOLEAN(char value) {
        super(value);
    }
    public BOOLEAN(int base, MemoryAccess access) {
        super(base, access);
    }
}

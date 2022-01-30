package yokwe.majuro.type;

// BLOCK: TYPE = ARRAY [0..0) OF UNSPECIFIED;
public class BLOCK extends MemoryBase {
    public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();
    public static final String   NAME = SELF.getSimpleName();

    public static final int WORD_SIZE = 0;
    public static final int BIT_SIZE  = 0;

    //
    // Constructor
    //
    public BLOCK(int base) {
        super(base);
    }
    //
    // Access to Element of Array
    //
    public UNSPECIFIED element(int index, MemoryAccess memoryAccess) {
        return new UNSPECIFIED(base + (UNSPECIFIED.WORD_SIZE * index), memoryAccess);
    }
}

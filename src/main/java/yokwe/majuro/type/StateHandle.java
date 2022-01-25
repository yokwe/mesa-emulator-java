package yokwe.majuro.type;

// StateHandle: TYPE = LONG POINTER TO StateVector;
public final class StateHandle extends MemoryData32 {
    public static final String NAME      = "StateHandle";
    public static final int    WORD_SIZE =             2;
    public static final int    BIT_SIZE  =            32;

    //
    // Constructor
    //
    public StateHandle(int value) {
        super(value);
    }
    public StateHandle(int base, MemoryAccess access) {
        super(base, access);
    }
    public StateHandle(int base, int index, MemoryAccess access) {
        super(base + (WORD_SIZE * index), access);
    }
}

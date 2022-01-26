package yokwe.majuro.type;

// StateHandle: TYPE = LONG POINTER TO StateVector;
public final class StateHandle extends MemoryBase {
    public static final String NAME      = "StateHandle";
    public static final int    WORD_SIZE =             2;
    public static final int    BIT_SIZE  =            32;

    //
    // Constructor
    //
    public StateHandle(int base) {
        super(base);
    }
    public StateHandle(int base, int index) {
        super(base + (StateVector.WORD_SIZE * index));
    }
}

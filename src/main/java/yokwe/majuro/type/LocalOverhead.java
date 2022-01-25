package yokwe.majuro.type;

// LocalOverhead: TYPE = RECORD[word (0:0..15): LocalWord, returnlink (1:0..15): ShortControlLink, globallink (2:0..15): GFTHandle, pc (3:0..15): CARDINAL, local (4): LocalVariables];
public final class LocalOverhead {
    public static final String NAME     = "LocalOverhead";
    public static final int    SIZE     =               4;
    public static final int    BIT_SIZE =              64;

    public final int base;

    public LocalOverhead(int value) {
        this.base = value;
    }

    public static final int OFFSET_WORD       = 0; // word       (0:0..15): LocalWord
    public static final int OFFSET_RETURNLINK = 1; // returnlink (1:0..15): ShortControlLink
    public static final int OFFSET_GLOBALLINK = 2; // globallink (2:0..15): GFTHandle
    public static final int OFFSET_PC         = 3; // pc         (3:0..15): CARDINAL
    public static final int OFFSET_LOCAL      = 4; // local      (4):       LocalVariables

}

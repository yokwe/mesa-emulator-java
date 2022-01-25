package yokwe.majuro.type;

// BitAddress: TYPE = RECORD[word (0:0..31): LONG POINTER, reserved (2:0..11): UNSPECIFIED, bit (2:12..15): CARDINAL];
public final class BitAddress {
    public static final String NAME     = "BitAddress";
    public static final int    SIZE     =            3;
    public static final int    BIT_SIZE =           48;

    //
    // Constants for field access
    //
    public static final int OFFSET_WORD     = 0; // word     (0:0..31):  LONG POINTER
    public static final int OFFSET_RESERVED = 2; // reserved (2:0..11):  UNSPECIFIED
    public static final int OFFSET_BIT      = 2; // bit      (2:12..15): CARDINAL

    public final int base;

    //
    // Constructor
    //
    public BitAddress(int value) {
        this.base = value;
    }

}

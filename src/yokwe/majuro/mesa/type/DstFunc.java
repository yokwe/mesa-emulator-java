package yokwe.majuro.mesa.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

//
// DstFunc: TYPE = {null(0), and(1), or(2), xor(3)};
//

public class DstFunc {
    private static final Logger logger = LoggerFactory.getLogger(DstFunc.class);

    public static final int SIZE = 1;

    // enum value
    public static final int NULL             = 0;
    public static final int AND              = 1;
    public static final int OR               = 2;
    public static final int XOR              = 3;

    public static String toString(int value) {
        switch(value) {
        case NULL            : return "NULL";
        case AND             : return "AND";
        case OR              : return "OR";
        case XOR             : return "XOR";
        default:
            logger.error("value is out of range");
            logger.error("  value {}", value);
            throw new UnexpectedException("value is out of range");
        }
    }
    public static int get(int base) {
        return checkValue(Memory.fetch(base));
    }
    public static void set(int base, int newValue) {
        Memory.store(base, checkValue(newValue));
    }
    public static int checkValue(int value) {
        if (Debug.ENABLE_TYPE_RANGE_CHECK) {
            switch(value) {
            case NULL:
            case AND:
            case OR:
            case XOR:
                break;
            default:
                logger.error("value is out of range");
                logger.error("  value {}", value);
                throw new UnexpectedException("value is out of range");
            }
        }
        return value;
    }
}

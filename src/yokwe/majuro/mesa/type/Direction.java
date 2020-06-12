package yokwe.majuro.mesa.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

//
// Direction: TYPE = {forward(0), backward(1)};
//

public class Direction {
    private static final Logger logger = LoggerFactory.getLogger(Direction.class);

    public static final int SIZE = 1;

    // enum value
    public static final int FORWARD          = 0;
    public static final int BACKWARD         = 1;

    public static String toString(int value) {
        switch(value) {
        case FORWARD         : return "FORWARD";
        case BACKWARD        : return "BACKWARD";
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
            case FORWARD:
            case BACKWARD:
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

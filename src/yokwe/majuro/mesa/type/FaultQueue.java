package yokwe.majuro.mesa.type;

import yokwe.majuro.mesa.Type.*;

public final class FaultQueue {
    public static final int SIZE = 2;

    // offset    0  size    1  type Queue     name queue
    // offset    1  size    1  type Condition  name condition

    public static final class queue {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        //   Queue  tail
        public static final class tail {
            public static final int OFFSET = queue.OFFSET +  0;

            public static @CARD16 int get(@LONG_POINTER int base) {
                return Queue.tail.get(base + OFFSET);
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                Queue.tail.set(base + OFFSET, newValue);
            }
        }
    }
    public static final class condition {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  1;
        //   Condition  tail
        public static final class tail {
            public static final int OFFSET = condition.OFFSET +  0;

            public static @CARD16 int get(@LONG_POINTER int base) {
                return Condition.tail.get(base + OFFSET);
            }
            public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
                Condition.tail.set(base + OFFSET, newValue);
            }
        }
        //   Condition  abortable
        public static final class abortable {
            public static final int OFFSET = condition.OFFSET +  0;

            public static boolean get(@LONG_POINTER int base) {
                return Condition.abortable.get(base + OFFSET);
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                Condition.abortable.set(base + OFFSET, newValue);
            }
        }
        //   Condition  wakeup
        public static final class wakeup {
            public static final int OFFSET = condition.OFFSET +  0;

            public static boolean get(@LONG_POINTER int base) {
                return Condition.wakeup.get(base + OFFSET);
            }
            public static void set(@LONG_POINTER int base, boolean newValue) {
                Condition.wakeup.set(base + OFFSET, newValue);
            }
        }
    }
}

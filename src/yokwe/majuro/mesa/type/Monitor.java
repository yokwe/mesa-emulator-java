package yokwe.majuro.mesa.type;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Type.*;

public final class Monitor {
    public static final int SIZE = 1;

    // offset    0  size    1  type           name reserved
    //   bit startBit  0  stopBit  2
    // offset    0  size    1  type PsbIndex  name tail
    //   bit startBit  3  stopBit 12
    // offset    0  size    1  type           name available
    //   bit startBit 13  stopBit 14
    // offset    0  size    1  type boolean   name locked
    //   bit startBit 15  stopBit 15

    public static final class tail {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        public static final         int SHIFT      =  3;
        public static final @CARD16 int MASK       = 0b0001_1111_1111_1000;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static @CARD16 int get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(base + OFFSET));
        }
        public static void set(@LONG_POINTER int base, @CARD16 int newValue) {
            Memory.modify(base + OFFSET, Monitor.tail::setBit, newValue);
        }
    }
    public static final class locked {
        public static final         int SIZE       =  1;
        public static final         int OFFSET     =  0;
        public static final         int SHIFT      =  0;
        public static final @CARD16 int MASK       = 0b0000_0000_0000_0001;

        public static @CARD16 int getBit(@CARD16 int value) {
            return (value & MASK) >>> SHIFT;
        }
        public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {
            return ((newValue << SHIFT) & MASK) | (value & ~MASK);
        }
        public static boolean get(@LONG_POINTER int base) {
            return getBit(Memory.fetch(base + OFFSET)) != 0;
        }
        public static void set(@LONG_POINTER int base, boolean newValue) {
            Memory.modify(base + OFFSET, Monitor.locked::setBit, (newValue ? 1 : 0));
        }
    }
}

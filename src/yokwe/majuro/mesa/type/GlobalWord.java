package yokwe.majuro.mesa.type;

import yokwe.majuro.mesa.Memory;

//
// GlobalWord: TYPE = RECORD[gfi (0:0..13): GFTIndex, trapxfers (0:14..14): BOOL, codelinks (0:15..15): BOOL];
//

public final class GlobalWord {
    public static final int SIZE = 1;

    // gfi (0:0..13): GFTIndex
    public static final class gfi {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b1111_1111_1111_1100;
        private static final int SHIFT = 2;

        private static int getBit(int value) {
            return checkValue((value & MASK) >>> SHIFT);
        }
        private static int setBit(int value, int newValue) {
            return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
        }

        private static final int MAX = MASK >>> SHIFT;
        private static final Subrange SUBRANGE = new Subrange(0, MAX);

        public static int checkValue(int value) {
            SUBRANGE.check(value);
            return GFTIndex.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), GlobalWord.gfi::setBit, newValue);
        }
    }
    // trapxfers (0:14..14): BOOL
    public static final class trapxfers {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0000_0000_0000_0010;
        private static final int SHIFT = 1;

        private static int getBit(int value) {
            return checkValue((value & MASK) >>> SHIFT);
        }
        private static int setBit(int value, int newValue) {
            return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
        }

        private static final int MAX = MASK >>> SHIFT;
        private static final Subrange SUBRANGE = new Subrange(0, MAX);

        public static int checkValue(int value) {
            SUBRANGE.check(value);
            return value;
        }
        public static boolean get(int base) {
            return getBit(Memory.fetch(getAddress(base))) != 0;
        }
        public static void set(int base, boolean newValue) {
            Memory.modify(getAddress(base), GlobalWord.trapxfers::setBit, (newValue ? 1 : 0));
        }
    }
    // codelinks (0:15..15): BOOL
    public static final class codelinks {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0000_0000_0000_0001;
        private static final int SHIFT = 0;

        private static int getBit(int value) {
            return checkValue((value & MASK) >>> SHIFT);
        }
        private static int setBit(int value, int newValue) {
            return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);
        }

        private static final int MAX = MASK >>> SHIFT;
        private static final Subrange SUBRANGE = new Subrange(0, MAX);

        public static int checkValue(int value) {
            SUBRANGE.check(value);
            return value;
        }
        public static boolean get(int base) {
            return getBit(Memory.fetch(getAddress(base))) != 0;
        }
        public static void set(int base, boolean newValue) {
            Memory.modify(getAddress(base), GlobalWord.codelinks::setBit, (newValue ? 1 : 0));
        }
    }
}

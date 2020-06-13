/*******************************************************************************
 * Copyright (c) 2020, Yasuhiro Hasegawa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. The name of the author may not be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *******************************************************************************/
package yokwe.majuro.mesa.type;

import yokwe.majuro.mesa.Memory;

//
// BitBltFlags: TYPE = RECORD[direction (0:0..0): Direction, disjoint (0:1..1): BOOL, disjointItems (0:2..2): BOOL, gray (0:3..3): BOOL, srcFunc (0:4..4): SrcFunc, dstFunc (0:5..6): DstFunc, reserved (0:7..15): UNSPECIFIED];
//

public final class BitBltFlags {
    public static final int SIZE = 1;

    // direction (0:0..0): Direction
    public static final class direction {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b1000_0000_0000_0000;
        private static final int SHIFT = 15;

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
            return Direction.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), BitBltFlags.direction::setBit, newValue);
        }
    }
    // disjoint (0:1..1): BOOL
    public static final class disjoint {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0100_0000_0000_0000;
        private static final int SHIFT = 14;

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
            Memory.modify(getAddress(base), BitBltFlags.disjoint::setBit, (newValue ? 1 : 0));
        }
    }
    // disjointItems (0:2..2): BOOL
    public static final class disjointItems {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0010_0000_0000_0000;
        private static final int SHIFT = 13;

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
            Memory.modify(getAddress(base), BitBltFlags.disjointItems::setBit, (newValue ? 1 : 0));
        }
    }
    // gray (0:3..3): BOOL
    public static final class gray {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0001_0000_0000_0000;
        private static final int SHIFT = 12;

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
            Memory.modify(getAddress(base), BitBltFlags.gray::setBit, (newValue ? 1 : 0));
        }
    }
    // srcFunc (0:4..4): SrcFunc
    public static final class srcFunc {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0000_1000_0000_0000;
        private static final int SHIFT = 11;

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
            return SrcFunc.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), BitBltFlags.srcFunc::setBit, newValue);
        }
    }
    // dstFunc (0:5..6): DstFunc
    public static final class dstFunc {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0000_0110_0000_0000;
        private static final int SHIFT = 9;

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
            return DstFunc.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), BitBltFlags.dstFunc::setBit, newValue);
        }
    }
    // reserved (0:7..15): UNSPECIFIED
    public static final class reserved {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0000_0001_1111_1111;
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
            return UNSPECIFIED.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), BitBltFlags.reserved::setBit, newValue);
        }
    }
}

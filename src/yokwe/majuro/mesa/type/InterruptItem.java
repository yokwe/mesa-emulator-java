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
// InterruptItem: TYPE = RECORD[condition (0:0..15): Condition, available (1:0..15): UNSPECIFIED];
//

public final class InterruptItem {
    public static final int SIZE = 2;

    // condition (0:0..15): Condition
    public static final class condition {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand Record in Record
        //   Condition: TYPE = RECORD[reserved (0:0..2): UNSPECIFIED, tail (0:3..12): PsbIndex, available (0:13..13): UNSPECIFIED, abortable (0:14..14): BOOL, wakeup (0:15..15): BOOL];
        //     reserved (0:0..2): UNSPECIFIED
        public static final class reserved {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return InterruptItem.condition.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 13;
            private static final int MASK  = 0b1110_0000_0000_0000;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                return UNSPECIFIED.checkValue(BITFIELD.checkValue(value));
            }
            public static int get(int base) {
                return UNSPECIFIED.checkValue(BITFIELD.getBit(Memory.fetch(getAddress(base))));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BITFIELD::setBit, UNSPECIFIED.checkValue(newValue));
            }
        }
        //     tail (0:3..12): PsbIndex
        public static final class tail {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return InterruptItem.condition.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 3;
            private static final int MASK  = 0b0001_1111_1111_1000;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                return PsbIndex.checkValue(BITFIELD.checkValue(value));
            }
            public static int get(int base) {
                return PsbIndex.checkValue(BITFIELD.getBit(Memory.fetch(getAddress(base))));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BITFIELD::setBit, PsbIndex.checkValue(newValue));
            }
        }
        //     available (0:13..13): UNSPECIFIED
        public static final class available {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return InterruptItem.condition.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 2;
            private static final int MASK  = 0b0000_0000_0000_0100;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                return UNSPECIFIED.checkValue(BITFIELD.checkValue(value));
            }
            public static int get(int base) {
                return UNSPECIFIED.checkValue(BITFIELD.getBit(Memory.fetch(getAddress(base))));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BITFIELD::setBit, UNSPECIFIED.checkValue(newValue));
            }
        }
        //     abortable (0:14..14): BOOL
        public static final class abortable {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return InterruptItem.condition.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 1;
            private static final int MASK  = 0b0000_0000_0000_0010;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                return BITFIELD.checkValue(value);
            }
            public static boolean get(int base) {
                return BITFIELD.getBit(Memory.fetch(getAddress(base))) != 0;
            }
            public static void set(int base, boolean newValue) {
                Memory.modify(getAddress(base), BITFIELD::setBit, (newValue ? 1 : 0));
            }
        }
        //     wakeup (0:15..15): BOOL
        public static final class wakeup {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return InterruptItem.condition.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 0;
            private static final int MASK  = 0b0000_0000_0000_0001;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                return BITFIELD.checkValue(value);
            }
            public static boolean get(int base) {
                return BITFIELD.getBit(Memory.fetch(getAddress(base))) != 0;
            }
            public static void set(int base, boolean newValue) {
                Memory.modify(getAddress(base), BITFIELD::setBit, (newValue ? 1 : 0));
            }
        }
    }
    // available (1:0..15): UNSPECIFIED
    public static final class available {
        public static final int SIZE = 1;

        private static final int OFFSET = 1;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return UNSPECIFIED.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            UNSPECIFIED.set(getAddress(base), newValue);
        }
    }
}

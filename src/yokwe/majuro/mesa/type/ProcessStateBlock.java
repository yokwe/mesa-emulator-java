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
// ProcessStateBlock: TYPE = RECORD[link (0:0..15): PsbLink, flags (1:0..15): PsbFlags, context (2:0..15): POINTER, timeout (3:0..15): CARDINAL, mds (4:0..15): CARDINAL, available (5:0..15): UNSPECIFIED, stickty (6:0..31): LONG_UNSPECIFIED];
//

public final class ProcessStateBlock {
    public static final int SIZE = 8;

    // link (0:0..15): PsbLink
    public static final class link {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand Record in Record
        //   PsbLink: TYPE = RECORD[priority (0:0..2): Priority, next (0:3..12): PsbIndex, failed (0:13..13): BOOL, permanent (0:14..14): BOOL, preempted (0:15..15): BOOL];
        //     priority (0:0..2): Priority
        public static final class priority {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.link.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b1110_0000_0000_0000;
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
                return Priority.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), ProcessStateBlock.link.priority::setBit, newValue);
            }
        }
        //     next (0:3..12): PsbIndex
        public static final class next {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.link.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0001_1111_1111_1000;
            private static final int SHIFT = 3;

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
                return PsbIndex.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), ProcessStateBlock.link.next::setBit, newValue);
            }
        }
        //     failed (0:13..13): BOOL
        public static final class failed {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.link.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_0000_0000_0100;
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
                return value;
            }
            public static boolean get(int base) {
                return getBit(Memory.fetch(getAddress(base))) != 0;
            }
            public static void set(int base, boolean newValue) {
                Memory.modify(getAddress(base), ProcessStateBlock.link.failed::setBit, (newValue ? 1 : 0));
            }
        }
        //     permanent (0:14..14): BOOL
        public static final class permanent {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.link.getAddress(base) + OFFSET;
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
                Memory.modify(getAddress(base), ProcessStateBlock.link.permanent::setBit, (newValue ? 1 : 0));
            }
        }
        //     preempted (0:15..15): BOOL
        public static final class preempted {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.link.getAddress(base) + OFFSET;
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
                Memory.modify(getAddress(base), ProcessStateBlock.link.preempted::setBit, (newValue ? 1 : 0));
            }
        }
    }
    // flags (1:0..15): PsbFlags
    public static final class flags {
        public static final int SIZE = 1;

        private static final int OFFSET = 1;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand Record in Record
        //   PsbFlags: TYPE = RECORD[available (0:0..2): UNSPECIFIED, cleanup (0:3..12): PsbIndex, reserved (0:13..13): UNSPECIFIED, waiting (0:14..14): BOOL, abort (0:15..15): BOOL];
        //     available (0:0..2): UNSPECIFIED
        public static final class available {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b1110_0000_0000_0000;
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
                return UNSPECIFIED.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), ProcessStateBlock.flags.available::setBit, newValue);
            }
        }
        //     cleanup (0:3..12): PsbIndex
        public static final class cleanup {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0001_1111_1111_1000;
            private static final int SHIFT = 3;

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
                return PsbIndex.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), ProcessStateBlock.flags.cleanup::setBit, newValue);
            }
        }
        //     reserved (0:13..13): UNSPECIFIED
        public static final class reserved {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.flags.getAddress(base) + OFFSET;
            }
            private static final int MASK  = 0b0000_0000_0000_0100;
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
                return UNSPECIFIED.checkValue(value);
            }
            public static int get(int base) {
                return getBit(Memory.fetch(getAddress(base)));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), ProcessStateBlock.flags.reserved::setBit, newValue);
            }
        }
        //     waiting (0:14..14): BOOL
        public static final class waiting {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.flags.getAddress(base) + OFFSET;
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
                Memory.modify(getAddress(base), ProcessStateBlock.flags.waiting::setBit, (newValue ? 1 : 0));
            }
        }
        //     abort (0:15..15): BOOL
        public static final class abort {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return ProcessStateBlock.flags.getAddress(base) + OFFSET;
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
                Memory.modify(getAddress(base), ProcessStateBlock.flags.abort::setBit, (newValue ? 1 : 0));
            }
        }
    }
    // context (2:0..15): POINTER
    public static final class context {
        public static final int SIZE = 1;

        private static final int OFFSET = 2;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return POINTER.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            POINTER.set(getAddress(base), newValue);
        }
    }
    // timeout (3:0..15): CARDINAL
    public static final class timeout {
        public static final int SIZE = 1;

        private static final int OFFSET = 3;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return CARDINAL.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            CARDINAL.set(getAddress(base), newValue);
        }
    }
    // mds (4:0..15): CARDINAL
    public static final class mds {
        public static final int SIZE = 1;

        private static final int OFFSET = 4;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return CARDINAL.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            CARDINAL.set(getAddress(base), newValue);
        }
    }
    // available (5:0..15): UNSPECIFIED
    public static final class available {
        public static final int SIZE = 1;

        private static final int OFFSET = 5;
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
    // stickty (6:0..31): LONG_UNSPECIFIED
    public static final class stickty {
        public static final int SIZE = 2;

        private static final int OFFSET = 6;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return LONG_UNSPECIFIED.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            LONG_UNSPECIFIED.set(getAddress(base), newValue);
        }
    }
}

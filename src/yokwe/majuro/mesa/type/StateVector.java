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
// StateVector: TYPE = RECORD[stack (0:0..223): ARRAY CARDINAL [0..StackDepth) OF UNSPECIFIED, word (14:0..15): StateWord, frame (15:0..15): POINTER, data (16): BLOCK];
//

public final class StateVector {
    public static final int SIZE = 16;

    // stack (0:0..223): ARRAY CARDINAL [0..StackDepth) OF UNSPECIFIED
    public static final class stack {
        public static final int SIZE = 14;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // StateVector#stack: TYPE = ARRAY CARDINAL [0..StackDepth) OF UNSPECIFIED;
        //   CARDINAL: TYPE = CARDINAL [0..65536)
        //   UNSPECIFIED: TYPE = UNSPECIFIED [0..65536)
        private static final int ELEMENT_SIZE = 1;
        public static int getAddress(int base, int index) {
            return StateVector.stack.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
        }

        private static final int INDEX_MIN    = 0;
        private static final int INDEX_MAX    = 13;
        private static final Subrange INDEX_SUBRANGE = new Subrange(INDEX_MIN, INDEX_MAX);
        private static int checkIndex(int index) {
            return INDEX_SUBRANGE.checkValue(index);
        }

        public static int get(int base, int index) {
            return UNSPECIFIED.get(StateVector.stack.getAddress(base, checkIndex(index)));
        }
        public static void set(int base, int index, int newValue) {
            UNSPECIFIED.set(StateVector.stack.getAddress(base, checkIndex(index)), newValue);
        }
    }
    // word (14:0..15): StateWord
    public static final class word {
        public static final int SIZE = 1;

        private static final int OFFSET = 14;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // Expand Record in Record
        //   StateWord: TYPE = RECORD[instByte (0:0..7): BYTE, stkPtr (0:8..15): BYTE];
        //     instByte (0:0..7): BYTE
        public static final class instByte {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return StateVector.word.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 8;
            private static final int MASK  = 0b1111_1111_0000_0000;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                return BYTE.checkValue(BITFIELD.checkValue(value));
            }
            public static int get(int base) {
                return BYTE.checkValue(BITFIELD.getBit(Memory.fetch(getAddress(base))));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BITFIELD::setBit, BYTE.checkValue(newValue));
            }
        }
        //     stkPtr (0:8..15): BYTE
        public static final class stkPtr {
            public static final int SIZE = 1;

            private static final int OFFSET = 0;
            public static int getAddress(int base) {
                return StateVector.word.getAddress(base) + OFFSET;
            }
            private static final int SHIFT = 0;
            private static final int MASK  = 0b0000_0000_1111_1111;
            private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
            public static int checkValue(int value) {
                return BYTE.checkValue(BITFIELD.checkValue(value));
            }
            public static int get(int base) {
                return BYTE.checkValue(BITFIELD.getBit(Memory.fetch(getAddress(base))));
            }
            public static void set(int base, int newValue) {
                Memory.modify(getAddress(base), BITFIELD::setBit, BYTE.checkValue(newValue));
            }
        }
    }
    // frame (15:0..15): POINTER
    public static final class frame {
        public static final int SIZE = 1;

        private static final int OFFSET = 15;
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
    // data (16): BLOCK
    public static final class data {
        public static final int SIZE = 0;

        private static final int OFFSET = 16;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // BLOCK: TYPE = ARRAY CARDINAL [0..0) OF UNSPECIFIED;
        //   CARDINAL: TYPE = CARDINAL [0..65536)
        //   UNSPECIFIED: TYPE = UNSPECIFIED [0..65536)
        private static final int ELEMENT_SIZE = 1;
        public static int getAddress(int base, int index) {
            return StateVector.data.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
        }

        private static int checkIndex(int index) {
            return CARDINAL.checkValue(index);
        }

        public static int get(int base, int index) {
            return UNSPECIFIED.get(StateVector.data.getAddress(base, checkIndex(index)));
        }
        public static void set(int base, int index, int newValue) {
            UNSPECIFIED.set(StateVector.data.getAddress(base, checkIndex(index)), newValue);
        }
    }
}

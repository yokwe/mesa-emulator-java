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
// StateWord: TYPE = RECORD[instByte (0:0..7): BYTE, stkPtr (0:8..15): BYTE];
//

public final class StateWord {
    public static final int SIZE = 1;

    // instByte (0:0..7): BYTE
    public static final class instByte {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 8;
        private static final int MASK  = 0b1111_1111_0000_0000;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            return BYTE.checkValue(BITFIELD.checkValue(value));
        }
        public static int get(int base) {
            return BITFIELD.getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), BITFIELD::setBit, newValue);
        }
    }
    // stkPtr (0:8..15): BYTE
    public static final class stkPtr {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int SHIFT = 0;
        private static final int MASK  = 0b0000_0000_1111_1111;
        private static final Bitfield BITFIELD = new Bitfield(SHIFT, MASK);
        public static int checkValue(int value) {
            return BYTE.checkValue(BITFIELD.checkValue(value));
        }
        public static int get(int base) {
            return BITFIELD.getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), BITFIELD::setBit, newValue);
        }
    }
}

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
// GFTItem: TYPE = RECORD[globalFrame (0:0..15): LONG_POINTER, codebase (2:0..31): LONG_POINTER];
//

public final class GFTItem {
    public static final int SIZE = 4;

    // globalFrame (0:0..15): LONG_POINTER
    public static final class globalFrame {
        public static final int SIZE = 2;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b1111_1111_1111_1111_0000_0000_0000_0000;
        private static final int SHIFT = 16;

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
            return LONG_POINTER.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.readDbl(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modifyDbl(getAddress(base), GFTItem.globalFrame::setBit, newValue);
        }
    }
    // codebase (2:0..31): LONG_POINTER
    public static final class codebase {
        public static final int SIZE = 2;

        private static final int OFFSET = 2;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        public static int get(int base) {
            return LONG_POINTER.get(getAddress(base));
        }
        public static void set(int base, int newValue) {
            LONG_POINTER.set(getAddress(base), newValue);
        }
    }
}

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
// AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
//

public final class AVItem {
    public static final int SIZE = 1;

    // data (0:0..13): UNSPECIFIED
    public static final class data {
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
            return UNSPECIFIED.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), AVItem.data::setBit, newValue);
        }
    }
    // tag (0:14..15): AVItemType
    public static final class tag {
        public static final int SIZE = 1;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        private static final int MASK  = 0b0000_0000_0000_0011;
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
            return AVItemType.checkValue(value);
        }
        public static int get(int base) {
            return getBit(Memory.fetch(getAddress(base)));
        }
        public static void set(int base, int newValue) {
            Memory.modify(getAddress(base), AVItem.tag::setBit, newValue);
        }
    }
}

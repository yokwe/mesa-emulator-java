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

import yokwe.majuro.mesa.Debug;

//
// CodeSegment: TYPE = RECORD[available (0:0..63): ARRAY CARDINAL [0..4) OF UNSPECIFIED, code (4): BLOCK];
//

public final class CodeSegment {
    public static final int SIZE = 4;

    // available (0:0..63): ARRAY CARDINAL [0..4) OF UNSPECIFIED
    public static final class available {
        public static final int SIZE = 4;

        private static final int OFFSET = 0;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // CodeSegment#available: TYPE = ARRAY CARDINAL [0..4) OF UNSPECIFIED;
        //   CARDINAL: TYPE = CARDINAL [0..65536)
        //   UNSPECIFIED: TYPE = UNSPECIFIED [0..65536)
        private static final int ELEMENT_SIZE = 1;
        public static int getAddress(int base, int index) {
            return CodeSegment.available.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
        }

        private static final int INDEX_MIN    = 0;
        private static final int INDEX_MAX    = 3;
        private static final Subrange INDEX_SUBRANGE = new Subrange(INDEX_MIN, INDEX_MAX);
        private static int checkIndex(int index) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return INDEX_SUBRANGE.checkValue(index);
            } else {
                return index;
            }
        }

        public static int get(int base, int index) {
            return UNSPECIFIED.get(CodeSegment.available.getAddress(base, checkIndex(index)));
        }
        public static void set(int base, int index, int newValue) {
            UNSPECIFIED.set(CodeSegment.available.getAddress(base, checkIndex(index)), newValue);
        }
    }
    // code (4): BLOCK
    public static final class code {
        public static final int SIZE = 0;

        private static final int OFFSET = 4;
        public static int getAddress(int base) {
            return base + OFFSET;
        }
        // BLOCK: TYPE = ARRAY CARDINAL [0..0) OF UNSPECIFIED;
        //   CARDINAL: TYPE = CARDINAL [0..65536)
        //   UNSPECIFIED: TYPE = UNSPECIFIED [0..65536)
        private static final int ELEMENT_SIZE = 1;
        public static int getAddress(int base, int index) {
            return CodeSegment.code.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);
        }

        private static int checkIndex(int index) {
            if (Debug.ENABLE_TYPE_CHECK_VALUE) {
                return CARDINAL.checkValue(index);
            } else {
                return index;
            }
        }

        public static int get(int base, int index) {
            return UNSPECIFIED.get(CodeSegment.code.getAddress(base, checkIndex(index)));
        }
        public static void set(int base, int index, int newValue) {
            UNSPECIFIED.set(CodeSegment.code.getAddress(base, checkIndex(index)), newValue);
        }
    }
}

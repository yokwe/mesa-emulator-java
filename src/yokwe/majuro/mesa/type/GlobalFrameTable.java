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

//
// GlobalFrameTable: TYPE = ARRAY GFTIndex OF GFTItem;
//   GFTIndex: TYPE = CARDINAL [0..16384)
//   GFTItem: TYPE = RECORD[globalFrame (0:0..15): LONG_POINTER, codebase (2:0..31): LONG_POINTER]
//

public final class GlobalFrameTable {
    public static final int SIZE = 65536;

    private static final int ELEMENT_SIZE = 4;
    public static int getAddress(int base, int index) {
        return base + (checkIndex(index) * ELEMENT_SIZE);
    }

    private static int checkIndex(int index) {
        return GFTIndex.checkValue(index);
    }

    // Expand GFTItem: TYPE = RECORD[globalFrame (0:0..15): LONG_POINTER, codebase (2:0..31): LONG_POINTER];
    //   globalFrame (0:0..15): LONG_POINTER
    public static final class globalFrame {
        public static int get(int base, int index) {
            return GFTItem.globalFrame.get(getAddress(base, index));
        }
        public static void set(int base, int index, int newValue) {
            GFTItem.globalFrame.set(getAddress(base, index), newValue);
        }
    }
    //   codebase (2:0..31): LONG_POINTER
    public static final class codebase {
        public static int get(int base, int index) {
            return GFTItem.codebase.get(getAddress(base, index));
        }
        public static void set(int base, int index, int newValue) {
            GFTItem.codebase.set(getAddress(base, index), newValue);
        }
    }
}

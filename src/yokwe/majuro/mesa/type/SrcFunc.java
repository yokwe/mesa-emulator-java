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
import yokwe.majuro.mesa.Memory;

//
// SrcFunc: TYPE = {null(0), complement(1)};
//

public final class SrcFunc {
    public static final int SIZE = 1;

    public static final int NULL       =  0;
    public static final int COMPLEMENT =  1;

    private static final Enum ENUM = Enum.builder().add(NULL, "NULL").add(COMPLEMENT, "COMPLEMENT").build();
    public static String toString(int value) {
        return ENUM.toString(value);
    }
    public static int checkValue(int value) {
        if (Debug.ENABLE_TYPE_CHECK_VALUE) {
            return ENUM.checkValue(value);
        } else {
            return value;
        }
    }
    public static int get(int base) {
        return checkValue(Memory.fetch(base));
    }
    public static void set(int base, int newValue) {
        Memory.store(base, checkValue(newValue));
    }
    public static int checkValue(Bitfield bitfield, int value) {
        if (Debug.ENABLE_TYPE_CHECK_VALUE) {
            return checkValue(bitfield.checkValue(value));
        } else {
            return value;
        }
    }
    public static int get(Bitfield bitfield, int base) {
        return checkValue(bitfield.getBit(Memory.fetch(base)));
    }
    public static void set(Bitfield bitfield, int base, int newValue) {
        Memory.modify(base, bitfield::setBit, checkValue(newValue));
    }
}

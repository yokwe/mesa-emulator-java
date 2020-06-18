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
// XferType: TYPE = {return(0), call(1), localCall(2), part(3), xfer(4), trap(5), processSwitch(6), unused(7)};
//

public final class XferType {
    public static final int SIZE = 1;

    public static final int RETURN         =  0;
    public static final int CALL           =  1;
    public static final int LOCAL_CALL     =  2;
    public static final int PART           =  3;
    public static final int XFER           =  4;
    public static final int TRAP           =  5;
    public static final int PROCESS_SWITCH =  6;
    public static final int UNUSED         =  7;

    private static final Enum ENUM = Enum.builder().add(RETURN, "RETURN").add(CALL, "CALL").add(LOCAL_CALL, "LOCAL_CALL").add(PART, "PART").add(XFER, "XFER").add(TRAP, "TRAP").add(PROCESS_SWITCH, "PROCESS_SWITCH").add(UNUSED, "UNUSED").build();
    public static String toString(int value) {
        return ENUM.toString(value);
    }
    public static int checkValue(int value) {
        return ENUM.checkValue(value);
    }
    public static int get(int base) {
        return checkValue(Memory.fetch(base));
    }
    public static void set(int base, int newValue) {
        Memory.store(base, checkValue(newValue));
    }
    public static int checkValue(Bitfield bitfield, int value) {
        return checkValue(bitfield.checkValue(value));
    }
    public static int get(Bitfield bitfield, int base) {
        return checkValue(bitfield.getBit(Memory.fetch(base)));
    }
    public static void set(Bitfield bitfield, int base, int newValue) {
        Memory.modify(base, bitfield::setBit, checkValue(newValue));
    }
}

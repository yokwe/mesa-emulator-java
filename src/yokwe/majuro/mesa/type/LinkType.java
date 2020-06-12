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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.Memory;

//
// LinkType: TYPE = {frame(0), oldProcedure(1), indirect(2), newProcedure(3)};
//

public class LinkType {
    private static final Logger logger = LoggerFactory.getLogger(LinkType.class);

    public static final int SIZE = 1;

    // enum value
    public static final int FRAME            = 0;
    public static final int OLD_PROCEDURE    = 1;
    public static final int INDIRECT         = 2;
    public static final int NEW_PROCEDURE    = 3;

    public static String toString(int value) {
        switch(value) {
        case FRAME           : return "FRAME";
        case OLD_PROCEDURE   : return "OLD_PROCEDURE";
        case INDIRECT        : return "INDIRECT";
        case NEW_PROCEDURE   : return "NEW_PROCEDURE";
        default:
            logger.error("value is out of range");
            logger.error("  value {}", value);
            throw new UnexpectedException("value is out of range");
        }
    }
    public static int get(int base) {
        return checkValue(Memory.fetch(base));
    }
    public static void set(int base, int newValue) {
        Memory.store(base, checkValue(newValue));
    }
    public static int checkValue(int value) {
        if (Debug.ENABLE_TYPE_RANGE_CHECK) {
            switch(value) {
            case FRAME:
            case OLD_PROCEDURE:
            case INDIRECT:
            case NEW_PROCEDURE:
                break;
            default:
                logger.error("value is out of range");
                logger.error("  value {}", value);
                throw new UnexpectedException("value is out of range");
            }
        }
        return value;
    }
}

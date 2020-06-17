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

public final class Subrange {
    private static final Logger logger = LoggerFactory.getLogger(Subrange.class);

    public final long    min;
	public final long    max;
	public final long    length;
	public final boolean signed;
	
	public Subrange(long min, long max) {
		this.min    = min;
		this.max    = max;
		this.length = max - min + 1;
		this.signed = min < 0;
	}
	
	public int checkValue(int value) {
        if (Debug.ENABLE_TYPE_RANGE_CHECK) {
        	long longValue = signed ? (long)value : Integer.toUnsignedLong(value);
        	if (min <= longValue && longValue <= max) {
                return value;
        	} else {
                logger.error("value is out of range");
                logger.error("  value     {}", value);
                logger.error("  longValue {}", longValue);
                logger.error("  min       {}", min);
                logger.error("  max       {}", max);
                throw new UnexpectedException("value is out of range");
        	}
        }
        return value;
	}
}
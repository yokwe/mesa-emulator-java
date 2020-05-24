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
package yokwe.majuro.mesa;

import yokwe.majuro.mesa.Type.BytePair;
import yokwe.majuro.mesa.Type.CARD16;
import yokwe.majuro.mesa.Type.CARD8;
import yokwe.majuro.mesa.Type.LONG_POINTER;

public final class CodeCache {
	private static final int PAGE_SIZE_IN_BYTE = Memory.PAGE_SIZE * 2;
	private static final int PAGE_MASK_IN_BYTE = PAGE_SIZE_IN_BYTE - 1;
	
	private static int     cb;
	private static int     pc;
	private static int     wordPC;
	private static boolean even;
	
	private static int lastWordPC;
	private static int lastWord;

	private static short[] page;
	private static int     wordOffset;
	private static int     startPC;
	private static int     endPC;

	public static void setCB(@LONG_POINTER int newValue) {
		cb = newValue;
		invalidate();
	}
	public static @LONG_POINTER int getCB() {
		return cb;
	}
	
	public static void setPC(@CARD16 int newValue) {
		// size of pc is 16 bit
		pc     = newValue & 0xFFFF;
		wordPC = pc / 2;
		even   = (pc & 1) == 0;
		
		lastWordPC = -1;
	}
	public static @CARD16 int getPC() {
		return pc;
	}
	
	public static @CARD8 int getCodeByte() {		
		if (Perf.ENABLE) Perf.codeCacheCodeByte++;
		if (lastWordPC != wordPC) {
			// unit of pc is byte
			if (startPC <= pc && pc <= endPC) {
				if (Perf.ENABLE) Perf.codeCacheHit++;
			} else {
				if (Perf.ENABLE) Perf.codeCacheMiss++;
				setup();
			}
			lastWord = page[wordOffset + wordPC];
		}
		
		// RETURN[IF even THEN word.left ELSE word.right];
		int ret = even ? BytePair.left(lastWord) : BytePair.right(lastWord);
		
		// increment pc
		// size of pc is 16 bit
		pc     = (pc + 1) & 0xFFFF;
		wordPC = pc / 2;
		even   = !even;
		
		return ret;
	}
	public static @CARD16 int getCodeWord() {
		int left  = getCodeByte();
		int right = getCodeByte();
		
		return (left << 8) | right;
	}
	
	private static void invalidate() {
		startPC    = 0x10000;
		endPC      = 0;
		lastWordPC = -1;
	}
	
	private static void setup() {
		// To prevent bogus PageFault, PC need to have real value
		int va = cb + wordPC;
		page = Memory.fetchPage(va);
		// unit of offsetCB is byte
		int offsetCB = (cb * 2) & PAGE_MASK_IN_BYTE;
		
		if ((pc + offsetCB) < PAGE_SIZE_IN_BYTE) {
			// Valid PC range is [startPC..endPC]
			startPC    = 0;
			endPC      = PAGE_SIZE_IN_BYTE - offsetCB - 1;
			wordOffset = offsetCB / 2;
		} else {
			// Valid PC range is [startPC..endPC]
			startPC    = ((pc + offsetCB) & ~PAGE_MASK_IN_BYTE) - offsetCB;
			endPC      = startPC + PAGE_SIZE_IN_BYTE - 1; // to end of page
			wordOffset = (-startPC) / 2;
		}
	}

}

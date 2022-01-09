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

import yokwe.util.UnexpectedException;

public final class LFCache {
	private static int     lf;
	private static int     offset;
	private static int     pageEnd;
	private static short[] page;
	
	public static void setLF(/* POINTER */ int newValue) {
		lf      = newValue & 0xFFFF;
		offset  = lf % Memory.PAGE_SIZE;
		pageEnd = Memory.PAGE_SIZE - offset - 1;
		page    = Memory.storePage(Memory.lengthenPointer(lf));
	}
	public static /* POINTER */ int getLF() {
		return lf;
	}
	public static /* CARD16 */ int fetch(int index) {
		// NOTE index can be negative value to access LocalOverhead
		int pageIndex = offset + index;
		if (0 <= pageIndex) {
			if (pageIndex <= pageEnd) {
				if (Perf.ENABLE) Perf.lfCacheHit++;
				return page[pageIndex] & 0xFFFF;
			} else {
				if (Perf.ENABLE) Perf.lfCacheMiss++;
				return Memory.fetchMDS(lf + index);
			}
		} else {
			throw new UnexpectedException("");
		}
	}
	public static void store(/* CARD16 */ int index, int newValue) {
		// NOTE index can be negative value to access LocalOverhead
		int pageIndex = offset + index;
		if (0 <= pageIndex) {
			if (pageIndex <= pageEnd) {
				if (Perf.ENABLE) Perf.lfCacheHit++;
				page[pageIndex] = (short)newValue;
			} else {
				if (Perf.ENABLE) Perf.lfCacheMiss++;
				Memory.storeMDS(lf + index, newValue);
			}
		} else {
			throw new UnexpectedException("");
		}
	}

}

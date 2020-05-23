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
package yokwe.majuro.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import yokwe.majuro.mesa.LFCache;
import yokwe.majuro.mesa.Memory;

public class TestLFCache extends TestBase {
	@Test
	public void testSetGetLF() {
		int value = 0x9876;
		
		// Before
		assertNotEquals(value, LFCache.getLF());
		
		LFCache.setLF(value);
		
		// After
		assertEquals(value, LFCache.getLF());
	}

	@Test
	public void testFetchLF() {
		int mds   = 0x40000;
		int lf    = 0x3456;
		int index = 4;
		
		Memory.setMDS(mds);
		LFCache.setLF(lf);
		int va = Memory.lengthenPointer(lf + index);
		
		// Before
		assertNotEquals(0x1234, LFCache.fetch(index + 0));
		assertNotEquals(0x5678, LFCache.fetch(index + 1));
		
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);

		// After
		assertEquals(0x1234, LFCache.fetch(index + 0));
		assertEquals(0x5678, LFCache.fetch(index + 1));
	}

	@Test
	public void testFetchLF2() {
		int mds   = 0x40000;
		int lf    = 0x34F0;
		int index = 15;
		
		Memory.setMDS(mds);
		LFCache.setLF(lf);
		int va = Memory.lengthenPointer(lf + index);
		
//		logger.info("va {}", String.format("%X", va));
		fillPageZero(va + 0);
		fillPageZero(va + 1);

		// Before
		assertNotEquals(0x1234, LFCache.fetch(index + 0));
		assertNotEquals(0x5678, LFCache.fetch(index + 1));
		
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);

		// After
		assertEquals(0x1234, LFCache.fetch(index + 0));
		assertEquals(0x5678, LFCache.fetch(index + 1));
	}

	@Test
	public void testStoreLF() {
		int mds   = 0x40000;
		int lf    = 0x3456;
		int index = 4;
		
		Memory.setMDS(mds);
		LFCache.setLF(lf);
		int va = Memory.lengthenPointer(lf + index);
		
		// Before
		assertNotEquals(0x1234, LFCache.fetch(index + 0));
		assertNotEquals(0x5678, LFCache.fetch(index + 1));
		
		LFCache.store(index + 0, 0x1234);
		LFCache.store(index + 1, 0x5678);

		// After
		assertEquals(0x1234, Memory.rawRead(va + 0));
		assertEquals(0x5678, Memory.rawRead(va + 1));
	}

	@Test
	public void testStoreLF2() {
		int mds   = 0x40000;
		int lf    = 0x34F0;
		int index = 15;
		
		Memory.setMDS(mds);
		LFCache.setLF(lf);
		int va = Memory.lengthenPointer(lf + index);
		
		// Before
		assertNotEquals(0x1234, LFCache.fetch(index + 0));
		assertNotEquals(0x5678, LFCache.fetch(index + 1));
		
		LFCache.store(index + 0, 0x1234);
		LFCache.store(index + 1, 0x5678);

		// After
		assertEquals(0x1234, Memory.rawRead(va + 0));
		assertEquals(0x5678, Memory.rawRead(va + 1));
	}

}

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

import yokwe.majuro.mesa.CodeCache;
import yokwe.majuro.mesa.Memory;

public class TestCodeCache extends TestBase {
	@Test
	public void testSetGetCB() {
		int value = 0x123456;
		
		// Before
		assertNotEquals(value, CodeCache.getCB());
		
		CodeCache.setCB(value);
		
		// After
		assertEquals(value, CodeCache.getCB());
	}
	
	@Test
	public void testSetGetPC() {
		int value = 0x9876;
		
		// Before
		assertNotEquals(value, CodeCache.getPC());
		
		CodeCache.setPC(value);
		
		// After
		assertEquals(value, CodeCache.getPC());
	}
	
	@Test
	public void testGetCodeByte() {
		int cb = 0x400080;
		int pc = 0x20;
		
		int va = cb + (pc / 2);
		
		// Before
		CodeCache.setCB(cb);
		CodeCache.setPC(pc);
		
		fillPageZero(va + 0);
		fillPageZero(va + 1);
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);
		
		// After
		assertEquals(0x12, CodeCache.getCodeByte());
		assertEquals(0x34, CodeCache.getCodeByte());
		assertEquals(0x56, CodeCache.getCodeByte());
		assertEquals(0x78, CodeCache.getCodeByte());
	}

	@Test
	public void testGetCodeByte2() {
		int cb = 0x400080;
		int pc = 0x9876;
		
		int va = cb + (pc / 2);
		
		// Before
		CodeCache.setCB(cb);
		CodeCache.setPC(pc);
		
		fillPageZero(va + 0);
		fillPageZero(va + 1);
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);
		
		// After
		assertEquals(0x12, CodeCache.getCodeByte());
		assertEquals(0x34, CodeCache.getCodeByte());
		assertEquals(0x56, CodeCache.getCodeByte());
		assertEquals(0x78, CodeCache.getCodeByte());
	}

	@Test
	public void testGetCodeByte3() {
		int cb = 0x400080;
		int pc = 0x00FE;
		
		int va = cb + (pc / 2);
		
		// Before
		CodeCache.setCB(cb);
		CodeCache.setPC(pc);
		
//		logger.info("va {}", String.format("%X", va));
		fillPageZero(va + 0);
		fillPageZero(va + 1);
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);
		
		// After
		assertEquals(0x12, CodeCache.getCodeByte());
		assertEquals(0x34, CodeCache.getCodeByte());
		assertEquals(0x56, CodeCache.getCodeByte());
		assertEquals(0x78, CodeCache.getCodeByte());
	}

	@Test
	public void testGetCodeWord() {
		int cb = 0x400080;
		int pc = 0x9876;
		
		int va = cb + (pc / 2);
		
		// Before
		CodeCache.setCB(cb);
		CodeCache.setPC(pc);
		
		fillPageZero(va + 0);
		fillPageZero(va + 1);
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);
		
		// After
		assertEquals(0x1234, CodeCache.getCodeWord());
		assertEquals(0x5678, CodeCache.getCodeWord());
	}
	@Test
	public void testGetCodeWord2() {
		int cb = 0x400080;
		int pc = 0x9876;
		
		int va = cb + (pc / 2);
		
		// Before
		CodeCache.setCB(cb);
		CodeCache.setPC(pc);
		
		fillPageZero(va + 0);
		fillPageZero(va + 1);
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);
		
		// After
		assertEquals(0x12,   CodeCache.getCodeByte());
		assertEquals(0x3456, CodeCache.getCodeWord());
		assertEquals(0x78,   CodeCache.getCodeByte());
	}
	@Test
	public void testGetCodeWord3() {
		int cb = 0x400080;
		int pc = 0x00FE;
		
		int va = cb + (pc / 2);
		
		// Before
		CodeCache.setCB(cb);
		CodeCache.setPC(pc);
		
		fillPageZero(va + 0);
		fillPageZero(va + 1);
		Memory.rawWrite(va + 0, 0x1234);
		Memory.rawWrite(va + 1, 0x5678);
		
		// After
		assertEquals(0x12,   CodeCache.getCodeByte());
		assertEquals(0x3456, CodeCache.getCodeWord());
		assertEquals(0x78,   CodeCache.getCodeByte());
	}


}

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

import yokwe.majuro.mesa.Processor;

public class TestProcessor extends TestBase {
	@Test
	public void testPushLong() {
		// Before
		assertEquals(0, Processor.SP);
		assertNotEquals(0x5678, Processor.stack[0]); // low  word
		assertNotEquals(0x1234, Processor.stack[1]); // high word

		Processor.pushLong(0x12345678);
		
		// After
		assertEquals(2, Processor.SP);
		assertEquals(0x5678, Processor.stack[0]); // low  word
		assertEquals(0x1234, Processor.stack[1]); // high word
	}
	
	@Test
	public void testPopLong() {
		// Before
		assertEquals(0, Processor.SP);

		Processor.SP = 2;
		Processor.stack[0] = 0x5678; // low  word
		Processor.stack[1] = 0x1234; // high word
		
		int value = Processor.popLong();
		
		// After
		assertEquals(0, Processor.SP);
		assertEquals(0x12345678, value);
	}
}

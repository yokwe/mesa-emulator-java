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

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.mesa.CodeCache;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Perf;
import yokwe.majuro.mesa.Processor;

public class TestBase {
	protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);
	
	protected static final Random random = new Random(System.currentTimeMillis());
	
	protected static void fillPage(int va, int value, int inc) {
		short[] page = Memory.rawPage(va);
		for(int i = 0; i < page.length; i++) {
			page[i] = (short)value;
			value += inc;
		}
	}
	protected static void fillPage(int va, int value) {
		fillPage(va, value, 0);
	}
	protected static void fillPageZero(int va) {
		fillPage(va, 0);
	}
	
	protected static void fillStackZero() {
		for(int i = 0; i < Processor.stack.length; i++) {
			Processor.stack[i] = 0;
		}
	}
	
	protected static final int DEFAULT_MDS = 0x0004_0000;
	protected static final int DEFAULT_CB  = 0x0003_0080;
	protected static final int DEFAULT_PC  = 0x0020;
	
	@Before
	public void setUp() throws Exception {
		int vmBIts = 23;
		int rmBits = 20;
		
		Perf.initialize();

		Memory.initialize(vmBIts, rmBits);
		
		Memory.setMDS(DEFAULT_MDS);

		CodeCache.setCB(DEFAULT_CB);
		CodeCache.setPC(DEFAULT_PC);
		
		Processor.SP = 0;
		
		Processor.savedPC = Processor.SP;
		Processor.savedPC = CodeCache.getPC();
		
		fillStackZero();
		
		fillPage(0x0003_0000, 0x3000, 1);
		fillPage(0x0003_0000, 0x3000, 1);
		fillPage(0x0003_0100, 0x3100, 1);
		fillPage(0x0004_0000, 0x4000, 1);
		fillPage(0x0004_0100, 0x4100, 1);
		fillPage(0x0005_0000, 0x5000, 1);
	}
	
	@After
	public void tearDown() throws Exception {
	}

}

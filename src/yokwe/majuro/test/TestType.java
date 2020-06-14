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

import static org.junit.Assert.*;

import org.junit.Test;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.type.*;
import yokwe.util.LoggingUtil;

public class TestType extends TestBase {
	@Test
	public void testLocalWord() {
		int va = 0x1_0000;
		
		//LocalWord: TYPE = MACHINE DEPENDENT RECORD[
		//  available(0:0..7): BYTE,
		//  fsi(0:8..15): FSIndex];

		assertEquals(1, LocalWord.SIZE);

		{
			fillPageZero(va);
			
			assertNotEquals(0xAB, LocalWord.available.get(va));
			assertNotEquals(0xCD, LocalWord.fsi.get(va));

			Memory.rawWrite(va, 0xABCD);
			
			assertEquals(0xAB, LocalWord.available.get(va));
			assertEquals(0xCD, LocalWord.fsi.get(va));
		}
		
		{
			fillPageZero(va);
			
			assertNotEquals(0xAB, LocalWord.available.get(va));
			assertNotEquals(0xCD, LocalWord.fsi.get(va));

			LocalWord.available.set(va, 0xAB);
			assertEquals(0xAB, LocalWord.available.get(va));
			assertNotEquals(0xCD, LocalWord.fsi.get(va));
			
			LocalWord.fsi.set(va, 0xCD);
			assertEquals(0xAB, LocalWord.available.get(va));
			assertEquals(0xCD, LocalWord.fsi.get(va));
		}
	}
	
	@Test
	public void testLocalOverhead() {
		int va = 0x1_0000;
		
		//LocalOverhead : TYPE = MACHINE DEPENDENT RECORD [
		//  word (0):       LocalWord.
		//  returnlink(1) : ShortControlLink,
		//  globallink(2) : GFTHandle,
		//  pc(3):          CARDINAL,
		//  local(4):       LocaiVariables];
		
		// LocalWord: TYPE = RECORD [
		//   available (0:0.. 7): BYTE,
		//   fsi       (0:8..15): FSIndex];

		assertEquals(4, LocalOverhead.SIZE);

		{
			fillPageZero(va);
			
			assertNotEquals(0x12,   LocalOverhead.word.available.get(va));
			assertNotEquals(0x34,   LocalOverhead.word.fsi.get(va));
			assertNotEquals(0x2345, LocalOverhead.returnlink.get(va));
			assertNotEquals(0x3456, LocalOverhead.globallink.get(va));
			assertNotEquals(0x4567, LocalOverhead.pc.get(va));

			Memory.rawWrite(va + 0, 0x1234);
			Memory.rawWrite(va + 1, 0x2345);
			Memory.rawWrite(va + 2, 0x3456);
			Memory.rawWrite(va + 3, 0x4567);
			
			assertEquals(0x12, LocalOverhead.word.available.get(va));
			assertEquals(0x34, LocalOverhead.word.fsi.get(va));
			assertEquals(0x2345, LocalOverhead.returnlink.get(va));
			assertEquals(0x3456, LocalOverhead.globallink.get(va));
			assertEquals(0x4567, LocalOverhead.pc.get(va));
		}
	}
	
	@Test
	public void testTaggedControlLink() {
		int va = 0x1_0000;
		
		// TaggedControlLink: TYPE = RECORD[
		//   data (0: 0..13): UNSPECIFIED,
		//   tag  (0:14..15): LinkType,
		//   fill (1: 0..15): UNSPECIFIED];

		assertEquals(2, TaggedControlLink.SIZE);

		{
			fillPageZero(va);
			
			assertNotEquals(0x4567 >> 2, TaggedControlLink.data.get(va));
			assertNotEquals(0x0003, TaggedControlLink.tag.get(va));
			assertNotEquals(0x89AB, TaggedControlLink.fill.get(va));
			
			Memory.rawWrite(va + 0, 0x4567);
			Memory.rawWrite(va + 1, 0x89AB);
			
			assertEquals(0x4567 >> 2, TaggedControlLink.data.get(va));
			assertEquals(0x0003, TaggedControlLink.tag.get(va));
			assertEquals(0x89AB, TaggedControlLink.fill.get(va));
		}
	}

	@Test
	public void testGlobalWord() {
		int va = 0x1_0000;
		
		// GlobalWord: TYPE = MACHINE DEPENDENT RECORD [
		//   gfi (0:0..13): GFTIndex,
		//   trapxfers (0:14..14),
		//   codelinks (0:15..15): BOOLEAN];

		assertEquals(1, GlobalWord.SIZE);

		{
			fillPageZero(va);
			
			assertNotEquals(0xABCD >> 2, GlobalWord.gfi.get(va));
//			assertNotEquals(false, GlobalWord.trapxfers.get(va));
			assertNotEquals(true, GlobalWord.codelinks.get(va));

			Memory.rawWrite(va, 0xABCD);
			
			assertEquals(0xABCD >> 2, GlobalWord.gfi.get(va));
			assertEquals(false, GlobalWord.trapxfers.get(va));
			assertEquals(true, GlobalWord.codelinks.get(va));
		}
		
		{
			fillPageZero(va);

			GlobalWord.gfi.set(va, 0x3FFF);			
			
			assertEquals(0xFFFC, Memory.rawRead(va));
		}
		
		{
			fillPageZero(va);

			GlobalWord.trapxfers.set(va, true);			
			
			assertEquals(0x0002, Memory.rawRead(va));
		}
		
		{
			fillPageZero(va);

			GlobalWord.codelinks.set(va, true);			
			
			assertEquals(0x0001, Memory.rawRead(va));
		}
	}

	
	// Simple Record
	public void testAVItem() {
		int va = 0x1_0000;
		
		// AVItem: TYPE = RECORD[data (0:0..13): UNSPECIFIED, tag (0:14..15): AVItemType];
		
		assertEquals(1, AVItem.SIZE);

		// read
		{
			fillPageZero(va);
			
			assertNotEquals(0xABCD >> 2, AVItem.data.get(va));
			assertNotEquals(0x01, AVItem.tag.get(va));

			Memory.rawWrite(va, 0xABCD);
			
			assertEquals(0xABCD >> 2, AVItem.data.get(va));
			assertEquals(0x01, AVItem.tag.get(va));
		}

		// write
		{
			fillPageZero(va);
			
			AVItem.data.set(va, 0x3FFF);
			
			assertEquals(0xFFFC, Memory.rawRead(va));
		}
		// write
		{
			fillPageZero(va);
			
			AVItem.tag.set(va, 0x3);
			
			assertEquals(0x3, Memory.rawRead(va));
		}
	}
	
	// Simple Enum
	@Test
	public void testAVItemType() {
		int va = 0x1_0000;
		
		// AVItemType: TYPE = {frame(0), empty(1), indirect(2), unused(3)};
		
		assertEquals(1, AVItemType.SIZE);

		// read
		{
			fillPageZero(va);
			
			Memory.rawWrite(va, AVItemType.UNUSED);
			
			assertEquals(0x3, AVItemType.get(va));
		}

		// write
		{
			fillPageZero(va);
			
			AVItemType.set(va, AVItemType.UNUSED);
			
			assertEquals(AVItemType.UNUSED, Memory.rawRead(va));
		}
		
		// Exception get
		{
			fillPageZero(va);

			Memory.rawWrite(va, 0xFFFF);
			
			try {
				LoggingUtil.turnOff();
				
				AVItemType.get(va);
				fail();
			} catch (UnexpectedException e) {
				//
			} finally {
				LoggingUtil.turnOn();
			}
		}
		
		// Exception set
		{
			fillPageZero(va);
			
			try {
				LoggingUtil.turnOff();

				AVItemType.set(va, 0xFFFF);
				fail();
			} catch (UnexpectedException e) {
				//
			} finally {
				LoggingUtil.turnOn();
			}
		}
	}

	// Simple Array
	@Test
	public void testStateAllocationTable() {
		int va = 0x1_0000;

		assertEquals(8, StateAllocationTable.SIZE);

		// read
		{
			fillPageZero(va);
			
			Memory.rawWrite(va + 3, 0xABCD);
			
			assertEquals(0xABCD, StateAllocationTable.get(va, 3));
		}
		
		// write
		{
			fillPageZero(va);
			
			StateAllocationTable.set(va, 3, 0xABCD);
			
			assertEquals(0xABCD, Memory.rawRead(va + 3));
		}
		
		// Exception get
		{
			fillPageZero(va);

			try {
				LoggingUtil.turnOff();
				
				StateAllocationTable.get(va, 10);
				fail();
			} catch (UnexpectedException e) {
				//
			} finally {
				LoggingUtil.turnOn();
			}
		}
		
		// Exception set
		{
			fillPageZero(va);

			try {
				LoggingUtil.turnOff();
				
				StateAllocationTable.set(va, 10, 10);
				fail();
			} catch (UnexpectedException e) {
				//
			} finally {
				LoggingUtil.turnOn();
			}
		}

	}

	// Simple Subrange
	@Test
	public void testPriority() {
		int va = 0x1_0000;
		
	//  Priority: TYPE = CARDINAL [0..7];
		
		assertEquals(1, Priority.SIZE);

		// read
		{
			fillPageZero(va);
			
			Memory.rawWrite(va, 0x7);
			
			assertEquals(0x7, Priority.get(va));
		}

		// write
		{
			fillPageZero(va);
			
			Priority.set(va, 0x7);
			
			assertEquals(0x7, Memory.rawRead(va));
		}
		
		// Exception get
		{
			fillPageZero(va);

			Memory.rawWrite(va, 0xFFFF);
			
			try {
				LoggingUtil.turnOff();
				
				Priority.get(va);
				fail();
			} catch (UnexpectedException e) {
				//
			} finally {
				LoggingUtil.turnOn();
			}
		}
		
		// Exception set
		{
			fillPageZero(va);
			
			try {
				LoggingUtil.turnOff();

				Priority.set(va, 0xFFFF);
				fail();
			} catch (UnexpectedException e) {
				//
			} finally {
				LoggingUtil.turnOn();
			}
		}
	}

}

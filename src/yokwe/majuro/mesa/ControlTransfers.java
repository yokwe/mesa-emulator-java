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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Type.CARD16;
import yokwe.majuro.mesa.Type.CARD32;
import yokwe.majuro.mesa.Type.CARD8;
import yokwe.majuro.mesa.Type.LONG_POINTER;
import yokwe.majuro.mesa.Type.POINTER;
import yokwe.majuro.mesa.type.XferType;

public final class ControlTransfers {
	private static final Logger logger = LoggerFactory.getLogger(ControlTransfers.class);

	// 9.5.3 Trap Handlers
	
//	StateWord: TYPE = MACHINE DEPENDENT RECORD [
//        break(0: 0..7),
//        stkptr(0: 8..14): BYTE];
//
//  StateVector: TYPE = MACHINE DEPENDENT RECORD [
//        stack(0): ARRAY [0..StackDepth) OF UNSPECIFIED,
//        word(14): StateWord,
//        frame(15): LocalFrameHandle,
//        data(16): BLOCK];
	
	public static void SaveStack(@LONG_POINTER int state) {
		// FIXME
	}
	public static void LoadStack(@LONG_POINTER int state) {
		// FIXME
	}

	public static void XFER(@CARD32 int dst, @CARD16 int src, XferType type, boolean freeFlag) {
		// FIXME
	}

	// 9.5.1 Trap Routines
	public static void boundsTrap() {
		// FIXME
	}
	public static void breakTrap() {
		// FIXME
	}
	public static void codeTrap(@POINTER int gfi) {
		// FIXME
	}
	public static void controlTrap(@CARD16 int src) {
		// FIXME
	}
	public static void divCheckTrap() {
		// FIXME
	}
	public static void divZeroTrap() {
		// FIXME
	}
	public static void escOpcodeTrap(@CARD8 int opcode) {
		// FIXME
	}
	public static void interruptError() {
		// FIXME
	}
	public static void opcodeTrap(@CARD8 int opcode) {
		// FIXME
	}
	public static void pointerTrap() {
		// FIXME
	}
	public static void processTrap() {
		// FIXME
	}
	public static void rescheduleError() {
		// FIXME
	}
	public static void stackError() {
		logger.error("stackError");
		throw new UnexpectedException();
	}
	public static void unboundTrap(@CARD32 int dst) {
		// FIXME
	}
	public static void hardwareError() {
		logger.error("hardwareError");
		throw new UnexpectedException();
	}


}

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

import yokwe.majuro.mesa.Type.CARD16;
import yokwe.majuro.mesa.Type.CARD32;
import yokwe.majuro.mesa.Type.CARD8;
import yokwe.majuro.mesa.Type.FAULT_INDEX;
import yokwe.majuro.mesa.Type.LONG_POINTER;
import yokwe.majuro.mesa.Type.LongNumber;
import yokwe.majuro.mesa.Type.POINTER;
import yokwe.majuro.mesa.Type.PSB_INDEX;
import yokwe.majuro.mesa.type.ProcessDataArea;
import yokwe.majuro.mesa.type.StateVector;

public final class Processes {
	// 10.3.1 Queuing Procedures
	public static void requeue(@LONG_POINTER int src, @LONG_POINTER int dst, @PSB_INDEX int psb) {
		// FIXME
		if (psb == 0) Processor.error();
		dequeue(src, psb);
		enqueue(dst, psb);
	}
	
	public static void dequeue(@LONG_POINTER int src, @PSB_INDEX int psb) {
		// FIXME
	}
	public static void enqueue(@LONG_POINTER int src, @PSB_INDEX int psb) {
		// FIXME
	}
	
	// 10.4.1 Scheduler
	public static void reschedule(boolean preemption) {
		// FIXME
	}
	public static void reschedule() {
		reschedule(false);
	}
	
	// 10.4.4.2 Interrupt Processing
	public static void notifyWakeup(@LONG_POINTER int c) {
		// FIXME
	}
	// 10.4.3 Faults
	public static void faultOne(@FAULT_INDEX int fi, @CARD16 int parameter) {
		//psb: PsbIndex = Fault[fi];
		@PSB_INDEX int psb = fault(fi);
		// state: POINTER TO StateVector = Fetch[@PDA.block[psb].context]^;
		@POINTER int state = Memory.fetch(ProcessDataArea.block.conext.getAddress(Memory.PDA,  psb));
		// StorePda[@state.data[o]]^ = parameter;
		Memory.storePDA(StateVector.data.getAddress(state, 0), parameter);
		Processor.abort();
	}
	public static void faultTwo(@FAULT_INDEX int fi, @CARD32 int parameter) {
		//psb: PsbIndex = Fault[fi];
		@PSB_INDEX int psb = fault(fi);
		// state: POINTER TO StateVector = Fetch[@PDA.block[psb].context]^;
		@POINTER int state = Memory.fetch(ProcessDataArea.block.conext.getAddress(Memory.PDA,  psb));
		// StorePda[@state.data[0]]^ = LowHalf[parameter];
		Memory.storePDA(StateVector.data.getAddress(state, 0), LongNumber.lowHalf(parameter));
		// StorePda[@state.data[1]]^ = LowHalf[parameter];
		Memory.storePDA(StateVector.data.getAddress(state, 1), LongNumber.highHalf(parameter));
		Processor.abort();
	}
	public static @PSB_INDEX int fault(@FAULT_INDEX int fi) {
		@PSB_INDEX int faulted = Processor.PSB;
		
		// Requeue[src: @PDA.ready, dst: @PDA.fault[fi].queue, psb: faulted];
		int src = ProcessDataArea.ready.getAddress(Memory.PDA);
		int dst = ProcessDataArea.fault.getAddress(Memory.PDA, fi);
		requeue(src, dst, faulted);
		notifyWakeup(ProcessDataArea.fault.condition.getAddress(Memory.PDA, fi));
		CodeCache.setPC(Processor.savedPC);
		Processor.SP = Processor.savedSP;
		reschedule(true);
		return faulted;
	}
	
	//Framefault: PROCEDURE [hi: FStndex] = {FaultOne[qFrameFault, fsi]);
	public static void frameFault(@CARD8 int fsi) {
		faultOne(Constant.qFrameFault, fsi);
	}
	//PageFault: PROCEDURE [ptr: LONG POINTER] = {FaultTwo[qPageFault, ptr]};
	public static void pageFault(@LONG_POINTER int ptr) {
		faultTwo(Constant.qPageFault, ptr);
	}
	//WriteProtectFault: PROCEDURE [ptr: LONG POINTER] = {FaultTwo[qWriteProtectFault, ptr]};
	public static void writeProtectFault(@LONG_POINTER int ptr) {
		faultTwo(Constant.qWriteProtectFault, ptr);
	}
}

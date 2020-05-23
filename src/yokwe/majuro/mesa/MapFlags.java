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

import yokwe.majuro.mesa.Type.LONG_POINTER;

public final class MapFlags {
	//MapFlags: TYPE = MACHINE DEPENDENT RECORD (
	//  reserved (0:0..12) : UNSPEClFIED[0..17777B],
	//  protected (0:13..13) : BOOLEAN,
	//  dirty (0:14..14): BOOLEAN,
	//  referenced (0:15..15): BOOLEAN];
	public static final short CLEAR      = 0;
	public static final short REFERENCED = 0x0001;
	public static final short DIRYT      = 0x0002;
	public static final short PROTECT    = 0x0004;
	public static final short VACANT     = DIRYT | PROTECT;
	public static final short REFERENCED_AND_DIRTY = REFERENCED | DIRYT;
	
	public int rmPage;
	public int flag;
	
	public MapFlags() {
		rmPage = 0;
		flag   = VACANT;
	}
	public void set(int rmPage, int flag) {
		this.rmPage = rmPage;
		this.flag   = flag;
	}
	
	public boolean isVacant() {
		return flag == VACANT;
	}
	private boolean isProtect() {
		return (flag & PROTECT) != 0;
	}

	public void checkFetch(@LONG_POINTER int va) {
		// check vacant
		if (isVacant()) {
			Processes.pageFault(va);
		}
	}
	public void updateFlagFetch() {
		// set referenced flag
		flag = (~REFERENCED & flag) | REFERENCED;
	}
	
	public void checkStore(@LONG_POINTER int va) {
		// check vacant
		if (isVacant()) {
			Processes.pageFault(va);
		}
		// check protect
		if (isProtect()) {
			Processes.writeProtectFault(va);
		}
	}
	public boolean isReferencedDirty() {
		return (flag & REFERENCED_AND_DIRTY) == REFERENCED_AND_DIRTY;
	}
	public void updateFlagStore() {
		// set referenced and dirty flag
		flag = (~REFERENCED_AND_DIRTY & flag) | REFERENCED_AND_DIRTY;
	}
}
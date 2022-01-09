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
package yokwe.majuro.symbol;

import org.slf4j.LoggerFactory;

import yokwe.util.UnexpectedException;

public class BitInfo {
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(BitInfo.class);

	final int startBit;
	final int stopBit;
	
	final int    shift;
	final String mask;
	final String bits;
	
	public BitInfo(int size, int startBit, int stopBit) {
		this.startBit = startBit;
		this.stopBit  = stopBit;
		
		int width = stopBit - startBit;
		long bits  = (1 << (width + 1)) - 1;
		switch(size) {
		case 1:
			this.shift = 15 - stopBit;
			this.mask  = String.format("%16s", Long.toBinaryString((bits << shift))).replace(" ", "0").replaceAll("(.{4})(.{4})(.{4})(.{4})", "0b$1_$2_$3_$4");
			this.bits  = String.format("%16s", Long.toBinaryString(bits)).replace(" ", "0").replaceAll("(.{4})(.{4})(.{4})(.{4})", "0b$1_$2_$3_$4");
			break;
		case 2:
			this.shift = 31 - stopBit;
			this.mask  = String.format("%32s", Long.toBinaryString(bits << shift)).replace(" ", "0").replaceAll("(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})", "0b$1_$2_$3_$4_$5_$6_$7_$8");
			this.bits  = String.format("%32s", Long.toBinaryString(bits)).replace(" ", "0").replaceAll("(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})", "0b$1_$2_$3_$4_$5_$6_$7_$8");
			break;
		default:
			logger.error("size {}", size);
			throw new UnexpectedException("Unexpected");
		}
	}
	
	@Override
	public String toString() {
		return String.format("{%d %d}", startBit, stopBit);
	}
}
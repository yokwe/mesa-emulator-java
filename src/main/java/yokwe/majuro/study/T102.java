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
package yokwe.majuro.study;

import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Perf;
import yokwe.majuro.mesa.type.LocalOverhead;

public class T102 {
	private static final Logger logger = LoggerFactory.getLogger(T102.class);

	
	static Random random = new Random();
	
	static void S001(int[] address, long[] elapsedTime) {
		for(int i = 0; i < address.length; i++) {
			long timeStart = System.currentTimeMillis();
			
			Memory.fetch(address[i]);
			
			long timeStop  = System.currentTimeMillis();
			elapsedTime[i] = timeStop - timeStart;
		}
	}
	
	static void S002(int[] address, long[] elapsedTime) {
		for(int i = 0; i < address.length; i++) {
			long timeStart = System.currentTimeMillis();
			
			LocalOverhead.returnlink.get(address[i]);
			
			long timeStop  = System.currentTimeMillis();
			elapsedTime[i] = timeStop - timeStart;
		}
	}
	
	static void S003(int[] address, long[] elapsedTime) {
		for(int i = 0; i < address.length; i++) {
			long timeStart = System.currentTimeMillis();
			
//			LocalOverhead.getFsi(address[i]);
			
			long timeStop  = System.currentTimeMillis();
			elapsedTime[i] = timeStop - timeStart;
		}
	}
	
	static void S004(int[] address, long[] elapsedTime) {
		for(int i = 0; i < address.length; i++) {
			long timeStart = System.currentTimeMillis();
			
//			LocalOverhead.setFsi(address[i], i);
			
			long timeStop  = System.currentTimeMillis();
			elapsedTime[i] = timeStop - timeStart;
		}
	}
	
	static void measure() {
		int count = 10000000;
		
		int[] address = new int[count];
		for(int i = 0; i < count; i++) {
			address[i] = 0x1_0000 + random.nextInt(0x1_00000);
		}
		long[] time = new long[count];
		
		int loop = 2;
		
		S001(address, time);

		logger.info("==== S001");
		for(int i = 0; i < loop; i++) {
			S001(address, time);
			logger.info("S001 {} {} {}", Arrays.stream(time).min().getAsLong(), Arrays.stream(time).max().getAsLong(), Arrays.stream(time).average().getAsDouble());
		}
		
		logger.info("==== S002");
		for(int i = 0; i < loop; i++) {
			S002(address, time);
			logger.info("S002 {} {} {}", Arrays.stream(time).min().getAsLong(), Arrays.stream(time).max().getAsLong(), Arrays.stream(time).average().getAsDouble());
		}
		
		logger.info("==== S003");
		for(int i = 0; i < loop; i++) {
			S003(address, time);
			logger.info("S003 {} {} {}", Arrays.stream(time).min().getAsLong(), Arrays.stream(time).max().getAsLong(), Arrays.stream(time).average().getAsDouble());
		}
		
		logger.info("==== S004");
		for(int i = 0; i < loop; i++) {
			S004(address, time);
			logger.info("S004 {} {} {}", Arrays.stream(time).min().getAsLong(), Arrays.stream(time).max().getAsLong(), Arrays.stream(time).average().getAsDouble());
		}
	}
	public static void main(String[] args) {
		logger.info("START");
		
		Memory.initialize(24, 22);
		
		measure();

		Perf.stats();
		logger.info("STOP");
	}

}

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
package yokwe.majuro.mesa.opcode;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.CodeCache;
import yokwe.majuro.mesa.ControlTransfers;
import yokwe.majuro.mesa.Perf;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.mesa.opcode.Opcode.Register;
import yokwe.majuro.mesa.opcode.Opcode.Type;
import yokwe.util.ClassUtil;

public class Interpreter {
	private static final Logger logger = LoggerFactory.getLogger(Interpreter.class);

	public static final boolean EnableStats = true;
	
	public static final Runnable[] tableMop = new Runnable[256];
	public static final Runnable[] tableEsc = new Runnable[256];
	
	public static final long[] countMop = new long[256];
	public static final long[] countEsc = new long[256];
	
	public static void execute() {
		Processor.savedPC = CodeCache.getPC();
		Processor.savedSP = Processor.SP;
		dispatchMop(CodeCache.getCodeByte());
	}
	
	public static void dispatchMop(/* CARD8 */ int opcode) {
		if (Perf.ENABLE) Perf.dispatch++;
		tableMop[opcode].run();
		if (EnableStats) countMop[opcode]++;
	}
	public static void dispatchEsc(/* CARD8 */ int opcode) {
		tableEsc[opcode].run();
		if (EnableStats) countEsc[opcode]++;
	}
	
	public static void stats() {
		for(Opcode info: Opcode.values()) {
			long count = (info.type == Type.MOP) ? countMop[info.code] : countEsc[info.code];
			if (count != 0) {
				logger.info("{}", String.format("%s %-8s  %10d", info.type, info.name, countMop[info.code]));
			}
		}
	}
	
	private static class OpcodeTrap implements Runnable {
		private final int code;
		OpcodeTrap(int code) {
			this.code = code;
		}
		public void run() {
			ControlTransfers.opcodeTrap(code);
		}
	}
	private static class EscOpcodeTrap implements Runnable {
		private final int code;
		EscOpcodeTrap(int code) {
			this.code = code;
		}
		public void run() {
			ControlTransfers.escOpcodeTrap(code);
		}
	}
	
	private static void register(Opcode info, Runnable runnable) {
		logger.info("register {}", info);
		switch(info.type) {
		case MOP:
			if (tableMop[info.code] == null) {
				tableMop[info.code] = runnable;
			} else {
				logger.error("already assigned ");
				logger.error("  opcode   {}", info);
			}
			break;
		case ESC:
		case ESCL:
			if (tableEsc[info.code] == null) {
				tableEsc[info.code] = runnable;
			} else {
				logger.error("already assigned ");
				logger.error("  opcode   {}", info);
			}
			break;
		default:
			throw new UnexpectedException();
		}
	}
	
	private static String PACKAGE_NAME_FOR_SCAN = "mh.majuro.mesa.opcode";
	public static void initialize() {
		int count = 0;
		
		for(int i = 0; i < tableMop.length; i++) tableMop[i] = null;
		for(int i = 0; i < tableEsc.length; i++) tableEsc[i] = null;
		
		logger.info("Start initialize opcode  package = {}", PACKAGE_NAME_FOR_SCAN);
		List<Class<?>> classes = ClassUtil.findClass(PACKAGE_NAME_FOR_SCAN);
		
		for(Class<?> clazz : classes) {
			// scan method - find annotated static method match Runnable and register method reference of the method
			for(Method method: clazz.getDeclaredMethods()) {
				if (method.isAnnotationPresent(Register.class)) {
					Runnable runnable = ClassUtil.toRunnable(method);
					if (runnable != null) {
						Register registerOpcode = method.getAnnotation(Register.class);
						register(registerOpcode.value(), runnable);
						count++;
					} else {
						logger.error("Cannot get runnable");
						logger.error("  clazz  {}", clazz.getName());
						logger.error("  method {}", method.toString());
						throw new UnexpectedException();
					}
				}
			}
		}
		logger.info(String.format("Stop  initialize opcode  count = %d / %d", count, Opcode.values().length));
		
		// install opcode trap for empty entry of tableMop and tableEsc.
		for(Opcode info: Opcode.values()) {
			int code = info.code;
			switch (info.type) {
			case MOP:
				if (tableMop[code] == null) {
					tableMop[code] = new OpcodeTrap(code);
				}
				break;
			default:
				if (tableEsc[code] == null) {
					tableEsc[code] = new EscOpcodeTrap(code);
				}
				break;
			}
		}
	}

	public static void main(String[] args) {
    	logger.info("START");
    	initialize();
    	tableMop[0].run();
    	logger.info("STOP");
    }
}

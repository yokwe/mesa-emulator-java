package yokwe.majuro.opcode;

import static yokwe.majuro.opcode.Opcode.*;

import java.lang.reflect.Method;
import java.util.List;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.ControlTransfers;
import yokwe.majuro.mesa.Perf;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.util.ClassUtil;

public class Interpreter {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Interpreter.class);

	public static final boolean ENABLE_STATS = true;

	public static final Runnable[] tableMop = new Runnable[256];
	public static final Runnable[] tableEsc = new Runnable[256];
	
	public static final long[] countMop = new long[256];
	public static final long[] countEsc = new long[256];
	
	public static void execute() {
		Processor.savedPC = Processor.PC;
		Processor.savedSP = Processor.SP;
// FIXME		dispatchMop(CodeCache.getCodeByte());
	}
	
	public static void dispatchMop(byte opcode) {
		if (Perf.ENABLED) Perf.dispatch++;
		tableMop[opcode].run();
		if (ENABLE_STATS) countMop[opcode]++;
	}
	public static void dispatchEsc(byte opcode) {
		tableEsc[opcode].run();
		if (ENABLE_STATS) countEsc[opcode]++;
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
		private final byte code;
		OpcodeTrap(byte code) {
			this.code = code;
		}
		public void run() {
			ControlTransfers.opcodeTrap(code);
		}
	}
	private static class EscOpcodeTrap implements Runnable {
		private final byte code;
		EscOpcodeTrap(byte code) {
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
			byte code = (byte)info.code;
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

package yokwe.majuro.opcode;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.CodeCache;
import yokwe.majuro.mesa.ControlTransfers;
import yokwe.majuro.mesa.Perf;
import yokwe.majuro.mesa.Processor;
import yokwe.majuro.opcode.Opcode.Register;
import yokwe.majuro.util.ClassUtil;

public final class Interpreter {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Interpreter.class);

	public static final Runnable[] tableMop = new Runnable[256];
	public static final Runnable[] tableEsc = new Runnable[256];
	
	public static void execute() {
		Processor.savedPC = CodeCache.PC();
		Processor.savedSP = Processor.SP;
		dispatchMop(CodeCache.getCodeByte());
	}
	
	public static void dispatchMop(int opcode) {
		if (Perf.ENABLED) Perf.dispatch++;
		tableMop[opcode].run();
		// opcode can rise Fault or Trap, so do increment count after call run()
		if (Perf.OPCODE) Perf.opcodeMop[opcode]++;
	}
	public static void dispatchEsc(int opcode) {
		tableEsc[opcode].run();
		// opcode can rise Fault or Trap, so do increment count after call run()
		if (Perf.OPCODE) Perf.opcodeEsc[opcode]++;
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
	
	private static String PACKAGE_NAME_FOR_SCAN = "yokwe.majuro.opcode";
	public static void initialize() {
		int count = 0;
		
		for(int i = 0; i < tableMop.length; i++) tableMop[i] = null;
		for(int i = 0; i < tableEsc.length; i++) tableEsc[i] = null;
		
		logger.info("Start initialize opcode  package = {}", PACKAGE_NAME_FOR_SCAN);
		List<Class<?>> classes = ClassUtil.findClass(PACKAGE_NAME_FOR_SCAN);
		Collections.sort(classes, (a, b) -> a.getSimpleName().compareTo(b.getSimpleName()));
		
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

package yokwe.majuro.mesa;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.opcode.Opcode;

public final class Perf {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Perf.class);

	public static final boolean ENABLED = true;
	public static final boolean OPCODE  = true;
	
	// Memory
	public static long cacheHit          = 0;
	public static long cacheMissConflict = 0;
	public static long cacheMissEmpty    = 0;

	public static long fetch       = 0;
	public static long store       = 0;
	public static long map         = 0;
	
	public static long readReal16  = 0;
	public static long writeReal16 = 0;
	public static long readReal32  = 0;
	public static long writeReal32 = 0;
	public static long read16      = 0;
	public static long write16     = 0;
	public static long read32      = 0;
	public static long write32     = 0;
	
	public static long lengthenMDS = 0;
	public static long fetchMDS    = 0;
	public static long storeMDS    = 0;
	public static long read16MDS   = 0;
	public static long write16MDS  = 0;
	public static long read32MDS   = 0;
	public static long write32MDS  = 0;
	
	public static long lengthenPDA = 0;
	
	// fault
	public static long frameFault        = 0;
	public static long pageFault         = 0;
	public static long writeProtectFault = 0;
	
	//
	public static long dispatch = 0;
	
	// code cache
	public static long codeCacheCodeByte    = 0;
	public static long codeCacheCodeWord    = 0;
	public static long codeCacheSameLast    = 0;
	public static long codeCacheSamePage    = 0;
	public static long codeCacheNotSamePage = 0;

	// opcode
	public static final long[] opcodeMop = new long[256];
	public static final long[] opcodeEsc = new long[256];
	

	private static final Field[] fields;
	static {
		List<Field> list = new ArrayList<>();
		for(Field field: Perf.class.getDeclaredFields()) {
			// Skip if field is not static field
			if (!Modifier.isStatic(field.getModifiers())) continue;
			// Skip if field type is not long
			if (!field.getType().equals(Long.TYPE)) continue;
			// Skip if field type is array
			if (field.getType().isArray()) continue;
			
			list.add(field);
		}
		fields = list.toArray(new Field[0]);
	}
	
	public static void stats() {
		{
			int count = 0;
			for(Field field: fields) {
				try {
					String name  = field.getName();
					long   value = field.getLong(null);
					
					// Skip if value is zero
					if (value == 0) continue;
					
					if (count == 0) logger.info("Perf stats START");
					count++;
					logger.info("{}", String.format("%-24s %10d", name, value));				
				} catch (IllegalArgumentException | IllegalAccessException e) {
					String exceptionName = e.getClass().getSimpleName();
					logger.error("{} {}", exceptionName, e);
					logger.error("field {}", field);
					throw new UnexpectedException(exceptionName, e);
				}
			}
			if (count != 0) logger.info("Perf stats STOP");
		}
		{
			int count = 0;
			for(Opcode info: Opcode.values()) {
				long opcodeCount = (info.type == Opcode.Type.MOP) ? opcodeMop[info.code] : opcodeEsc[info.code];
				if (opcodeCount != 0) {
					if (count == 0) logger.info("Perf opcode START");
					logger.info("{}", String.format("%s %-8s  %10d", info.type, info.name, opcodeCount));
					count++;
				}
			}
			if (count != 0) logger.info("Perf opcode STOP");
		}
	}
	
	public static void clear() {
		for(Field field: fields) {
			try {
				field.setLong(null, 0);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				String exceptionName = e.getClass().getSimpleName();
				logger.error("{} {}", exceptionName, e);
				logger.error("field {}", field);
				throw new UnexpectedException(exceptionName, e);
			}
		}
		for(int i = 0; i < opcodeMop.length; i++) {
			opcodeMop[i] = 0;
		}
		for(int i = 0; i < opcodeEsc.length; i++) {
			opcodeEsc[i] = 0;
		}
	}

}

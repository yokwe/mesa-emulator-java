package yokwe.majuro.mesa;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import yokwe.majuro.UnexpectedException;

public final class Perf {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Perf.class);

	public static final boolean ENABLED = true;
	
	// Memory
	public static long memoryFetch = 0;
	public static long memoryStore = 0;
	
	public static long cacheHit          = 0;
	public static long cacheMissConflict = 0;
	public static long cacheMissEmpty    = 0;

	// Mesa
	public static long fetch       = 0;
	public static long store       = 0;
	public static long mapFlag     = 0;
	public static long readReal16  = 0;
	public static long writeReal16 = 0;
	public static long readReal32  = 0;
	public static long writeReal32 = 0;
	public static long read16      = 0;
	public static long write16     = 0;
	public static long read32      = 0;
	public static long write32     = 0;
	
	// fault
	public static long pageFault         = 0;
	public static long writeProtectFault = 0;
	
	public static void dump() {
		Class<?> clazz = Perf.class;
		
		logger.info("Perf dump START");
		for(Field field: clazz.getDeclaredFields()) {
			try {
				// Skip if field is not static field
				if (!Modifier.isStatic(field.getModifiers())) continue;
				// Skip if field type is not long
				if (!field.getType().equals(Long.TYPE)) continue;

				String name  = field.getName();
				long   value = field.getLong(null);
				logger.info("{}", String.format("%-24s %10d", name, value));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				String exceptionName = e.getClass().getSimpleName();
				logger.error("{} {}", exceptionName, e);
				logger.error("clazz {}", clazz);
				logger.error("field {}", field);
				throw new UnexpectedException(exceptionName, e);
			}
		}
		logger.info("Perf dump STOP");
	}
}

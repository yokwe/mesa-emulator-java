package yokwe.majuro.mesa;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class Perf {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Perf.class);

	public static final boolean ENABLED = true;
	
	public static long fetchMemory = 0;
	public static long storeMemory = 0;
	
	public static long pageCacheHit          = 0;
	public static long pageCacheMissConflict = 0;
	public static long pageCacheMissEmpty    = 0;

	public static long fetch       = 0;
	public static long store       = 0;
	public static long readDbl     = 0;
	
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
				throw new Error(exceptionName, e);
			}
		}
		logger.info("Perf dump STOP");
	}
}

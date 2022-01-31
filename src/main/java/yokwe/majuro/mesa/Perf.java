package yokwe.majuro.mesa;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

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
	
	public static long lengthenMDS = 0;
	public static long read16MDS   = 0;
	public static long write16MDS  = 0;
	public static long read32MDS   = 0;
	public static long write32MDS  = 0;
	
	// fault
	public static long pageFault         = 0;
	public static long writeProtectFault = 0;
	
	private static final Field[] fields;
	static {		
		List<Field> list = new ArrayList<>();
		for(Field field: Perf.class.getDeclaredFields()) {
			// Skip if field is not static field
			if (!Modifier.isStatic(field.getModifiers())) continue;
			// Skip if field type is not long
			if (!field.getType().equals(Long.TYPE)) continue;
			
			list.add(field);
		}
		fields = list.toArray(new Field[0]);
	}
	
	public static void stats() {
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
	}

}

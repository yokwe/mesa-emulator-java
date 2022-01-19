package yokwe.majuro.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;

public class ClassUtil {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ClassUtil.class);

	private static Map<String, Map<String, Long>> staticLongValueMap = new TreeMap<>();
	
	public static Long getStaticNumericValue(Class<?> clazz, String name) {
		String className = clazz.getCanonicalName();
		
		final Map<String, Long> map;
		
		if (staticLongValueMap.containsKey(className)) {
			map = staticLongValueMap.get(className);
		} else {
			map = new TreeMap<>();
			
			for(Field field: clazz.getDeclaredFields()) {
				try {
					int modifiers = field.getModifiers();
					// Ignore not static
					if (!Modifier.isStatic(modifiers)) continue;
					// Ignore not final
					if (!Modifier.isFinal(modifiers)) continue;
					
					String fieldName  = field.getName();
					String fieldType  = field.getType().getCanonicalName();
					Object fieldValue;
					fieldValue = field.get(null);
					
					if (fieldType.equals("int")) {
						long value = (int)fieldValue;
						map.put(fieldName, value);
					}
					if (fieldType.equals("long")) {
						long value = (long)fieldValue;
						map.put(fieldName, value);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					String exceptionName = e.getClass().getSimpleName();
					logger.error("{} {}", exceptionName, e);
					throw new UnexpectedException(exceptionName, e);
				}
			}
			staticLongValueMap.put(className, map);
		}
		if (map.containsKey(name)) {
			return map.get(name);
		} else {
			return null;
		}
	}

	public static Long getStaticNumericValue(String variableName) {
		final String className;
		final String name;
		
		{
			String[] names = variableName.split("\\.");
			name = names[names.length - 1];
			
			StringBuilder sb = new StringBuilder();
			sb.append(names[0]);
			for(int i = 1; i < names.length - 1; i++) {
				sb.append(".") ;
				sb.append(names[i]);
			}
			className = sb.toString();
		}
		
		try {
			Class<?> clazz = Class.forName(className);
			return getStaticNumericValue(clazz, name);
		} catch (ClassNotFoundException e) {
			logger.error("variableName {}", variableName);
			logger.error("className    {}", className);
			
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
}

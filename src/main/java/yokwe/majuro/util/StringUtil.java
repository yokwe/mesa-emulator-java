package yokwe.majuro.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;

public final class StringUtil {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(StringUtil.class);

	//
	// toJavaBinaryString
	//
	public static String toJavaBinaryString(char value) {
		return toJavaBinaryString(value, 16);
	}
	public static String toJavaBinaryString(short value) {
		return toJavaBinaryString(value, 16);
	}
	public static String toJavaBinaryString(int value) {
		return toJavaBinaryString(value, 32);
	}
	public static String toJavaBinaryString(long value) {
		return toJavaBinaryString(value, 64);
	}
	public static String toJavaBinaryString(long value, int digits) {
		String binaryString = Long.toBinaryString(value);
		String prefix = "0".repeat(digits - binaryString.length());
		String maskString = prefix + binaryString;
		return "0b" + maskString.replaceAll("....(?!$)", "$0_");
	}
	
	//
	// toHumanReadableString
	//
	private static Map<Long, String> numberStringMap = new TreeMap<>();
	static {
		// usual pattern
		numberStringMap.put(       0xFFL,        "0xFF");
		numberStringMap.put(     0xFFFFL,      "0xFFFF");
		numberStringMap.put(0xFFFF_FFFFL, "0xFFFF_FFFFL");

		// Java constant
		numberStringMap.put((long)Short.MIN_VALUE,   "Short.MIN_VALUE");
		numberStringMap.put((long)Short.MAX_VALUE,   "Short.MAX_VALUE");
		numberStringMap.put((long)Integer.MIN_VALUE, "Integer.MIN_VALUE");
		numberStringMap.put((long)Integer.MAX_VALUE, "Integer.MAX_VALUE");

	}
	public static String toJavaString(long value) {
		if (numberStringMap.containsKey(value)) {
			return numberStringMap.get(value);
		} else {
			final String suffix;
			if (Integer.MAX_VALUE < value) {
				// add L suffix
				suffix = "L";
			} else {
				suffix = "";
			}
			return Long.toString(value) + suffix;
		}
	}

	//
	// toJavaName
	//
	private static Map<String, String> javaKeywordMap = new TreeMap<>();
	static {
		javaKeywordMap.put("null",             "null_");
		javaKeywordMap.put("LONG CARDINAL",    "LONG_CARDINAL");
		javaKeywordMap.put("LONG UNSPECIFIED", "LONG_UNSPECIFIED");
		javaKeywordMap.put("LONG POINTER",     "LONG_POINTER");
	}
	public static String toJavaName(String name) {
		if (javaKeywordMap.containsKey(name)) {
			return javaKeywordMap.get(name);
		} else {
			return name;
		}
	}
	//
	// toJavaConstName
	//
	private enum CharKind {
		LOWER,
		UPPER,
		DIGIT,
		UNKNOWN
	}
	public static String toJavaConstName(String name) {
		StringBuilder ret = new StringBuilder();
		CharKind lastCharKind = CharKind.UNKNOWN;
		
		name = name.replace(" ", "_");

		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);

			if (Character.isLowerCase(c)) {
				// If this is first lower character after upper character, need special handling.
				// abcREITProfit => ABC_REIT_PROFIT
				if (lastCharKind == CharKind.UPPER) {
					int length = ret.length();
					if (2 <= length) {
						String c1 = ret.substring(0, length - 2);
						String c2 = ret.substring(length - 2, length - 1);
						String c3 = ret.substring(length - 1, length);
						if (c2.equals("_")) {
							//
						} else {
							ret.setLength(0);
							ret.append(c1);
							ret.append(c2);
							ret.append("_");
							ret.append(c3);
						}
					}
				}
				ret.append(Character.toUpperCase(c));
				lastCharKind = CharKind.LOWER;
			} else if (Character.isDigit(c)) {
				if (lastCharKind == CharKind.DIGIT) {
					ret.append(c);
				} else {
					if (ret.length() == 0) {
						ret.append(c);
					} else {
						ret.append('_').append(c);
					}
				}
				lastCharKind = CharKind.DIGIT;
			} else if (Character.isUpperCase(c)) {
				if (lastCharKind == CharKind.UPPER) {
					ret.append(c);
				} else {
					if (ret.length() == 0) {
						ret.append(c);
					} else {
						ret.append('_').append(c);
					}
				}
				lastCharKind = CharKind.UPPER;
			} else if (c == '-') {
				ret.append('_');
			} else {
				logger.error("{}", String.format("Unknown character type = %c - %04X", c, (int)c));
				logger.error("  name {}", name);
				throw new UnexpectedException("Unknown character");
			}
		}
		return ret.toString();
	}

	//
	// toHexString
	//
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	public static String toHexString(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	//
	// toString(Object)
	//
	public enum TimeZone {
		UTC,
		LOCAL,
		NEW_YORK,
	}
	
	public static final ZoneId UTC      = ZoneOffset.UTC;
	public static final ZoneId LOCAL    = ZoneId.systemDefault();
	public static final ZoneId NEW_YORK = ZoneId.of("America/New_York");
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface UseTimeZone {
		TimeZone value();
	}

	private static class ClassInfo {
		private static class FieldInfo {
			final Field    field;
			final String   name;
			final String   type;
			final boolean  isArray;
			final boolean  isEnum;
			final TimeZone useTimeZone;

			FieldInfo(Field field) {
				Class<?> type = field.getType();
				
				this.field   = field;
				this.name    = field.getName();
				this.type    = type.getName();
				this.isArray = type.isArray();
				this.isEnum  = type.isEnum();
				
				UseTimeZone useTimeZone = field.getDeclaredAnnotation(UseTimeZone.class);
				if (useTimeZone != null) {
					this.useTimeZone = useTimeZone.value();
				} else {
					this.useTimeZone = null;
				}
			}
		}

		private static Map<String, ClassInfo> map = new TreeMap<>();
		
		final FieldInfo[] fieldInfos;
		
		static ClassInfo get(Object o) {
			Class<?> clazz = o.getClass();
			String clazzName = clazz.getName();
			if (map.containsKey(clazzName)) {
				return map.get(clazzName);
			} else {
				ClassInfo classInfo = new ClassInfo(clazz);
				map.put(clazzName, classInfo);
				return classInfo;
			}
		}
		
		private static List<FieldInfo> getFieldList(Class<?> clazz) {
			List<FieldInfo> list = new ArrayList<>();
			
			// add filed of super
			{
				Class<?> superClass = clazz.getSuperclass();
				if (superClass != null) {
					list.addAll(getFieldList(superClass));
				}
			}
			
			// add field of clazz
			for(Field field: clazz.getDeclaredFields()) {
				int modifiers = field.getModifiers();
				// Ignore static
				if (Modifier.isStatic(modifiers)) continue;
				field.setAccessible(true); // to access protected and private file, call setAccessble(true) of the field
				list.add(new FieldInfo(field));
			}

			return list;
		}
		
		ClassInfo(Class<?> clazz) {
			fieldInfos = getFieldList(clazz).toArray(new FieldInfo[0]);
		}
	}
	public static String toString(Object o) {
		try {
			ClassInfo classInfo = ClassInfo.get(o);

			List<String>  result = new ArrayList<>();
			StringBuilder line   = new StringBuilder();
			
			for(ClassInfo.FieldInfo fieldInfo: classInfo.fieldInfos) {
				line.setLength(0);
				line.append(fieldInfo.name).append(": ");
				
				switch(fieldInfo.type) {
				case "double":
					line.append(Double.toString(fieldInfo.field.getDouble(o)));
					break;
				case "float":
					line.append(fieldInfo.field.getFloat(o));
					break;
				case "long":
					line.append(fieldInfo.field.getLong(o));
					break;
				case "int":
					line.append(fieldInfo.field.getInt(o));
					break;
				case "short":
					line.append(fieldInfo.field.getShort(o));
					break;
				case "byte":
					line.append(fieldInfo.field.getByte(o));
					break;
				case "char":
					line.append(String.format("'%c'", fieldInfo.field.getChar(o)));
					break;
				case "boolean":
					line.append(fieldInfo.field.getBoolean(o) ? "true" : "false");
					break;
				default:
				{
					Object value = fieldInfo.field.get(o);
					if (value == null) {
						line.append("null");
					} else if (value instanceof String) {
						// Quote special character in string \ => \\  " => \"
						String stringValue = value.toString().replace("\\", "\\\\").replace("\"", "\\\"");
						line.append("\"").append(stringValue).append("\"");
					} else if (value instanceof BigDecimal) {
						BigDecimal bigDecimal = (BigDecimal)value;
						line.append(bigDecimal.toPlainString());
					} else if (value instanceof LocalDateTime) {
						LocalDateTime  localDateTime  = (LocalDateTime)value;
						OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.UTC);
						
						String stringValue;
						if (fieldInfo.useTimeZone != null) {
							switch(fieldInfo.useTimeZone) {
							case UTC:
								stringValue = offsetDateTime.atZoneSameInstant(UTC).toLocalDateTime().toString();
								break;
							case LOCAL:
								stringValue = offsetDateTime.atZoneSameInstant(LOCAL).toLocalDateTime().toString();
								break;
							case NEW_YORK:
								stringValue = offsetDateTime.atZoneSameInstant(NEW_YORK).toLocalDateTime().toString();
								break;
							default:
								logger.error("Unexptected useTimeZone value {}", fieldInfo.useTimeZone);
								throw new UnexpectedException("Unexptected useTimeZone value");
							}
						} else {
							// Treat as LOCAL
							stringValue = offsetDateTime.atZoneSameInstant(LOCAL).toLocalDateTime().toString();
						}
						line.append(stringValue);
					} else if (fieldInfo.isArray) {
						List<String> arrayElement = new ArrayList<>();
						int length = Array.getLength(value);
						for(int i = 0; i < length; i++) {
							Object element = Array.get(value, i);
							if (element instanceof String) {
								// Quote special character in string \ => \\  " => \"
								String stringValue = toString(element).replace("\\", "\\\\").replace("\"", "\\\"");
								arrayElement.add(String.format("\"%s\"", stringValue));
							} else {
								arrayElement.add(String.format("%s", toString(element)));
							}
						}						
						line.append("[").append(String.join(", ", arrayElement)).append("]");
					} else if (fieldInfo.isEnum) {
						line.append(value.toString());
					} else {
						if (fieldInfo.type.startsWith("java")) {
							// Dont' dig into system class
							line.append(value.toString());
						} else {
							line.append(toString(value));
						}
					}
				}
					break;
				}
				result.add(line.toString());
			}
			
			return String.format("{%s}", String.join(", ", result));
		} catch (IllegalAccessException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

}

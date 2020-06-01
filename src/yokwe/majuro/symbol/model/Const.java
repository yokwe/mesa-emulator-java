package yokwe.majuro.symbol.model;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class Const {
	private static final Logger logger = LoggerFactory.getLogger(Const.class);

	public final String       name;
	public final String       typeName;
	public final String       stringValue;
	public final TypeSubrange typeSubrange;
	
	public       boolean needsFix;
	public       Long    numericValue;
	
	public Const(String name, String typeName, String value) {
		// sanity check
		TypeSubrange typeSubrange;
		{
			Type type = Type.get(typeName);
			if (type.isSubrange()) {
				typeSubrange = (TypeSubrange)type;
			} else {
				logger.error("Unexpected type");
				logger.error("  typeName {}", typeName);
				logger.error("  type     {}", type);
				throw new UnexpectedException("Unexpected type");
			}
		}

		this.name         = name;
		this.typeName     = typeName;
		this.stringValue  = value;
		this.typeSubrange = typeSubrange;
		this.needsFix     = true;
		this.numericValue = null;
		
		fix();
		
		register(this);
	}
	public Const(String name, String typeName, long value) {
		// sanity check
		this.name         = name;
		this.typeName     = typeName;
		this.stringValue  = null;
		this.typeSubrange = null;
		this.needsFix     = false;
		this.numericValue = value;
		
		register(this);
	}

	
	public boolean hasNumeriValue() {
		return numericValue != null;
	}
	
	public void fix() {
		if (this.needsFix) {
			if (Type.getNumericValue(stringValue) != null) {
				this.numericValue = Type.getNumericValue(stringValue);
				this.needsFix = false;
			} else {
				if (stringValue.contains(".")) {
					this.numericValue = getNumericStaticFieldValue(stringValue);
					this.needsFix = false;
				} else {
					if (map.containsKey(stringValue)) {
						Const nextConst = map.get(stringValue);
						if (nextConst.needsFix) nextConst.fix();
						if (!nextConst.needsFix) {
							this.numericValue = nextConst.numericValue;
							this.needsFix = false;
						}
					}
				}
			}
			
			// Sanity check
			if (!needsFix) {
				typeSubrange.checkValue(this.numericValue);
			}
		}
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %s %s %s}", name, typeName, stringValue, needsFix, numericValue);
	}
	
	public static Map<String, Const> map = new TreeMap<>();
	
	private static void register(Const constant) {
		String name = constant.name;
		
		if (map.containsKey(name)) {
			Const old = map.get(name);
			
			logger.error("Duplicate const name");
			logger.error("  new {} {}", name, constant);
			logger.error("  old {} {}", old.name,  old);
			throw new UnexpectedException("Duplicate const name");
		} else {
			map.put(name, constant);
		}
	}
	public static void fixAll() {
		for(Const e: map.values()) {
			e.fix();
		}
	}
	
	public static void stats() {
		int needsFix = 0;
		Map<String, Integer> typeNameMap = new TreeMap<>();

		for(Const e: map.values()) {
			if (e.needsFix) needsFix++;
			if (typeNameMap.containsKey(e.typeName)) {
				typeNameMap.put(e.typeName, typeNameMap.get(e.typeName) + 1);
			} else {
				typeNameMap.put(e.typeName, 1);
			}
		}
		
		logger.info("stats");
		logger.info("  {}", String.format("%-14s  %3d", "all", map.size()));
		logger.info("  {}", String.format("%-14s  %3d", "needsFix", needsFix));
		logger.info("  ==");
		for(String typeName: typeNameMap.keySet()) {
			logger.info("  {}", String.format("%-14s  %3d", typeName, typeNameMap.get(typeName)));
		}

		if (0 < needsFix) {
			logger.info("  == needs fix");
			for(Const e: map.values()) {
				if (!e.needsFix) continue;
				logger.info("{}", String.format("%-14s %-10s %s", e.name, e.typeName, e.stringValue));
			}
		}
		logger.info("  == fixed");
		for(Const e: map.values()) {
			if (e.needsFix) continue;
			if (e.name.contains("#")) continue;
			logger.info("  {}", String.format("%-24s %-10s %-10s %s", e.name, e.typeName, e.stringValue, e.numericValue));
		}
	}

	public static Const get(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		} else {
			logger.error("Unexpected name");
			logger.error("  name {}", name);
			throw new UnexpectedException("Unexpected name");
		}
	}
	
	public static long getNumericStaticFieldValue(String name) {
		if (name.contains(".")) {
			String className = name.substring(0, name.lastIndexOf("."));
			String fieldName = name.substring(name.lastIndexOf(".") + 1);
			
			try {
				Class<?> targetClass = Class.forName(className);
				java.lang.reflect.Field targetField = targetClass.getDeclaredField(fieldName);
				int modifiers = targetField.getModifiers();
				Class<?> targetType = targetField.getType();
				
				if (!Modifier.isStatic(modifiers)) {
					logger.error("field is not static");
					logger.error("  name  {}", name);
					logger.error("  class {}", targetClass);
					logger.error("  field {}", targetField);
					throw new UnexpectedException("field is not static");
				}
				if (!Modifier.isPublic(modifiers)) {
					logger.error("field is not public");
					logger.error("  name  {}", name);
					logger.error("  class {}", targetClass);
					logger.error("  field {}", targetField);
					throw new UnexpectedException("field is not public");
				}
				if (!Modifier.isFinal(modifiers)) {
					logger.error("field is not final");
					logger.error("  name  {}", name);
					logger.error("  class {}", targetClass);
					logger.error("  field {}", targetField);
					throw new UnexpectedException("field is not final");
				}
				
				if (targetType.equals(Integer.class)) {
					return targetField.getInt(null);
				} else if (targetType.equals(Integer.TYPE)) {
					return targetField.getInt(null);
				} else if (targetType.equals(Long.class)) {
					return targetField.getLong(null);
				} else if (targetType.equals(Long.TYPE)) {
					return targetField.getLong(null);
				} else {
					logger.error("Unexpected field type");
					logger.error("  name  {}", name);
					logger.error("  class {}", targetClass);
					logger.error("  field {}", targetField);
					throw new UnexpectedException("Unexpected field type");
				}
			} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				String exceptionName = e.getClass().getSimpleName();
				logger.error("{} {}", exceptionName, e);
				throw new UnexpectedException(exceptionName, e);
			}
		} else {
			logger.error("name is not qualified");
			logger.error("  name  {}", name);
			throw new UnexpectedException("name is not qualified");
		}
	}
}

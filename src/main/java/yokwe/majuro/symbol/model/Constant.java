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
package yokwe.majuro.symbol.model;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.util.UnexpectedException;

public class Constant {
	private static final Logger logger = LoggerFactory.getLogger(Constant.class);

	public final String       name;
	public final String       typeName;
	public final String       stringValue;
	public final TypeSubrange typeSubrange;
	
	private boolean needsFix;
	private long    numericValue;
	
	public Constant(String name, String typeName, String value) {
		this.name         = name;
		this.typeName     = typeName;
		this.stringValue  = value;

		this.typeSubrange = Type.getSubrange(typeName);
		this.numericValue = 0;	
		
		this.needsFix     = true;
		
		fix();
		
		register(this);
	}
	
	public boolean needsFix() {
		return needsFix;
	}
	public boolean hasValue() {
		return !needsFix;
	}	
	public long getNumericValue() {
		if (needsFix) {
			logger.error("Unexpected needsFix");
			logger.error("  needsFix {}", needsFix);
			throw new UnexpectedException("Unexpected needsFix");
		}
		return numericValue;
	}
	public void setNumericValue(long newValue) {
		numericValue = newValue;
		needsFix = false;
	}
	
	@Override
	public String toString() {
//		return String.format("{%s %s %s %s %s}", name, typeName, stringValue, needsFix, numericValue);
		if (hasValue()) {
			return String.format("{%s %s %d}", name, typeName, numericValue);
		} else {
			return String.format("{%s %s %s %s}", name, typeName, stringValue, "*UNKNOWN*");
		}
	}
	
	// special constructor for Type.XXX_VALUE
	public Constant(String name, String typeName, long value) {
		this.name         = name;
		this.typeName     = typeName;
		this.stringValue  = null;
		
		this.typeSubrange = null;
		this.numericValue = value;
		
		this.needsFix     = false;

		register(this);
	}
	
	public void fix() {
		if (needsFix) {
			Long longValue = Type.getNumericValue(stringValue);
			if (longValue != null) {
				this.numericValue = longValue;
				this.needsFix     = false;
			} else {
				if (stringValue.contains(".")) {
					// If stringValue is qualified name, take value from target static field
					this.numericValue = getNumericStaticFieldValue(stringValue);
					this.needsFix     = false;
				} else {
					// If stringValue is NOT qualified name, take value from map
					if (map.containsKey(stringValue)) {
						Constant nextConst = map.get(stringValue);
						nextConst.fix();
						if (!nextConst.needsFix) {
							this.numericValue = nextConst.numericValue;
							this.needsFix = false;
						}
					}
				}
			}
			
			// Sanity check
			if (!needsFix) {
				typeSubrange.checkValue(numericValue);
			}
		}
	}
	
	public static Map<String, Constant> map = new TreeMap<>();
	
	private static void register(Constant constant) {
		String name = constant.name;
		
		if (map.containsKey(name)) {
			Constant old = map.get(name);
			
			logger.error("Duplicate const name");
			logger.error("  new {} {}", name, constant);
			logger.error("  old {} {}", old.name,  old);
			throw new UnexpectedException("Duplicate const name");
		} else {
			map.put(name, constant);
		}
	}
	public static void fixAll() {
		for(Constant e: map.values()) {
			e.fix();
		}
	}
	
	public static void stats() {
		int needsFix = 0;
		Map<String, Integer> typeNameMap = new TreeMap<>();

		for(Constant e: map.values()) {
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
			for(Constant e: map.values()) {
				if (!e.needsFix) continue;
				logger.info("{}", String.format("%-14s %-10s %s %s", e.name, e.typeName, e.stringValue, e.numericValue));
				logger.info("{}", String.format("    %s", e.typeSubrange));
			}
		}
		logger.info("  == fixed");
		for(Constant e: map.values()) {
			if (e.needsFix) continue;
			if (e.name.contains("#")) continue;
			logger.info("  {}", String.format("%-24s %-10s %-10s %s", e.name, e.typeName, e.stringValue, e.numericValue));
		}
	}

	public static Constant get(String name) {
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

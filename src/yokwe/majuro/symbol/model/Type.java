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

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public abstract class Type {
	private static final Logger logger = LoggerFactory.getLogger(Type.class);

	public enum Kind {
		// predefined
		BOOL,
		// subrange of CARDINAL, INTEGER
		SUBRANGE,
		// constructed
		ARRAY, ENUM, RECORD,
		// reference
		REFERENCE,
	}
	
	public final String name;
	public final Kind   kind;
	public final int    originalSize;
	
	private boolean needsFix;
	private int     size;
	
	public static final int UNKNOWN_SIZE = -1;
	
	protected Type(String name, Kind kind, int size) {
		this.name         = name;
		this.kind         = kind;
		this.originalSize = size;
		
		if (0 <= size) {
			this.needsFix = false;
			this.size     = size;
		} else {
			this.needsFix = true;
			this.size     = UNKNOWN_SIZE;
		}
		
		register(this);
	}
	protected Type(String name, Kind kind) {
		this(name, kind, UNKNOWN_SIZE);
	}
	
	@Override
	public String toString() {
		if (hasValue()) {
			return String.format("{%s %s %d}", name, kind, getSize());
		} else {
			return String.format("{%s %s %s}", name, kind, "*UNKNOWN*");
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Type) {
			Type that = (Type)o;
			return this.name.equals(that.name);
		} else {
			return false;
		}
	}
	
	public boolean needsFix() {
		return needsFix;
	}
	public boolean hasValue() {
		return !needsFix;
	}
	public int getSize() {
		if (needsFix) {
			logger.error("Unexpected needsFix");
			logger.error("  needsFix {}", needsFix);
			throw new UnexpectedException("Unexpected needsFix");
		}
		return size;
	}
	protected void setSize(int newValue) {
		size     = newValue;
		needsFix = false;
	}

	
	protected abstract void fix();
	
	public String toMesaString() {
		return String.format("%s: TYPE = %s;", name, toMesaType());
	}
	public abstract String toMesaType();
	
	public static Map<String, Type> map = new TreeMap<>();
	
	protected static void register(Type type) {
		String name = type.name;
		
		if (map.containsKey(name)) {
			Type old = map.get(name);
			
			logger.error("Duplicate type name");
			logger.error("  new {} {}", name, type);
			logger.error("  old {} {}", old.name,  old);
			throw new UnexpectedException("Duplicate type name");
		} else {
			map.put(name, type);
		}
	}
	public static Type get(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		} else {
			logger.error("Unexpected name");
			logger.error("  name {}", name);
			throw new UnexpectedException("Unexpected name");
		}
	}
	public static TypeSubrange getSubrange(String name) {
		Type type = get(name);
		if (type.isSubrange()) return (TypeSubrange)type;
		
		logger.error("Unexpected type");
		logger.error("  name {}", name);
		logger.error("  type {}", type);
		throw new UnexpectedException("Unexpected type");
	}
	
	public static void fixAll() {
		for(Type type: map.values()) {
			type.fix();
		}
	}
	
	public static void stats() {
		int needsFix = 0;
		Map<Type.Kind, Integer> kindMap = new TreeMap<>();
		for(Type.Kind e: Type.Kind.values()) {
			kindMap.put(e, 0);
		}
		
		for(Type type: map.values()) {
			if (type.needsFix) needsFix++;
			kindMap.put(type.kind, kindMap.get(type.kind) + 1);
		}
		
		logger.info("stats");
		logger.info("  {}", String.format("%-9s  %3d", "all", map.size()));
		logger.info("  {}", String.format("%-9s  %3d", "needsFix", needsFix));
		logger.info("  ==");
		for(Type.Kind e: Type.Kind.values()) {
			logger.info("  {}", String.format("%-9s  %3d", e, kindMap.get(e)));
		}
		
		if (0 < needsFix) {
			logger.info("  == needs fix");
			for(Type type: map.values()) {
				if (type.needsFix) {
					switch(type.kind) {
					case SUBRANGE:
						logger.info("  {}", String.format("%-24s %-10s %s", type.name, type.kind, ((TypeSubrange)type).baseType.baseName));
						break;
					case REFERENCE:
						logger.info("  {}", String.format("%-24s %-10s %s", type.name, type.kind, ((TypeReference)type).baseName));
						break;
					default:
						logger.info("  {}", String.format("%-24s %-10s", type.name, type.kind));
						break;
					}
				}
			}
		}
		logger.info("  == fixed");
		for(Type type: map.values()) {
			if (type.isReference()) continue;
			if (type.needsFix) continue;
			logger.info("  {}", String.format("%-34s %-10s", type.name, type.kind));
		}
	}
	
	public boolean isReference() {
		return kind == Kind.REFERENCE;
	}
	public boolean isSubrange() {
		return kind == Kind.SUBRANGE;
	}
	
	public static final long CARDINAL_MIN = 0;
	public static final long CARDINAL_MAX = (1L << 16) - 1;
	
	public static final long LONG_CARDINAL_MIN = 0;
	public static final long LONG_CARDINAL_MAX = (1L << 32) - 1;

	public static final long INTEGER_MIN = Short.MIN_VALUE;
	public static final long INTEGER_MAX = Short.MAX_VALUE;

	public static final long LONG_INTEGER_MIN = Integer.MIN_VALUE;
	public static final long LONG_INTEGER_MAX = Integer.MAX_VALUE;

	public static final String BOOL             = "BOOL";
	public static final String CARDINAL         = "CARDINAL";
	public static final String LONG_CARDINAL    = "LONG_CARDINAL";
	public static final String INTEGER          = "INTEGER";
	public static final String LONG_INTEGER     = "LONG_INTEGER";
	public static final String UNSPECIFIED      = "UNSPECIFIED";
	public static final String LONG_UNSPECIFIED = "LONG_UNSPECIFIED";
	public static final String POINTER          = "POINTER";
	public static final String LONG_POINTER     = "LONG_POINTER";
	
	// Define predefined type
	public static final TypeBool     BOOL_VALUE;
	public static final TypeSubrange CARDINAL_VALUE;
	public static final TypeSubrange LONG_CARDINAL_VALUE;
	public static final TypeSubrange INTEGER_VALUE;
	public static final TypeSubrange LONG_INTEGER_VALUE;
	public static final TypeSubrange UNSPECIFIED_VALUE;
	public static final TypeSubrange LONG_UNSPECIFIED_VALUE;
	public static final TypeSubrange POINTER_VALUE;
	public static final TypeSubrange LONG_POINTER_VALUE;

	static {
		BOOL_VALUE             = new TypeBool();
		CARDINAL_VALUE         = new TypeSubrangeRange(CARDINAL,         1, CARDINAL,         CARDINAL_MIN,      CARDINAL_MAX,      true);
		LONG_CARDINAL_VALUE    = new TypeSubrangeRange(LONG_CARDINAL,    2, LONG_CARDINAL,    LONG_CARDINAL_MIN, LONG_CARDINAL_MAX, true);
		INTEGER_VALUE          = new TypeSubrangeRange(INTEGER,          1, INTEGER,          INTEGER_MIN,       INTEGER_MAX,       true);
		LONG_INTEGER_VALUE     = new TypeSubrangeRange(LONG_INTEGER,     2, LONG_INTEGER,     LONG_INTEGER_MIN,  LONG_INTEGER_MAX,  true);
		UNSPECIFIED_VALUE      = new TypeSubrangeRange(UNSPECIFIED,      1, UNSPECIFIED,      CARDINAL_MIN,      CARDINAL_MAX,      true);
		LONG_UNSPECIFIED_VALUE = new TypeSubrangeRange(LONG_UNSPECIFIED, 2, LONG_UNSPECIFIED, LONG_CARDINAL_MIN, LONG_CARDINAL_MAX, true);
		POINTER_VALUE          = new TypeSubrangeRange(POINTER,          1, POINTER,          CARDINAL_MIN,      CARDINAL_MAX,      true);
		LONG_POINTER_VALUE     = new TypeSubrangeRange(LONG_POINTER,     2, LONG_POINTER,     LONG_CARDINAL_MIN, LONG_CARDINAL_MAX, true);
	}
	
	private static Map<String, Type> predefinedTypeMap = new TreeMap<>();
	static {
		predefinedTypeMap.put(BOOL_VALUE.name,             BOOL_VALUE);
		predefinedTypeMap.put(CARDINAL_VALUE.name,         CARDINAL_VALUE);
		predefinedTypeMap.put(LONG_CARDINAL_VALUE.name,    LONG_CARDINAL_VALUE);
		predefinedTypeMap.put(INTEGER_VALUE.name,          INTEGER_VALUE);
		predefinedTypeMap.put(LONG_INTEGER_VALUE.name,     LONG_INTEGER_VALUE);
		predefinedTypeMap.put(UNSPECIFIED_VALUE.name,      UNSPECIFIED_VALUE);
		predefinedTypeMap.put(LONG_UNSPECIFIED_VALUE.name, LONG_UNSPECIFIED_VALUE);
		predefinedTypeMap.put(POINTER_VALUE.name,          POINTER_VALUE);
		predefinedTypeMap.put(LONG_POINTER_VALUE.name,     LONG_POINTER_VALUE);
	}
	public static boolean isPredefined(String name) {
		return predefinedTypeMap.containsKey(name);
	}
	public static boolean isPredefined(Type type) {
		return isPredefined(type.name);
	}
	public boolean isPredefined() {
		return isPredefined(name);
	}

	
	public static Long getNumericValue(String value) {
		if (value.matches("^-?[0-9]+$")) {
			// decimal
			return Long.parseUnsignedLong(value.substring(0, value.length()), 10);
		}
		if (value.matches("^[0-9]+[Bb]$")) {
			// octal
			return Long.parseUnsignedLong(value.substring(0, value.length() - 1), 8);
		}
		if (value.matches("^[0-9][0-9A-Fa-f]+[Xx]$")) {
			// hexadecimal
			return Long.parseUnsignedLong(value.substring(0, value.length() - 1), 16);
		}

		return null;
	}
}

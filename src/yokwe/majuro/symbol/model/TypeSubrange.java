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

import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public abstract class TypeSubrange extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeSubrange.class);

	public final TypeReference baseType;
	public final Constant      valueMinConst;
	public final Constant      valueMaxConst;
	public final boolean       valueMaxInclusive;
	
	private long valueMin;
	private long valueMax;
	
	public TypeSubrange(String name, int size, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		super(name, Kind.SUBRANGE);
		
		this.baseType          = new TypeReference(name + "#base", baseName);
		this.valueMinConst     = new Constant(name + "#valueMin", Type.LONG_CARDINAL, valueMin);
		this.valueMaxConst     = new Constant(name + "#valueMax", Type.LONG_CARDINAL, valueMax);
		this.valueMaxInclusive = valueMaxInclusive;
		
		fix();
	}
	// special constructor for Type.XXX_VALUE
	public TypeSubrange(String name, int size, String baseName, long valueMin, long valueMax, boolean valueMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.baseType          = new TypeReference(name + "#base", baseName);
		this.valueMinConst     = null;
		this.valueMaxConst     = null;
		this.valueMaxInclusive = valueMaxInclusive;
		
		this.valueMin = valueMin;
		this.valueMax = valueMax + (valueMaxInclusive ? 0 : -1);
	}
	public TypeSubrange(String name, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		this(name, Type.UNKNOWN_SIZE, baseName, valueMin, valueMax, valueMaxInclusive);
	}
	
	public long getValueMin() {
		if (needsFix()) {
			logger.error("Unexpected needsFix");
			logger.error("  needsFix {}", needsFix());
			throw new UnexpectedException("Unexpected needsFix");
		}
		return valueMin;
	}
	public long getValueMax() {
		if (needsFix()) {
			logger.error("Unexpected needsFix");
			logger.error("  needsFix {}", needsFix());
			throw new UnexpectedException("Unexpected needsFix");
		}
		return valueMax;
	}
	
	public void checkValue(long valueMax, long valueMin) {
		if (valueMin < getValueMin()) {
			logger.error("Unexpected rangeMin");
			logger.error("  rangeMin {}", valueMin);
			logger.error("  valueMin {}", getValueMin());
			throw new UnexpectedException("Unexpected rangeMin");
		}
		if (getValueMax() < valueMax) {
			logger.error("Unexpected rangeMax");
			logger.error("  rangeMax {}", valueMax);
			logger.error("  valueMax {}", getValueMax());
			throw new UnexpectedException("Unexpected rangeMax");
		}
	}

	public void checkValue(long value) {
		if (value < getValueMin()) {
			logger.error("Unexpected value");
			logger.error("  value    {}", value);
			logger.error("  valueMin {}", getValueMin());
			logger.error("  this     {}", this);
			throw new UnexpectedException("Unexpected rangeMin");
		}
		if (getValueMax() < value) {
			logger.error("Unexpected value");
			logger.error("  value    {}", value);
			logger.error("  valueMax {}", getValueMax());
			logger.error("  this     {}", this);
			throw new UnexpectedException("Unexpected rangeMax");
		}
	}

	@Override
	public String toString() {
//		return String.format("{%s %d %s %s %s %s %s}", name, size, kind, baseType, valueMin, valueMax, valueMaxInclusive);
		if (hasValue()) {
			if (valueMaxInclusive) {
				return String.format("{%s %s %s %s [%d..%d]}", name, kind, getSize(), baseType.baseName, valueMin, valueMax);
			} else {
				return String.format("{%s %s %s %s [%d..%d)}", name, kind, getSize(), baseType.baseName, valueMin, valueMax + 1);
			}
		} else {
			if (valueMaxInclusive) {
				return String.format("{%s %s %s %s [%s..%s]}", name, kind, "*UNKNOWN*", baseType.baseName, valueMinConst.stringValue, valueMaxConst.stringValue);
			} else {
				return String.format("{%s %s %s %s [%s..%s)}", name, kind, "*UNKNOWN*", baseType.baseName, valueMinConst.stringValue, valueMaxConst.stringValue);
			}
		}
	}
	@Override
	public String toMesaType() {
		if (isPredefined()) {
			return String.format("%s [%d..%d)", baseType.baseName, valueMin, valueMax + 1);
		} else {
			return String.format("%s [%s..%s%c",
					baseType.baseName,
					valueMinConst.stringValue,
					valueMaxConst.stringValue,
					valueMaxInclusive ? ']' : ')');
		}
	}

	private static Set<String> predefinedNameSet = new TreeSet<>();
	static {
		predefinedNameSet.add(Type.CARDINAL);
		predefinedNameSet.add(Type.LONG_CARDINAL);
		predefinedNameSet.add(Type.INTEGER);
		predefinedNameSet.add(Type.LONG_INTEGER);
		predefinedNameSet.add(Type.UNSPECIFIED);
		predefinedNameSet.add(Type.LONG_UNSPECIFIED);
		predefinedNameSet.add(Type.POINTER);
		predefinedNameSet.add(Type.LONG_POINTER);
	}

	@Override
	protected void fix() {
		if (needsFix()) {
			baseType.fix();
			valueMinConst.fix();
			valueMaxConst.fix();
			
			if (baseType.hasValue() && valueMinConst.hasValue() && valueMaxConst.hasValue()) {
				if (!baseType.baseType.isSubrange()) {
					logger.error("Unexpected baseType");
					logger.error("  baseType {}", baseType);
					throw new UnexpectedException("Unexpected baseType");
				}
				
				long valueMin = valueMinConst.getNumericValue();
				long valueMax = valueMaxConst.getNumericValue() + (valueMaxInclusive ? 0 : -1);
				long length   = valueMax - valueMin + 1;
				// sanity check
				if (length < 0) {
					logger.error("Unexpected length");
					logger.error("  valueMin {}", valueMin);
					logger.error("  valueMax {}", valueMax);
					logger.error("  length   {}", length);
					throw new UnexpectedException("Unexpected length");
				}
				
				if (predefinedNameSet.contains(name)) {
					// don't check for predefined type
				} else {
					if (Integer.MAX_VALUE <= length) {
						logger.error("Unexpected length");
						logger.error("  valueMin {}", valueMin);
						logger.error("  valueMax {}", valueMax);
						logger.error("  length   {}", length);
						throw new UnexpectedException("Unexpected length");
					}		
				}
				
				this.valueMin = valueMin;
				this.valueMax = valueMax;
				
				setSize(baseType.getSize());
			}
		}
	}
}

class TypeSubrangeRange extends TypeSubrange {
	public TypeSubrangeRange(String name, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		super(name, baseName, valueMin, valueMax, valueMaxInclusive);
	}
	
	// special constructor for Type.XXX_VALUE
	public TypeSubrangeRange(String name, int size, String baseName, long valueMin, long valueMax, boolean valueMaxInclusive) {
		super(name, size, baseName, valueMin, valueMax, valueMaxInclusive);
	}
}


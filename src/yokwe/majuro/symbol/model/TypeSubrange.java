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
	public       Const         valueMinConst;
	public       Const         valueMaxConst;
	public final boolean       valueMaxInclusive;
	
	public long valueMin;
	public long valueMax;
	public int  length;
	
	public TypeSubrange(String name, int size, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.baseType          = new TypeReference(name + "#base", baseName);
		this.valueMinConst     = new Const(name + "#valueMin", Type.LONG_CARDINAL, valueMin);
		this.valueMaxConst     = new Const(name + "#valueMax", Type.LONG_CARDINAL, valueMax);
		this.valueMaxInclusive = valueMaxInclusive;
		
		this.needsFix = true;
		
		fix();
	}
	// special constructor for Type.XXX_VALUE
	public TypeSubrange(String name, int size, String baseName, long valueMin, long valueMax, boolean valueMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.baseType          = new TypeReference(name + "#base", baseName);
		this.valueMinConst     = new Const(name + "#valueMin", Type.LONG_CARDINAL, valueMin);
		this.valueMaxConst     = new Const(name + "#valueMax", Type.LONG_CARDINAL, valueMax);
		this.valueMaxInclusive = valueMaxInclusive;
		
		this.needsFix = true;
		
		fix();
	}
	public TypeSubrange(String name, String baseName, String valueMin, String valueMax, boolean valueMaxInclusive) {
		this(name, Type.UNKNOWN_SIZE, baseName, valueMin, valueMax, valueMaxInclusive);
	}
	
	public void checkValue(long valueMax, long valueMin) {
		if (!needsFix) {
			if (valueMin < this.valueMin) {
				logger.error("Unexpected rangeMin");
				logger.error("  rangeMin {}", valueMin);
				logger.error("  valueMin {}", this.valueMin);
				throw new UnexpectedException("Unexpected rangeMin");
			}
			if (this.valueMax < valueMax) {
				logger.error("Unexpected rangeMax");
				logger.error("  rangeMax {}", valueMax);
				logger.error("  valueMax {}", this.valueMax);
				throw new UnexpectedException("Unexpected rangeMax");
			}
		}
	}

	public void checkValue(long value) {
		if (!needsFix) {
			if (value < this.valueMin) {
				logger.error("Unexpected value");
				logger.error("  value    {}", value);
				logger.error("  valueMin {}", this.valueMin);
				logger.error("  this     {}", this);
				throw new UnexpectedException("Unexpected rangeMin");
			}
			if (this.valueMax < value) {
				logger.error("Unexpected value");
				logger.error("  value    {}", value);
				logger.error("  valueMax {}", this.valueMax);
				logger.error("  this     {}", this);
				throw new UnexpectedException("Unexpected rangeMax");
			}
		}
	}

	@Override
	public String toString() {
		return String.format("{%s %d %s %s %s %s %s}", name, size, kind, baseType, valueMin, valueMax, valueMaxInclusive);
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
		if (needsFix) {
			baseType.fix();
			valueMinConst.fix();
			valueMaxConst.fix();
			
			if (!baseType.needsFix && !valueMinConst.needsFix && !valueMaxConst.needsFix) {
				if (!baseType.baseType.isSubrange()) {
					logger.error("Unexpected baseType");
					logger.error("  baseType {}", baseType);
					throw new UnexpectedException("Unexpected baseType");
				}
				
				long valueMin = valueMinConst.numericValue;
				long valueMax = valueMaxConst.numericValue;
				long length = valueMax - valueMin + 1;
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
				
				this.size     = baseType.size;
				this.valueMin = valueMin;
				this.valueMax = valueMax + (valueMaxInclusive ? 0 : -1);
				this.length   = (int)length;
				this.needsFix = false;
			}
		}
	}
}

class TypeSubrangeFull extends TypeSubrange {
	private static final Logger logger = LoggerFactory.getLogger(TypeSubrangeFull.class);
	public TypeSubrangeFull(String name, String baseName) {
		super(name, baseName, "0", "0", false);
	}
	
	@Override
	protected void fix() {
		{
			baseType.fix();
			
			if (!baseType.needsFix) {
				if (!baseType.baseType.isSubrange()) {
					logger.error("Unexpected baseType");
					logger.error("  baseType {}", baseType);
					throw new UnexpectedException("Unexpected baseType");
				}
				TypeSubrange typeSubrange = (TypeSubrange)baseType.baseType;
				this.valueMin = typeSubrange.valueMin;
				this.valueMax = typeSubrange.valueMax;
			}
		}
		super.fix();
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


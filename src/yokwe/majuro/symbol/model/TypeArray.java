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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public abstract class TypeArray extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeArray.class);
	
	public enum ArrayKind {
		OPEN, SUBRANGE, FULL,
	}
	
	public final ArrayKind arrayKind;

	public final TypeReference elementType;
	public final TypeReference indexType;
	
	public final Constant      rangeMinConst;
	public final Constant      rangeMaxConst;
	public final boolean       rangeMaxInclusive;
	
	protected long rangeMin;
	protected long rangeMax;
	protected int  length;

	protected TypeArray(String name, int size, ArrayKind arrayKind, String elementName, String indexName, String rangeMin, String rangeMax, boolean rangeMaxInclusive) {
		super(name, Kind.ARRAY);
		
		this.arrayKind         = arrayKind;
		this.elementType       = new TypeReference(name + "#element", elementName);
		this.indexType         = new TypeReference(name + "#index", indexName);
		this.rangeMinConst     = new Constant(name + "#valueMin", Type.LONG_CARDINAL, rangeMin);
		this.rangeMaxConst     = new Constant(name + "#valueMax", Type.LONG_CARDINAL, rangeMax);
		this.rangeMaxInclusive = rangeMaxInclusive;
		
		this.rangeMin = 0;
		this.rangeMax = 0;
		this.length   = 0;
		
		fix();
	}
	
	public long getRangeMin() {
		if (needsFix()) {
			logger.error("Unexpected needsFix");
			logger.error("  needsFix {}", needsFix());
			throw new UnexpectedException("Unexpected needsFix");
		}
		return rangeMin;
	}
	public long getRangeMax() {
		if (needsFix()) {
			logger.error("Unexpected needsFix");
			logger.error("  needsFix {}", needsFix());
			throw new UnexpectedException("Unexpected needsFix");
		}
		return rangeMax;
	}
	
	@Override
	public String toString() {
		if (hasValue()) {
//			return String.format("{%s %s %s %s %s %s %s %s %s}", name, kind, getSize(), arrayKind, arrayKind, elementType.baseName, indexType.name, rangeMinConst, rangeMaxConst);
			if (rangeMaxInclusive) {
				return String.format("{%s %s %s %s %s %s (%d)[%d..%d]}", name, kind, getSize(), arrayKind, elementType.baseName, indexType.baseName, length, rangeMin, rangeMax);
			} else {
				return String.format("{%s %s %s %s %s %s (%d)[%d..%d)}", name, kind, getSize(), arrayKind, elementType.baseName, indexType.baseName, length, rangeMin, rangeMax + 1, length);
			}
		} else {
			return String.format("{%s %s %s %s %s %s}", name, kind, "*UNKNOWN*", arrayKind, elementType.baseName, indexType.name);
		}
	}

	@Override
	protected void fix() {
		if (needsFix()) {
			elementType.fix();
			indexType.fix();
			rangeMinConst.fix();
			rangeMaxConst.fix();
			
			if (indexType.hasValue() && elementType.hasValue() && rangeMinConst.hasValue() && rangeMaxConst.hasValue()) {				
				long rangeMin = rangeMinConst.getNumericValue();
				long rangeMax = rangeMaxConst.getNumericValue() + (rangeMaxInclusive ? 0 : -1);
				long length   = rangeMax - rangeMin + 1;
				long size     = elementType.getSize() * length;
				
				// sanity check
				if (Type.CARDINAL_MAX < length) {
					logger.error("Unexpected length");
					logger.error("  rangeMin {}", rangeMin);
					logger.error("  rangeMax {}", rangeMax);
					logger.error("  length   {}", length);
					logger.error("  size     {}", size);
					throw new UnexpectedException("Unexpected size");
				}
				if (Type.CARDINAL_MAX < size) {
					logger.error("Unexpected size");
					logger.error("  rangeMin {}", rangeMin);
					logger.error("  rangeMax {}", rangeMax);
					logger.error("  length   {}", length);
					logger.error("  size     {}", size);
					throw new UnexpectedException("Unexpected size");
				}
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					baseType.checkValue(rangeMin, rangeMax);
				}
					break;
				case SUBRANGE:
				{
					TypeSubrange baseType = (TypeSubrange)indexType.baseType;
					
					if (arrayKind == ArrayKind.OPEN) {
						// don't check for open array
					} else {
						baseType.checkValue(rangeMin, rangeMax);
					}
				}
					break;
				default:
					logger.error("Unexpected indexType");
					logger.error("  indexType {}", indexType);
					throw new UnexpectedException("Unexpected indexType");
				}

				this.rangeMin = rangeMin;
				this.rangeMax = rangeMax;
				this.length   = (int)length;
				
				setSize((int)size);
			}
		}
	}
}

class TypeArrayOpen extends TypeArray {
	TypeArrayOpen(String name, String elementName, String indexName, String rangeMinMax) {
		super(name, 0, ArrayKind.OPEN, elementName, indexName, rangeMinMax, rangeMinMax, false);
	}
}
class TypeArrayFull extends TypeArray {
	private static final Logger logger = LoggerFactory.getLogger(TypeArrayFull.class);

	TypeArrayFull(String name, String elementName, String indexName) {
		super(name, Type.UNKNOWN_SIZE, ArrayKind.FULL, elementName, indexName, "0", "0", true);
	}
	@Override
	public void fix() {
		{
			indexType.fix();
			if (indexType.hasValue()) {
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					this.rangeMin = baseType.getValueMin();
					this.rangeMax = baseType.getValueMax();
				}
					break;
				case SUBRANGE:
				{
					TypeSubrange baseType = (TypeSubrange)indexType.baseType;
					this.rangeMin = baseType.getValueMin();
					this.rangeMax = baseType.getValueMax();
				}
					break;
				default:
					logger.error("Unexpected indexType");
					logger.error("  indexType {}", indexType);
					throw new UnexpectedException("Unexpected indexType");
				}
			}
		}
		super.fix();
	}
}
class TypeArraySubrange extends TypeArray {
	TypeArraySubrange(String name, String elementName, String indexName, String rangeMin, String rangeMax, boolean rangeMaxInclusive) {
		super(name, Type.UNKNOWN_SIZE, ArrayKind.SUBRANGE, elementName, indexName, rangeMin, rangeMax, rangeMaxInclusive);
	}
}

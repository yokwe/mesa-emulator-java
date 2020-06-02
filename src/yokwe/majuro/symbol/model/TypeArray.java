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
	
	public enum RecordKind {
		OPEN, SUBRANGE, FULL,
	}
	
	public final RecordKind recordKind;

	public final TypeReference elementType;
	public final TypeReference indexType;
	
	public final Const         rangeMinConst;
	public final Const         rangeMaxConst;
	public final boolean       rangeMaxInclusive;
	
	public       long rangeMin;
	public       long rangeMax;

	protected TypeArray(String name, int size, RecordKind recordKind, String elementName, String indexName, String rangeMin, String rangeMax, boolean rangeMaxInclusive) {
		super(name, Kind.SUBRANGE, size);
		
		this.recordKind        = recordKind;
		this.elementType       = new TypeReference(name + "#element", elementName);
		this.indexType         = new TypeReference(name + "#index", indexName);
		this.rangeMinConst     = new Const(name + "#valueMin", Type.LONG_CARDINAL, rangeMin);
		this.rangeMaxConst     = new Const(name + "#valueMax", Type.LONG_CARDINAL, rangeMax);
		this.rangeMaxInclusive = rangeMaxInclusive;
		
		this.rangeMin = 0;
		this.rangeMax = 0;
		
		this.needsFix = true;

		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %d %s %s %s %s}", name, kind, size, elementType, indexType, rangeMinConst, rangeMaxConst);
	}

	@Override
	protected void fix() {
		if (needsFix) {
			elementType.fix();
			indexType.fix();
			rangeMinConst.fix();
			rangeMaxConst.fix();
			
			if (!indexType.needsFix && !elementType.needsFix && !rangeMinConst.needsFix && !rangeMaxConst.needsFix) {				
				long rangeMin = rangeMinConst.numericValue;
				long rangeMax = rangeMaxConst.numericValue + (rangeMaxInclusive ? 0 : -1);
				int length;
				
				// sanity check
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					baseType.checkValue(rangeMin, rangeMax);
					length = baseType.length;
				}
					break;
				case SUBRANGE:
				{
					TypeSubrange baseType = (TypeSubrange)indexType.baseType;
					baseType.checkValue(rangeMin, rangeMax);
					length = baseType.length;
				}
					break;
				default:
					logger.error("Unexpected indexType");
					logger.error("  indexType {}", indexType);
					throw new UnexpectedException("Unexpected indexType");
				}

				this.size     = elementType.size * length;
				this.rangeMin = rangeMin;
				this.rangeMax = rangeMax;
				
				this.needsFix = false;
			}
		}
	}
}

class TypeArrayOpen extends TypeArray {
	TypeArrayOpen(String name, String elementName, String indexName, String rangeMinMax) {
		super(name, 0, RecordKind.OPEN, elementName, indexName, rangeMinMax, rangeMinMax, false);
	}
	@Override
	public void fix() {
		needsFix = false;
	}
}
class TypeArrayFull extends TypeArray {
	private static final Logger logger = LoggerFactory.getLogger(TypeArrayFull.class);

	TypeArrayFull(String name, String elementName, String indexName) {
		super(name, Type.UNKNOWN_SIZE, RecordKind.FULL, elementName, indexName, "0", "0", true);
	}
	@Override
	public void fix() {
		{
			indexType.fix();
			if (!indexType.needsFix) {
				switch(indexType.baseType.kind) {
				case ENUM:
				{
					TypeEnum baseType = (TypeEnum)indexType.baseType;
					this.rangeMin = baseType.valueMin;
					this.rangeMax = baseType.valueMax;
				}
					break;
				case SUBRANGE:
				{
					TypeSubrange baseType = (TypeSubrange)indexType.baseType;
					this.rangeMin = baseType.valueMin;
					this.rangeMax = baseType.valueMax;
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
		super(name, Type.UNKNOWN_SIZE, RecordKind.SUBRANGE, elementName, indexName, rangeMin, rangeMax, rangeMaxInclusive);
	}
}


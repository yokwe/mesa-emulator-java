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

import yokwe.util.UnexpectedException;

public class Field implements Comparable<Field> {
	protected static final Logger logger = LoggerFactory.getLogger(Field.class);

	public final FieldName     fieldName;
	public final TypeReference type;
	public final Select        select;
	
	private boolean needsFix;
	private int     size;
	private boolean bitField;
	
	private Field(String prefix, String name, int offset, int startPos, int stopPos, String typeName, Select select) {
		this.fieldName = new FieldName(name, offset, startPos, stopPos);
		this.type      = (typeName != null) ? new TypeReference(prefix + "#" + name + "#type", typeName) : null;
		this.select    = select;
		
		this.needsFix  = true;
		this.size      = Type.UNKNOWN_SIZE;
		this.bitField  = false;
		
		fix();
	}
	private Field(String prefix, String name, int offset, String typeName, Select select) {
		this.fieldName = new FieldName(name, offset);
		this.type      = (typeName != null) ? new TypeReference(prefix + "#" + name + "#type", typeName) : null;
		this.select    = select;
		
		this.needsFix  = true;
		this.size      = Type.UNKNOWN_SIZE;
		this.bitField  = false;
		
		fix();
	}

	// type
	public Field(String prefix, String name, int offset, int startPos, int stopPos, String typeName) {
		this(prefix, name, offset, startPos, stopPos, typeName, null);
	}
	public Field(String prefix, String name, int offset, String typeName) {
		this(prefix, name, offset, typeName, null);
	}
	// select
	public Field(String prefix, String name, int offset, int startPos, int stopPos, Select select) {
		this(prefix, name, offset, startPos, stopPos, null, select);
	}
	public Field(String prefix, String name, int offset, Select select) {
		this(prefix, name, offset, -1, -1, null, select);
	}
	
	protected boolean needsFix() {
		return needsFix;
	}
	protected boolean hasValue() {
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
	public boolean isBitfield() {
		if (needsFix) {
			logger.error("Unexpected needsFix");
			logger.error("  needsFix {}", needsFix);
			throw new UnexpectedException("Unexpected needsFix");
		}
		return bitField;
	}
		
	@Override
	public int compareTo(Field that) {
		return this.fieldName.compareTo(that.fieldName);
	}
	
	@Override
	public String toString() {
		if (hasValue()) {
			return String.format("{%s %s %s %s %s}", fieldName, "*UNKNOWN*", type, select);
		} else {
			return String.format("{%s %s %s %s %s}", fieldName, getSize(), type, select);
		}
	}

	public String toMesaType() {
		String fieldType = null;
		if (type != null) {
			if (type.baseName.contains("#" )) {
				// Expand type definition
				fieldType = type.baseType.toMesaType();
			} else {
				fieldType = type.baseType.name;
			}
		}
		if (select != null) {
			fieldType = select.toMesaType();
		}
		
		return String.format("%s: %s", fieldName, fieldType);
	}
	
	public void fix() {
		if (needsFix()) {
			if (type != null) {
				type.fix();
				if (type.hasValue()) {
					int size = type.getSize();
					setSize(size);
					
					Type baseType = type.isReference() ? ((TypeReference)type).baseType : type;
					switch(baseType.kind) {
					case BOOL:
					case SUBRANGE:
					case ENUM:
						if (fieldName.bitField) {
							bitField = fieldName.startPos != 0 || fieldName.stopPos != (size * 16) - 1;
						} else {
							bitField = false;
						}
						break;
					default:
						bitField = false;
						break;
					}
				}
			}
			if (select != null) {
				select.fix();
				if (select.hasValue()) {
					setSize(select.getSize());
				}
			}
		}
	}
}

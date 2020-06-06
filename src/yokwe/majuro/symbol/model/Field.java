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

abstract class Field {
	protected static final Logger logger = LoggerFactory.getLogger(Field.class);

	public enum TargetKind {
		TYPE, SELECT
	}
	public enum FieldKind {
		FIELD, BIT_FIELD
	}
	
	public final String    name;
	public final int       offset;
	public final int       startPos;
	public final int       stopPos;
	
	public final TargetKind targetKind;
	public final FieldKind  fieldKind;
	
	private boolean needsFix;
	private int     size;
	
	protected Field(String name, int offset, int startPos, int stopPos, TargetKind targetKind) {
		this.name       = name;
		this.offset     = offset;
		this.startPos   = startPos;
		this.stopPos    = stopPos;
		this.targetKind = targetKind;
		this.fieldKind  = FieldKind.BIT_FIELD;
		
		this.needsFix  = true;
		this.size      = Type.UNKNOWN_SIZE;
	}
	protected Field(String name, int offset, TargetKind targetKind) {
		this.name       = name;
		this.offset     = offset;
		this.startPos   = -1;
		this.stopPos    = -1;
		this.targetKind = targetKind;
		this.fieldKind  = FieldKind.FIELD;
		
		this.needsFix  = true;
		this.size      = Type.UNKNOWN_SIZE;
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
	
	@Override
	public String toString() {
		logger.error("Unexpected");
		throw new UnexpectedException("Unexpected");
	}
	
	
	public abstract String toMesaType();
	public abstract void fix();
}

class FieldType extends Field {
	public final String        fieldName;
	public final TypeReference type;
	
	public FieldType(String prefix, String name, int offset, String typeName) {
		super(name, offset, TargetKind.TYPE);
		
		this.fieldName = name;
		this.type = new TypeReference(prefix + "#" + name + "#fieldType", typeName);
		
		fix();
	}
	public FieldType(String prefix, String name, int offset, int startPos, int stopPos, String typeName) {
		super(name, offset, startPos, stopPos, TargetKind.TYPE);
		
		this.fieldName = name;
		this.type      = new TypeReference(prefix + "#" + name + "#fieldType", typeName);
		
		fix();
	}

	@Override
	public String toString() {
		if (hasValue()) {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", fieldName, offset, targetKind, fieldKind, getSize(), type.baseName);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", fieldName, offset, startPos, stopPos, targetKind, fieldKind, getSize(), type);
			}
		} else {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", fieldName, offset, targetKind, fieldKind, "*UNKNOWN*", type.baseName);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", fieldName, offset, startPos, stopPos, targetKind, fieldKind, "*UNKNOWN*", type);
			}
		}
		logger.error("Unexpected");
		throw new UnexpectedException("Unexpected");
	}

	@Override
	public String toMesaType() {
		String fieldType;
		if (type.baseName.contains("#")) {
			switch(type.baseType.kind) {
			case ARRAY:
				fieldType = ((TypeArray)type.baseType).toMesaTypeType();
				break;
			default:
				throw new UnexpectedException();
			}
		} else {
			fieldType = type.baseType.name;
		}
		
		if (type.baseType.kind == Type.Kind.ARRAY && type.baseName.contains("#")) {
			TypeArray typeArray = (TypeArray)type.baseType;
			fieldType = typeArray.toMesaTypeType();
		} else {
			fieldType = type.baseType.name;
		}
		
		switch (fieldKind) {
		case FIELD:
			return String.format("%s(%d): %s", fieldName, offset, fieldType);
		case BIT_FIELD:
			return String.format("%s(%d:%d..%d): %s", fieldName, offset, startPos, stopPos, fieldType);
		default:
			throw new UnexpectedException();
		}
	}
	
	@Override
	public void fix() {
		if (needsFix()) {
			type.fix();
			if (type.hasValue()) {
				setSize(type.getSize());
			}
		}
	}
}


class FieldSelect extends Field {
	public final String fieldName;
	public final Select select;
	
	public FieldSelect(String prefix, String name, int offset, Select select) {
		super(name, offset, TargetKind.SELECT);
		
		this.fieldName = name;
		this.select    = select;
		
		fix();
	}
	public FieldSelect(String prefix, String name, int offset, int startPos, int stopPos, Select select) {
		super(name, offset, startPos, stopPos, TargetKind.SELECT);
		
		this.fieldName = name;
		this.select    = select;
		
		fix();
	}

	@Override
	public String toString() {
		if (hasValue()) {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", name, offset, targetKind, fieldKind, getSize(), select);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", name, offset, startPos, stopPos, targetKind, fieldKind, getSize(), select);
			}
		} else {
			switch (fieldKind) {
			case FIELD:
				return String.format("{%s(%d) %s %s %s %s}", fieldName, offset, targetKind, fieldKind, "*UNKNOWN*", select);
			case BIT_FIELD:
				return String.format("{%s(%d:%d..%d) %s %s %s %s}", fieldName, offset, startPos, stopPos, targetKind, fieldKind, "*UNKNOWN*", select);
			}
		}
		logger.error("Unexpected");
		throw new UnexpectedException("Unexpected");
	}

	@Override
	public String toMesaType() {
		switch (fieldKind) {
		case FIELD:
			return String.format("%s(%d) %s", fieldName, offset, select.toMesaType());
		case BIT_FIELD:
			return String.format("%s(%d:%d..%d) %s", fieldName, offset, startPos, stopPos, select.toMesaType());
		default:
			throw new UnexpectedException();
		}
	}

	@Override
	public void fix() {
		if (select.needsFix()) {
			select.fix();
			if (select.hasValue()) {
				setSize(select.getSize());
			}
		}
	}
}

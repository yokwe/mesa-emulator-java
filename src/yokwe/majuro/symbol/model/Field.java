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
	private static final Logger logger = LoggerFactory.getLogger(Type.class);

	public enum TargetKind {
		TYPE, SELECT
	}
	public enum FieldKind {
		FEILD, BIT_FIELD
	}
	
	public final String    name;
	public final int       offset;
	
	public final TargetKind targetKind;
	public final FieldKind  fieldKind;
	
	public boolean needsFix;
	public int     size;
	
	protected Field(String name, int offset, TargetKind targetKind, FieldKind fieldKind) {
		this.name       = name;
		this.offset     = offset;
		this.targetKind = targetKind;
		this.fieldKind  = fieldKind;
		
		this.needsFix  = true;
		this.size      = Type.UNKNOWN_SIZE;
	}
	
	@Override
	public String toString() {
		logger.error("Unexpected");
		throw new UnexpectedException("Unexpected");
	}
	
	public abstract void fix();
}

class FieldType extends Field {
	public final TypeReference type;
	
	protected FieldType(String name, int offset, FieldKind fieldKind, String typeName) {
		super(name, offset, TargetKind.TYPE, fieldKind);
		
		this.type = new TypeReference(name + "#fieldType", typeName);
	}
	public FieldType(String name, int offset, String typeName) {
		this(name, offset, FieldKind.FEILD, typeName);
	}

	@Override
	public void fix() {
		if (type.needsFix) {
			type.fix();
			if (!type.needsFix) {
				size = type.size;
				needsFix = false;
			}
		}
	}
}
class BitFieldType extends FieldType {
	public final int startPos;
	public final int stopPos;
	
	public BitFieldType(String name, int offset, int startPos, int stopPos, String typeName) {
		super(name, offset, FieldKind.BIT_FIELD, typeName);
		
		this.startPos = startPos;
		this.stopPos  = stopPos;
	}
}



class FieldSelect extends Field {
	public final Select select;
	
	protected FieldSelect(String name, int offset, FieldKind fieldKind, Select select) {
		super(name, offset, TargetKind.SELECT, fieldKind);
		
		this.select = select;
	}
	public FieldSelect(String name, int offset, Select select) {
		this(name, offset, FieldKind.FEILD, select);
	}

	@Override
	public void fix() {
		if (select.needsFix) {
			select.fix();
			if (!select.needsFix) {
				size = select.size;
				needsFix = false;
			}
		}
	}
}
class BitFieldSelect extends FieldSelect {
	public final int startPos;
	public final int stopPos;
	
	public BitFieldSelect(String name, int offset, int startPos, int stopPos, Select select) {
		super(name, offset, FieldKind.BIT_FIELD, select);
		
		this.startPos = startPos;
		this.stopPos  = stopPos;
	}
}


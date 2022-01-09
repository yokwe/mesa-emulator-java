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
package yokwe.majuro.genType;

import yokwe.util.UnexpectedException;

public class FieldInfo {
	final FieldType fieldType;
	final String    name;
	final String    type;
	final int       offset;
	final int       size;
	
	protected FieldInfo(FieldType fieldType, String name, String type, int offset, int size) {
		this.fieldType = fieldType;
		this.name      = name;
		this.type      = type;
		this.offset    = offset;
		this.size      = size;
	}
	public FieldInfo(String name, String type, int offset, int size) {
		this(FieldType.SIMPLE, name, type, offset, size);
	}
	
	public boolean isEmpty() {
		if (fieldType == FieldType.ARRAY) {
			ArrayFieldInfo arrayFieldInfo = (ArrayFieldInfo)this;
			return arrayFieldInfo.isEmpty();
		} else {
			return type.isEmpty();
		}
	}
	
	@Override
	public String toString() {
		switch(fieldType) {
		case SIMPLE:
			return toStringInternal();
		case ARRAY:
			return ((ArrayFieldInfo)this).toString();
		case BIT:
			return ((BitFieldInfo)this).toString();
		default:
			throw new UnexpectedException("Unexpected");
		}
	}
	private String toStringInternal() {
		return String.format("{%s %s %s %d %d}", fieldType, name, type, offset, size);
	}
	protected String toStringInternal(String extra) {
		return String.format("{%s %s %s %d %d %s}", fieldType, name, type, offset, size, extra);
	}
}
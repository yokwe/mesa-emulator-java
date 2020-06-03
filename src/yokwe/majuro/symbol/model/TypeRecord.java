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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeRecord extends Type {
	static final Logger logger = LoggerFactory.getLogger(TypeRecord.class);
	
	public final List<Field> fieldList;
	
	public TypeRecord(String name, List<Field> fieldList) {
		super(name, Kind.RECORD);
		
		this.fieldList = fieldList;
		
		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %d %s}", name, kind, size, fieldList);
	}
	
	@Override
	protected void fix() {
		if (needsFix) {
			boolean foundProblem = false;
			int recordSize = 0;
			for(Field field: fieldList) {
				if (field.needsFix) field.fix();
				if (field.needsFix) {
					foundProblem = true;
				} else {
					recordSize += field.size;
				}
			}
			if (!foundProblem) {
				this.size     = recordSize;
				this.needsFix = false;
			}
		}
	}
}
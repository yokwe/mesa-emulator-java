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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.TypeEnum.Element;

public class Select {
	private static final Logger logger = LoggerFactory.getLogger(Select.class);

	public static class SelectCase {
		public final String selector;
		public final int    value;
		
		public List<Field> fieldList;
		
		private boolean needsFix;
		private int     size;

		public SelectCase(String selector, int value, List<Field> fieldList) {
			this.selector  = selector;
			this.value     = value;
			this.fieldList = fieldList;
			this.needsFix  = true;
			this.size      = Type.UNKNOWN_SIZE;
		}
		public SelectCase(String selector, List<Field> fieldList) {
			this(selector, -1, fieldList);
		}
		
		@Override
		public String toString() {
			if (value == -1) {
				return String.format("{%s %s}", selector, fieldList);
			} else {
				return String.format("{%s(%d) %s}", selector, value, fieldList);
			}
		}
		
		// header(0) => [ ... ]
		public String toMesaType() {
			List<String> list = fieldList.stream().map(o -> o.toMesaType()).collect(Collectors.toList());
			
			return String.format("%s(%d) => [%s]", selector, value, String.join(", ", list));
		}
		
		public void fix() {
			if (needsFix) {
				boolean foundProblem   = false;
				int     selectCaseSize = 0;
				for(Field field: fieldList) {
					field.fix();
					if (field.hasValue()) {
						selectCaseSize = Math.max(selectCaseSize, field.fieldName.offset + field.getSize());
					} else {
						foundProblem = true;
					}
				}
				if (!foundProblem) {
					setSize(selectCaseSize);
				}
			}
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
	}
	
	public enum SelectKind {
		OVERLAID_ANON, // SELECT OVERLAID              * FROM
		OVERLAID_TYPE, // SELECT OVERLAID ControlLinkTag FROM
		TAG_ANON,      // SELECT type (0:0..15):       * FROM
		TAG_TYPE,      // SELECT tag (0:0..1):   LinkTag FROM
	}
	
	public final SelectKind       selectKind;
	public final FieldName        tagName;
	public final TypeReference    tagType;
	public final List<SelectCase> selectCaseList;
	
	private boolean needsFix;
	private int     size;
	
	private Select(String prefix, SelectKind selectKind, FieldName tagName, String tagTypeName, List<SelectCase> selectCaseList) {
		this.selectKind     = selectKind;
		this.tagName        = tagName;
		
		if (tagTypeName != null) {
			this.tagType = new TypeReference(prefix + "#" + tagTypeName + "#tagType", tagTypeName);
		} else {
			String enumName = prefix + "#" + tagTypeName + "#anon";
			
			this.tagType = new TypeReference(prefix + "#" + tagTypeName + "#tagType", enumName);
			
			// create anon enum
			List<Element> elementList = new ArrayList<>();
			for(SelectCase selectCase: selectCaseList) {
				elementList.add(new TypeEnum.Element(selectCase.selector, selectCase.value));
			}
			new TypeEnum(enumName, elementList);
		}
		
		this.selectCaseList = selectCaseList;
		
		this.needsFix       = true;
		this.size           = Type.UNKNOWN_SIZE;
		
		fix();
	}
	private Select(String prefix, SelectKind selectKind, String name, int offset, int startPos, int stopPos, String tagTypeName, List<SelectCase> selectCaseList) {
		this(prefix, selectKind, new FieldName(name, offset, startPos, stopPos), tagTypeName, selectCaseList);
	}
	private Select(String prefix, SelectKind selectKind, String name, int offset, String tagTypeName, List<SelectCase> selectCaseList) {
		this(prefix, selectKind, new FieldName(name, offset), tagTypeName, selectCaseList);
	}
	
	public Select(String prefix, List<SelectCase> selectCaseList) {
		this(prefix, SelectKind.OVERLAID_ANON, null, null, selectCaseList);
	}
	public Select(String prefix, String tagTypeName, List<SelectCase> selectCaseList) {
		this(prefix, SelectKind.OVERLAID_TYPE, null, tagTypeName, selectCaseList);
	}
	public Select(String prefix, String name, int offset, int startPos, int stopPos, List<SelectCase> selectCaseList) {
		this(prefix, SelectKind.TAG_ANON, name, offset, startPos, stopPos, null, selectCaseList);
	}
	public Select(String prefix, String name, int offset, List<SelectCase> selectCaseList) {
		this(prefix, SelectKind.TAG_ANON, name, offset, null, selectCaseList);
	}
	public Select(String prefix, String name, int offset, int startPos, int stopPos, String tagTypeName, List<SelectCase> selectCaseList) {
		this(prefix, SelectKind.TAG_TYPE, name, offset, startPos, stopPos, tagTypeName, selectCaseList);
	}
	public Select(String prefix, String name, int offset, String tagTypeName, List<SelectCase> selectCaseList) {
		this(prefix, SelectKind.TAG_TYPE, name, offset, tagTypeName, selectCaseList);
	}
	
	@Override
	public String toString() {
		switch(selectKind) {
		case OVERLAID_ANON:
			return String.format("{%s %s}", selectKind, selectCaseList);
		case OVERLAID_TYPE:
			return String.format("{%s %s %s}", selectKind, tagType.baseName, selectCaseList);
		case TAG_ANON:
			return String.format("{%s %s %s}", selectKind, tagName, selectCaseList);
		case TAG_TYPE:
			return String.format("{%s %s %s %s}", selectKind, tagName, tagType.baseName, selectCaseList);
		default:
			throw new UnexpectedException();
		}
	}

	public String toMesaType() {
		List<String> list = selectCaseList.stream().map(o -> o.toMesaType()).collect(Collectors.toList());

		switch(selectKind) {
		case OVERLAID_ANON:
			return String.format("SELECT OVERLAID * FROM %s ENDCASE", String.join(", ", list));
		case OVERLAID_TYPE:
			return String.format("SELECT OVERLAID %s FROM %s ENDCASE", tagType.baseName, String.join(", ", list));
		case TAG_ANON:
			return String.format("SELECT %s: * FROM %s ENDCASE", tagName, String.join(", ", list));
		case TAG_TYPE:
			return String.format("SELECT %s: %s FROM %s ENDCASE", tagName, tagType.baseName, String.join(", ", list));
		default:
			throw new UnexpectedException();
		}
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

	public void fix() {
		if (needsFix()) {
			boolean foundProblem = false;
			
			int size;
			if (tagType == null) {
				size = 0;
			} else {
				tagType.fix();
				if (tagType.hasValue()) {
					size = tagType.getSize();
				} else {
					foundProblem = true;
					size = 0;
				}
			}
			
			for(SelectCase selectCase: selectCaseList) {
				selectCase.fix();
				if (selectCase.needsFix()) {
					foundProblem = true;
				} else {
					size = Math.max(size, selectCase.getSize());
				}
			}
			if (!foundProblem) {
				setSize(size);
			}
		}
	}
}

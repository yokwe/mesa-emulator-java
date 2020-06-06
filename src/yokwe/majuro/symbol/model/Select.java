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
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public abstract class Select {
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
						selectCaseSize = Math.max(selectCaseSize, field.offset + field.getSize());
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
	public final List<SelectCase> selectCaseList;
	
	private boolean needsFix;
	private int     size;
	
	public Select(SelectKind selectKind, List<SelectCase> selectCaseList) {
		this.selectKind     = selectKind;
		this.selectCaseList = selectCaseList;
		
		this.needsFix       = true;
		this.size           = Type.UNKNOWN_SIZE;
	}
	
	public abstract String toMesaType();
	
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
			int size = 0;
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

// SELECT OVERLAID * FROM
class SelectOvelaidAnon extends Select {
	public SelectOvelaidAnon(String prefix, List<SelectCase> selectCaseList) {
		super(SelectKind.OVERLAID_ANON, selectCaseList);
		
		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s}", selectKind, selectCaseList);
	}
	
	@Override
	public String toMesaType() {
		List<String> list = selectCaseList.stream().map(o -> o.toMesaType()).collect(Collectors.toList());

		return String.format("SELECT OVERLAID * FROM %s ENDCASE", String.join(", ", list));
	}

}

// SELECT OVERLAID ControlLinkTag FROM
class SelectOvelaidType extends Select {
	public final TypeReference tagType;
	
	public SelectOvelaidType(String prefix, String tagTypeName, List<SelectCase> selectCaseList) {
		super(SelectKind.OVERLAID_TYPE, selectCaseList);
		
		this.tagType = new TypeReference(prefix + "#" + tagTypeName + "#selector", tagTypeName);
		
		fix();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s}", selectKind, tagType.baseName, selectCaseList);
	}
	
	@Override
	public String toMesaType() {
		List<String> list = selectCaseList.stream().map(o -> o.toMesaType()).collect(Collectors.toList());

		return String.format("SELECT OVERLAID %s FROM %s ENDCASE", tagType.baseType.name, String.join(", ", list));
	}
	
	@Override
	public void fix() {
		if (needsFix()) {
			boolean foundProblem = false;
			
			tagType.fix();
			if (tagType.needsFix()) {
				foundProblem = true;
			}
			
			int size = 0;
			for(SelectCase selectCase: selectCaseList) {
				selectCase.fix();
				if (selectCase.needsFix()) {
					foundProblem = true;
				} else {
					size += selectCase.getSize();
				}
			}
			if (!foundProblem) {
				setSize(size);
			}
		}
	}
}

// SELECT type (0:0..15): * FROM
class SelectTagAnon extends Select {
	public final String tagName;
	public final int    offset;
	public final int    startPos;
	public final int    stopPos;
	
	public SelectTagAnon(String prefix, String tagName, int offset, int startPos, int stopPos, List<SelectCase> selectCaseList) {
		super(SelectKind.TAG_ANON, selectCaseList);
		
		this.tagName  = tagName;
		this.offset   = offset;
		this.startPos = startPos;
		this.stopPos  = stopPos;
		
		fix();
	}
	
	public SelectTagAnon(String prefix, String tagName, int offset, List<SelectCase> selectCaseList) {
		this(prefix, tagName, offset, -1, -1, selectCaseList);
	}

	@Override
	public String toString() {
		if (startPos == -1) {
			return String.format("{%s %s (%d) %s}", selectKind, tagName, offset, selectCaseList);
		} else {
			return String.format("{%s %s (%d:%d..%d) %s}", selectKind, tagName, offset, startPos, stopPos, selectCaseList);
		}
	}
	
	@Override
	public String toMesaType() {
		List<String> list = selectCaseList.stream().map(o -> o.toMesaType()).collect(Collectors.toList());

		if (startPos == -1) {
			return String.format("SELECT %s(%d): * FROM %s ENDCASE", tagName, offset, String.join(", ", list));
		} else {
			return String.format("SELECT %s(%d:%d..%d): * FROM %s ENDCASE", tagName, offset, startPos, stopPos, String.join(", ", list));
		}
	}
}

// SELECT tag (0:0..1): LinkTag FROM
class SelectTagType extends Select {
	public final String tagName;
	public final int    offset;
	public final int    startPos;
	public final int    stopPos;
	
	public final TypeReference tagType;

	public SelectTagType(String prefix, String tagName, int offset, int startPos, int stopPos, String tagTypeName, List<SelectCase> selectCaseList) {
		super(SelectKind.TAG_TYPE, selectCaseList);
		
		this.tagName  = tagName;
		this.offset   = offset;
		this.startPos = startPos;
		this.stopPos  = stopPos;
		
		this.tagType = new TypeReference(prefix + "#" + tagTypeName + "#selector", tagTypeName);

		fix();
	}
	
	public SelectTagType(String prefix, String selectorName, int offset, String selectorTypeName, List<SelectCase> selectCaseList) {
		this(prefix, selectorName, offset, -1, -1, selectorTypeName, selectCaseList);
	}

	@Override
	public void fix() {
		if (needsFix()) {
			boolean foundProblem = false;
			
			tagType.fix();
			if (tagType.needsFix()) {
				foundProblem = true;
			}
			
			int size = 0;
			for(SelectCase selectCase: selectCaseList) {
				selectCase.fix();
				if (selectCase.needsFix()) {
					foundProblem = true;
				} else {
					size += selectCase.getSize();
				}
			}
			if (!foundProblem) {
				setSize(size);
			}
		}
	}

	@Override
	public String toString() {
		if (startPos == -1) {
			return String.format("{%s %s (%d) %s %s}", selectKind, tagName, offset, tagType.baseName, selectCaseList);
		} else {
			return String.format("{%s %s (%d:%d..%d) %s %s}", selectKind, tagName, offset, startPos, stopPos, tagType.baseName, selectCaseList);
		}
	}
	
	@Override
	public String toMesaType() {
		List<String> list = selectCaseList.stream().map(o -> o.toMesaType()).collect(Collectors.toList());

		// SELECT tag (0:0..1): LinkTag FROM
		if (startPos == -1) {
			return String.format("SELECT %s(%d): %s FROM %s ENDCASE", tagName, offset, tagType.baseName, String.join(", ", list));
		} else {
			return String.format("SELECT %s(%d:%d..%d): %s FROM %s ENDCASE", tagName, offset, startPos, tagType.baseName, stopPos, String.join(", ", list));
		}
	}
}

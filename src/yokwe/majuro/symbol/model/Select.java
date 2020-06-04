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

import yokwe.majuro.UnexpectedException;

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
		
		public void fix() {
			if (needsFix) {
				boolean foundProblem   = false;
				int     selectCaseSize = 0;
				for(Field field: fieldList) {
					field.fix();
					if (field.hasValue()) {
						selectCaseSize += field.getSize();
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
	
	// FIXME handle only "SELECT OVERLAID *"

	// SELECT OVERLAID              * FROM
	// SELECT OVERLAID ControlLinkTag FROM
	// SELECT type (0:0..15):       * FROM
	// SELECT tag (0:0..1):   LinkTag FROM
	
	public final List<SelectCase> selectCaseList;
	
	private boolean needsFix;
	private int     size;
	
	public Select(List<SelectCase> selectCaseList) {
		this.selectCaseList = selectCaseList;
		this.needsFix       = true;
		this.size           = Type.UNKNOWN_SIZE;
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s}", "OVERLAID*", selectCaseList);
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
		if (needsFix) {
			boolean foundProblem = false;
			int recordSize = 0;
			for(SelectCase selectCase: selectCaseList) {
				selectCase.fix();
				if (selectCase.needsFix) {
					foundProblem = true;
				} else {
					recordSize += selectCase.size;
				}
			}
			if (!foundProblem) {
				setSize(recordSize);
			}
		}
	}
}
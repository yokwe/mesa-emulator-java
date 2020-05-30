package yokwe.majuro.symbol.antlr.model;

import java.util.ArrayList;
import java.util.List;

import yokwe.majuro.symbol.antlr.model.TypeRecord.RecordField;

public class Select {
	public static class CaseField {
		public final RecordField recordField;
		public final Select      select;
		
		public boolean needsFix;
		public int     size;

		public CaseField(RecordField recordField) {
			this.recordField = recordField;
			this.select      = null;
			
			this.needsFix = true;
			this.size     = 0;
		}
		public CaseField(Select select) {
			this.recordField = null;
			this.select      = select;
			
			this.needsFix = true;
			this.size     = 0;
		}
		
		public void fix() {
			if (needsFix) {
				if (recordField != null) {
					recordField.fix();
					if (!recordField.needsFix) {
						this.size = recordField.size;
						this.needsFix = false;
					}
				}
				if (select != null) {
					recordField.fix();
					if (!select.needsFix) {
						this.size = select.size;
						this.needsFix = false;
					}
				}
			}
		}
	}
	
	public static class SelectCase {
		public final String selector;
		public final int    value;
		
		public List<CaseField> caseFieldList;
		
		public boolean needsFix;
		public int     size;

		public SelectCase(String selector, int value, List<CaseField> caseFieldList) {
			this.selector      = selector;
			this.value         = value;
			this.caseFieldList = caseFieldList;
			this.needsFix      = true;
			this.size          = -1;
		}
		public SelectCase(String selector, int value) {
			this.selector      = selector;
			this.value         = value;
			this.caseFieldList = new ArrayList<>();
			this.needsFix      = true;
			this.size          = -1;
		}
		
		public void addField(RecordField newValue) {
			caseFieldList.add(new CaseField(newValue));
		}
		public void addField(Select newValue) {
			caseFieldList.add(new CaseField(newValue));
		}
		
		public void fix() {
			if (needsFix) {
				boolean foundProblem = false;
				int selectCaseSize = 0;
				for(CaseField caseFeld: caseFieldList) {
					if (caseFeld.needsFix) caseFeld.fix();
					if (caseFeld.needsFix) {
						foundProblem = true;
					} else {
						selectCaseSize += caseFeld.size;
					}
				}
				if (!foundProblem) {
					this.size     = selectCaseSize;
					this.needsFix = false;
				}
			}
		}
	}
	
	
	
	public static enum Kind {
		OVERLAID,
		ANON,
		ENUM
	}
	
	public final Kind kind;
	
	public final List<SelectCase> selectCaseList;
	
	// FIXME
	public boolean needsFix;
	public int     size;
	
	public Select(List<SelectCase> selectCaseList) {
		this.kind           = Kind.OVERLAID;
		this.selectCaseList = selectCaseList;
		this.needsFix       = true;
		this.size           = -1;
	}
	

	public void fix() {
		if (needsFix) {
			boolean foundProblem = false;
			int recordSize = 0;
			for(SelectCase selectCase: selectCaseList) {
				if (selectCase.needsFix) selectCase.fix();
				if (selectCase.needsFix) {
					foundProblem = true;
				} else {
					recordSize += selectCase.size;
				}
			}
			if (!foundProblem) {
				this.size     = recordSize;
				this.needsFix = false;
			}
		}
	}

	@Override
	public String toString() {
		// FIXME
		return "FIXME  SELECT";
	}
}
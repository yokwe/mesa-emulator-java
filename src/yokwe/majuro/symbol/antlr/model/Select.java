package yokwe.majuro.symbol.antlr.model;

import java.util.ArrayList;
import java.util.List;

public class Select {
	public static class CaseField {
		public final Field field;
		
		public boolean needsFix;
		public int     size;

		public CaseField(Field field) {
			this.field = field;
			
			this.needsFix = true;
			this.size     = 0;
		}
		
		public void fix() {
			if (needsFix) {
				field.fix();
				if (!field.needsFix) {
					this.size = field.size;
					this.needsFix = false;
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
			this.size          = Type.UNKNOWN_SIZE;
		}
		public SelectCase(String selector, int value) {
			this.selector      = selector;
			this.value         = value;
			this.caseFieldList = new ArrayList<>();
			this.needsFix      = true;
			this.size          = Type.UNKNOWN_SIZE;
		}
		
		public void addField(Field newValue) {
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
	
	// FIXME should I create separate file for overlaid, ano, enum?
	public static enum Kind {
		OVERLAID,
		ANON,
		ENUM
	}
	
	public final Kind kind;
	
	public final List<SelectCase> selectCaseList;
	
	public boolean needsFix;
	public int     size;
	
	public Select(List<SelectCase> selectCaseList) {
		this.kind           = Kind.OVERLAID;
		this.selectCaseList = selectCaseList;
		this.needsFix       = true;
		this.size           = Type.UNKNOWN_SIZE;
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
package yokwe.majuro.symbol.antlr.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

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
package yokwe.majuro.symbol.antlr.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class TypeRecord extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeRecord.class);
	
	public static class RecordField {
		public final String name;
		public final int    pos;
		public final int    startPos;
		public final int    stopPos;
		
		public final Type   type;
		public final Select select;
		
		public boolean needsFix;
		public long    size;
		
		RecordField(String name, int pos, int startPos, int stopPos, Type type) {
			this.name     = name;
			this.pos      = pos;
			this.startPos = startPos;
			this.stopPos  = stopPos;
			
			this.type     = type;
			this.select   = null;
			
			this.needsFix = true;
			this.size     = -1;
			
			fix();
		}
		RecordField(String name, int pos, Type type) {
			this(name, pos, -1, -1, type);
		}
		RecordField(String name, int pos, int startPos, int stopPos, Select select) {
			this.name     = name;
			this.pos      = pos;
			this.startPos = startPos;
			this.stopPos  = stopPos;
			
			this.type     = null;
			this.select   = select;
			
			this.needsFix = true;
			this.size     = -1;
			
			fix();
		}
		RecordField(String name, int pos, Select select) {
			this(name, pos, -1, -1, select);
		}
		
		@Override
		public String toString() {
			if (type != null) return String.format("{%s %d %d %d %s}", name, pos, startPos, stopPos, type);
			if (select != null) return String.format("{%s %d %d %d %s}", name, pos, startPos, stopPos, select);
			logger.error("Unexpected");
			throw new UnexpectedException("Unexpected");
		}
		
		void fix() {
			if (needsFix) {
				if (type != null) {
					type.fix();
					if (!type.needsFix) {
						size = type.size;
						needsFix = false;
					}
				}
				if (select != null) {
					select.fix();
					if (!select.needsFix) {
						size = select.size;
						needsFix = false;
					}
				}
				logger.error("Unexpected");
				throw new UnexpectedException("Unexpected");
			}
		}
	}
	
	public final List<RecordField> fieldList;
	
	TypeRecord(String name, List<RecordField> fieldList) {
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
			for(RecordField field: fieldList) {
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
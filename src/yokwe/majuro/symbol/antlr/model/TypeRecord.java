package yokwe.majuro.symbol.antlr.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class TypeRecord extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeRecord.class);
	
	public static class RecordField {
		public final String  name;
		public final int     offset;
		
		public final boolean isBitField;
		public final int     startPos;
		public final int     stopPos;
		
		public final Type    type;
		public final Select  select;
		
		public boolean needsFix;
		public int     size;
		
		
		private RecordField(String name, int offset, int startPos, int stopPos, Type type, Select select, boolean isBitField) {
			this.name       = name;
			this.offset     = offset;
			
			this.isBitField = isBitField;
			this.startPos   = startPos;
			this.stopPos    = stopPos;
			
			this.type       = type;
			this.select     = select;
			
			this.needsFix   = true;
			this.size       = -1;
			
			fix();
		}
		RecordField(String name, int offset, int startPos, int stopPos, Type type) {
			this(name, offset, startPos, stopPos, type, null, true);
		}
		RecordField(String name, int offset, Type type) {
			this(name, offset, -1, -1, type, null, false);
		}
		
		RecordField(String name, int offset, int startPos, int stopPos, Select select) {
			this(name, offset, startPos, stopPos, null, select, true);
		}
		RecordField(String name, int offset, Select select) {
			this(name, offset, -1, -1, null, select, false);
		}
		
		@Override
		public String toString() {
			if (type != null) {
				if (isBitField) {
					return String.format("{%s %d %d %d %s}", name, offset, startPos, stopPos, type);
				} else {
					return String.format("{%s %d %s}", name, offset, type);
				}
			}
			if (select != null) {
				if (isBitField) {
					return String.format("{%s %d %d %d %s}", name, offset, startPos, stopPos, select);
				} else {
					return String.format("{%s %d %s}", name, offset, select);
				}
			}
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
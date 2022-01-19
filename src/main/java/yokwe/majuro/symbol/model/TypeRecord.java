package yokwe.majuro.symbol.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeRecord extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypeRecord.class);

	public static class Field {
		public static final int    NO_VALUE = -1;
		public static final String NO_VALUE_STRING = Integer.toString(NO_VALUE);
		
		public final String name;
		public final int    offset;
		public final int    startBit;
		public final int    stopBit;
		public final Type   type;
		
		public Field(String name, String offset, String startBit, String stopBit, Type type) {
			this.name     = name;
			this.offset   = Integer.parseInt(offset);
			this.startBit = Integer.parseInt(startBit);
			this.stopBit  = Integer.parseInt(stopBit);
			this.type     = type;
		}
		public Field(String name, String offset, Type type) {
			this(name, offset, NO_VALUE_STRING, NO_VALUE_STRING, type);
		}
		
		@Override
		public String toString() {
			return StringUtil.toString(this);
		}
		
		public boolean hasStartBit() {
			return startBit == NO_VALUE;
		}

	}

	public enum Align {
		BIT_16,
		BIT_32,
	}
	public final Align align;
	
	public List<Field> fieldList;
	
	public TypeRecord(String name, Align align, List<Field> fieldList) {
		super(name, Kind.RECORD);
		this.align     = align;
		this.fieldList = fieldList;
		
		// sanity check
		{
			boolean foundProblem = false;
			
			{
				Map<String, Field> map = new TreeMap<>();
				for(var e: fieldList) {
					if (map.containsKey(e.name)) {
						logger.error("field name has duplication");
						logger.error("  new  {}", e);
						logger.error("  old  {}", map.get(e.name));
						foundProblem = true;
					} else {
						map.put(e.name, e);
					}
				}
			}
			{
				Map<String, Field> map = new TreeMap<>();
				for(var e: fieldList) {
					String key = String.format("%d %d %d", e.offset, e.startBit, e.stopBit);
					if (map.containsKey(key)) {
						logger.error("field position has duplication");
						logger.error("  new  {}", e);
						logger.error("  old  {}", map.get(key));
						foundProblem = true;
					} else {
						map.put(key, e);
					}
				}
			}
			
			if (foundProblem) {
				logger.error("found problem");
				logger.error("  name {}", name);
				throw new UnexpectedException("found problem");
			}
		}
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	@Override
	public void fix() {
		if (needsFix) {
			int countNeedsFix = 0;
			for(var e: fieldList) {
				e.type.fix();
				if (e.type.needsFix) countNeedsFix++;
			}
			if (countNeedsFix == 0) {
				needsFix = false;
			}
		}
	}
	
}

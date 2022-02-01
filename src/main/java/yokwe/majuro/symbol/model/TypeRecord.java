package yokwe.majuro.symbol.model;

import static yokwe.majuro.mesa.Constant.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
		public final int    bitSize;
		
		public Field(String name, String offset, String startBit, String stopBit, Type type) {
			this.name     = name;
			this.offset   = Integer.parseInt(offset);
			this.startBit = Integer.parseInt(startBit);
			this.stopBit  = Integer.parseInt(stopBit);
			this.type     = type;
			
			if (this.startBit == NO_VALUE) {
				this.bitSize  = 0;
			} else {
				this.bitSize  = this.stopBit - this.startBit + 1;
			}
		}
		public Field(String name, String offset, Type type) {
			this(name, offset, NO_VALUE_STRING, NO_VALUE_STRING, type);
		}
		
		@Override
		public String toString() {
			return StringUtil.toString(this);
		}
		
		public boolean hasStartBit() {
			return startBit != NO_VALUE;
		}
		
		public String toMesaType() {
			if (this.startBit == NO_VALUE) {
				return String.format("%s (%d): %s", name, offset, type.toMesaType());
			} else {
				return String.format("%s (%d:%d..%d): %s", name, offset, startBit, stopBit, type.toMesaType());
			}
		}
	}

	public enum Align {
		BIT_16,
		BIT_32,
	}
	public final Align align;
	
	public List<Field> fieldList;
	
	public TypeRecord(String name, Align align, List<Field> fieldList) {
		super(name);
		this.align     = align;
		this.fieldList = fieldList;
		
		{
			bitSize = 0;
			for(var e: fieldList) {
				bitSize += e.bitSize; // use bitSize of field not e.type.bitSize
			}
		}

		// sanity check

		{
			boolean foundProblem = false;
			
			if (align == Align.BIT_32 && bitSize != 32) {
				foundProblem = true;
				logger.error("bitSize is not 32 for RECORD32");
			}
			
			if (8 < bitSize && (bitSize % 16) != 0) {
				foundProblem = true;
				logger.error("bitSize is not multiple of 16");
				logger.error("  {}", String.format("%-30s  %4d:%2d", name, bitSize / 16, bitSize % 16));						
			}
			
			// check duplicate filed name
			{
				Map<String, Field> map = new TreeMap<>();
				for(var e: fieldList) {
					if (map.containsKey(e.name)) {
						logger.error("dupblicate field name");
						logger.error("  new  {}", e);
						logger.error("  old  {}", map.get(e.name));
						foundProblem = true;
					} else {
						map.put(e.name, e);
					}
				}
			}
			
			{
				Map<Integer, Field> map = new TreeMap<>();
				
				// check overlap of field bit position
				for(var e: fieldList) {
					if (e.type.bitSize == 0) continue;
					if (!e.hasStartBit()) continue;
					
					int startBit = e.offset * 16 + e.startBit;
					int stopBit  = e.offset * 16 + e.stopBit;
					
					for(int i = startBit; i <= stopBit; i++) {
						// check overlap
						if (map.containsKey(i)) {
							foundProblem = true;
							Field o = map.get(i);
							logger.error("field overlap at bit {}", i);
							logger.error("{}", String.format("  old %-10s  %2d:%2d - %2d", o.name, o.offset, o.startBit, o.stopBit));
							logger.error("{}", String.format("  new %-10s  %2d:%2d - %2d", e.name, e.offset, e.startBit, e.stopBit));
						} else {
							map.put(i, e);
						}
					}
				}
				
				// check unused bit position in record
				{
					int minBit = 0;
					int maxBit = 0;
					for(var e: fieldList) {
						if (e.type.bitSize == 0) continue;
						if (!e.hasStartBit()) continue;
						
						int start = e.offset * 16 + e.startBit;
						int stop  = e.offset * 16 + e.startBit;
						
						if (start < minBit) minBit = start;
						if (maxBit < stop)  maxBit = stop;
					}
					
					for(int i = minBit; i <= maxBit; i++) {
						if (map.containsKey(i)) {
							// OK
						} else {
							// found hole
							foundProblem = true;
							int start = i;
							int stop  = start;
							for(int j = i; j <= maxBit; j++) {
								if (map.containsKey(j)) break;
								stop = j;
							}
							logger.error("hole  {}  {}  {}:{} - {}:{}", name, bitSize, start / 16, start % 16, stop / 16, stop % 16);
						}
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
				
				// check field width
				{
					boolean foundProblem = false;
					
					for(var e: fieldList) {
						Type type = e.type.getRealType();
						
						int fieldBitSize = e.bitSize;
						
						int typeBitSize = type.bitSize;
						if (bitField16() || bitField32()) {
							typeBitSize  = type.bitSize;
						} else {
							typeBitSize = type.wordSize() * WORD_BITS;
						}
						
						if (fieldBitSize == 0) continue;
						if (fieldBitSize != typeBitSize) {
							// UNSPECIFIED and CARDINAL can have variable length
							if (type == Type.UNSPECIFIED) continue;
							if (type == Type.CARDINAL)    continue;
							foundProblem = true;
							logger.error("field  {}  {}  fieldBitSize  {}  typeBitSize  {}", e.name, type.name, fieldBitSize, typeBitSize);
						}
					}
					
					if (foundProblem) {
						logger.error("found problem");
						logger.error("  {}", name);
						for(var e: fieldList) {
							logger.error("  {}", String.format("%-10s %-10s  field %2d  type %2d", e.name, e.type.getRealType().name, e.bitSize, e.type.bitSize));
						}
						throw new UnexpectedException("found problem");
					}
				}
			}
		}
	}

	@Override
	public String toMesaType() {
		String baseType;
		switch(align) {
		case BIT_16:
			baseType = "RECORD";
			break;
		case BIT_32:
			baseType = "RECORD32";
			break;
		default:
			throw new UnexpectedException("Unexpected");
		}
		
		List<String> list = fieldList.stream().map(o -> o.toMesaType()).collect(Collectors.toList());
		return String.format("%s[%s]", baseType, String.join(", ", list));
	}

	@Override
	public boolean container() {
		if (bitField16()) return false;
		if (bitField32()) return false;
		return true;
	}
}

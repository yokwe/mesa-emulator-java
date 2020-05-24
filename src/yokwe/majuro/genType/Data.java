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
package yokwe.majuro.genType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.util.StringUtil;
import yokwe.util.libreoffice.Sheet;
import yokwe.util.libreoffice.SpreadSheet;

public class Data {
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(Data.class);

	@Sheet.SheetName("Record")
	@Sheet.HeaderRow(0)
	@Sheet.DataRow(1)
	public static class Record extends Sheet {
		@ColumnName("name")          public String name;
		@ColumnName("field")         public String field;
		@ColumnName("type")          public String type;
		@ColumnName("offset")        public String offset;
		@ColumnName("size")          public String size;
		@ColumnName("startBit")      public String startBit;
		@ColumnName("stopBit")       public String stopBit;
		@ColumnName("indexType")     public String indexType;
		@ColumnName("elementType")   public String elementType;
		@ColumnName("elementSize")   public String elementSize;
		@ColumnName("elementLength") public String elementLength;
		
		public Record() {
		}
		
		public boolean isEmpty() {
			return name.isEmpty();
		}
		
		public void normalize() {
			if (name          == null) name = "";
			if (field         == null) field = "";
			
			if (type          == null) type = "";
			
			if (offset        == null) offset = "";
			else offset = Integer.toString((int)Double.parseDouble(offset));
			
			if (size          == null) size = "";
			else size = Integer.toString((int)Double.parseDouble(size));
			
			if (startBit      == null) startBit = "";
			else startBit = Integer.toString((int)Double.parseDouble(startBit));
			
			if (stopBit       == null) stopBit = "";
			else stopBit = Integer.toString((int)Double.parseDouble(stopBit));

			if (indexType     == null) indexType = "";
			if (elementType   == null) elementType = "";
			
			if (elementSize   == null) elementSize = "";
			else elementSize = Integer.toString((int)Double.parseDouble(elementSize));

			if (elementLength == null) elementLength = "";
			else elementLength = Integer.toString((int)Double.parseDouble(elementLength));
		}
	}
	
	@Sheet.SheetName("Type")
	@Sheet.HeaderRow(0)
	@Sheet.DataRow(1)
	public static class Type extends Sheet {
		@ColumnName("name")     public String name;
		@ColumnName("type")     public String type;
		@ColumnName("minValue") public String minValue;
		@ColumnName("maxValue") public String maxValue;
		
		public Type() {
		}
		
		public boolean isEmpty() {
			return name.isEmpty();
		}
		
		public void normalize() {
			if (name     == null) name = "";
			if (type     == null) type = "";
			
			if (minValue == null) minValue = "";
			else minValue = Integer.toString((int)Double.parseDouble(minValue));

			if (maxValue == null) maxValue = "";
			else maxValue = Integer.toString((int)Double.parseDouble(maxValue));
		}
	}
	
	static void buildRecordMap(Map<String, RecordInfo> map, List<Record> recordList) {
		// Sanity check
		{
			for(Record record: recordList) {
				record.normalize();
				if (record.isEmpty()) continue;
				
				if (record.name.isEmpty()) {
					logger.error("name is empty");
					throw new UnexpectedException();
				}
				if (record.field.isEmpty()) {
					logger.error("field is empty");
					throw new UnexpectedException();
				}
				if (record.offset.isEmpty()) {
					logger.error("offset is empty");
					throw new UnexpectedException();
				}
				if (record.size.isEmpty()) {
					logger.error("size is null");
					throw new UnexpectedException();
				}
				
				if (record.startBit.isEmpty()) {
					if (!record.stopBit.isEmpty()) {
						logger.error("startBit is empty but stopBit is not empty");
						throw new UnexpectedException();
					}
				}
				if (record.stopBit.isEmpty()) {
					if (!record.startBit.isEmpty()) {
						logger.error("stopBit is empty but startBit is not empty");
						throw new UnexpectedException();
					}
				}
				if (record.elementSize.isEmpty()) {
					if (!record.elementLength.isEmpty()) {
						logger.error("elementSize is empty but elementLength is not empty");
						throw new UnexpectedException();
					}
				}
				if (record.elementLength.isEmpty()) {
					if (!record.elementSize.isEmpty()) {
						logger.error("elementLength is empty but elementSize is not empty");
						throw new UnexpectedException();
					}
				}
			}
		}

		Map<String, List<Record>> recordMap = new TreeMap<>();
		for(Record e: recordList) {
			List<Record> myList;
			if (recordMap.containsKey(e.name)) {
				myList = recordMap.get(e.name);
			} else {
				myList = new ArrayList<>();
				recordMap.put(e.name, myList);
			}
			myList.add(e);
		}
		
		for(List<Record> list: recordMap.values()) {
			Record last = list.get(list.size() - 1);
			int size = Integer.parseInt(last.offset) + Integer.parseInt(last.size);
			
			List<FieldInfo> fields = new ArrayList<>();
			
			for(Record e: list) {
				if (!e.startBit.isEmpty() && e.elementType.isEmpty()) {
					// BIT
					fields.add(new BitFieldInfo(
						e.field, e.type, Integer.parseInt(e.offset), Integer.parseInt(e.size),
						Integer.parseInt(e.startBit), Integer.parseInt(e.stopBit)));
				} else if (e.startBit.isEmpty() && !e.elementType.isEmpty()) {
					// ARRAY
					fields.add(new ArrayFieldInfo(
						e.field, e.type, Integer.parseInt(e.offset), Integer.parseInt(e.size),
						e.indexType, e.elementType, Integer.parseInt(e.elementSize), Integer.parseInt(e.elementLength)));
				} else if (e.startBit.isEmpty() && e.elementType.isEmpty()) {
					// NORMAL
					fields.add(new FieldInfo(
						e.field, e.type, Integer.parseInt(e.offset), Integer.parseInt(e.size)));
				} else {
					logger.error("Unexpected field type");
					throw new UnexpectedException("Unexpected field type");
				}
			}
			
			map.put(last.name, new RecordInfo(last.name, size, fields));
		}
	}

	static void buildTypeMap(Map<String, TypeInfo> map, List<Type> typeList) {
		for(Type type: typeList) {
			type.normalize();
			
			if (type.isEmpty() && type.type.isEmpty() && type.minValue.isEmpty() && type.maxValue.isEmpty()) continue;
			
			// Sanity check
			{
				if (type.name.isEmpty()) {
					logger.error("name is empty");
					throw new UnexpectedException();
				}
				if (type.type.isEmpty()) {
					logger.error("type is empty");
					throw new UnexpectedException();
				}
				if (type.minValue.isEmpty()) {
					logger.error("minValue is empty");
					throw new UnexpectedException();
				}
				if (type.maxValue.isEmpty()) {
					logger.error("maxValue is empty");
					throw new UnexpectedException();
				}
			}
			
			map.put(type.name, new TypeInfo(type.name, type.type, Integer.parseInt(type.minValue), Integer.parseInt(type.maxValue)));
		}
	}

	
	public static final String URL_DATA_FILE = "file:///home/hasegawa/git/mesa-emulator-java/data/type/MesaRecordType.fods";
	
	public static void readData(Map<String, RecordInfo> recordMap, Map<String, TypeInfo> typeMap) {
		try (SpreadSheet docActivity = new SpreadSheet(URL_DATA_FILE, true)) {

			List<Record> recordList = Sheet.extractSheet(docActivity, Record.class);
			List<Type>   typeList   = Sheet.extractSheet(docActivity, Type.class);

			logger.info("recordList {}", recordList.size());
			logger.info("typeList   {}", typeList.size());
			buildRecordMap(recordMap, recordList);
			buildTypeMap  (typeMap,   typeList);
			logger.info("recordMap  {}", recordMap.size());
			logger.info("typeMap    {}", typeMap.size());
		}
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		Map<String, RecordInfo> recordMap = new TreeMap<>();
		Map<String, TypeInfo>   typeMap   = new TreeMap<>();
		
		readData(recordMap, typeMap);
		
		logger.info("recordMap {}", recordMap.size());
		logger.info("typeMap {}",   typeMap.size());
		
		logger.info("====");
		for(RecordInfo e: recordMap.values()) {
			logger.info("RECORD {}", StringUtil.toString(e));
		}
		logger.info("====");
		for(TypeInfo e: typeMap.values()) {
			logger.info("TYPE {}", StringUtil.toString(e));
		}

		logger.info("STOP");
		System.exit(0);
	}


}

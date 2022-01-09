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

import yokwe.util.UnexpectedException;

public class Context {
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(Context.class);

	final Map<String, RecordInfo> recordMap;
	final Map<String, TypeInfo>   typeMap;
	final Map<String, EnumInfo>   enumMap;
	
	Context(Map<String, RecordInfo> recordMap, Map<String, TypeInfo> typeMap, Map<String, EnumInfo> enumMap) {
		this.recordMap = recordMap;
		this.typeMap   = typeMap;
		this.enumMap   = enumMap;
	}
	
	boolean isPrimitive(String name) {
		switch(name) {
		case "boolean":
		case "CARD8":
		case "CARD16":
		case "CARD32":
		case "POINTER":
		case "LONG_POINTER":
			return true;
		default:
			return false;
		}
	}
	boolean isRecord(String name) {
		return recordMap.containsKey(name);
	}
	boolean isType(String name) {
		return typeMap.containsKey(name);
	}
	boolean isEnum(String name) {
		return enumMap.containsKey(name);
	}
	RecordInfo getRecordInfo(String name) {
		if (recordMap.containsKey(name)) {
			return recordMap.get(name);
		} else {
			logger.error("name {}", name);
			throw new UnexpectedException("name");
		}
	}
	int getSize(String name) {
		String type = name;
		for(;;) {
			switch(type) {
			case "boolean":
			case "CARD8":
			case "CARD16":
			case "POINTER":
				return 1;
			case "CARD32":
			case "LONG_POINTER":
				return 2;
			default:
				if (typeMap.containsKey(type)) {
					type = typeMap.get(type).type;
					continue;
				}
				if (recordMap.containsKey(name)) {
					return recordMap.get(name).size;
				} else if (enumMap.containsKey(name)) {
					return 1;
				} else {
					logger.error("name {}", name);
					throw new UnexpectedException("name");
				}
			}
		}
	}
	String getBaseType(String name) {
		String type = name;
		for(;;) {
			switch(type) {
			case "boolean":
			case "CARD8":
			case "CARD16":
			case "CARD32":
			case "POINTER":
			case "LONG_POINTER":
				return type;
			default:
				if (typeMap.containsKey(type)) {
					type = typeMap.get(type).type;
					continue;
				}
				if (recordMap.containsKey(type)) {
					return type;
				} else if (enumMap.containsKey(name)) {
					return "CARD16";
				} else {
					logger.error("name {}", name);
					logger.error("type {}", type);
					logger.error("recordMap {}", recordMap.keySet());
					throw new UnexpectedException("Unexpected");
				}
			}
		}
	}

	void sanityCheck() {
		boolean hasProblem = false;
		for(TypeInfo e: typeMap.values()) {
			{
				String type = getBaseType(e.type);
				if (isPrimitive(type)) {
					//
				} else {
					logger.error("TYPE type is not primitive");
					logger.error("  type {}", e);
					hasProblem = true;
				}
			}
			{
				int minValue = e.minValue;
				int maxValue = e.maxValue;
				if (minValue < 0) {
					logger.error("TYPE minValue is negative");
					logger.error("  type {}", e);
					hasProblem = true;
				}
				if (maxValue < minValue) {
					logger.error("TYPE minValue is greather than maxValue");
					logger.error("  type {}", e);
					hasProblem = true;
				}
			}
		}
		for(RecordInfo e: recordMap.values()) {
			// check record fieldList
			if (e.fieldList.isEmpty()) {
				logger.error("RECORD fieldList is empty");
				logger.error("  record {}", e);
				hasProblem = true;
			}
			// check record size
			{
				int recordSize = 0;
				for(FieldInfo ee: e.fieldList) {
					recordSize = Math.max(recordSize, ee.offset + ee.size);
				}
				if (e.size != recordSize) {
					logger.error("RECORD size doesn't match with last field");
					logger.error("  record {}", e);
					hasProblem = true;
				}
			}
			
			// check field size
			{
				for(FieldInfo ee: e.fieldList) {
					if (ee.isEmpty()) continue;
					
					switch(ee.fieldType) {
					case SIMPLE:
					case BIT:
					{
						String type = getBaseType(ee.type);
						int size = getSize(type);
						if (ee.size != size) {
							logger.error("RECORD unexpected size");
							logger.error("  expect   {}", size);
							logger.error("  actual   {}", ee.size);
							logger.error("  record   {}", e);
							logger.error("  field    {}", ee);
							hasProblem = true;
						}
					}
						break;
					case ARRAY:
					{
						ArrayFieldInfo arrayFieldInfo = (ArrayFieldInfo)ee;
						ArrayInfo arrayInfo = arrayFieldInfo.arrayInfo;
						String type = getBaseType(arrayInfo.type);
						int size = getSize(type);
						if (arrayInfo.size != size) {
							logger.error("RECORD unexpected array element size");
							logger.error("  expect   {}", size);
							logger.error("  actual   {}", arrayInfo.size);
							logger.error("  record   {}", e);
							logger.error("  field    {}", ee);
							hasProblem = true;
						}
						if (arrayFieldInfo.size != (arrayInfo.size * arrayInfo.length)) {
							logger.error("RECORD unexpected size vs array");
							logger.error("  expect   {}", arrayInfo.size * arrayInfo.length);
							logger.error("  actual   {}", arrayFieldInfo.size);
							logger.error("  record   {}", e);
							logger.error("  field    {}", ee);
							hasProblem = true;
						}
					}
						break;
					default:
						throw new UnexpectedException("Unexpected");
					}
				}
			}
			{
				FieldInfo last = null;
				for(FieldInfo ee: e.fieldList) {
					if (last != null) {
						if (ee.offset == last.offset && ee.size == last.size) {
							if (ee.fieldType == FieldType.BIT) {
								//
							} else {
								logger.warn("RECORD duplicate offset and size");								
								logger.warn("  last       {}.{}", e.name, last.name);
								logger.warn("  record     {}.{}", e.name, ee.name);
							}
							continue;
						} else {
							if (ee.offset != (last.offset + last.size)) {
								if (ee.offset == 0) {
									// start union
									logger.warn("RECORD unexpected offset become zero");
									logger.warn("  record     {}.{}", e.name, ee.name);
								} else {
									logger.error("RECORD unexpected offset or size");
									logger.error("  last       {}", last);
									logger.error("  record     {}", e);
									logger.error("  field      {}", ee);
									hasProblem = true;
								}
							}
						}
					}
					last = ee;
				}
			}
			
			List<BitFieldInfo> bitFieldInfoList = new ArrayList<>();
			for(FieldInfo ee: e.fieldList) {
				if (ee.type.isEmpty()) {
					//
				} else {
					String type = getBaseType(ee.type);
					if (isPrimitive(type)) {
						// OK
					} else if (isRecord(type)) {
						// OK
					} else if (isType(type)) {
						// OK
					} else {
						logger.error("RECORD unknown type");
						logger.error("  record {}", e);
						logger.error("  field  {}", ee);
						hasProblem = true;
					}
				}
				if (ee.fieldType == FieldType.ARRAY) {
					ArrayFieldInfo arrayFieldInfo = (ArrayFieldInfo)ee;
					ArrayInfo arrayInfo = arrayFieldInfo.arrayInfo;
					if (arrayFieldInfo.isEmpty()) {
						//
					} else {
						{
							String type = getBaseType(arrayInfo.indexType);
							if (isPrimitive(type)) {
								// OK
							} else if (isRecord(type)) {
								// OK
							} else if (isType(type)) {
								// OK
							} else {
								logger.error("RECORD unknown array indexType");
								logger.error("  record {}", e);
								logger.error("  field  {}", ee);
								hasProblem = true;
							}
						}
						{
							String type = getBaseType(arrayInfo.type);
							if (isPrimitive(type)) {
								// OK
							} else if (isRecord(type)) {
								// OK
							} else if (isType(type)) {
								// OK
							} else {
								logger.error("RECORD unknown array type");
								logger.error("  record {}", e);
								logger.error("  field  {}", ee);
								hasProblem = true;
							}
						}
						{
							if (arrayInfo.size <= 0) {
								logger.error("RECORD array size is less than or equals to zero");
								logger.error("  record {}", e);
								logger.error("  field  {}", ee);
								hasProblem = true;
							}
						}
						{
							if (arrayInfo.length <= 0) {
								logger.error("RECORD array length is less than or equals to zero");
								logger.error("  record {}", e);
								logger.error("  field  {}", ee);
								hasProblem = true;
							}
						}
					}
				}
				if (ee.fieldType == FieldType.BIT) {
					BitFieldInfo bitFieldInfo = (BitFieldInfo)ee;
					BitInfo bitInfo = bitFieldInfo.bitInfo;
					// save for later sanity check
					bitFieldInfoList.add(bitFieldInfo);

					switch(bitFieldInfo.size) {
					case 1:
						if (0 <= bitInfo.startBit && bitInfo.startBit <= 15) {
							//
						} else {
							logger.error("RECORD bit startBit is out of bound");
							logger.error("  record {}", e);
							logger.error("  field  {}", ee);
							hasProblem = true;
						}
						if (0 <= bitInfo.stopBit && bitInfo.stopBit <= 15) {
							//
						} else {
							logger.error("RECORD bit stopBit is out of bound");
							logger.error("  record {}", e);
							logger.error("  field  {}", ee);
							hasProblem = true;
						}
						if (bitInfo.startBit <= bitInfo.stopBit) {
							//
						} else {
							logger.error("RECORD bit startBit is greather than stopBit");
							logger.error("  record {}", e);
							logger.error("  field  {}", ee);
							hasProblem = true;
						}
						break;
					case 2:
						if (0 <= bitInfo.startBit && bitInfo.startBit <= 31) {
							//
						} else {
							logger.error("RECORD bit startBit is out of bound");
							logger.error("  record {}", e);
							logger.error("  field  {}", ee);
							hasProblem = true;
						}
						if (0 <= bitInfo.stopBit && bitInfo.stopBit <= 31) {
							//
						} else {
							logger.error("RECORD bit stopBit is out of bound");
							logger.error("  record {}", e);
							logger.error("  field  {}", ee);
							hasProblem = true;
						}
						if (bitInfo.startBit <= bitInfo.stopBit) {
							//
						} else {
							logger.error("RECORD bit startBit is greather than stopBit");
							logger.error("  record {}", e);
							logger.error("  field  {}", ee);
							hasProblem = true;
						}
						break;
					default:
						throw new UnexpectedException("Unexpected");
					}
				}
			}
			
			if (!bitFieldInfoList.isEmpty()) {
				// check bit field
				Map<Integer, List<BitFieldInfo>> map = new TreeMap<>();
				for(BitFieldInfo ee: bitFieldInfoList) {
					List<BitFieldInfo> list;
					if (map.containsKey(ee.offset)) {
						list = map.get(ee.offset);
					} else {
						list = new ArrayList<>();
						map.put(ee.offset, list);
					}
					list.add(ee);
				}
				
				for(List<BitFieldInfo> ee: map.values()) {
					int size = ee.get(0).size;
					// check size
					{
						boolean hasInconsistent = false;
						for(BitFieldInfo eee: ee) {
							if (eee.size != size) hasInconsistent = true;
						}
						if (hasInconsistent) {
							logger.error("RECORD bit size is incosistent");
							logger.error("  size   {}", size);
							logger.error("  record {}", e);
							for(BitFieldInfo eee: ee) {
								logger.error("  field {}", eee);
							}
							hasProblem = true;
						}
					}
					// check bit pattern hole
					{
						switch(size) {
						case 1:
						case 2:
							break;
						default:
							throw new UnexpectedException("Unexpected");
						}
						boolean bitArray[] = new boolean[size * 16];
						for(int i = 0; i < bitArray.length; i++) bitArray[i] = false;
						
						for(BitFieldInfo eee: ee) {
							for(int i = eee.bitInfo.startBit; i <= eee.bitInfo.stopBit; i++) {
								bitArray[i] = true;
							}
						}
						boolean hasHole = false;
						for(int i = 0; i < bitArray.length; i++) {
							if (!bitArray[i]) hasHole = true;
						}
						if (hasHole) {
							StringBuilder pattern = new StringBuilder();
							for(int i = 0; i < bitArray.length; i++) {
								pattern.append(bitArray[i] ? "1" : "0");
							}
							logger.error("RECORD bit has hole");
							logger.error("  pattern {}", pattern);
							logger.error("  record  {}", e);
							for(BitFieldInfo eee: ee) {
								logger.error("  field {}", eee);
							}
							hasProblem = true;
						}
					}
				}
			}
		}
		
		{
			for(EnumInfo enumInfo: enumMap.values()) {
				Map<String, ElementInfo> nameMap = new TreeMap<>();
				for(ElementInfo e: enumInfo.elementList) {
					if (nameMap.containsKey(e.name)) {
						logger.error("ENUM duplicate element name");
						logger.error("  enum      {}", enumInfo);
						logger.error("  element   {}", e);
						logger.error("  duplicate {}", nameMap.get(e.name));
						hasProblem = true;
					} else {
						nameMap.put(e.name, e);
					}
				}
			}
		}
		
		if (hasProblem) throw new UnexpectedException("hasProblem");
	}
}
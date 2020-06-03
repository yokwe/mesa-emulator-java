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

public class TypeEnum extends Type {
	private static final Logger logger = LoggerFactory.getLogger(TypeEnum.class);

	public static class Element {
		public final String name;
		public final int    value;
		Element(String name, int value) {
			this.name  = name;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.format("{%s %d}", name, value);
		}
	}
	
	public  final List<Element> elementList;
	private final int           valueMin;
	private final int           valueMax;
	private final int           length;
	
	public TypeEnum(String name, List<Element> elementList) {
		super(name, Kind.ENUM, 1);
				
		int valueMin = elementList.stream().mapToInt(o -> o.value).min().getAsInt();
		int valueMax = elementList.stream().mapToInt(o -> o.value).max().getAsInt();
		int length = valueMax - valueMin + 1;
		// sanity check
		{
			if (length < 0) {
				logger.error("Unexpected length");
				logger.error("  valueMin {}", valueMin);
				logger.error("  valueMax {}", valueMax);
				logger.error("  length   {}", length);
				throw new UnexpectedException("Unexpected length");
			}
			if (Integer.MAX_VALUE <= length) {
				logger.error("Unexpected length");
				logger.error("  valueMin {}", valueMin);
				logger.error("  valueMax {}", valueMax);
				logger.error("  length   {}", length);
				throw new UnexpectedException("Unexpected length");
			}
		}
		
		this.elementList = elementList;
		this.valueMin    = valueMin;
		this.valueMax    = valueMax;
		this.length      = (int)length;
	}
	
	public long getValueMin() {
		return valueMin;
	}
	public long getValueMax() {
		return valueMax;
	}
	public long getLength() {
		return length;
	}

	public void checkValue(long rangeMax, long rangeMin) {
		if (rangeMin < this.valueMin) {
			logger.error("Unexpected rangeMin");
			logger.error("  rangeMin {}", rangeMin);
			logger.error("  valueMin {}", this.valueMin);
			throw new UnexpectedException("Unexpected rangeMin");
		}
		if (this.valueMax < rangeMax) {
			logger.error("Unexpected rangeMax");
			logger.error("  rangeMax {}", rangeMax);
			logger.error("  valueMax {}", this.valueMax);
			throw new UnexpectedException("Unexpected rangeMax");
		}
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %d %d %d %s}", name, kind, getSize(), valueMin, valueMax, elementList);
//		return String.format("{%s %s %d %s}", name, kind, size, elementList);
	}
	
	@Override
	protected void fix() {
	}
}
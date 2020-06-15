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
package yokwe.majuro.mesa.type;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;

public final class Enum {
    private static final Logger logger = LoggerFactory.getLogger(Enum.class);
    
    public static final class Builder {
    	private Map<Integer, String> map = new TreeMap<>();
    	
    	public Builder add(int value, String name) {
    		map.put(value, name);
    		return this;
    	}
    	
    	public Enum build() {
    		int max = map.keySet().stream().mapToInt(o -> o.intValue()).max().getAsInt();
    		
    		String[] names = new String[max + 1];
    		for(int i = 0; i < names.length; i++) names[i] = null;
    		
    		for(Map.Entry<Integer, String> entry: map.entrySet()) {
    			int key      = entry.getKey();
    			String value = entry.getValue();
    			names[key] = value;
    		}
    		
    		return new Enum(names);
    	}
    }
    
    public static Builder builder() {
    	return new Builder();
    }
    

	private final String[] names;
	
	public Enum(String[] newValue) {
		this.names = newValue;
	}
	
	public String toString(int value) {
    	if (0 <= value && value < names.length) {
    		if (names[value] != null) return names[value];
    	}
    	
        logger.error("value is out of range");
        logger.error("  value  {}", value);
        logger.error("  length {}", names.length);
        
        throw new UnexpectedException("value is out of range");
	}
	
	public void check(int value) {
        if (Debug.ENABLE_TYPE_RANGE_CHECK) {
        	if (0 <= value && value < names.length) {
        		if (names[value] != null) return;
        	}
        	
            logger.error("value is out of range");
            logger.error("  value  {}", value);
            logger.error("  length {}", names.length);
            
            throw new UnexpectedException("value is out of range");
        }
	}
}
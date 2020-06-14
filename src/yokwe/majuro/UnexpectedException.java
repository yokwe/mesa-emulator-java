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
package yokwe.majuro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.mesa.Debug;

@SuppressWarnings("serial")
public class UnexpectedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(UnexpectedException.class);

    public UnexpectedException() {
		super();
		
		logger.error("stack trace limits {} levels", Debug.STACK_TRACE_LIMIT);
        int count = 0;
        for(var e: getStackTrace()) {
        	if (Debug.STACK_TRACE_LIMIT <= count++) break;
        	logger.error("  {}", e);
        }
	}
	public UnexpectedException(String message) {
		super(message);
		
		logger.error("message {}", message);
		logger.error("stack trace limits {} levels", Debug.STACK_TRACE_LIMIT);
        int count = 0;
        for(var e: getStackTrace()) {
        	if (Debug.STACK_TRACE_LIMIT <= count++) break;
        	logger.error("  {}", e);
        }

	}
	public UnexpectedException(String message, Throwable throwable) {
		super(message, throwable);
		
		logger.error("message   {}", message);
		logger.error("throwable {}", throwable);
		logger.error("stack trace limits {} levels", Debug.STACK_TRACE_LIMIT);
        int count = 0;
        for(var e: getStackTrace()) {
        	if (Debug.STACK_TRACE_LIMIT <= count++) break;
        	logger.error("  {}", e);
        }
	}
}

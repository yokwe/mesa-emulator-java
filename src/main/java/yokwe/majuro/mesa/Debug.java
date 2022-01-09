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
package yokwe.majuro.mesa;

public final class Debug {
	// Procesor.AbortRuntimeException
	public static final boolean ENABLE_ABORT_STACK_TRACE = false;
		
	// Keyboard and Mouse
	public static final boolean SHOW_EVENT_KEY       = false;
	public static final boolean SHOW_EVENT_MOUSE     = false;

	// Network
	public static final boolean SHOW_NETWORK_PACKET  = false;
	public static final boolean TRACE_NETWORK_PACKET = false;

	// Debug emulator
	public static final boolean SHOW_DUMMY_IMPL_OPCODE = false;
	public static final boolean SHOW_RUNNING           = false;
	public static final boolean TRACE_OPCODE           = false;
	public static final boolean SHOW_OPCODE_STATS      = false;
	public static final boolean TRACE_XFER             = false;

	// Show Fault
	public static final boolean SHOW_FRAME_FAULT         = false;
	public static final boolean SHOW_PAGE_FAULT          = false;
	public static final boolean SHOW_WRITE_PROTECT_FAULT = true;

	// Show Trap
	public static final boolean SHOW_BOUNDS_TRAP     = false;
	public static final boolean SHOW_BREAK_TRAP      = false;
	public static final boolean SHOW_CODE_TRAP       = false;
	public static final boolean SHOW_CONTROL_TRAP    = false;
	public static final boolean SHOW_DIV_CHECK_TRAP  = true;
	public static final boolean SHOW_DIV_ZERO_TRAP   = true;
	public static final boolean SHOW_ESC_OPCODE_TRAP = false;
	public static final boolean SHOW_INTERRUPT_ERROR = true;
	public static final boolean SHOW_OPCODE_TRAP     = false;
	public static final boolean SHOW_POINTER_TRAP    = true;
	public static final boolean SHOW_PROCESS_TRAP    = true;
	public static final boolean SHOW_RESCHEDULE_TRAP = true;
	public static final boolean SHOW_STACK_ERROR     = true;
	public static final boolean SHOW_UNBOUND_TRAP    = true;
	public static final boolean SHOW_HARDWARE_ERROR  = true;
	public static final boolean SHOW_XFER_TRAP       = true;

	// Show Agent
	public static final boolean SHOW_AGENT_BEEP      = false;
	public static final boolean SHOW_AGENT_DISK      = false;
	public static final boolean SHOW_AGENT_DISPLAY   = false;
	public static final boolean SHOW_AGENT_FLOPPY    = false;
	public static final boolean SHOW_AGENT_MOUSE     = false;
	public static final boolean SHOW_AGENT_NETWORK   = false;
	public static final boolean SHOW_AGENT_PROCESSOR = false;
	public static final boolean SHOW_AGENT_STREAM    = false;

	// Stream
	public static final boolean SHOW_STREAM_BOOT     = true;
	public static final boolean SHOW_STREAM_COPYPASTE= false;
	public static final boolean SHOW_STREAM_PCFA     = false;
	public static final boolean SHOW_STREAM_TCP      = false;
	public static final boolean SHOW_STREAM_WWC      = false;

	// Stop emulator at
	public static final boolean STOP_AT_CONTROL_TRAP = true;
	public static final boolean STOP_AT_UNBOUND_TRAP = false;
	public static final boolean STOP_AT_OPCODE_TRAP  = false;
	public static final boolean STOP_AT_PAGE_FAULT   = false;
	public static final boolean STOP_AT_MP_915       = false;
	public static final boolean STOP_AT_STACK_ERROR  = true;
	public static final boolean STOP_AT_NOT_RUNNING  = false;

	public static final boolean STOP_MESSAGE_UNTIL_MP = false;

	// UnexpectedException
	public static final int     STACK_TRACE_LIMIT = 10;

	// Type
	public static final boolean ENABLE_TYPE_CHECK_VALUE = true;
}

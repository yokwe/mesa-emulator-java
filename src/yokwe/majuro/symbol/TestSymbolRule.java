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
package yokwe.majuro.symbol;

import java.io.IOException;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.symbol.antlr.SymbolLexer;
import yokwe.majuro.symbol.antlr.SymbolParser;
import yokwe.majuro.symbol.antlr.model.Symbol;

public class TestSymbolRule {
	private static final Logger logger = LoggerFactory.getLogger(TestSymbolRule.class);

	private static final boolean VERBOSE = false;

    private static void printPrettyLispTree(String tree) {
        int indentation = 1;
        StringBuilder sb = new StringBuilder();
        
        for (char c : tree.toCharArray()) {
            if (c == '(') {
                if (indentation > 1) {
                    logger.info("tree  {}", sb.toString());
                    sb.setLength(0);
                }
                for (int i = 0; i < indentation; i++) {
                    sb.append("  ");
                }
                indentation++;
            }
            else if (c == ')') {
                indentation--;
            }
            sb.append(c);
        }
        if (0 < sb.length()) {
            logger.info("tree  {}", sb.toString());
            sb.setLength(0);
        }
    }

	public static void main(String[] args) {
		logger.info("START");

		String path = Symbol.PATH_RULE_FILE;
		
		logger.info("path {}", path);
		try {
			CharStream input = CharStreams.fromFileName(path);
			
			SymbolLexer       lexer   = new SymbolLexer(input);
			CommonTokenStream tokens  = new CommonTokenStream(lexer);
			
			tokens.fill();
			if (VERBOSE) {
				for (Token t : tokens.getTokens()) {
					String symbolicName = SymbolLexer.VOCABULARY.getSymbolicName(t.getType());
		            String literalName  = SymbolLexer.VOCABULARY.getLiteralName(t.getType());
		            String line = String.format("  %-20s '%s'",
		                symbolicName == null ? literalName : symbolicName,
		                t.getText().replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t"));
					logger.info("token {}", line);
				}
			}
			
			SymbolParser parser = new SymbolParser(tokens);
//			parser.setTrace(true);
			parser.setErrorHandler(new BailErrorStrategy());
			
			if (VERBOSE) {
				ParseTree tree = parser.symbol();
				printPrettyLispTree(tree.toStringTree(parser));
			} else {
				parser.symbol();
			}
			
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
		}

		logger.info("STOP");
	}

}

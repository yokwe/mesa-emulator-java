open module yokwe.majuro {
    exports yokwe.majuro.study;

	// logging
	requires org.slf4j;
	
    // antlr
	requires org.antlr.antlr4.runtime;
	requires logback.classic;
	
	// junit
//	requires org.junit.jupiter.api;
}

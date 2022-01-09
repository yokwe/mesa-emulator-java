open module yokwe.majuro {
        exports yokwe.majuro;

        // http
        requires httpcore5;
        requires httpcore5.h2;
        
        // json
        requires transitive jakarta.json;
        requires jakarta.json.bind;
        
        // xml binding
        requires transitive java.xml;
        requires transitive jakarta.xml.bind;
        
        // logging
        requires org.slf4j;
        
        // yokwe-util
        requires transitive yokwe.util;
        
        // antlr
		requires org.antlr.antlr4.runtime;
}

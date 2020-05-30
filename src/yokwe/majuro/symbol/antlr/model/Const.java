package yokwe.majuro.symbol.antlr.model;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;

public class Const {
	private static final Logger logger = LoggerFactory.getLogger(Const.class);

	public String name;
	public String value;
	
	public Const(String name, String value) {
		this.name  = name;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s}", name, value);
	}
	
	public static Map<String, Const> map = new TreeMap<>();
	
	protected static void register(Const constant) {
		String name = constant.name;
		
		if (map.containsKey(name)) {
			Const old = map.get(name);
			
			logger.error("Duplicate type name");
			logger.error("  new {} {}", name, constant);
			logger.error("  old {} {}", old.name,  old);
			throw new UnexpectedException("Duplicate type name");
		} else {
			map.put(name, constant);
		}
	}

}

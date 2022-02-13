package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;

public final class ContextEnum implements CheckValue {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(java.lang.invoke.MethodHandles.lookup().lookupClass());

	public final String   name;
	public final int[]    validValues;
	public final String[] validNames;
	
	public ContextEnum(String name, int[] validValues, String[] validNames) {
		this.name        = name;
		this.validValues = validValues;
		this.validNames  = validNames;
	}
	
	public void check(int value) {
		toString(value);
	}
	public String toString(int value) {
		for(int i = 0; i < validValues.length; i++) {
			if (validValues[i] == value) return validNames[i];
		}
		logger.error("Unexpected");
		logger.error("  class {}", name);
		logger.error("  value {}", value);
		throw new UnexpectedException("Unexpected");
	}
}


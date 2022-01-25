package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;

public final class EnumContext implements CheckValue {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EnumContext.class);

	public final String   name;
	public final int[]    validValues;
	public final String[] validNames;
	
	public EnumContext(String name, int[] validValues, String[] validNames) {
		this.name        = name;
		this.validValues = validValues;
		this.validNames  = validNames;
	}
	
	public void check(long value) {
		toString(value);
	}
	public String toString(long value) {
		for(int i = 0; i < validValues.length; i++) {
			if (validValues[i] == value) return validNames[i];
		}
		logger.error("Unexpected");
		logger.error("  class {}", name);
		logger.error("  value {}", value);
		throw new UnexpectedException("Unexpected");
	}
}


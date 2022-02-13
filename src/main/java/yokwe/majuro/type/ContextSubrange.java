package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;

public final class ContextSubrange implements CheckValue {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(java.lang.invoke.MethodHandles.lookup().lookupClass());

	public final String name;
	public final int    minValue;
	public final int    maxValue;
	
	public ContextSubrange(String name, int minValue, int maxValue) {
		this.name     = name;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public void check(int value) {
		if (minValue <= value && value <= maxValue) return;
		logger.error("Unexpected");
		logger.error("  class     {}", name);
		logger.error("  value     {}", value);
		throw new UnexpectedException("Unexpected");
	}
}

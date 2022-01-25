package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;

public final class SubrangeContext implements CheckValue {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SubrangeContext.class);

	public final String name;
	public final long   minValue;
	public final long   maxValue;
	
	public SubrangeContext(String name, long minValue, long maxValue) {
		this.name     = name;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public void check(long value) {
		if (minValue <= value && value <= maxValue) return;
		logger.error("Unexpected");
		logger.error("  class     {}", name);
		logger.error("  value     {}", value);
		throw new UnexpectedException("Unexpected");
	}
}

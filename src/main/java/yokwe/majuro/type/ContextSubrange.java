package yokwe.majuro.type;

import yokwe.majuro.UnexpectedException;

public final class ContextSubrange implements CheckValue {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

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
		logger.error("  class     %s", name);
		logger.error("  value     %s", value);
		throw new UnexpectedException("Unexpected");
	}
}

package yokwe.majuro.symbol.model;

public class FieldName implements Comparable<FieldName> {
	public final boolean bitField;
	public final String  name;
	public final int	 offset;
	public final int     startPos;
	public final int	 stopPos;
	
	public FieldName(String name, int offset, int startPos, int stopPos) {
		this.bitField = true;
		this.name     = name;
		this.offset   = offset;
		this.startPos = startPos;
		this.stopPos  = stopPos;
	}
	public FieldName(String name, int offset) {
		this.bitField = false;
		this.name     = name;
		this.offset   = offset;
		this.startPos = -1;
		this.stopPos  = -1;
	}

	@Override
	public String toString() {
		if (bitField) {
			return String.format("%s (%d:%d..%d)", name, offset, startPos, stopPos);
		} else {
			return String.format("%s (%d)", name, offset);
		}
	}
	@Override
	public int compareTo(FieldName that) {
		int ret = this.offset - that.offset;
		if (ret == 0) ret = this.startPos - that.startPos;
		return ret;
	}
}

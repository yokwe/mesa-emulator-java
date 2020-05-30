package yokwe.majuro.symbol.antlr.model;

import java.util.Arrays;
import java.util.List;

public class TypeEnum extends Type {
	public static class Element {
		public final String name;
		public final int    value;
		Element(String name, int value) {
			this.name  = name;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.format("{%s %d}", name, value);
		}
	}
	
	public final List<Element> elementList;
	public final int valueMin;
	public final int valueMax;
	
	TypeEnum(String name, List<Element> elementList) {
		super(name, Kind.ENUM, 1);
		
		this.elementList = elementList;
		this.valueMin = elementList.stream().mapToInt(o -> o.value).min().getAsInt();
		this.valueMax = elementList.stream().mapToInt(o -> o.value).max().getAsInt();
	}
	
	@Override
	public String toString() {
		return String.format("{%s %s %d %d %d %s}", name, kind, size, valueMin, valueMax, elementList);
//		return String.format("{%s %s %d %s}", name, kind, size, elementList);
	}
	
	@Override
	protected void fix() {
		this.needsFix = false;
	}
}
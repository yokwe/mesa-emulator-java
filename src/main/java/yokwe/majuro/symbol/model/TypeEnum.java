package yokwe.majuro.symbol.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.util.StringUtil;

public class TypeEnum extends Type {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TypeEnum.class);

	public static class Item {
		public final String name;
		public final int    value;
		
		public Item(String name, String value) {
			this.name  = name;
			this.value = Integer.parseInt(value);
		}
		
		@Override
		public String toString() {
			return StringUtil.toString(this);
		}
	}
	
	public final List<Item> itemList;
	public final long minValue;
	public final long maxValue;
	public final long size;
	
	public TypeEnum(String name, List<Item> itemList) {
		super(name);
		this.itemList = itemList;
		
		{
			int min = itemList.get(0).value;
			int max = itemList.get(0).value;
			for(var e: itemList) {
				int value = e.value;
				if (value < min) min = value;
				if (max < value) max = value;
			}
			minValue = min;
			maxValue = max;
		}
		size    = maxValue - minValue + 1;
		bitSize = Type.bitSize(size - 1);
		needsFix = true;
		
		// sanity check
		{
			{
				if (minValue != 0) {
					logger.warn("Unexpected");
					logger.warn("  minValue != 0");
					logger.warn("  enum {}", this);
				}
			}

			boolean foundProblem = false;
			{
				if (size == 0) {
					logger.error("Unexpected");
					logger.error("  size == 0");
					foundProblem = true;
				}
			}
			{
				for(var e: itemList) {
					int value = e.value;
					if (0 <= value && value <= 0xFFFF) continue;
					logger.error("item value has out of range");
					logger.error("  item {}", e);
					foundProblem = true;
				}
			}
			{
				Map<String, Item> map = new TreeMap<>();
				for(var e: itemList) {
					if (map.containsKey(e.name)) {
						logger.error("item name has duplication");
						logger.error("  new  {}", e);
						logger.error("  old  {}", map.get(e.name));
						foundProblem = true;
					} else {
						map.put(e.name, e);
					}
				}
			}
			{
				Map<Integer, Item> map = new TreeMap<>();
				for(var e: itemList) {
					if (map.containsKey(e.value)) {
						logger.error("item value has duplication");
						logger.error("  new  {}", e);
						logger.error("  old  {}", map.get(e.value));
						foundProblem = true;
					} else {
						map.put(e.value, e);
					}
				}
			}
			
			if (foundProblem) {
				logger.error("found problem");
				logger.error("  enum {}", this);
				throw new UnexpectedException("found problem");
			}
		}
	}
	
	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	@Override
	public void fix() {
		needsFix = false;
	}
	
	public int getItemValue(String itemName) {
		for(var e: itemList) {
			if (e.name.equals(itemName)) return e.value;
		}
		logger.error("Unexpected");
		logger.error("  itemName {}", itemName);
		throw new UnexpectedException("Unexpected");
	}

	@Override
	public String toMesaType() {
		List<String> list = itemList.stream().map(o -> String.format("%s(%d)", o.name, o.value)).collect(Collectors.toList());
		return String.format("{%s}", String.join(", ", list));
	}

	@Override
	public boolean container() {
		return false;
	}
}

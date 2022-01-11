package yokwe.majuro.mesa;

public final class Perf {
	public static final boolean ENABLED = true;
	
	public static long fetchMemory = 0;
	public static long storeMemory = 0;
	
	public static long pageCacheHit          = 0;
	public static long pageCacheMissConflict = 0;
	public static long pageCacheMissEmpty    = 0;

	public static long fetch       = 0;
	public static long store       = 0;
	public static long readDbl     = 0;
}

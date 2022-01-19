package yokwe.majuro.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel.MapMode;

public class T104 {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(T104.class);

	public static void main(String[] args) {
		logger.info("START");
		
		logger.info("native byte order {}", ByteOrder.nativeOrder().toString());
		
		File disk = new File("data/GVWin21/GVWIN21.DSK.BE");
		logger.info("path   {}", disk.getPath());
		logger.info("exists {}", disk.exists());
		long length = disk.length();
		logger.info("size   {}", length);
		
		try (var raf = new RandomAccessFile(disk, "r")) {
			MappedByteBuffer mbb = raf.getChannel().map(MapMode.READ_ONLY, 0, length);
//			mbb.order(ByteOrder.LITTLE_ENDIAN);
			
			ShortBuffer sb = mbb.asShortBuffer();
			logger.info("sb {}", sb.order());
			
			logger.info("mbb hasArray {}", mbb.hasArray());

			
			int b0 = mbb.get(0) & 0xFF;
			int b1 = mbb.get(1) & 0xFF;
			int s0 = sb.get(0) & 0xFFFF;
			
			int t0 = b0 << 8 | b1;
			int t1 = b1 << 8 | b0;
			
			logger.info("byte {}", String.format("%02X%02X", b0, b1));
			logger.info("word {}", String.format("%04X", s0));
			logger.info("word {}", String.format("%o", s0));
			
			logger.info("word {}", String.format("%o", t0));
			logger.info("word {}", String.format("%o", t1));

			
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.warn("{} {}", exceptionName, e);
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.warn("{} {}", exceptionName, e);
		}
		
		logger.info("STOP");
	}
}

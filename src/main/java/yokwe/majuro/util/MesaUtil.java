package yokwe.majuro.util;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import yokwe.majuro.UnexpectedException;

public final class MesaUtil {
	private static final FormatLogger logger = FormatLogger.getLogger();

	public static final int getMicroTime() {
		var now = Instant.now();
		long epochSecond = now.getEpochSecond();
		long nano = now.getNano();
		long time = epochSecond * 1_000_000 + (nano / 1_000);
		return (int)time;
	}
	public static final void msleep(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	public static final int getUnixTime() {
		var now = Instant.now();
		return (int)now.getEpochSecond();
	}
	
	//From System.mesa
	//-- Time of day
	//
	//GreenwichMeanTime: TYPE = RECORD [LONG CARDINAL];
	//-- A greenwich mean time t represents the time which is t-gmtEpoch seconds after
	//-- midnight, 1 January 1968, the time chosen as the epoch or beginning of the Pilot
	//-- time standard.  Within the range in which they overlap, the Alto and Pilot time
	//-- standards assign identical bit patterns, but the Pilot standard runs an additional
	//-- 67 years before overflowing.
	//-- Greenwich mean times should be compared directly only for equality; to find which of
	//-- two gmt's comes first, apply SecondsSinceEpoch to each and compare the result.  If t2
	//-- is a gmt known to occur after t1, then t2-t1 is the seconds between t1 and t2.  If t
	//-- is a gmt, then System.GreenwichMeanTime[t+60] is the gmt one minute after t.
	//gmtEpoch: GreenwichMeanTime = [2114294400];
	//-- = (67 years * 365 days + 16 leap days) * 24 hours * 60 minutes * 60 seconds
	//GetGreenwichMeanTime: PROCEDURE RETURNS [gmt: GreenwichMeanTime];

	// Unix Time Epoch  1970-01-01 00:00:00
	// Mesa Time Epoch  1968-01-01 00:00:00
	//   Difference between above 2 date is 731 days.
	private static final int EPOCH_DIFF = (int)2114294400 + (int)(731 * 60 * 60 * 24);

	public static final int toMesaTime(int unixTime) {
		return unixTime + EPOCH_DIFF;
	}
	public static final int toUnixTime(int mesaTime) {
		return mesaTime - EPOCH_DIFF;
	}

	private static final Map<Integer, String> mpcodeMessageMap = new HashMap<>();
	static {
		mpcodeMessageMap.put(900, "MP  900 cGerm");                      // Germ entered
		mpcodeMessageMap.put(910, "MP  910 cGermAction");                // Germ action running (e.g. inLoad, outLoad)
		mpcodeMessageMap.put(911, "MP  911 cGermBadPhysicalVolume");     // Germ and physical volume have incompatible version numbers
		mpcodeMessageMap.put(912, "MP  912 cGermBadBootFileVersion");    // Germ and boot file have incompatible version numbers
		mpcodeMessageMap.put(917, "MP  917 cRespondingToEtherDebugger"); // talking to ethernet debugger
		mpcodeMessageMap.put(915, "MP  915 cWaitingForEtherDebugger");   // waiting for ethernet debugger to begin debugging me
		mpcodeMessageMap.put(919, "MP  919 cGermFinished");              // Germ transferred control back to caller (who has hung)
		mpcodeMessageMap.put(920, "MP  920 cGermDriver");                // Germ driver running (e.g. disk, ether, floppy)
		mpcodeMessageMap.put(921, "MP  921 cGermDeviceError");           // hard error on device being booted
		mpcodeMessageMap.put(922, "MP  922 cGermTimeout");               // operation on boot device no completed in expected time
		mpcodeMessageMap.put(924, "MP  924 cGermNoServer");              // no response to Germ's request for ether boot file
		mpcodeMessageMap.put(925, "MP  925 cGermFunnyPacket");           // e.g. unexpected sequence number or size
		mpcodeMessageMap.put(927, "MP  927 cGermShortBootFile");         // boot file ends before it should (try reinstalling)
		mpcodeMessageMap.put(928, "MP  928 cWaitingForBootServer");      // waiting for any boot server to respond
		mpcodeMessageMap.put(930, "MP  930 cControl");                   // Pilot Control and MesaRuntime components being initialized
		mpcodeMessageMap.put(937, "MP  937 cTimeNotAvailable");          // trying to get the time from either hardware clock or ethernet
		mpcodeMessageMap.put(938, "MP  938 cCleanup");                   // running cleanup procedures, e.g. before going to debugger
		mpcodeMessageMap.put(939, "MP  939 cPowerOff");                  // ProcessorFace.PowerOff called but no power control relay
		mpcodeMessageMap.put(940, "MP  940 cStorage");                   // Pilot Store component being initialized
		mpcodeMessageMap.put(960, "MP  960 cDeleteTemps");               // temporary files from previous run being deleted
		mpcodeMessageMap.put(970, "MP  970 cMap");                       // client and other non-bootloaded code being mapped
		mpcodeMessageMap.put(980, "MP  980 cCommunication");             // Pilot Communication component being initialized
		mpcodeMessageMap.put(990, "MP  990 cClient");                    // PilotClient.Run called
	}
	public static final String getMPCodeMessage(int mpcode) {
		if (mpcodeMessageMap.containsKey(mpcode)) {
			return mpcodeMessageMap.get(mpcode);
		} else {
			return String.format("MP %04d", mpcode);
		}
	}

}

import java.io.File;


public class MemoryDump {

	public static void main(String[] args) {
		dumpMemory();
	}

	/**
	 * dumpMemory ()
	 * Dumps memory 
	 */
	public static void dumpMemory ()
	{
		try {
			String sJdkBinPath = System.getProperty("java.library.path");
			sJdkBinPath = sJdkBinPath.substring(0, sJdkBinPath.indexOf(";"));
			String sDumpFilename = "C:/temp/" + "memdumpfile_"
					+ System.currentTimeMillis() + ".bin";
			String jvmName = java.lang.management.ManagementFactory
					.getRuntimeMXBean().getName();
			String pid = "";
			final int index = jvmName.indexOf('@');
			pid = Long.toString(Long.parseLong(jvmName.substring(0, index)));
			File dumpFile = new File(sDumpFilename);

			if (dumpFile.exists()) {
				dumpFile.delete();
			}

			Process p = Runtime.getRuntime().exec(
					sJdkBinPath + "\\jmap -dump:live,format=b,file="
							+ sDumpFilename + " " + pid);
			System.out.println("Created memory dump file -> " + sDumpFilename);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

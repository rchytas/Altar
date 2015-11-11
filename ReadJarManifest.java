import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ReadJarManifest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JarFile jarfile = new JarFile(
					"C:/Program Files/Documentum/Shared/dfc.jar");

			Manifest manifest = jarfile.getManifest();

			// Iterate over the manifest's entries looking for what you need.
			// Check out the Manifest class API.
			Attributes attributes = manifest.getMainAttributes();			
	        String impVersion = attributes.getValue("Created-By");
	        System.out.println(attributes.keySet());
	        System.out.println(impVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

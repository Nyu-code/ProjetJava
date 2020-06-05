import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltreExtensionFichier extends FileFilter{

	   public boolean accept(File f) {
		   if(f.isDirectory()|| f.getName().endsWith(".ser")) {
			   return true;
		   }
		   return false;
	   }
	   public String getDescription() {
		   return "Fichiers .ser";
	   }
}

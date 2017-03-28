// Importation des paquets n�cessaires. Le plugin n'est pas lui-m�me un paquet (pas de mot-cl� package)
import ij.*; 							// pour classes ImagePlus et IJ
import ij.plugin.filter.PlugInFilter; 	// pour interface PlugInFilter
import ij.process.*; 					// pour classe ImageProcessor

public class Color_Counter implements PlugInFilter {
	ImagePlus imp;
	int colors;
	static int MAX_COLORS = 256*256*256;
	int[] counts = new int[MAX_COLORS]; 		// Histogramme des couleurs

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp; 						// Rem.: pas utilis� dans run()
		return DOES_RGB+NO_UNDO+NO_CHANGES; 	// Traite les images couleurs ; pas d'annulation possible
	}
	
	public void run(ImageProcessor ip) {
		int[] pixels = (int[])ip.getPixels(); //r�cup�ration des pixels de l'image dans le tableau pixels
		
		for (int i=0; i<pixels.length; i++)
			counts[pixels[i]&0xffffff]++; // Masquage n�cessaire car pixels[i] est sign�
			
		for (int i=0; i<MAX_COLORS; i++) { // Comptage des couleurs diff�rentes
			if (counts[i]>0) colors++;
		}
		IJ.log("Couleurs diff�rentes : "+colors);
		if (colors<=64) { // Affichage des couleurs trouv�es dans fen�tre Log (si elles sont moins de 64)
			IJ.log("Counts");
			for (int i=0; i<MAX_COLORS; i++) {
				if (counts[i]>0) IJ.log(" "+Integer.toHexString(i)+": "+counts[i]);
			}
		}
	}
}
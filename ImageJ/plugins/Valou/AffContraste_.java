import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class AffContraste_ implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {
		
		double moyenne = 0 ;
		int tot = 0 ;
		
		//Rectangle r = ip.getRoi();
		
		for (int x=0; x<ip.getHeight(); x++)
			for (int y=0; y<ip.getWidth(); y++){	
				moyenne = moyenne + (ip.getPixel(y,x)& 0xff);
				tot++;
				}
				
		moyenne = moyenne/tot;	
		IJ.log("moyenne : "+moyenne);		
		
		
		double res = moyenne;
		
		for (int y=0; y<ip.getHeight(); y++)
			for (int x=0; x<ip.getWidth(); x++){
				double tmp = (ip.getPixel(x,y)& 0xff)-moyenne;
				res =res+ tmp*tmp;
				}
		
		res = (1./(tot))*res;
		res = Math.sqrt(res);
		IJ.log("ecart type : "+res);
	//*/
	}

}


import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class Sobel_ implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {
		
		double [] pixel = new double[256];
		double tot = 0 ;
		
		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				//pixel[ip.getPixel(y,x)& 0xff]++;
				//tot++;
				if(y>1){
				double pix = ip.getPixel(y,x)& 0xff;
				double pix2 = ip.getPixel(y-1,x)& 0xff;
				int res = (int)(pix -pix2);
				ip.set(x,y,res);
				}
			}
		}
		
			
		/*
		IJ.log("egalization");
	//*/
	}

}


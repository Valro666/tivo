import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class Flip_ implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {
		
		double [] pixel = new double[256];
		double tot = 0 ;
		double max = ip.getWidth();
		
		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				pixel[ip.getPixel(y,x)& 0xff]++;
				tot++;
			}
		}
		
		

		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth()/2;y++){
				int pos = (int)(max-y)-1;
				int tmp = (int)(ip.getPixel(pos,x)& 0xff);
				ip.set(pos,x,ip.getPixel(y,x)& 0xff);
				ip.set(y,x,tmp);
			}
		}		
		/*
		IJ.log("egalization");
	//*/
	}

}


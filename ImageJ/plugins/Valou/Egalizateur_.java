import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class Egalizateur_ implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {
		
		double [] pixel = new double[256];
		double tot = 0 ;
		
		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				pixel[ip.getPixel(y,x)& 0xff]++;
				tot++;
			}
		}
		
		double[] r = new double[256];
		
		for(int i =0;i<pixel.length;i++){
			pixel[i]=pixel[i]/tot;
		}
		
		for(int i =0;i<r.length;i++){
			for(int j =0;j<i;j++){
				r[i]=r[j]+pixel[j];
			}
		}

		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				ip.set(y,x,(int)(255*r[(ip.getPixel(y,x)& 0xff)]));
			}
		}		
		/*
		IJ.log("egalization");
	//*/
	}

}


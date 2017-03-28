import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class etirage_ implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {
		
		 int ng =0,i=0,j=0;
		
		 int[] LUT= new int[256];
		
		 int min = ip.getPixel(0,0) & 0xff,max = ip.getPixel(0,0) & 0xff;
		
		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				
				if(ip.getPixel(x,y)<min){
					min = ip.getPixel(x,y)& 0xff;
				}
				if(ip.getPixel(x,y)>max){
					max = ip.getPixel(x,y)& 0xff;
				}
			}
		}
		
		IJ.log("min : "+min);
		IJ.log("max : "+max);
		
		
		for(ng = 0;ng<256;ng++){
			LUT[ng] = (255*(ng-min))/(max-min);
		}
		
		ip.applyTable(LUT);
		
		/*
		// Initialisation de la LUT
		for(ng = 0; ng < 256; ng++)
			LUT[ng] = 255 (ng – min) / (max – min)
		// Calcul de la transformation
		for(i = 0; i < L; i++)
			for(j = 0; j < H; j++)
				I’ (i, j) = LUT[I (i, j)]
		//*/
	}

}


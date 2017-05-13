import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class Norme_Gradient implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {

		double [] pixel = new double[256];
		int [][] image = new int[ ip.getWidth()][ ip.getHeight()];
		double tot = 0 ;

		double [][] sy ={{-1,0,1},{-2,0,2},{-1,0,1}};
		double [][] sx ={{-1,-2,-1},{0,0,0},{1,2,1}};

		//double [][] sy ={{-1,0,1},{-2,0,2},{-1,0,1}};
		//double [][] sx ={{-1,-2,-1},{0,0,0},{1,2,1}};

		for(int x = 1; x < ip.getHeight()-1;x++){
			for(int y = 1; y < ip.getWidth()-1;y++){
				//pixel[ip.getPixel(y,yy)& 0xff]++;

				double a = 0;
				double b = 0;
				double c = 0;
				double d = 0;
				double e = 0;
				double f = 0;
				double g = 0;
				double h = 0;
				double i = 0;

				double j = 0;
				double k = 0;
				double l = 0;
				double m = 0;
				double n = 0;
				double o = 0;
				double p = 0;
				double q = 0;
				double r = 0;

				 a = (ip.getPixel(x-1,y-1) )*sx[0][0];
				 b = (ip.getPixel(x-1,y))*sx[0][1];
				 c = (ip.getPixel(x-1,y+1))*sx[0][2];
				 d = (ip.getPixel(x,y-1))*sx[1][0];
				 e = (ip.getPixel(x,y))*sx[1][1];
				 f = (ip.getPixel(x,y+1))*sx[1][2];
				 g = (ip.getPixel(x+1,y-1))*sx[2][0];
				 h = (ip.getPixel(x+1,y))*sx[2][1];
				 i = (ip.getPixel(x+1,y+1))*sx[2][2];

				 j = (ip.getPixel(x-1,y-1))*sy[0][0];
				 k = (ip.getPixel(x-1,y))*sy[0][1];
				 l = (ip.getPixel(x-1,y+1))*sy[0][2];
				 m = (ip.getPixel(x,y-1))*sy[1][0];
				 n = (ip.getPixel(x,y))*sy[1][1];
				 o = (ip.getPixel(x,y+1))*sy[1][2];
				 p = (ip.getPixel(x+1,y-1))*sy[2][0];
				 q = (ip.getPixel(x+1,y))*sy[2][1];
				 r = (ip.getPixel(x+1,y+1))*sy[2][2];

				double ssx = a+b+c+d+e+f+g+h+i;
				double ssy = j+k+l+m+n+o+p+q+r;


				if(ssx>255){
					ssx=255;
				}
				if(ssx<60){
					ssx=0;
				}

				if(ssy>255){
					ssy=255;
				}
				if(ssy<60){
					ssy=0;
				}


				ssx = ssx*ssx;
				ssy = ssy*ssy;

				double tmp = (ssx+ssy);

				tmp = Math.abs(Math.sqrt(tmp));

				if(tmp>255){
					tmp = 255;
				}

				/*
				if(tmp>50){
					tmp = 255 ;
				}else{
					tmp = 0;
				}*/

				image[x][y] = (int)tmp ;
				//image[x][y] = (int)ssy ;
				//ip.set(x,y,(int)pix);
				tot++;
			}
		}

		for(int y = 1; y < ip.getHeight()-1;y++){
			for(int x = 1; x < ip.getWidth()-1;x++){


				ip.set(x,y,image[x][y]);

			}
		}


	}

}


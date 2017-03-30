import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class Egalizateur_Couleur implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {



		double [][] convert = new double[ip.getWidth()][ip.getHeight()];
		double tot = 0 ;		
		
		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				int[] pix = new int[3];
				ip.getPixel(y,x,pix);
				int rouge  = (int)(pix[0]);
				int vert  = (int)(pix[1]);
				int bleu = (int)(pix[2]);
				
				convert[y][x] = (rouge*0.3)+(vert*0.59)+(bleu*0.11);
				
				/*
				convert[y][x]=(int)(((ip.getPixel(y,x)& 0xff0000)>>16)*0.3)
				+(int)((ip.getPixel(y,x)& 0x0000ff)*0.11)
				+(int)(((ip.getPixel(y,x)& 0x00ff00)>>8)*0.59);
				/*///convert[y][x]=pix;
				tot++;
			}
		}
		
		double [] pixel = new double[256];

		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				//if(((int [])convert[y][x])[0]!=null){
					pixel[((int)convert[y][x])]++;	
				//}
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
		
	/*	for(int o = 0 ; o < convert.length;o++){
			for(int p = 0 ; p < convert[o].length;p++){
				for(int m : (int[])convert[o][p]){
					m*=255;
				}
			}
		}
//*/
		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				//(int [])convert[y][x]
				int[] pix = new int[3];
				ip.getPixel(y,x,pix);
				int rouge  = (int)(pix[0]);
				int vert  = (int)(pix[1]);
				int bleu = (int)(pix[2]);
				
				rouge = (int)(255*r[rouge]);
				vert  = (int)(255*r[vert]);
				bleu  = (int)(255*r[bleu]);
				
				pix[0]=rouge;// & 0xff;
				pix[1]=vert;//& 0xff;
				pix[2]=bleu;//& 0xff;
				int[] pix2 = {rouge,vert,bleu};
				ip.set(y,x,0);
				ip.putPixel(y,x,pix2);
			}
		}		
		//*/
	}
}


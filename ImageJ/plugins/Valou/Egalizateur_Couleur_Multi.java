import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.awt.*;

public class Egalizateur_Couleur_Multi implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
		return DOES_ALL+DOES_STACKS+SUPPORTS_MASKING;
	}

	public void run(ImageProcessor ip) {



		double [] pixel = new double[256];
		double [] rouge = new double[256];
		double [] vert = new double[256];
		double [] bleu = new double[256];
		double tot = 0 ;
		
		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				int [] tmp = new int[3];
				
				ip.getPixel(y,x,tmp);
				pixel[ip.getPixel(y,x)& 0xff]++;
			
				rouge[tmp[0]]++;
				vert[tmp[1]]++;
				bleu[tmp[2]]++;
				
				tot++;
			}
		}
		
		double[] r  = new double[256];
		double[] rR = new double[256];
		double[] rV = new double[256];
		double[] rB = new double[256];
		
		for(int i =0;i<pixel.length;i++){
			pixel[i]=pixel[i]/tot;
			rouge[i]=rouge[i]/tot;
			vert[i]=vert[i]/tot;
			bleu[i]=bleu[i]/tot;
		}
		
		for(int i =0;i<r.length;i++){
			for(int j =0;j<i;j++){
				r[i]=r[j]+pixel[j];
				rR[i]=r[j]+rouge[j];
				rB[i]=r[j]+vert[j];
				rV[i]=r[j]+bleu[j];
				
			}
		}

		for(int x = 0; x < ip.getHeight();x++){
			for(int y = 0; y < ip.getWidth();y++){
				
				int[] pix = new int[3];
				ip.getPixel(y,x,pix);
				int rr  = (int)(pix[0]);
				int v  = (int)(pix[1]);
				int b = (int)(pix[2]);
				
				rr = (int)(255*rR[rr]);
				v  = (int)(255*rV[v]);
				b  = (int)(255*rB[b]);
				
				pix[0]=rr;// & 0xff;
				pix[1]=v;//& 0xff;
				pix[2]=b;//& 0xff;
				int[] pix2 = {rr,v,b};
				ip.putPixel(y,x,pix2);
			}
		}	
}}


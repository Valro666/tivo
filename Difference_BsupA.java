import ij .*;
import ij. process .*;
import ij.gui .*;
import java .awt .*;
import ij. plugin . frame .*;

public class Difference_BsupA extends PlugInFrame {
	public Difference_BsupA (){
		super ( "Soustraction entre images ");
	}

	public void run ( String arg) {
		ImagePlus imgDiffA = new ImagePlus ("imA.pgm");
		ImageProcessor ipDiffA = imgDiffA . getProcessor ();
		byte [] pixelsDiffA = ( byte []) ipDiffA.getPixels ();
	
		ImagePlus imgDiffB = new ImagePlus ("imB.pgm");
		ImageProcessor ipDiffB = imgDiffB . getProcessor ();
		byte [] pixelsDiffB = ( byte []) ipDiffB.getPixels ();

		int w = ipDiffB.getWidth ();
		int h = ipDiffB.getHeight ();

		ImageProcessor ipRes = new ByteProcessor (w,h);
		ImagePlus imgRes = new ImagePlus (" BsupA ",ipRes );
		
		byte [] pixelsRes = ( byte []) ipRes.getPixels ();
		
		for(int y = 0 ; y < ipDiffA.getHeight();y++){
			for(int x = 0 ; x < ipDiffA.getWidth();x++){
				int a = ipDiffA.getPixel(x,y);
				int b = ipDiffB.getPixel(x,y);
				int c = a-b;
				if(a!=b){
					if(b>a){
					ipRes.set(x,y,b);	
					}else{
						
					ipRes.set(x,y,a);	
					}
				}else{
					ipRes.set(x,y,0);
				}
				
			}
			
		}
		
		imgRes.show ();
		imgRes.updateAndDraw ();
	}	
}	
		
		
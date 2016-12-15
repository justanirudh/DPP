//package cop5618;
package concurrent_programming.hw3;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;

public class FJBufferedImage extends BufferedImage {

	private class GetRGBTask extends RecursiveAction{

		final int xStart;
		final int yStart;
		final int w;
		final int h;
		final int[] rgbArray;
		final int offset;
		final int scansize;
		final int startIndex;

		public GetRGBTask(int xStart, int yStart, int w, int h, int[] rgbArray, int offset, int scansize, int startIndex){
			this.xStart = xStart;
			this.yStart = yStart;
			this.w = w;
			this.h = h;
			this.rgbArray = rgbArray;
			this.offset = offset;
			this.scansize = scansize;
			this.startIndex = startIndex;
		}

		@Override
		protected void compute(){
			if(h == 1){ //base case; 1 row
				int[] temp = new int[w];
				temp = FJBufferedImage.super.getRGB(xStart, yStart, w, h, temp, offset, scansize);
				System.arraycopy(temp, 0, rgbArray, startIndex, w);
				return;
			}
			int split = h/2;
			GetRGBTask up = new GetRGBTask(xStart, yStart, w, split, rgbArray, offset, scansize, startIndex);
			int splitdown;
			if(h % 2 == 0)
				splitdown = split;
			else //if the number of rows are odd
				splitdown = split + 1;
			GetRGBTask down = new GetRGBTask(xStart, yStart + split, w, splitdown, rgbArray, offset, scansize, startIndex + (w * split));
			invokeAll(up, down);
		}
	}

	private class SetRGBTask extends RecursiveAction{

		final int xStart;
		final int yStart;
		final int w;
		final int h;
		final int[] rgbArray;
		final int offset;
		final int scansize;
		final int startIndex;

		public SetRGBTask(int xStart, int yStart, int w, int h, int[] rgbArray, int offset, int scansize, int startIndex){
			this.xStart = xStart;
			this.yStart = yStart;
			this.w = w;
			this.h = h;
			this.rgbArray = rgbArray;
			this.offset = offset;
			this.scansize = scansize;
			this.startIndex = startIndex;
		}

		@Override
		protected void compute(){
			if(h == 1){ //base case; 1 row
				int[] temp = Arrays.copyOfRange(rgbArray, startIndex, startIndex + w);
				FJBufferedImage.super.setRGB(xStart, yStart, w, h, temp, offset, scansize);
				return;
			}
			int split = h/2;
			SetRGBTask up = new SetRGBTask(xStart, yStart, w, split, rgbArray, offset, scansize, startIndex);
			int splitdown;
			if(h % 2 == 0)
				splitdown = split;
			else //if the number of rows are odd
				splitdown = split + 1;
			SetRGBTask down = new SetRGBTask(xStart, yStart + split, w, splitdown, rgbArray, offset, scansize, startIndex + (w * split));
			invokeAll(up, down);
		}
	}


	/**Constructors*/

	public FJBufferedImage(int width, int height, int imageType) {
		super(width, height, imageType);
	}

	public FJBufferedImage(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
	}

	public FJBufferedImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied,
						   Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
	}


	/**
	 * Creates a new FJBufferedImage with the same fields as source.
	 * @param source
	 * @return
	 */
	public static FJBufferedImage BufferedImageToFJBufferedImage(BufferedImage source){
		Hashtable<String,Object> properties=null;
		String[] propertyNames = source.getPropertyNames();
		if (propertyNames != null) {
			properties = new Hashtable<String,Object>();
			for (String name: propertyNames){properties.put(name, source.getProperty(name));}
		}
		return new FJBufferedImage(source.getColorModel(), source.getRaster(), source.isAlphaPremultiplied(), properties);
	}

	@Override
	public void setRGB(int xStart, int yStart, int w, int h, int[] rgbArray, int offset, int scansize){
		SetRGBTask set = new SetRGBTask(xStart, yStart, w, h, rgbArray, offset, scansize, 0);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(set);
	}

	@Override
	public int[] getRGB(int xStart, int yStart, int w, int h, int[] rgbArray, int offset, int scansize){
		GetRGBTask get = new GetRGBTask(xStart, yStart, w, h, rgbArray, offset, scansize, 0);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(get);
		return rgbArray;
	}

}

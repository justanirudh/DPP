package cop5618;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ColorHistEq {

	//Use these labels to instantiate you timers.  You will need 8 invocations of now()
	static String[] labels = { "getRGB", "convert to HSB", "create brightness map", "parallel prefix",
			"probability array", "equalize pixels", "setRGB" };

	private static class HSB {
		float hue;
		float saturation;
		float brightness;
		HSB(float hue, float saturation, float brightness){
			this.hue = hue;
			this.saturation = saturation;
			this.brightness = brightness;
		}
	}

	static Timer colorHistEq_serial(BufferedImage image, BufferedImage newImage) {
		Timer time = new Timer(labels);
		ColorModel colorModel = ColorModel.getRGBdefault();
		int w = image.getWidth();
		int h = image.getHeight();
		int imageSize = w * h;
		time.now();
		//1
		int[] sourcePixelArray = image.getRGB(0, 0, w, h, new int[imageSize], 0, w);
		time.now();
		//2
		HSB[] hsbSourcePixelArray =
				Arrays.stream(sourcePixelArray)
						.mapToObj(pixel -> {
							float[] arr = Color.RGBtoHSB(
									colorModel.getRed(pixel),
									colorModel.getGreen(pixel),
									colorModel.getBlue(pixel), null);
							return new HSB(arr[0],arr[1],arr[2]);
						})
						.toArray(HSB[]::new);

		time.now();
		//3
		Map<Integer,Long> binNumberToNumElems =
				Arrays.stream(hsbSourcePixelArray)
						.mapToDouble(b -> b.brightness)
						.boxed()
						.collect(Collectors.groupingBy(v->(int)(v.doubleValue()*256.0), Collectors.counting()));
		time.now();
		//4
		List<Integer> sortedKeys = new ArrayList<Integer>(binNumberToNumElems.keySet());
		Collections.sort(sortedKeys);
		double[] elementsInEachBin = new double[sortedKeys.size()];
		for (int i = 0; i < sortedKeys.size(); ++i) {
			elementsInEachBin[i] = (double)binNumberToNumElems.get(i);
		}
		Arrays.parallelPrefix(elementsInEachBin, (x, y) -> (x + y));
		time.now();
		//5
		double[] cumulativeProbs = new double[elementsInEachBin.length];
		for(int i = 0; i < elementsInEachBin.length; ++i){
			cumulativeProbs[i] = elementsInEachBin[i]/imageSize;
		}
		time.now();
		//6
		int[] newSourcePixelArray =
				Arrays.stream(hsbSourcePixelArray)
						.mapToInt(pixel ->
								Color.HSBtoRGB(
										pixel.hue,
										pixel.saturation,
										(float)cumulativeProbs[(int) (pixel.brightness * 256.0)]))
						.toArray();

		time.now();
		//7
		newImage.setRGB(0, 0, w, h, newSourcePixelArray, 0, w);
		time.now();

		return time;
	}

	//Note: The question does not explicitly tells you to use parallel streams for this function
	//but I am assuming we are supposed to. Hence, converting the streams to parallel streams
	//Speed up obtained: 1.1
	static Timer colorHistEq_parallel(FJBufferedImage image, FJBufferedImage newImage) {
		Timer time = new Timer(labels);
		ColorModel colorModel = ColorModel.getRGBdefault();
		int w = image.getWidth();
		int h = image.getHeight();
		int imageSize = w * h;
		time.now();
		//1
		int[] sourcePixelArray = image.getRGB(0, 0, w, h, new int[imageSize], 0, w);
		time.now();
		//2
		HSB[] hsbSourcePixelArray =
				Arrays.stream(sourcePixelArray)
						.parallel()
						.mapToObj(pixel -> {
							float[] arr = Color.RGBtoHSB(
									colorModel.getRed(pixel),
									colorModel.getGreen(pixel),
									colorModel.getBlue(pixel), null);
							return new HSB(arr[0],arr[1],arr[2]);
						})
						.toArray(HSB[]::new);
		time.now();
		//3
		Map<Integer,Long> binNumberToNumElems =
				Arrays.stream(hsbSourcePixelArray)
						.parallel()
						.mapToDouble(b -> b.brightness)
						.boxed()
						.collect(Collectors.groupingBy(v->(int)(v.doubleValue()*256.0), Collectors.counting()));
		time.now();
		//4
		List<Integer> sortedKeys = new ArrayList<Integer>(binNumberToNumElems.keySet());
		Collections.sort(sortedKeys);
		double[] elementsInEachBin = new double[sortedKeys.size()];
		for (int i = 0; i < sortedKeys.size(); ++i) {
			elementsInEachBin[i] = (double)binNumberToNumElems.get(i);
		}
		Arrays.parallelPrefix(elementsInEachBin, (x, y) -> (x + y));
		time.now();
		//5
		double[] cumulativeProbs = new double[elementsInEachBin.length];
		for(int i = 0; i < elementsInEachBin.length; ++i){
			cumulativeProbs[i] = elementsInEachBin[i]/imageSize;
		}
		time.now();
		//6
		int[] newSourcePixelArray =
				Arrays.stream(hsbSourcePixelArray)
						.parallel()
						.mapToInt(pixel ->
								Color.HSBtoRGB(
										pixel.hue,
										pixel.saturation,
										(float)cumulativeProbs[(int) (pixel.brightness * 256.0)]))
						.toArray();

		time.now();
		//7
		newImage.setRGB(0, 0, w, h, newSourcePixelArray, 0, w);
		time.now();

		return time;
	}

}

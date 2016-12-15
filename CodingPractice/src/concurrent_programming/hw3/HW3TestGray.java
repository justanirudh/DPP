/**
 * 
 */
//package concurrent_programming.hw3;
package concurrent_programming.hw3;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit test class for HW3 problem 1
 * 
 * This class can be run as an application or as JUnit tests. The application runs the tests
 * in a predetermined order; it is necessary to run testGray_SS() before the other cases in
 * order to compute speedup.
 * 
 * The file name with the source image must be provided as a property defined
 * with a vm argument on the command line:
 * 
 * -DsourceImageFilename="C:\Path\to\Pictures\hw3photo.jpg" 
 * 
 * Properties warmupReps and reps can also be defined similarly, but have default values if not defined.
 * Examples:
 * 
 * -DwarmupReps="2"
 * -Dreps="7"
 * 
 *
 */
public class HW3TestGray {

	static BufferedImage sourceImage;
	static String sourceImageFilename;
	static int WARMUPREPS = 5; // default value may be changed with command line
								// vm argument
	static int REPS = 5; // default value may be changed with command line vm
							// argument
	static double meanSerialDuration;
	static BufferedImage serialGray;

	/**
	 * Reads source image and values from System properties
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// read source image
		// The system property is set by command line vm argument
		//System properties: sourceImageFilename, WARMUPREPS, , serialGrey (used in tests) sets here
		// Example:
		sourceImageFilename = System.getProperty("sourceImageFilename");
		if (sourceImageFilename == null)
			System.out.println(
					"Provide image filename as system property on command line:  -DsourceImageFilename=\"C:\\Path\\to\\Pictures\\hw3photo.jpg\"");
		System.out.println("Opening image " + sourceImageFilename);
		File sourceFile = new File(sourceImageFilename);
		sourceImage = ImageIO.read(sourceFile);
		String warmupReps = System.getProperty("warmupReps");
		WARMUPREPS = warmupReps != null ? Integer.parseInt(System.getProperty("warmupReps")) : WARMUPREPS;
		String reps = System.getProperty("reps");
		REPS = reps != null ? Integer.parseInt(System.getProperty("reps")) : REPS;
		
		BufferedImage source = sourceImage;
		BufferedImage newImage = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Gray.gray_SS(source, newImage);
		serialGray = newImage;
		System.out.println("set correctGray");
	}

	/**
	 * Adds the given prefix to the given filename.
	 * Example:  
	 * old is "C:\Path\to\Pictures\hw3photo.jpg"
	 * prefix is "new_"
	 * returned String is "C:\Path\to\Pictures\new_hw3photo.jpg"
	 * 
	 * @param old   original filename
	 * @param prefix  prefix
	 * @return  string with modified filename
	 */
	String addPrefixToFileName(String old, String prefix) {
		int index = old.lastIndexOf(System.getProperty("file.separator")) + 1;
		return old.substring(0, index) + prefix + old.substring(index);
	}

	/**
	 * Test method for
	 * {@link concurrent_programming.hw3.Gray#gray_SS(java.awt.image.BufferedImage, java.awt.image.BufferedImage)}
	 * 
	 * This is the serial test case.  
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGray_SS() throws IOException {
		System.out.println("****Running test case gray_SS****");
		String filename = addPrefixToFileName(sourceImageFilename, "gray_ss_combined_setpar");
		System.out.println("writing file " + filename);
		File output = new File(filename);
		BufferedImage source = sourceImage;
		BufferedImage newImage = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		BufferedImage newImagePar = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		for (int rep = 0; rep < WARMUPREPS; rep++) {
			Timer timerData = Gray.gray_SS(source, newImage);
//			System.out.println(timerData.toString());
		}
		Timer[] timers = new Timer[REPS];
		System.out.println("**starting runs included in statistics**");
		for (int rep = 0; rep < REPS; rep++) {
			timers[rep] = Gray.gray_SS(source, newImage);
//			System.out.println(timers[rep].toString());
		}
		Gray.gray_SS(source, newImage);
		Gray.gray_SS(source, newImagePar);
		//write the last one
		ImageIO.write(newImagePar, "jpg", output);


		// print stats
		System.out.println("printing stats for gray_SS");
		double[] meanDuration = new double[1]; //passed as one-element array so it can be set in statsToString.
		System.out.println(Timer.statsToString(timers, meanDuration));
		meanSerialDuration= meanDuration[0];
	}
	


	/**
	 * Test method for
	 * {@link concurrent_programming.hw3.Gray#gray_PS(java.awt.image.BufferedImage, java.awt.image.BufferedImage)}
	 * .
	 * This test invokes the gray scale conversion with serial getRGB and setRGB and parallel stream processing.
	 * The speedup is compared to the duration from testGray_SS, or 0 if that test case has not run.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGray_PS() throws IOException {
			System.out.println("****Running test case gray_PS****");

			BufferedImage source = sourceImage;
			BufferedImage newImage = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
			for (int rep = 0; rep < WARMUPREPS; rep++) {
				Timer timerData = Gray.gray_PS(source, newImage);
//				System.out.println(timerData.toString());
			}
			Timer[] timers = new Timer[REPS];
			//System.out.println("**starting runs included in statistics**");
			for (int rep = 0; rep < REPS; rep++) {
				timers[rep] = Gray.gray_PS(source, newImage);
//				System.out.println(timers[rep].toString());
			}
			//write the last file
			String filename = addPrefixToFileName(sourceImageFilename, "gray_ps_");
			System.out.println("writing file " + filename);
			File output = new File(filename);
			ImageIO.write(newImage, "jpg", output);
			// print stats
			System.out.println("printing stats for gray_PS");
			System.out.println(Timer.statsToString(timers,meanSerialDuration));
			
			//check same as serial solution
			assertTrue(HW3Utils.equals(serialGray, newImage));
			
	}
	/**
	 * Test method for
	 * {@link concurrent_programming.hw3.Gray#gray_SS_FJ(concurrent_programming.hw3.FJBufferedImage, concurrent_programming.hw3.FJBufferedImage)}
	 * .
	 * This test invokes the gray scale conversion with parallel getRGB and setRGB and serial stream processing.
	 * The speedup is compared to the duration from testGray_SS, or 0 if that test case has not run.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGray_SS_FJ() throws IOException {
		System.out.println("****Running test case gray_SS_FJ****");
		String filename = addPrefixToFileName(sourceImageFilename, "gray_ss_fj_");
		System.out.println("writing file " + filename);
		File output = new File(filename);
		FJBufferedImage source = FJBufferedImage.BufferedImageToFJBufferedImage(sourceImage);
		FJBufferedImage newImage = new FJBufferedImage(source.getWidth(), source.getHeight(), source.getType());
		for (int rep = 0; rep < WARMUPREPS; rep++) {
			Timer timerData = Gray.gray_SS_FJ(source, newImage);
//			System.out.println(timerData.toString());
		}
		Timer[] timers = new Timer[REPS];
		//System.out.println("**starting runs included in statistics**");
		for (int rep = 0; rep < REPS; rep++) {
			timers[rep] = Gray.gray_SS_FJ(source, newImage);
//			System.out.println(timers[rep].toString());
		}
		ImageIO.write(newImage, "jpg", output);

		// print stats
		System.out.println("printing stats for gray_SS_FJ");
		System.out.println(Timer.statsToString(timers,meanSerialDuration));

		//check same as serial solution
		assertTrue(HW3Utils.equals(serialGray, newImage));
	}

	/**
	 * Test method for
	 * {@link concurrent_programming.hw3.Gray#gray_PS_FJ(concurrent_programming.hw3.FJBufferedImage, concurrent_programming.hw3.FJBufferedImage)}
	 * .
	 * This test invokes the gray scale conversion with parallel getRGB and setRGB and parallel stream processing.
	 * The speedup is compared to the duration from testGray_SS, or 0 if that test case has not run.
	 * @throws IOException 
	 */
	@Test
	public void testGray_PS_FJ() throws IOException {
		System.out.println("****Running test case gray_PS_FJ****");
		String filename = addPrefixToFileName(sourceImageFilename, "gray_ps_fj_");
		System.out.println("writing file " + filename);
		File output = new File(filename);
		FJBufferedImage source = FJBufferedImage.BufferedImageToFJBufferedImage(sourceImage);
		FJBufferedImage newImage = new FJBufferedImage(source.getWidth(), source.getHeight(), source.getType());
		for (int rep = 0; rep < WARMUPREPS; rep++) {
			Timer timerData = Gray.gray_PS_FJ(source, newImage);
//			System.out.println(timerData.toString());
		}
		Timer[] timers = new Timer[REPS];
		//System.out.println("**starting runs included in statistics**");
		for (int rep = 0; rep < REPS; rep++) {
			timers[rep] = Gray.gray_PS_FJ(source, newImage);
//			System.out.println(timers[rep].toString());
		}
		//write the last one
		ImageIO.write(newImage, "jpg", output);
		// print stats
		System.out.println("printing stats for gray_PS_FJ");
		System.out.println(Timer.statsToString(timers,meanSerialDuration));
		// check same as serial solution
		assertTrue(HW3Utils.equals(serialGray, newImage));
	}

	
	/**
	 * Allows the test cases to be invoked as an application without the JUnit test framework in a controlled order.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		setUpBeforeClass();
		HW3TestGray test = new HW3TestGray();
		test.testGray_SS();
		test.testGray_PS();
		test.testGray_SS_FJ();
		test.testGray_PS_FJ();
	}

}

package gaussianfilter;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

/**
 * Created by Sam Roberts on 8/7/2016.
 */
public class ImageUtils {

	/**
	 * Coefficient values from https://en.wikipedia.org/wiki/Relative_luminance
	 */
	private static final double RED_COEFF = 0.2126;
	private static final double GREEN_COEFF = 0.7152;
	private static final double BLUE_COEFF = 0.0722;

	/**
	 * DEFAULT DELTA is just a value > 0 so a division by 0 never occurs.
	 */
	private static final double DEFAULT_DELTA = 1.0;
	private static final double DEFAULT_THETA = -200;

	private static final double THETA_THRESHOLD = 360;
	private static final double DELTA_THRESHOLD = 25;


	//calculates Luminosity of a pixel
	public static double calculateLuminosity(int pixelRGB) {
		double red = (pixelRGB >> 16) & 0xff;
		double green = (pixelRGB >> 8) & 0xff;
		double blue = (pixelRGB) & 0xff;
		return (RED_COEFF * red)
				+ (GREEN_COEFF * green)
				+ (BLUE_COEFF * blue);
	}

	/**
	 *
	 * @param image image where edges are being detected
	 * @param c x value of pixel operations are being preformed on
	 * @param r y value of pixel operations are being preformed on
	 * @return true if c, and r are within the bounds of the image, otherwise false
	 */
	public static boolean inRange(BufferedImage image, int c, int r) {
		return (c > 0) &&
				(c < image.getWidth()) &&
				(r > 0) &&
				(r < image.getHeight());
	}

	/**
	 *
	 * @param image image where edges are being detected
	 * @param c x value of pixel operations are being preformed on
	 * @param r y value of pixel operations are being preformed on
	 * @return luminosity of a pixel with respect to the luminosity of the pixels to the north and the south
	 */
	public static double calculateDy(BufferedImage image, int c, int r) {
		if(!inRange(image, c, r)) {
			return DEFAULT_DELTA;
		}
		double dy = calculateLuminosity(image.getRGB(c+1, r))
				- calculateLuminosity(image.getRGB(c-1,r));
		if(dy == 0) {
			return DEFAULT_DELTA;
		}

		return dy;
	}

	/**
	 *
	 * @param image image where edges are being detected
	 * @param c x value of pixel operations are being preformed on
	 * @param r y value of pixel operations are being preformed on
	 * @return luminosity of a pixel with respect to the luminosity of the pixels to the east and the west
	 */
	public static double calculateDx(BufferedImage image, int c, int r) {
		if(!inRange(image, c, r)) {
			return DEFAULT_DELTA;
		}
		double dx = calculateLuminosity(image.getRGB(c, r + 1)) -
				calculateLuminosity(image.getRGB(c, r - 1));
		if(dx == 0) {
			return DEFAULT_DELTA;
		}

		return dx;
	}

	/**
	 *
	 * @param dx luminosity of a pixel with respect to the luminosity of the pixels to the east and the west
	 * @param dy luminosity of a pixel with respect to the luminosity of the pixels to the north and the south
	 * @return magnitude of gradient produced by dx, and dy
	 */
	public static double calculateGradient(double dx, double dy) {
		return Math.sqrt((Math.pow(dx,2) + Math.pow(dy,2)));
	}

	public static double calculateGradient(BufferedImage image, int c , int r) {
		if(!inRange(image,c,r)) {
			return  DEFAULT_THETA;
		}

		double dx = calculateDx(image, c, r);
		double dy = calculateDy(image,c , r);

		return calculateGradient(dx,dy);
	}

	/**
	 *
	 * @param image image where edges are being detected
	 * @param c x value of pixel operations are being preformed on
	 * @param r y value of pixel operations are being preformed on
	 * @return the angle created between lines dx, and dy
	 */
	public static double calculateGradientTheta(BufferedImage image, int c , int r) {
		if(!inRange(image,c,r)) {
			return  DEFAULT_THETA;
		}
		double dx = calculateDx(image, c, r);
		double dy = calculateDy(image,c , r);

		if((dx == DEFAULT_DELTA) && (dy == DEFAULT_DELTA)) {
			return DEFAULT_THETA;
		}

		double theta = Math.atan2(dy,dx) * (180.0/Math.PI);
		if (theta < 0) {
			return Math.floor(theta);
		} else if (theta > 0) {
			Math.ceil(theta);
		}

		return theta;
	}

	/**
	 *
	 * @param image image where edges are to be detected
	 * @return image outlining the edges
	 */
	public static WritableImage rgbDetectEdges(BufferedImage image) {
		WritableImage writeableImage = new WritableImage(image.getWidth(),image.getHeight());
		PixelWriter pixelWriter = writeableImage.getPixelWriter();
		//iterate through images pixels
		for(int y = 0; y < image.getHeight()-1; y++) {
			for (int x = 0; x < image.getWidth()-1; x++) {
				double magn = calculateGradient(image, x, y);
				double theta = calculateGradientTheta(image, x, y);
				//if theta <360 and >-360 degress and the magitude of the gradient is >=20 than an edge has been detected
				if((Math.abs(theta) <= THETA_THRESHOLD) &&
						(magn >= DELTA_THRESHOLD)) {
					pixelWriter.setColor(x,y,Color.RED);
				} else {
					pixelWriter.setColor(x,y, Color.BLACK);
				}
			}
		}
		return writeableImage;
	}
}

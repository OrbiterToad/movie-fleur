package ch.wetwer.moviefleur.helper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 24.05.2019
 **/
public class AdditiveCombiner {

    /**
     * @param imgFrameLeft  will be the img for the left eye
     * @param imgFrameRight will be the img for the right eye
     *
     * @return a combined image with additive color filtering
     */
    public static BufferedImage combine(BufferedImage imgFrameLeft, BufferedImage imgFrameRight) {

        BufferedImage combined
                = new BufferedImage(imgFrameLeft.getWidth(), imgFrameLeft.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < imgFrameLeft.getWidth(); x++) {
            for (int y = 0; y < imgFrameLeft.getHeight(); y++) {
                combined.setRGB(x, y, additive(imgFrameLeft.getRGB(x, y), imgFrameRight.getRGB(x, y)));
            }
        }
        return combined;
    }

    /**
     * @param pixelLeft  Pixel for Red Colors
     * @param pixelRight Pixel for Green and Blue Colors
     *
     * @return the combined rgb-int value of both images
     */
    public static int additive(int pixelLeft, int pixelRight) {
        Color left = new Color(pixelLeft);
        Color right = new Color(pixelRight);

        int rgb = left.getRed();
        rgb = (rgb << 8) + right.getGreen();
        rgb = (rgb << 8) + right.getBlue();

        return rgb;
    }


}

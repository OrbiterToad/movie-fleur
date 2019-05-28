package ch.wetwer.moviefleur.color;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 27.05.2019
 **/
public class ColorFilter {

    /**
     * @param bufferedImg img to be converted to color scale CS_GRAY
     *
     * @return gray img with no colors
     */
    public static BufferedImage filterGrayscale(BufferedImage bufferedImg) {
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        return op.filter(bufferedImg, null);
    }

    /**
     * @param originalImg img to be filtered
     * @param colorMask color to be applied
     *
     * @return filtered img with given color
     */
    public static BufferedImage filterColor(BufferedImage originalImg, ColorMask colorMask) {
        BufferedImage colorImage = new BufferedImage(originalImg.getWidth(),
                originalImg.getHeight(), originalImg.getType());

        for (int pixelX = 0; pixelX < originalImg.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < originalImg.getHeight(); pixelY++) {
                int pixel = originalImg.getRGB(pixelX, pixelY) & colorMask.getMask();
                colorImage.setRGB(pixelX, pixelY, pixel);
            }
        }

        return colorImage;
    }

}

package ch.wetwer.moviefleur.color;

import java.awt.*;
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
     * @param colorMask   color to be applied
     *
     * @return filtered img with given color
     */
    public static BufferedImage filterColor(BufferedImage originalImg, ColorMask colorMask) {
        BufferedImage colorImage = new BufferedImage(originalImg.getWidth(),
                originalImg.getHeight(), originalImg.getType());

        for (int pixelX = 0; pixelX < originalImg.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < originalImg.getHeight(); pixelY++) {
                colorImage.setRGB(pixelX, pixelY,
                        originalImg.getRGB(pixelX, pixelY) & colorMask.getMask());
            }
        }

        return colorImage;
    }

    /**
     * @param image image to be inverted
     *
     * @return an inverted image where all rgb values are flipped
     */
    public static BufferedImage invert(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color col = new Color(image.getRGB(x, y), true);
                col = new Color(255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue());
                image.setRGB(x, y, col.getRGB());
            }
        }

        return image;
    }
}

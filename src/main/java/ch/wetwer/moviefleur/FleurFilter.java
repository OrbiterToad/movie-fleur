package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.color.ColorFilter;
import ch.wetwer.moviefleur.color.ColorMask;
import ch.wetwer.moviefleur.helper.AdditiveCombiner;
import ch.wetwer.moviefleur.helper.AlphaCombiner;

import java.awt.image.BufferedImage;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 29.05.2019
 *
 * Interface for actions to filter Images
 * Contents:
 * - Additive Image combining
 * - Alpha image Combining
 * - Color filtering on images
 * - Grayscaling on images
 * - Inverting Image colors
 * - Create transparency on images
 **/
public class FleurFilter {

    public static BufferedImage additive(BufferedImage image1, BufferedImage image2) {
        return AdditiveCombiner.combine(image1, image2);
    }

    public static BufferedImage alphaCombine(BufferedImage image1, BufferedImage image2) {
        return AlphaCombiner.combine(image1, image2);
    }

    public static BufferedImage color(BufferedImage image, ColorMask colorMask) {
        return ColorFilter.filterColor(image, colorMask);
    }

    public static BufferedImage gray(BufferedImage image) {
        return ColorFilter.filterGrayscale(image);
    }

    public static BufferedImage invert(BufferedImage image) {
        return ColorFilter.invert(image);
    }

    public static BufferedImage transparent(BufferedImage image, double transparency) {
        return AlphaCombiner.transparent(image, transparency);
    }

}

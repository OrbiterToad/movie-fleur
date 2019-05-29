package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.ImageSplitter;

import java.awt.image.BufferedImage;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 29.05.2019
 **/
public class FleurUtil {

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        return ImageSplitter.resize(image, width, height);
    }

    public static BufferedImage crop(BufferedImage image, int width, int height) {
        return ImageSplitter.crop(image, width, height);
    }

    public static BufferedImage crop(BufferedImage image, int offsetX, int offsetY, int width, int height) {
        return ImageSplitter.crop(image, offsetX, offsetY, width, height);
    }

}

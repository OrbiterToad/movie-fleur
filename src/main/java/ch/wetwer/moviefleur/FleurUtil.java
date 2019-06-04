package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.ImageHelper;
import ch.wetwer.moviefleur.helper.ImageSplitter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 29.05.2019
 *
 * Interface for utility actions
 * Contents:
 * - Resize Image
 * - Crop Image
 * - Read from File
 * - Convert to Image Type
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

    public static BufferedImage read(File imageFile) throws IOException {
        return ImageIO.read(imageFile);
    }

    public static BufferedImage read(String imagePath) throws IOException {
        return ImageIO.read(new File(imagePath));
    }

    public static BufferedImage toType(BufferedImage image, int type) {
        return ImageHelper.convertToType(image, type);
    }

}

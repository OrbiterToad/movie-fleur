package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.ImageSplitter;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 29.05.2019
 *
 * Interface for actions used with the default 3d actions
 * Contents:
 * - Split Image 50/50 on X axis
 * - Stretch Image by 200%
 **/
public class Fleur3dUtil {

    public static List<BufferedImage> split(BufferedImage image) {
        return ImageSplitter.split(image);
    }

    public static BufferedImage streetch(BufferedImage image) {
        return ImageSplitter.stretch(image);
    }

}

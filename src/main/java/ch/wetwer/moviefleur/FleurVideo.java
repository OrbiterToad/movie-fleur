package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.video.VideoCompiler;
import ch.wetwer.moviefleur.video.VideoExtractor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 29.05.2019
 **/
public class FleurVideo {

    public static BufferedImage extract(File video, int framePosition) {
        return new VideoExtractor().extractFrame(video, framePosition);
    }

    public static List<BufferedImage> extract(File video) {
        return new VideoExtractor().extractAll(video);
    }

    public static List<BufferedImage> extract(File video, int frameStart, int frameStop) {
        return new VideoExtractor().extractAll(video, frameStart, frameStop);
    }

    public static void create(List<BufferedImage> images, String fileName) {
        new VideoCompiler().create(images, fileName);
    }

}

package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.video.VideoCompiler;
import ch.wetwer.moviefleur.video.VideoExtractor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.moviefleur
 * @created 08.05.2019
 **/
public class Main {

    public static void main(String[] args) {
        List<BufferedImage> frames = new VideoExtractor()
                .extractAll(new File("3dVideo.mp4"), 6000, 6020);

        new VideoCompiler().create(frames, "demo.mp4");
    }
}

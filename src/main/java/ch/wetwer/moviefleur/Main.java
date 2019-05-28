package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.ImageSplitter;
import ch.wetwer.moviefleur.video.VideoExtractor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.moviefleur
 * @created 08.05.2019
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage frame = new VideoExtractor()
                .extractFrame(new File("3dVideo.mp4"), 6000);

        frame = ImageSplitter.split(frame).get(0);
    }
}

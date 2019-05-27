package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.AdditiveCombiner;
import ch.wetwer.moviefleur.helper.ColorFilter;
import ch.wetwer.moviefleur.helper.ColorMask;
import ch.wetwer.moviefleur.helper.ImageSplitter;
import ch.wetwer.moviefleur.helper.VideoExtractor;

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
        List<BufferedImage> frames = new VideoExtractor().extractAll(new File("3dVideo.mp4"));
        int i = 0;
        for (BufferedImage frameOriginal : frames) {
            List<BufferedImage> splited = ImageSplitter.split(frameOriginal);
            BufferedImage frameCombined = AdditiveCombiner.combine(
                    ColorFilter.filterColor(splited.get(0), ColorMask.RED),
                    ColorFilter.filterColor(splited.get(1), ColorMask.GREEN_BLUE));
            System.out.println(i);
            frames.set(i++, frameCombined);
        }

        new VideoCompiler().create(frames, "3d.mp4");
    }
}

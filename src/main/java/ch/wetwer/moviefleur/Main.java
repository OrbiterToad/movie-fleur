package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.AdditiveCombiner;
import ch.wetwer.moviefleur.helper.ColorFilter;
import ch.wetwer.moviefleur.helper.ColorMask;
import ch.wetwer.moviefleur.helper.ImageHelper;
import ch.wetwer.moviefleur.helper.ImageSplitter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.moviefleur
 * @created 08.05.2019
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        VideoBuilder videoBuilder = VideoBuilder.builder().inputFile("3dVideo.mp4").build();

        BufferedImage frame = new VideoConverter(videoBuilder).getFrame(6000);
        List<BufferedImage> splited = ImageSplitter.split(frame);
        BufferedImage frameCombined = AdditiveCombiner.combine(
                ColorFilter.filterColor(splited.get(0), ColorMask.RED),
                ColorFilter.filterColor(splited.get(1), ColorMask.GREEN_BLUE));

        ImageHelper.saveImage(frameCombined, "frame_combined.png");
    }
}

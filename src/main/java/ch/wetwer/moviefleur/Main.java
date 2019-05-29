package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.color.ColorMask;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.moviefleur
 * @created 08.05.2019
 **/
public class Main {

    public static void main(String[] args) {
        List<BufferedImage> images
                = FleurVideo.extract(new File("3dVideo.mp4"), 6000, 6010);

        List<BufferedImage> videoImages = new ArrayList<>();
        for (BufferedImage image : images) {
            List<BufferedImage> splits = Fleur3dUtil.split(image);

            videoImages.add(FleurFilter.additive(
                    FleurFilter.color(splits.get(0), ColorMask.RED),
                    FleurFilter.color(splits.get(1), ColorMask.GREEN_BLUE)
            ));
        }

        FleurVideo.create(videoImages, "test.mp4");
    }
}

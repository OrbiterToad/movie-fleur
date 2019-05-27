package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.VideoHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.moviefleur
 * @created 08.05.2019
 **/
public class VideoConverter {

    private VideoBuilder videoBuilder;

    /**
     * @param videoBuilder set {@link VideoBuilder} for converting video files
     */
    VideoConverter(VideoBuilder videoBuilder) {
        this.videoBuilder = videoBuilder;
    }

    public BufferedImage getFrame(int framePosition) {
        return new VideoHelper().extractFrame(videoBuilder.getInputFile(), framePosition);
    }

    /**
     * @param inputFile start from a given 3d split file to convert to 3d
     *
     * @return
     */
    public BufferedImage getImage(File inputFile) throws IOException {
        return ImageIO.read(inputFile);
    }
}

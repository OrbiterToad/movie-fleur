package ch.wetwer.moviefleur.model;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 13.05.2019
 **/

@Data
public class VideoFrame {

    private final BufferedImage frame;
    private final int framePosition;

    /**
     * @param frame         {@link BufferedImage} object of selected frame
     * @param framePosition frame position of paired {@link BufferedImage}
     *
     *                      Used for extracting multiple frames from a video
     */
    public VideoFrame(BufferedImage frame, int framePosition) {
        this.frame = frame;
        this.framePosition = framePosition;
    }

}

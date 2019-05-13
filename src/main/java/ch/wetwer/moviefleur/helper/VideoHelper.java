package ch.wetwer.moviefleur.helper;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.scorefleur
 * @created 13.05.2019
 **/
public class VideoHelper {

    private String savePath;

    public VideoHelper(String savePath) {
        this.savePath = savePath;
    }

    /**
     * @param file          video file to extract 3d frames
     * @param framePosition position of frame to be extracted from video file
     *
     * @return img file of
     */
    public BufferedImage extractFrame(String file, int framePosition) {
        try {
            Java2DFrameConverter converter = new Java2DFrameConverter();
            FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);
            frameGrabber.start();
            frameGrabber.setFrameNumber(framePosition);
            BufferedImage extractedFrame = converter.convert(frameGrabber.grab());
            ImageIO.write(extractedFrame, "png", new File("img/frame_default.png"));
            frameGrabber.stop();
            return extractedFrame;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

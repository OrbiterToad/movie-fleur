package ch.wetwer.moviefleur.helper;

import ch.wetwer.moviefleur.model.VideoFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.scorefleur
 * @created 13.05.2019
 **/
public class VideoHelper {

    private String outDir;

    public VideoHelper(String outDir) {
        this.outDir = outDir;
    }

    /**
     * @param file          video file to extract 3d frame
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
            ImageIO.write(extractedFrame, "png",
                    new File(outDir + "frame_" + framePosition + "_default.png"));
            frameGrabber.stop();
            return extractedFrame;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param file video file to extract 3d frames
     *
     * @return list of frames
     *
     * TODO: NOT WORKING CURRENTLY
     */
    public List<VideoFrame> extractAll(String file) {
        try {
            Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
            FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);
            frameGrabber.start();

            ArrayList<VideoFrame> extractedFrames = new ArrayList<>();

            for (int currentFrame = 11; currentFrame <= frameGrabber.getLengthInFrames();
                 currentFrame = currentFrame + 10000) {
                System.out.println(frameGrabber.getLengthInFrames());
                System.out.println(currentFrame);
                frameGrabber.setFrameNumber(currentFrame);
                BufferedImage bufferedImage = java2DFrameConverter.convert(frameGrabber.grab());
                extractedFrames.add(new VideoFrame(bufferedImage, currentFrame));
            }
            frameGrabber.stop();
            return extractedFrames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

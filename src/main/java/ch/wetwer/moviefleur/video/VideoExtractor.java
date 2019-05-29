package ch.wetwer.moviefleur.video;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.moviefleur
 * @created 13.05.2019
 **/
public class VideoExtractor {

    /**
     * @param file          video file to extract 3d frame
     * @param framePosition position of frame to be extracted from video file
     *
     * @return img file of
     */
    public BufferedImage extractFrame(File file, int framePosition) {
        try {
            Java2DFrameConverter converter = new Java2DFrameConverter();
            FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);
            frameGrabber.start();
            frameGrabber.setFrameNumber(framePosition);
            BufferedImage extractedFrame = converter.convert(frameGrabber.grab());
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
     */
    public List<BufferedImage> extractAll(File file) {
        try {
            FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);
            frameGrabber.start();

            ArrayList<BufferedImage> extractedFrames = new ArrayList<>();
            for (int frame = 1; frame <= frameGrabber.getFrameNumber(); frame = frame + 1) {
                System.out.println(frame);
                frameGrabber.setFrameNumber(frame);
                extractedFrames.add(new Java2DFrameConverter().convert(frameGrabber.grab()));
            }
            frameGrabber.stop();
            return extractedFrames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param file  video file to extract 3d frames
     * @param start frame where to start the extraction
     * @param end   frame to stop with the extraction
     *
     * @return list of frames in specified range
     */
    public List<BufferedImage> extractAll(File file, int start, int end) {
        try {
            FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);
            frameGrabber.start();

            ArrayList<BufferedImage> extractedFrames = new ArrayList<>();
            for (int frame = start; frame <= end; frame = frame + 1) {
                System.out.println(frame);
                frameGrabber.setFrameNumber(frame);
                extractedFrames.add(new Java2DFrameConverter().convert(frameGrabber.grab()));
            }
            frameGrabber.stop();
            return extractedFrames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.ImageHelper;
import ch.wetwer.moviefleur.helper.VideoHelper;
import ch.wetwer.moviefleur.model.VideoFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.scorefleur
 * @created 08.05.2019
 **/
public class VideoConverter {

    private VideoBuilder videoBuilder;

    private String outDir;

    /**
     * @param outDir set output directory for storing files
     */
    VideoConverter(String outDir) {
        if (outDir.endsWith("/")) {
            this.outDir = outDir;
        } else {
            this.outDir = outDir + "/";
        }
    }

    /**
     * @param videoBuilder set {@link VideoBuilder} for converting video files
     */
    VideoConverter(VideoBuilder videoBuilder) {
        this.videoBuilder = videoBuilder;
        this.outDir = "";
    }

    /**
     * @param videoBuilder set {@link VideoBuilder} for converting video files
     * @param outDir       set output directory for storing files
     */
    VideoConverter(VideoBuilder videoBuilder, String outDir) {
        this.videoBuilder = videoBuilder;
        if (outDir.endsWith("/")) {
            this.outDir = outDir;
        } else {
            this.outDir = outDir + "/";
        }
    }

    /**
     * @param framePosition Create a 3d Img for the exact frame at this position
     */
    public void convert(int framePosition) {
        try {
            VideoHelper videoHelper = new VideoHelper(outDir);
            BufferedImage frameDefault = videoHelper.extractFrame(videoBuilder.getInputFile(), framePosition);

            ImageHelper.outDir = this.outDir;
            List<BufferedImage> splits = ImageHelper.split(frameDefault, framePosition);
            ImageHelper.combine(splits.get(0), splits.get(1), framePosition);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts all Frames in a given framerate to 3d
     */
    public void convertAll() {
        VideoHelper videoHelper = new VideoHelper(outDir);
        try {
            for (VideoFrame frameDefault : videoHelper.extractAll(videoBuilder.getInputFile())) {
                ImageHelper.outDir = this.outDir;
                List<BufferedImage> splits = ImageHelper.split(frameDefault.getFrame(), frameDefault.getFramePosition());
                ImageHelper.combine(splits.get(0), splits.get(1), frameDefault.getFramePosition());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param inputFile start from a given 3d split file to convert to 3d
     */
    public void convertFromImg(File inputFile) {
        try {
            ImageHelper.outDir = this.outDir;
            List<BufferedImage> splits = ImageHelper.split(ImageIO.read(inputFile));
            ImageHelper.combine(splits.get(0), splits.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.ImageHelper;
import ch.wetwer.moviefleur.helper.VideoHelper;

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

    private String savePath;

    VideoConverter(String savePath) {
        if (savePath.endsWith("/")) {
            this.savePath = savePath;
        } else {
            this.savePath = savePath + "/";
        }
    }

    VideoConverter(VideoBuilder videoBuilder, String savePath) {
        this.videoBuilder = videoBuilder;
        if (savePath.endsWith("/")) {
            this.savePath = savePath;
        } else {
            this.savePath = savePath + "/";
        }
    }

    public void convert(int framePosition) {
        try {
            VideoHelper videoHelper = new VideoHelper(savePath);
            BufferedImage frameDefault = videoHelper.extractFrame(videoBuilder.getInputFile(), framePosition);

            ImageHelper.savePath = this.savePath;
            List<BufferedImage> splits = ImageHelper.split(frameDefault);
            ImageHelper.combine(splits.get(0), splits.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertFromImg(File inputFile) {
        try {
            ImageHelper.savePath = this.savePath;
            List<BufferedImage> splits = ImageHelper.split(ImageIO.read(inputFile));
            ImageHelper.combine(splits.get(0), splits.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

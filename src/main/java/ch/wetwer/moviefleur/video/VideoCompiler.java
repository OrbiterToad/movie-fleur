package ch.wetwer.moviefleur.video;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 27.05.2019
 **/
public class VideoCompiler {

    /**
     * @param imageList List of images in order to be added to the videofile
     * @param fileName  video file name to save to
     */
    public void create(List<BufferedImage> imageList, String fileName) {
        final IMediaWriter writer = ToolFactory.makeWriter(fileName);

        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
                imageList.get(0).getWidth(), imageList.get(0).getHeight());
        long startTime = System.nanoTime();
        for (BufferedImage frame : imageList) {
            writer.encodeVideo(0, convertToType(frame, BufferedImage.TYPE_3BYTE_BGR),
                    System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
            frame = null;
        }

        writer.close();

    }

    /**
     * @param sourceImage image to convert
     * @param type encoding type to convert to
     *
     * @return converted {@link BufferedImage} in given encoding
     */
    public static BufferedImage convertToType(BufferedImage sourceImage, int type) {

        BufferedImage image;
        // if the source image is already the target type, return the source image
        if (sourceImage.getType() == type) {
            image = sourceImage;
        }

        // otherwise create a new image of the target type and draw the new image
        else {
            image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), type);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;
    }

}

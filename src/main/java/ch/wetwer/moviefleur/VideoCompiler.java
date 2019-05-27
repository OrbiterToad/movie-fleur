package ch.wetwer.moviefleur;

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

    public void create(List<BufferedImage> imageList, String fileName) {
        final IMediaWriter writer = ToolFactory.makeWriter(fileName);

        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
                imageList.get(0).getWidth(), imageList.get(0).getHeight());
        long startTime = System.nanoTime();
        for (BufferedImage frame : imageList) {
            // take the screen shot
            BufferedImage bgrScreen = convertToType(frame, BufferedImage.TYPE_3BYTE_BGR);
            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        }

        // tell the writer to close and write the trailer if  needed
        writer.close();

    }

    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {

        BufferedImage image;
        // if the source image is already the target type, return the source image
        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        }

        // otherwise create a new image of the target type and draw the new image
        else {
            image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;
    }

}

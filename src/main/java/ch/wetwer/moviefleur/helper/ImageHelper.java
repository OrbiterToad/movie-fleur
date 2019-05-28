package ch.wetwer.moviefleur.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.moviefleur
 * @created 13.05.2019
 **/
public class ImageHelper {

    /**
     * @param bufferedImage Img to be saved to the disk
     * @param outFile       Title of file to be saved
     *
     * @return BufferedImage object as reference
     *
     * @throws IOException
     */
    public static BufferedImage saveImage(BufferedImage bufferedImage, String outFile) throws IOException {
        return saveImage(bufferedImage, new File(outFile));
    }

    /**
     * @param bufferedImage Img to be saved to the disk
     * @param outFile       Output file as object
     *
     * @return BufferedImage object as reference
     *
     * @throws IOException
     */
    public static BufferedImage saveImage(BufferedImage bufferedImage, File outFile) throws IOException {
        ImageIO.write(bufferedImage, "png", outFile);
        return bufferedImage;
    }


    /**
     * @param sourceImage image to convert
     * @param type        encoding type to convert to
     *
     * @return converted {@link BufferedImage} in given encoding
     */
    public static BufferedImage convertToType(BufferedImage sourceImage, int type) {

        if (sourceImage.getType() == type) {
            return sourceImage;
        } else {
            BufferedImage image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), type);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
            return image;
        }
    }
}

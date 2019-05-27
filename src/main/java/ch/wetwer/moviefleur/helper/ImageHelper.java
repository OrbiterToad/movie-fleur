package ch.wetwer.moviefleur.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

}

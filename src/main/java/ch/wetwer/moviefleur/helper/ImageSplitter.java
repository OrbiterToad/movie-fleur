package ch.wetwer.moviefleur.helper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 27.05.2019
 **/
public class ImageSplitter {

    /**
     * @param frameDefault VideoFrame shown as in default video
     *
     * @return list with leftFrame at [0] and rightFrame at [1]
     */
    public static List<BufferedImage> split(BufferedImage frameDefault) {
        BufferedImage splitLeft = stretch(crop(frameDefault,
                frameDefault.getWidth() / 2, frameDefault.getHeight()));

        BufferedImage splitRight = stretch(crop(frameDefault,
                frameDefault.getWidth() / 2, 0,
                frameDefault.getWidth() / 2, frameDefault.getHeight()));

        return Arrays.asList(splitLeft, splitRight);
    }

    /**
     * @param halfImg img to be stretched, currently using 50% of screen width
     *                scale with Image.getScaledInstance(...)
     *
     * @return stretched Img
     */
    public static BufferedImage stretch(BufferedImage halfImg) {
        return resize(halfImg, halfImg.getWidth() * 2, halfImg.getHeight());
    }

    /**
     * @param image  image to be resized
     * @param width  width to be applied
     * @param height height to be applied
     *
     * @return resized image according to width and height
     */
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        resizedImage.getGraphics().drawImage(
                image.getScaledInstance(width, height, Image.SCALE_FAST), 0, 0, null);

        return resizedImage;
    }

    /**
     * @param image  image to be cropped
     * @param width  width of crop field (cant be out of bounds)
     * @param height height of crop field (cant be out of bounds)
     *
     * @return cropped image according to crop field
     */
    public static BufferedImage crop(BufferedImage image, int width, int height) {
        return image.getSubimage(0, 0, width, height);
    }

    /**
     * @param image   image to be cropped
     * @param offsetX offset x on the image
     * @param offsetY offset y on the image
     * @param width   width of crop field (cant be out of bounds)
     * @param height  height of crop field (cant be out of bounds)
     *
     * @return cropped image according to crop field and offset point
     */
    public static BufferedImage crop(BufferedImage image, int offsetX, int offsetY, int width, int height) {
        return image.getSubimage(offsetX, offsetY, width, height);
    }

    public static BufferedImage scale(BufferedImage image, double scaleAmount) {
        BufferedImage resizedImage = new BufferedImage(
                (int) (image.getWidth() / scaleAmount), (int) (image.getHeight() / scaleAmount),
                BufferedImage.TYPE_INT_ARGB);
        resizedImage.getGraphics().drawImage(
                image.getScaledInstance(
                        (int) (image.getWidth() / scaleAmount), (int) (image.getHeight() / scaleAmount),
                        Image.SCALE_FAST), 0, 0, null);

        return resizedImage;
    }
}

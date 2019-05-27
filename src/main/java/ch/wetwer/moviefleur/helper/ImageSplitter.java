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
        BufferedImage splitLeft = stretch(frameDefault.getSubimage(0, 0,
                frameDefault.getWidth() / 2, frameDefault.getHeight()));
        BufferedImage splitRight = stretch(frameDefault.getSubimage(frameDefault.getWidth() / 2, 0,
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
        Image preloadImage = halfImg.getScaledInstance(halfImg.getWidth() * 2, halfImg.getHeight(), Image.SCALE_FAST);
        BufferedImage stretchedImg = new BufferedImage(
                preloadImage.getWidth(null), preloadImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        stretchedImg.getGraphics().drawImage(preloadImage, 0, 0, null);
        return stretchedImg;
    }

}

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
 * @package ch.wetwer.scorefleur
 * @created 13.05.2019
 **/
public class ImageHelper {

    public static String savePath = "";

    /**
     * @param halfImg img to be stretched, currently using 50% of screen width
     *                scale with Image.getScaledInstance(...)
     *
     * @return stretched Img
     */
    public static BufferedImage stretch(BufferedImage halfImg) {
        Image preloadImage = halfImg.getScaledInstance(halfImg.getWidth() * 2, halfImg.getHeight(), Image.SCALE_FAST);
        BufferedImage stretchedImg = new BufferedImage(preloadImage.getWidth(null), preloadImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        stretchedImg.getGraphics().drawImage(preloadImage, 0, 0, null);
        return stretchedImg;
    }

    /**
     * @param image img to be made transparent
     * @param alpha alpha value to apply to img (TYPE_INT_ARGB)
     *
     * @return Transparent BufferedImage
     */
    public static BufferedImage transparent(BufferedImage image, double alpha) {
        BufferedImage target = new BufferedImage(image.getWidth(), image.getHeight(), java.awt.Transparency.TRANSLUCENT);
        Graphics2D g = target.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
        g.drawImage(image, null, 0, 0);
        g.dispose();
        return target;
    }


    /**
     * @param frameDefault Frame shown as in default video
     *
     * @return list with leftFrame at [0] and rightFrame at [1]
     *
     * @throws IOException
     */
    public static List<BufferedImage> split(BufferedImage frameDefault) throws IOException {

        File imgFrameLeft = new File(savePath + "frame_left.png");
        File imgFrameRight = new File(savePath + "frame_right.png");

        ImageIO.write(stretch(frameDefault.getSubimage(0, 0, frameDefault.getWidth() / 2, frameDefault.getHeight())), "png", imgFrameLeft);
        ImageIO.write(stretch(frameDefault.getSubimage(frameDefault.getWidth() / 2, 0, frameDefault.getWidth() / 2, frameDefault.getHeight())), "png", imgFrameRight);

        return Arrays.asList(ImageIO.read(imgFrameLeft), ImageIO.read(imgFrameRight));
    }

    /**
     * @param imgFrameLeft  will be the img in the background with color red for left
     * @param imgFrameRight will be the img in the foreground with color blue for right and a transparency of 0.7
     *
     * @throws IOException
     */
    public static void combine(BufferedImage imgFrameLeft, BufferedImage imgFrameRight) throws IOException {
        BufferedImage combined = new BufferedImage(imgFrameLeft.getWidth(), imgFrameLeft.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();
        g.drawImage(imgFrameLeft, 0, 0, null);
        g.drawImage(transparent(imgFrameRight, 0.6D), 0, 0, null);

        ImageIO.write(combined, "png", new File(savePath + "frame_combined.png"));
    }

    /**
     * @param bufferedImage img to be converted to color scale CS_GRAY
     *
     * @return gray img with no colors
     */
    public static BufferedImage grayScale(BufferedImage bufferedImage) {
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        return op.filter(bufferedImage, null);
    }
}

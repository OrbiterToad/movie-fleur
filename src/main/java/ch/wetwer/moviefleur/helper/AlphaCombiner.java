package ch.wetwer.moviefleur.helper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 27.05.2019
 **/
public class AlphaCombiner {

    /**
     * @param imgFrameLeft  will be the img in the background with color red for left
     * @param imgFrameRight will be the img in the foreground with color blue for right and a transparency of 0.7
     *
     * @return Return combined image with both images
     */
    public static BufferedImage combine(BufferedImage imgFrameLeft, BufferedImage imgFrameRight) {
        BufferedImage combined
                = new BufferedImage(imgFrameLeft.getWidth(), imgFrameLeft.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();
        g.drawImage(imgFrameLeft, 0, 0, null);
        g.drawImage(transparent(imgFrameRight, 0.6D), 0, 0, null);
        return combined;
    }

    /**
     * @param bufferedImg img to be made transparent
     * @param alpha       alpha value to apply to img (TYPE_INT_ARGB)
     *
     * @return Transparent BufferedImg
     */
    public static BufferedImage transparent(BufferedImage bufferedImg, double alpha) {
        BufferedImage target = new BufferedImage(bufferedImg.getWidth(), bufferedImg.getHeight(), Transparency.TRANSLUCENT);
        Graphics2D g = target.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
        g.drawImage(bufferedImg, null, 0, 0);
        g.dispose();
        return target;
    }

}

package ch.wetwer.moviefleur.helper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 24.05.2019
 **/
public class AdditiveCombiner {

    /**
     * Not Working yet
     *
     * @param imgFrameLeft  will be the img for the left eye
     * @param imgFrameRight will be the img for the right eye
     *
     * @return
     */
    public BufferedImage getCombined(BufferedImage imgFrameLeft, BufferedImage imgFrameRight) {

        BufferedImage combined
                = new BufferedImage(imgFrameLeft.getWidth(), imgFrameLeft.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int pixelX = 0; pixelX < imgFrameLeft.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < imgFrameLeft.getHeight(); pixelY++) {
                Color pixelLeft = new Color(imgFrameLeft.getRGB(pixelX, pixelY));
                System.out.print(pixelLeft.getRed());
                System.out.print(pixelLeft.getGreen());
                System.out.println(pixelLeft.getBlue());

                Color pixelRight = new Color(imgFrameRight.getRGB(pixelX, pixelY));
                System.out.print(pixelRight.getRed());
                System.out.print(pixelRight.getGreen());
                System.out.println(pixelRight.getBlue());


//                combined.setRGB(pixelX, pixelY,);
                return null;
            }
            return null;
        }
        return combined;
    }

    private static int additive(int pixelLeft, int pixelRight) {
        return 0;
    }


}

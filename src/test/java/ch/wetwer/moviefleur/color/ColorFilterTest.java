package ch.wetwer.moviefleur.color;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ColorFilterTest {

    @Test
    public void testFilterGrayscale() throws IOException {
        // Setup
        final BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage result = ColorFilter.filterGrayscale(originalImg);

        // Verify the results
        assertNotEquals(originalImg.getRGB(1, 1), result.getRGB(1, 1));
    }

    @Test
    public void testFilterColor() throws IOException {
        // Setup
        final BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage resultRed = ColorFilter.filterColor(originalImg, ColorMask.RED);
        final BufferedImage resultGreen = ColorFilter.filterColor(originalImg, ColorMask.GREEN);
        final BufferedImage resultBlue = ColorFilter.filterColor(originalImg, ColorMask.BLUE);
        final BufferedImage resultGreenBlue = ColorFilter.filterColor(originalImg, ColorMask.GREEN_BLUE);

        // Verify the results
        assertEquals(-16056320, resultRed.getRGB(1, 1));
        assertEquals(-16774912, resultGreen.getRGB(1, 1));
        assertEquals(-16777211, resultBlue.getRGB(1, 1));
        assertEquals(-16774907, resultGreenBlue.getRGB(1, 1));
    }

    @Test
    public void testInvert() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage result = ColorFilter.invert(image);

        // Verify the results
        assertEquals(-723206, result.getRGB(1, 1));
    }
}

package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.color.ColorMask;
import ch.wetwer.moviefleur.helper.ImageSplitter;
import junit.framework.TestCase;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FleurFilterTest {

    @Test
    public void testAdditive() throws IOException {
        BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        List<BufferedImage> splits = ImageSplitter.split(originalImg);

        // Run the test
        final BufferedImage result = FleurFilter.additive(splits.get(0), splits.get(1));

        // Verify the results
        TestCase.assertEquals(splits.get(0).getWidth(), result.getWidth());
    }

    @Test
    public void testAlphaCombine() throws IOException {
        BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        List<BufferedImage> splits = ImageSplitter.split(originalImg);

        FleurFilter.alphaCombine(splits.get(0), splits.get(1));
    }

    @Test
    public void testColor() throws IOException {
        // Setup
        final BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage resultRed = FleurFilter.color(originalImg, ColorMask.RED);
        final BufferedImage resultGreen = FleurFilter.color(originalImg, ColorMask.GREEN);
        final BufferedImage resultBlue = FleurFilter.color(originalImg, ColorMask.BLUE);
        final BufferedImage resultYellow = FleurFilter.color(originalImg, ColorMask.YELLOW);
        final BufferedImage resultCyan = FleurFilter.color(originalImg, ColorMask.CYAN);
        final BufferedImage resultMagenta = FleurFilter.color(originalImg, ColorMask.MAGENTA);

        // Verify the results
        assertEquals(-16056320, resultRed.getRGB(1, 1));
        assertEquals(-16774912, resultGreen.getRGB(1, 1));
        assertEquals(-16777211, resultBlue.getRGB(1, 1));
        assertEquals(-16054016, resultYellow.getRGB(1, 1));
        assertEquals(-16774907, resultCyan.getRGB(1, 1));
        assertEquals(-16056315, resultMagenta.getRGB(1, 1));
    }

    @Test
    public void testGray() throws IOException {
        // Setup
        final BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage result = FleurFilter.gray(originalImg);

        // Verify the results
        assertNotEquals(originalImg.getRGB(1, 1), result.getRGB(1, 1));
    }

    @Test
    public void testInvert() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage result = FleurFilter.invert(image);

        // Verify the results
        assertEquals(-723206, result.getRGB(1, 1));
    }

    @Test
    public void testTransparent() throws IOException {
        // Setup
        final BufferedImage bufferedImg = ImageIO.read(new File("img/frame_default.png"));
        final double alpha = 0.5d;

        // Run the test
        final BufferedImage result = FleurFilter.transparent(bufferedImg, alpha);

        // Verify the results
        Color c = new Color(result.getRGB(1, 1), true);

        assertEquals(128, c.getAlpha());
    }
}

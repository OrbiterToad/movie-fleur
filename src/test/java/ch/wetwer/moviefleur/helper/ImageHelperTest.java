package ch.wetwer.moviefleur.helper;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ImageHelperTest {

    @Test
    public void testTransparent() throws IOException {
        // Setup
        final BufferedImage bufferedImg = ImageIO.read(new File("img/frame_default.png"));
        final double alpha = 0.5d;

        // Run the test
        final BufferedImage result = ImageHelper.transparent(bufferedImg, alpha);

        // Verify the results
        Color c = new Color(result.getRGB(1, 1), true);

        assertEquals(128, c.getAlpha());
    }

    @Test
    public void testSplit() throws Exception {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final List<BufferedImage> result = ImageHelper.split(frameDefault);

        // Verify the results
        assertEquals(frameDefault.getWidth() * 2, result.get(0).getWidth() + result.get(1).getWidth());
    }

    @Test(expected = IOException.class)
    public void testSplit_ThrowsIOException() throws Exception {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("test/test.png"));

        // Run the test
        ImageHelper.split(frameDefault);
    }

    @Test(expected = IOException.class)
    public void testSplit_ThrowsIOException1() throws Exception {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("test/test.png"));
        final int framePosition = 0;

        // Run the test
        ImageHelper.split(frameDefault, framePosition);
    }

    @Test(expected = IOException.class)
    public void testCombine_ThrowsIOException() throws Exception {
        // Setup
        final BufferedImage imgFrameLeft = ImageIO.read(new File("test/test.png"));
        final BufferedImage imgFrameRight = ImageIO.read(new File("test/test.png"));

        // Run the test
        ImageHelper.combine(imgFrameLeft, imgFrameRight);
    }

    @Test
    public void testCombine() throws Exception {
        // Setup
        BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        List<BufferedImage> splits = ImageHelper.split(originalImg);

        final BufferedImage imgFrameLeft = splits.get(0);
        final BufferedImage imgFrameRight = splits.get(1);

        // Run the test
        BufferedImage result = ImageHelper.combine(imgFrameLeft, imgFrameRight);

        // Verify the results
        assertEquals(originalImg.getWidth(), result.getWidth());
    }

    @Test(expected = IOException.class)
    public void testCombine_ThrowsIOException1() throws Exception {
        // Setup
        final BufferedImage imgFrameLeft = ImageIO.read(new File("test/test.png"));
        final BufferedImage imgFrameRight = ImageIO.read(new File("test/test.png"));
        final int framePosition = 0;

        // Run the test
        ImageHelper.combine(imgFrameLeft, imgFrameRight, framePosition);
    }

    @Test
    public void testFilterGrayscale() throws IOException {
        // Setup
        final BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage result = ImageHelper.filterGrayscale(originalImg);

        // Verify the results
        assertNotEquals(originalImg.getRGB(1, 1), result.getRGB(1, 1));
    }

    @Test
    public void testFilterColor() throws IOException {
        // Setup
        final BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage resultRed = ImageHelper.filterColor(originalImg, FilterColor.RED);
        final BufferedImage resultGreen = ImageHelper.filterColor(originalImg, FilterColor.GREEN);
        final BufferedImage resultBlue = ImageHelper.filterColor(originalImg, FilterColor.BLUE);

        // Verify the results
        assertEquals(-16774912, resultRed.getRGB(1, 1));
        assertEquals(-16777211, resultGreen.getRGB(1, 1));
        assertEquals(-16056320, resultBlue.getRGB(1, 1));
    }
}

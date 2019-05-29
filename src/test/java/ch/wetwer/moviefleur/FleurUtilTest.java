package ch.wetwer.moviefleur;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FleurUtilTest {
    @Test
    public void testResize() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 300;
        final int height = 300;

        // Run the test
        final BufferedImage result = FleurUtil.resize(image, width, height);

        // Verify the results
        assertEquals(width, result.getWidth());
        assertEquals(height, result.getHeight());
    }

    @Test
    public void testCrop() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 300;
        final int height = 300;

        // Run the test
        final BufferedImage result = FleurUtil.crop(image, width, height);

        // Verify the results
        assertEquals(width, result.getWidth());
        assertEquals(height, result.getHeight());
    }

    @Test(expected = RasterFormatException.class)
    public void testCrop_ThrowsRasterFormatException() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 2000;
        final int height = 2000;

        // Run the test
        FleurUtil.crop(image, width, height);
    }

    @Test
    public void testCropOffset() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 300;
        final int height = 300;
        final int offsetX = 20;
        final int offsetY = 20;

        // Run the test
        final BufferedImage result = FleurUtil.crop(image, offsetX, offsetY, width, height);

        // Verify the results
        assertEquals(width, result.getWidth());
        assertEquals(height, result.getHeight());
    }

    @Test(expected = RasterFormatException.class)
    public void testCropOffset_ThrowsRasterFormatException() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));
        final int width = 2000;
        final int height = 2000;
        final int offsetX = 20;
        final int offsetY = 20;

        // Run the test
        FleurUtil.crop(image, offsetX, offsetY, width, height);
    }

}

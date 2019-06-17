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

    @Test
    public void testConvertToType() throws IOException {
        // Setup
        final BufferedImage sourceImage = ImageIO.read(new File("img/frame_default.png"));
        final int type = BufferedImage.TYPE_INT_RGB;

        // Run the test
        final BufferedImage result2 = FleurUtil.toType(sourceImage, sourceImage.getType());
        final BufferedImage result = FleurUtil.toType(sourceImage, type);

        // Verify the results
        assertEquals(type, result.getType());
        assertEquals(5, result2.getType());
    }

    @Test
    public void testSaveImage() throws Exception {
        // Setup
        final BufferedImage bufferedImage = ImageIO.read(new File("img/frame_default.png"));
        final String outFile = "img/test_save_1.png";
        FleurUtil.save(bufferedImage, outFile);
        // Verify the results
        assertEquals(
                ImageIO.read(new File("img/test_save_1.png")).getWidth(),
                bufferedImage.getWidth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveImage_ThrowsIllegalArgumentException() throws Exception {
        // Setup
        final BufferedImage bufferedImage = null;
        final String outFile = "img/test_save.png";

        // Run the test
        FleurUtil.save(bufferedImage, outFile);
    }

    @Test
    public void testSaveImage1() throws Exception {
        // Setup
        final BufferedImage bufferedImage = ImageIO.read(new File("img/frame_default.png"));
        final File outFile = new File("img/test_save_2.png");

        // Run the test
        FleurUtil.save(bufferedImage, outFile);
        // Verify the results
        assertEquals(
                ImageIO.read(new File("img/test_save_2.png")).getWidth(),
                bufferedImage.getWidth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveImage_ThrowsIllegalArgumentException1() throws Exception {
        // Setup
        final BufferedImage bufferedImage = null;
        final File outFile = new File("img/test_save.png");

        // Run the test
        FleurUtil.save(bufferedImage, outFile);
    }

}

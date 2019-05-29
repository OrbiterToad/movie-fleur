package ch.wetwer.moviefleur.helper;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ImageHelperTest {

    @Test
    public void testSaveImage() throws Exception {
        // Setup
        final BufferedImage bufferedImage = ImageIO.read(new File("img/frame_default.png"));
        final String outFile = "img/test_save_1.png";
        ImageHelper.saveImage(bufferedImage, outFile).getWidth();
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
        ImageHelper.saveImage(bufferedImage, outFile);
    }

    @Test
    public void testSaveImage1() throws Exception {
        // Setup
        final BufferedImage bufferedImage = ImageIO.read(new File("img/frame_default.png"));
        final File outFile = new File("img/test_save_2.png");

        // Run the test
        ImageHelper.saveImage(bufferedImage, outFile).getWidth();
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
        ImageHelper.saveImage(bufferedImage, outFile);
    }

    @Test
    public void testConvertToType() throws IOException {
        // Setup
        final BufferedImage sourceImage = ImageIO.read(new File("img/frame_default.png"));
        final int type = BufferedImage.TYPE_INT_RGB;

        // Run the test
        final BufferedImage result2 = ImageHelper.convertToType(sourceImage, sourceImage.getType());
        final BufferedImage result = ImageHelper.convertToType(sourceImage, type);

        // Verify the results
        assertEquals(type, result.getType());
        assertEquals(5, result2.getType());
    }
}

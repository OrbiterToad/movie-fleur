package ch.wetwer.moviefleur.helper;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImageSplitterTest {

    @Test
    public void testSplit() throws Exception {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final List<BufferedImage> result = ImageSplitter.split(frameDefault);

        // Verify the results
        assertEquals(frameDefault.getWidth() * 2, result.get(0).getWidth() + result.get(1).getWidth());
    }

    @Test(expected = IOException.class)
    public void testSplit_ThrowsIOException() throws Exception {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("test/test.png"));

        // Run the test
        ImageSplitter.split(frameDefault);
    }
}

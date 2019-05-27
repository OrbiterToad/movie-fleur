package ch.wetwer.moviefleur.helper;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlphaCombinerTest {

    @Test
    public void testCombine() throws Exception {
        // Setup
        BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        List<BufferedImage> splits = ImageSplitter.split(originalImg);

        final BufferedImage imgFrameLeft = splits.get(0);
        final BufferedImage imgFrameRight = splits.get(1);

        // Run the test
        BufferedImage result = AlphaCombiner.combine(imgFrameLeft, imgFrameRight);

        // Verify the results
        assertEquals(originalImg.getWidth(), result.getWidth());
    }

    @Test(expected = IOException.class)
    public void testCombine_ThrowsIOException() throws Exception {
        // Setup
        final BufferedImage imgFrameLeft = ImageIO.read(new File("test/test.png"));
        final BufferedImage imgFrameRight = ImageIO.read(new File("test/test.png"));

        // Run the test
        AlphaCombiner.combine(imgFrameLeft, imgFrameRight);
    }

    @Test
    public void testTransparent() throws IOException {
        // Setup
        final BufferedImage bufferedImg = ImageIO.read(new File("img/frame_default.png"));
        final double alpha = 0.5d;

        // Run the test
        final BufferedImage result = AlphaCombiner.transparent(bufferedImg, alpha);

        // Verify the results
        Color c = new Color(result.getRGB(1, 1), true);

        assertEquals(128, c.getAlpha());
    }
}

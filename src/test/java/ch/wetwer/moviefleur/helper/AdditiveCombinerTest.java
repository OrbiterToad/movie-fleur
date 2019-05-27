package ch.wetwer.moviefleur.helper;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AdditiveCombinerTest {

    @Test
    public void testCombine() throws IOException {
        // Setup
        BufferedImage originalImg = ImageIO.read(new File("img/frame_default.png"));

        List<BufferedImage> splits = ImageSplitter.split(originalImg);

        final BufferedImage imgFrameLeft = splits.get(0);
        final BufferedImage imgFrameRight = splits.get(1);

        // Run the test
        final BufferedImage result = AlphaCombiner.combine(imgFrameLeft, imgFrameRight);

        // Verify the results
        assertEquals(imgFrameLeft.getWidth(), result.getWidth());
    }
}

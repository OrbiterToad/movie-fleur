package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.ImageSplitter;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Fleur3dUtilTest {

    @Test
    public void testSplit() throws IOException {
        // Setup
        final BufferedImage frameDefault = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final List<BufferedImage> result = Fleur3dUtil.split(frameDefault);

        // Verify the results
        assertEquals(frameDefault.getWidth() * 2, result.get(0).getWidth() + result.get(1).getWidth());
    }

    @Test
    public void testStreetch() throws IOException {
        // Setup
        final BufferedImage image = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage result = Fleur3dUtil.streetch(image);

        // Verify the results
        assertEquals(image.getWidth() * 2, result.getWidth());
    }
}

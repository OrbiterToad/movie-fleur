package ch.wetwer.moviefleur;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FleurVideoTest {

    @Test
    public void testExtract() {
        // Setup
        final File file = new File("img/video_default.mp4");
        final int framePosition = 10;

        // Run the test
        final BufferedImage result = FleurVideo.extract(file, framePosition);

        // Verify the results
        assertEquals(1920, result.getWidth());
    }

    @Test
    public void testExtract1() {
        // Setup
        final File file = new File("img/video_default.mp4");

        // Run the test
        final List<BufferedImage> result = FleurVideo.extract(file, 1, 10);

        // Verify the results
        assertEquals(10, result.size());
    }

    @Test
    public void testCreate() {
        // Setup
        final List<BufferedImage> imageList = FleurVideo.extract(
                new File("img/video_default.mp4"), 1, 5);
        final String fileName = "videoTest.mp4";

        // Run the test
        FleurVideo.create(imageList, fileName);

        // Verify the results
        assertTrue(300000 < new File(fileName).length());
    }
}

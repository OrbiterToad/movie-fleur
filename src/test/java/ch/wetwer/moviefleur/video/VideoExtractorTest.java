package ch.wetwer.moviefleur.video;

import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VideoExtractorTest {

    private VideoExtractor videoExtractorUnderTest;

    @Before
    public void setUp() {
        videoExtractorUnderTest = new VideoExtractor();
    }

    @Test
    public void testExtractFrame() {
        // Setup
        final File file = new File("img/video_default.mp4");
        final int framePosition = 10;

        // Run the test
        final BufferedImage result = videoExtractorUnderTest.extractFrame(file, framePosition);

        // Verify the results
        assertEquals(1920, result.getWidth());
    }

    @Test
    public void testExtractAllSpecific() {
        // Setup
        final File file = new File("img/video_default.mp4");

        // Run the test
        final List<BufferedImage> result = videoExtractorUnderTest.extractAll(file, 1, 10);
        final List<BufferedImage> resultShoved = videoExtractorUnderTest.extractAll(file, 10, 20);

        // Verify the results
        assertEquals(10, result.size());
        assertEquals(11, resultShoved.size());
    }
}

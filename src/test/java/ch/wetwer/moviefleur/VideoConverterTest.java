package ch.wetwer.moviefleur;

import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class VideoConverterTest {

    private VideoBuilder mockVideoBuilder = VideoBuilder.builder().inputFile("test.mp4").build();

    private VideoConverter videoConverterUnderTest;

    @Before
    public void setUp() {
        videoConverterUnderTest = new VideoConverter(mockVideoBuilder);
    }

    @Test
    public void testGetImage() throws Exception {
        // Setup
        final File inputFile = new File("img/frame_default.png");
        final BufferedImage expectedResult = ImageIO.read(new File("img/frame_default.png"));

        // Run the test
        final BufferedImage result = videoConverterUnderTest.getImage(inputFile);

        // Verify the results
        assertEquals(expectedResult.getWidth(), result.getWidth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetImage_ThrowsIllegalArgumentException() throws Exception {
        // Setup
        final File inputFile = null;

        // Run the test
        videoConverterUnderTest.getImage(inputFile);
    }
}

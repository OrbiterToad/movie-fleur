package ch.wetwer.moviefleur.video;

import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VideoCompilerTest {

    private VideoCompiler videoCompilerUnderTest;

    @Before
    public void setUp() {
        videoCompilerUnderTest = new VideoCompiler();
    }

    @Test
    public void testCreate() {
        // Setup
        final List<BufferedImage> imageList = new VideoExtractor().extractAll(
                new File("img/video_default.mp4"), 1, 5);
        final String fileName = "videoTest.mp4";

        // Run the test
        videoCompilerUnderTest.create(imageList, fileName);

        // Verify the results
        assertEquals(382639, new File(fileName).length());
    }

    @Test
    public void testConvertToType() throws IOException {
        // Setup
        final BufferedImage sourceImage = ImageIO.read(new File("img/frame_default.png"));
        final int type = BufferedImage.TYPE_3BYTE_BGR;

        // Run the test
        final BufferedImage result = VideoCompiler.convertToType(sourceImage, type);

        // Verify the results
        assertEquals(type, result.getType());
    }
}

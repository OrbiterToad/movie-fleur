package ch.wetwer.moviefleur;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoBuilderTest {

    private VideoBuilder videoBuilderUnderTest;

    @Before
    public void setUp() {
        videoBuilderUnderTest = new VideoBuilder("inputFile", "outputFile");
    }

    @Test
    public void testSetInputFile() {
        // Setup
        final String inputFile = "inputFile";

        // Run the test
        videoBuilderUnderTest.setInputFile(inputFile);

        // Verify the results
    }

    @Test
    public void testSetOutputFile() {
        // Setup
        final String outputFile = "outputFile";

        // Run the test
        videoBuilderUnderTest.setOutputFile(outputFile);

        // Verify the results
    }

    @Test
    public void testHashCode() {
        // Setup
        final int expectedResult = 9;

        // Run the test
        final int result = videoBuilderUnderTest.hashCode();

        // Verify the results
        assertEquals(expectedResult, String.valueOf(result).length());
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "VideoBuilder(inputFile=inputFile, outputFile=outputFile)";

        // Run the test
        final String result = videoBuilderUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}

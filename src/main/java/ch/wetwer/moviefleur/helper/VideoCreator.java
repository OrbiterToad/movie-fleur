package ch.wetwer.moviefleur.helper;

import javax.media.MediaLocator;
import java.io.File;
import java.util.Vector;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 14.05.2019
 **/
public class VideoCreator {


    public void makeVideo(Vector<File> imgLst, int width, int height, String fileName) {

        JpegImagesToMovie imageToMovie = new JpegImagesToMovie();
        MediaLocator mediaLocator;
        if ((mediaLocator = JpegImagesToMovie.createMediaLocator(fileName)) == null) {
            System.err.println("Cannot build media locator from: " + fileName);
            System.exit(0);
        }
        imageToMovie.doIt(width, height, 20, imgLst, mediaLocator);
    }
}

package ch.wetwer.moviefleur;

import java.io.File;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.scorefleur
 * @created 08.05.2019
 **/
public class Main {

    public static void main(String[] args) {
        VideoConverter videoConverterFromImg = new VideoConverter("img");
        videoConverterFromImg.convertFromImg(new File("img/frame_default.png"));

        /* only use if a 3d video is available
            VideoBuilder videoBuilder = VideoBuilder.builder().inputFile("Test3D.mp4").build();
            VideoConverter videoConverterFromVideo = new VideoConverter(videoBuilder, "img");
            videoConverterFromVideo.convert(6000);
        */
    }

}

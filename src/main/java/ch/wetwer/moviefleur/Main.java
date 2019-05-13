package ch.wetwer.moviefleur;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.scorefleur
 * @created 08.05.2019
 **/
public class Main {

    public static void main(String[] args) {
//        VideoConverter videoConverterFromImg = new VideoConverter("img");
//        videoConverterFromImg.convertFromImg(new File("img/frame_default.png"));

        VideoBuilder videoBuilder = VideoBuilder.builder().inputFile("3dVideo.mp4").build();
        VideoConverter videoConverterFromVideo = new VideoConverter(videoBuilder);
        videoConverterFromVideo.convert(6000);
    }

}

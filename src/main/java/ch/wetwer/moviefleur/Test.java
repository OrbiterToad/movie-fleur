package ch.wetwer.moviefleur;

import ch.wetwer.moviefleur.helper.AdditiveCombiner;
import ch.wetwer.moviefleur.helper.FilterColor;
import ch.wetwer.moviefleur.helper.ImageHelper;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur
 * @created 24.05.2019
 **/
public class Test {

    public static void main(String[] args) throws IOException {

        new AdditiveCombiner().getCombined(
                ImageHelper.filterColor(
                        ImageIO.read(new File("img/frame_6000_left.png")), FilterColor.GREEN),
                ImageHelper.filterColor(
                        ImageIO.read(new File("img/frame_6000_right.png")), FilterColor.RED)
        );
    }
}

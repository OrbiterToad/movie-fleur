package ch.wetwer.moviefleur;

import lombok.Builder;
import lombok.Data;

/**
 * @author Wetwer
 * @project score-fleur
 * @package ch.wetwer.scorefleur
 * @created 08.05.2019
 **/

@Data
@Builder
public class VideoBuilder {

    private String inputFile;

    private String outputFile;

}

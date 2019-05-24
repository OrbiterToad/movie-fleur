package ch.wetwer.moviefleur.helper;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 13.05.2019
 **/
public enum FilterColor {

    RED(0xFFFF0000),
    GREEN(0xFF00FF00),
    BLUE(0xFF0000FF);

    private int mask;

    FilterColor(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }
}

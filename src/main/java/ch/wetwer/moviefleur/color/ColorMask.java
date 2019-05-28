package ch.wetwer.moviefleur.color;

/**
 * @author Wetwer
 * @project movie-fleur
 * @package ch.wetwer.moviefleur.helper
 * @created 13.05.2019
 **/
public enum ColorMask {

    RED(0xFFFF0000),
    GREEN(0xFF00FF00),
    BLUE(0xFF0000FF),
    GREEN_BLUE(0xFF00FFFF);

    private int mask;

    ColorMask(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }
}

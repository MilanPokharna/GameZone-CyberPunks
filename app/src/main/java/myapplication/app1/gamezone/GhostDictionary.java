package myapplication.app1.gamezone;

/**
 * Created by MiLaN on 05-11-2017.
 */

public interface GhostDictionary {
    public final static int MIN_WORD_LENGTH = 4;
    boolean isWord(String word);
    String getAnyWordStartingWith(String prefix);
    String getGoodWordStartingWith(String prefix);
}

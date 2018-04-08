package myapplication.app1.gamezone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by MiLaN on 05-11-2017.
 */

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {

        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if (prefix.equals(""))
        {
            Random random = new Random();
            return words.get(random.nextInt(10000));
        }
        else
            return binarySearch(prefix);
    }

    private String binarySearch(String prefix) {
        String dictionaryWord;
        int low = 0;
        int high = (words.size() - 1);
        while (high >= low)
        {
            int middle = ((high+low)/2);
            dictionaryWord = words.get(middle);
            if (dictionaryWord.startsWith(prefix))
            {
                return dictionaryWord;
            }
            else if (dictionaryWord.compareTo(prefix) < 0)
            {
                low = middle+1;
            }
            else
            {
                high = middle-1;
            }
        }
        String n = null;
        return n;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }
}


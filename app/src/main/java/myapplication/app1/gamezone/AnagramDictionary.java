package myapplication.app1.gamezone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by MiLaN on 05-11-2017.
 */

class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private int wordlength = DEFAULT_WORD_LENGTH;

    public ArrayList<String> wordList = new ArrayList<>();
    public HashMap<String, ArrayList<String>> lettersToWOrd = new HashMap<>();
    public HashSet<String> wordSet = new HashSet<>();

    public HashMap<Integer, ArrayList<String>> sizeToWords = new HashMap<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim(); //removes the unwanted spaces

            wordList.add(word);

             wordSet.add(word);

            String sortedWord = sortLetters(word);

            //if hm already contains the key , we add it to the same key
            if (lettersToWOrd.containsKey(sortedWord))
            {
                lettersToWOrd.get(sortedWord).add(word);
            }
            //else we create a new arraylist and add that as the value to that key in the hm
            else {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(word);
                lettersToWOrd.put(sortedWord, temp);
            }
            //check word length and store in sizeToWords
            if (sizeToWords.containsKey(word.length()))
            {
                sizeToWords.get(word.length()).add(word);
            }
            else
            {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(word);
                sizeToWords.put(word.length(),temp);
            }
        }

    }

    public boolean isGoodWord(String word, String base)
    {
        //if valid , then hashSet has the word in it && the word should not be
        //a sub string of the baseWord , i.e prefix or postfix
        return (wordSet.contains(word) && !word.contains(base));
    }

    public ArrayList<String> getAnagrams(String targetWord)
    {
        ArrayList<String> result = new ArrayList<>();

        //sort the target word
        String sortedTargetWord = sortLetters(targetWord);

        //first step is to iterate through all 10000 words and find the anagrams
        for (String word : wordList)
        {
            //sort the word
            String sortedWord = sortLetters(word);

            //if it matches to sortedTargetWord, then its an anagram of it
            if (sortedTargetWord.equals(sortedWord))
            {
                //add the original word
                result.add(word);
            }
        }

        return result;

    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word)
    {
        ArrayList<String> result = new ArrayList<String>();

        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<alphabets.length(); i++)
        {
            String tempString = word;

            //add every letter and get the key
            tempString += alphabets.charAt(i);

            String key = sortLetters(tempString);

            //check if that key exists
            if (lettersToWOrd.containsKey(key))
            {
                //get all the values for that key
                ArrayList<String> tempList = lettersToWOrd.get(key);

                //check if the obtained words are notGoodWords again
                ArrayList<String> removeList = new ArrayList<>();
                for (String test : tempList)
                {
                    if (!isGoodWord(test, word))
                    {
                        removeList.add(test);
                    }
                }

                //remove all the notGoodWords
                tempList.removeAll(removeList);

                //add the list to the remaining list to be returned
                result.addAll(tempList);
            }
        }

        return result;
    }


    public String pickGoodStarterWord()
    {
        while (true)
        {
            //get all words with 3/4/5/ letters and pick from them only
            ArrayList<String> tempList = sizeToWords.get(wordlength);

            //generate a random number between 0 and sizeOf  list obtained
            Random random = new Random();
            int num = random.nextInt(tempList.size());


            //pick random word from the ArrayList
            String randomWord = tempList.get(num);

            //get all the anagrams for that random word
            ArrayList<String> arrayList = getAnagramsWithOneMoreLetter(randomWord);

            //validate the condition given
            if ((randomWord.length() == wordlength) && arrayList.size() > MIN_NUM_ANAGRAMS)
            {
                //increment the wordLength for next stage
                if (wordlength < MAX_WORD_LENGTH)
                    wordlength++;
                return randomWord;
            }
        }
    }

    public String sortLetters(String word)
    {
        char[] words = word.toCharArray();
        Arrays.sort(words);
        return new String(words);
    }
}


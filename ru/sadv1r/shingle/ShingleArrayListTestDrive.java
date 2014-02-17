package ru.sadv1r.shingle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author sadv1r
 * @version 0.1
 *          <p/>
 *          Created 2/11/14, 21:24 PM
 */
public class ShingleArrayListTestDrive {
    public String randomStringCreate(int wordsForString) {
        final String[] randomWords = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                ".", ",", "!", "?", ":", ";", "-", "\\", "/", "*", ")",
                "как", "не", "на", "в", "под", "кот", "дом", "стол", "home", "LOL", "Java", "TesT",
                "мама", "акfr", "лист", "ВКонтакте", "random", "parser", "hi", "почему", "ковер"};
        final double frequencyOfSpaces = 0.4;
        double randomNumber;
        String randomString = "";

        for (int i = 0; i < wordsForString; i++) {
            randomNumber = Math.random();
            randomString = randomString + randomWords[(int) (randomNumber * randomWords.length)];
            if (randomNumber < frequencyOfSpaces) {
                randomString = randomString + " ";
            }
        }

        return randomString;
    }

    @Test
    public void genShingle() {
        ShingleArrayList shingle = new ShingleArrayList();

        final int numberOfShinglesToTest = 25;

        for (int i = 0; i < numberOfShinglesToTest; i++) {
            int wordsForString = (int) Math.random() * 50;
            ArrayList<Integer> shingles = shingle.genShingle(randomStringCreate(wordsForString));
            assertTrue(shingles.size() == wordsForString);
        }
    }

    @Test
    public void compare() {
        ShingleArrayList shingle = new ShingleArrayList();

        ArrayList<ArrayList> someShingles = new ArrayList<ArrayList>();

        ArrayList<Integer> someShingle1 = new ArrayList<Integer>() {{
            add(-1686779329); add(-933210460); add(1260094582); add(-1254527460); add(200118338); add(67870150); add(-726237134); add(-494663146); add(978910674); add(-1840938762); add(-647949302); add(1056334202);
        }};
        someShingles.add(someShingle1);

        ArrayList<Integer> someShingle2 = new ArrayList<Integer>() {{
            add(-1686779329); add(-933210460); add(555414534); add(200118338); add(67870150); add(-726237134); add(-1254527460); add(-1840938762); add(-647949302); add(1056334202);
        }};
        someShingles.add(someShingle2);

        ArrayList<Integer> someShingle3 = new ArrayList<Integer>() {{
            add(-1686779329); add(267087682); add(200118338); add(67870150); add(-891326518); add(-1840938762); add(-647949302); add(1056334202);
        }};
        someShingles.add(someShingle3);

        ArrayList<Integer> someShingle4 = new ArrayList<Integer>() {{
            add(-1686779329); add(-933210460); add(555414534); add(-1840938762); add(-647949302); add(1056334202);
        }};
        someShingles.add(someShingle4);

        ArrayList<Integer> someShingle5 = new ArrayList<Integer>() {{
            add(-1476300136); add(-100773279); add(619538934); add(-1807486980); add(-443182373); add(-1333482600); add(-364592428); add(-1587253243); add(-1764138658); add(-1031010691);
        }};
        someShingles.add(someShingle5);

        final int[] compareResults = {100, 81, 60, 55, 0};

        for (int i = 0; i < someShingles.size(); i++) {
            double compareResult = shingle.compare(someShingles.get(0), someShingles.get(i));

            assertTrue(compareResult >= 0 && compareResult <= 100);

            assertEquals(compareResults[i], (int) compareResult);
        }
    }

}

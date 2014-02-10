package ru.sadv1r.shingle;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author sadv1r
 *
 * Created 2/11/14, 1:21 AM
 */
public class ShingleTestDrive {
    public String randomStringCreate() {
        final String[] randomWords = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", ",", "!", "?",
                ":", ";", "-", "\\", "/", "*", ")", "как", "не", "на", "в", "под",
                "кот", "дом", "стол", "home", "LOL", "Java", "TesT", "мама", "акfr",
                "лист", "ВКонтакте", "random", "parser", "hi", "почему", "ковер"};
        final int wordsForString = 50;
        final double frequencyOfSpaces = 0.4;
        String randomString = "";
        double randomNumber;

        for (int i = 0; i <= wordsForString; i++) {
            randomNumber = Math.random();
            randomString = randomString + randomWords[(int) (Math.random() * randomWords.length)];
            if (randomNumber < frequencyOfSpaces) {
                randomString = randomString + " ";
            }
        }

        return randomString;
    }

    @Test
    public void genShingle() {
        Shingle shingle = new Shingle();

        final int numberOfShinglesToTest = 25;

        for (int i = 0; i < numberOfShinglesToTest; i++) {
            String shinglesString = shingle.genShingle(randomStringCreate());

            assertTrue(shinglesString.matches("^-?\\d{4,10};.+"));
            assertTrue(shinglesString.matches("^[\\d{4,10}|\\-|;]*$"));                                                 //FIXME!!! минусов может не быть, а точки заяпятые и цифры должны

            String shingles[] = shinglesString.split(";");

            for (String shing : shingles) assertTrue(shing.matches("-?\\d+"));
        }
    }

    @Test
    public void compare() {
        Shingle shingle = new Shingle();

        final String[] someShingles = {"-1686779329;-933210460;1260094582;-1254527460;200118338;67870150;-726237134;-494663146;978910674;-1840938762;-647949302;1056334202;",
                "-1686779329;-933210460;555414534;200118338;67870150;-726237134;-1254527460;-1840938762;-647949302;1056334202;",
                "-1686779329;267087682;200118338;67870150;-891326518;-1840938762;-647949302;1056334202;",
                "-1686779329;-933210460;555414534;-1840938762;-647949302;1056334202;",
                "-1476300136;-100773279;619538934;-1807486980;-443182373;-1333482600;-364592428;-1587253243;-1764138658;-1031010691;"};
        final int[] compareResults = {100, 81, 60, 55, 0};

        for (int i = 0; i < someShingles.length; i++) {
            double compareResult = shingle.compare(someShingles[0], someShingles[i]);

            assertTrue(compareResult >= 0 && compareResult <= 100);

            assertEquals(compareResults[i], (int) compareResult);                                                        //FIXME!!! assertEquals problem with int
        }
    }


}

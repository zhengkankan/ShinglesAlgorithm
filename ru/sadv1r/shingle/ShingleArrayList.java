package ru.sadv1r.shingle;

import java.util.ArrayList;

/**
 * @author sadv1r
 * @version 0.6
 *
 * Created 2/11/14, 3:29 AM
 */
public class ShingleArrayList {
    private static final String STOP_SYMBOLS[] = {".", ",", "!", "?", ":", ";", "-", "\\", "/", "*", "(", ")", "+", "@",
                                                  "#", "$", "%", "^", "&", "=", "'", "\"", "[", "]", "{", "}", "|"};
    private static final String STOP_WORDS[] = {"это", "как", "так", "и", "в", "над", "к", "до", "не", "на", "но", "за",
                                                "то", "с", "ли", "а", "во", "от", "со", "для", "о", "же", "ну", "вы",
                                                "бы", "что", "кто", "он", "она"};
    private static final int SHINGLE_LEN = 2;

    private String canonize(String str) {
        for (String stopSymbol : STOP_SYMBOLS) {
            str = str.replace(stopSymbol, "");
        }

        for (String stopWord : STOP_WORDS) {
            str = str.replace(" " + stopWord + " ", " ");
        }

        return str;
    }

    public ArrayList<Integer> genShingle(String strNew) {
        ArrayList<Integer> shingles = new ArrayList<Integer>();
        String str = canonize(strNew.toLowerCase());
        String words[] = str.split(" ");
        int shinglesNumber = words.length - SHINGLE_LEN;

        //Create all shingles
        for (int i = 0; i <= shinglesNumber; i++) {
            String shingle = "";

            //Create one shingle
            for (int j = 0; j < SHINGLE_LEN; j++) {
                shingle = shingle + words[i+j] + " ";
            }

            shingles.add(shingle.hashCode());
        }

        return shingles;
    }

    public double compare(ArrayList<Integer> textShingles1New, ArrayList<Integer> textShingles2New) {
        //textShingles1New and textShingles2New equals "" or null bug fix
        if (textShingles1New == null || textShingles2New == null) return 0.0;

        int textShingles1Number = textShingles1New.size();
        int textShingles2Number = textShingles2New.size();

        double similarShinglesNumber = 0;

        for (int i = 0; i < textShingles1Number; i++) {
            for (int j = 0; j < textShingles2Number; j++) {
                if (textShingles1New.get(i).equals(textShingles2New.get(j))) similarShinglesNumber++;
            }
        }

        return ((similarShinglesNumber / ((textShingles1Number + textShingles2Number) / 2.0)) * 100);
    }
}

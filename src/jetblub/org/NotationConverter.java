package jetblub.org;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotationConverter {
    private String romanNotation;

    private String getRomanNotation() {
        return romanNotation;
    }

    private void setRomanNotation(String romanNotation) {
        this.romanNotation = romanNotation;
    }

    public int toArabic(String romanNotation) throws InvalidAttributeValueException {
        if (romanNotation == null) {
            throw new InvalidAttributeValueException("String must contain only valid roman numerals [I, V, X, L, C, D, M]");
        }
        if (isValid(romanNotation)) {
            setRomanNotation(romanNotation);

            HashMap<String, Integer> romanMap = getMap();

            String[] letters = getStringArray(getRomanNotation());

            List<String> stringList = getListNumbers(letters);

            return getResult(stringList, romanMap);
        } else {
            throw new InvalidAttributeValueException("String must contain only valid roman numerals [I, V, X, L, C, D, M]");
        }
    }

    private static String[] getStringArray(String romanNotation){
        String[] letters = romanNotation.split("(?<=.)");
        return letters;
    }

    private static List<String> getListNumbers(String[] letters){
        List<String> stringList = new ArrayList<>();
        String buffer = "";
        for (int i = 0; i < letters.length; i++) {
            if (i != (letters.length - 1)) {
                if (letters[i].equals("I") && letters[i + 1].equals("V")) {
                    buffer = letters[i] + letters[i + 1];
                    stringList.add(buffer);
                    i++;
                } else if (letters[i].equals("I") && letters[i + 1].equals("X")) {
                    buffer = letters[i] + letters[i + 1];
                    stringList.add(buffer);
                    i++;
                } else if (letters[i].equals("X") && letters[i + 1].equals("L")) {
                    buffer = letters[i] + letters[i + 1];
                    stringList.add(buffer);
                    i++;
                } else if (letters[i].equals("X") && letters[i + 1].equals("C")) {
                    buffer = letters[i] + letters[i + 1];
                    stringList.add(buffer);
                    i++;
                } else if (letters[i].equals("C") && letters[i + 1].equals("D")) {
                    buffer = letters[i] + letters[i + 1];
                    stringList.add(buffer);
                    i++;
                } else if (letters[i].equals("C") && letters[i + 1].equals("M")) {
                    buffer = letters[i] + letters[i + 1];
                    stringList.add(buffer);
                    i++;
                } else {
                    stringList.add(letters[i]);
                }
            } else {
                stringList.add(letters[i]);
            }
        }
        return stringList;
    }

    private static HashMap<String, Integer> getMap() {
        HashMap<String, Integer> romanMap = new HashMap<>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);
        romanMap.put("IV", 4);
        romanMap.put("IX", 9);
        romanMap.put("XL", 40);
        romanMap.put("XC", 90);
        romanMap.put("CD", 400);
        romanMap.put("CM", 900);
        return romanMap;
    }

    private static int getResult(List<String> stringList, HashMap<String, Integer> romanMap) {
        int result = 0;
        for (int i = 0; i < stringList.size(); i++) {
            result += romanMap.get(stringList.get(i));
        }
        return result;
    }

    private static boolean isValid(String romanNotation) {
        boolean check;
        Pattern pattern = Pattern.compile("(I)|(V)|(X)|(L)(C)(D)(M)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(romanNotation);
        if (romanNotation.equals("") || !matcher.find()) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }
}

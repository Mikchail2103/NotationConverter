package jetblub.org;

import javax.management.InvalidAttributeValueException;

public class Main {

    public static void main(String[] args) throws InvalidAttributeValueException {
        String test = "CMXLIX";
        NotationConverter notationConverter = new NotationConverter();
        int result = notationConverter.toArabic(test);
        System.out.println(result);
    }
}

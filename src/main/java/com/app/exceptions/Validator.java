package com.app.exceptions;

public class Validator {

    public static boolean isStringValid(String... s)  {
        for (String tmp : s) {
            if (!tmp.matches("[A-Z ]+")) {
                throw new MyException("Musisz użyć dużych liter! Błąd : " + tmp);
            }
        }
        return true;

    }
}

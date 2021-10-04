package com.company;
import java.lang.String;
import java.util.ArrayList;

public class Validator {

    int password_length = 8;
    char[] specialSymbolsConfig = {'!','@','#','$','%','^','&','*','-','(',')',',','.'};
    ArrayList<String> countryPrefixes = new ArrayList<String>();
    ArrayList<Integer> countryNumberLength = new ArrayList<Integer>();

    private void initCountries () {
        if (countryPrefixes.size()==0) {
            countryPrefixes.add("+370");
            countryNumberLength.add(12);
            countryPrefixes.add("+72");
            countryNumberLength.add(11);
        }
    }

    public boolean passwordChecker(String password){
        if (password == null) {
            return false;
        }
        if (isValidLength(password) == false) {
            return false;
        }
        if (hasSpecialSymbol(password) == false) {
            return false;
        }
        if (hasUppercase(password) == false) {
            return false;
        }

        return true;
    }

    private boolean isValidLength(String password){
        if (password.length() >= password_length)
            return true;
        else
            return false;
    }

    private boolean hasSpecialSymbol(String password){
        for (int i=0; i<password.length(); i++) {

            for (int j=0; j<specialSymbolsConfig.length; j++) {

                if (password.charAt(i) == specialSymbolsConfig[j])
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasUppercase(String password){
        for (int i=0; i<password.length(); i++) {

            if ((password.charAt(i) >= 'A') && (password.charAt(i) <= 'Z')) {
                return true;
            }
        }
        return false;
    }

    public boolean phoneNumberChecker(String number, String prefix){
        if (number == null) {
            return false;
        }

        convertPrefix(number);

        if (isValidForCountry(number,prefix) == false) {
            return false;
        }

        if (numbersOnly(number) == false) {
            return false;
        }
        return true;
    }

    public void addNumberValidationCountry (String prefix, int length) {
        countryPrefixes.add(prefix);
        countryNumberLength.add(length);
    }

    private boolean isValidForCountry (String number, String prefix){
        initCountries();

        for (int i=0; i<countryPrefixes.size(); i++) {

            if (prefix == countryPrefixes.get(i)) {
                    if (number.length()==countryNumberLength.get(i)){
                        return true;
                    }
                    else return false;
            }
        }
        return false;
    }

    public boolean numbersOnly(String number){
        int j=0;

        if (number.charAt(0)=='+') {
            j = 1;
        }

        for (int i = j; i<number.length(); i++) {

            if ((number.charAt(i) < '0') || (number.charAt(i) > '9')) {
                return false;
            }
        }
        return true;
    }

    public String convertPrefix(String number){
     if (number.charAt(0) == '8'){
         number = number.substring(1);
         number = "+370" + number;
     }
        return number;
    }

    public boolean emailChecker(String email){
        if (email == null){
            return false;
        }

        if (allowedSymbolsOnly(email) == false){
            return false;
        }

        if (hasEta(email) == false){
            return false;
        }

        if (validateTLDAndDomain(email) == false){
            return false;
        }

        return true;
    }

    private boolean hasEta(String email){
        for (int i=0; i<email.length(); i++) {

            if (email.charAt(i)=='@') {
                return true;
            }
        }
        return false;
    }

    public boolean allowedSymbolsOnly(String email){
        for (int i=0; i<email.length(); i++) {

            if ((email.charAt(i) >= 'A' && email.charAt(i) <= 'Z')
                || (email.charAt(i) >= 'a' && email.charAt(i) <= 'z')
                || (email.charAt(i) >= '0' && email.charAt(i) <= '9')
                || (email.charAt(i) == '.' ) || (email.charAt(i) == '-') || (email.charAt(i) == '@')) {}
            else return false;
        }
        return true;
    }

    private boolean validateTLDAndDomain(String email){
        boolean hasTLD = false;

        for (int i=0; i<email.length(); i++) {
            if (email.charAt(i)=='@') {

                for (int j = i+1; j<email.length(); j++) {
                    if ((email.charAt(j) == '.') && (j!=i+1)) {

                        for (int z = j+1; z<email.length(); z++) {
                            if ((email.charAt(j) >= 'a') && (email.charAt(j) <= 'z')) {
                                hasTLD = true;
                            }
                            else return false;
                        }
                    }

                    if ((email.charAt(j) >= 'a') && (email.charAt(j) <= 'z')
                            || ((email.charAt(j) >= '0') && (email.charAt(j) <= '9'))) {}
                    else return false;
                }
            }
        }
        if (hasTLD==true){
            return true;
        }
        else return false;
    }

}

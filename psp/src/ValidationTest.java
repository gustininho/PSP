package com.my.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationTest {

    Validator validator = new Validator();

    // ------------Password validation--------------
    @Test
    void testPasswordLengthValidation() {
        Assertions.assertTrue(validator.isPasswordLengthValid("Slaptazodziukas1."));
    }

    @Test
    void testUppercaseValidation() {
        Assertions.assertTrue(validator.uppercaseExist("Slaptazodziukas1."));
    }

    @Test
    void testSymbolValidation() {
        Assertions.assertTrue(validator.symbolExist("Slaptazodziukas1."));
    }

    // ------------Phone number validation--------------

    @Test
    void testNumbersValidation() {
        Assertions.assertTrue(validator.isNumbersOnly("86930345"));
    }

    @Test
    void testAdditionCountryPrefix() {
        Assertions.assertEquals("+3706930345",validator.addCountryPrefix("86930345"));
    }

    @Test
    void testCountryValid() {
        Assertions.assertTrue(validator.isValidForCountry("+370","86930345")); // checks for specific country requirements
    }

    // ------------Email validation--------------

    @Test
    void testEtaValidation() {
        Assertions.assertTrue(validator.etaExist("aaaa@gmail.com");
    }

    @Test
    void testEmailAllowedSymbolsValidation() {
        Assertions.assertTrue(validator.symbolsValid("aaaa@gmail.com");
    }

    @Test
    void testEmailDomainTldValidation() {
        Assertions.assertTrue(validator.isValidDomainTld("aaaa@gmail.com");
    }

}
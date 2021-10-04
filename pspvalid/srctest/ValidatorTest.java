
import com.company.Validator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    Validator validator;

    @Before
    public void init() {
        validator = new Validator();
    }

    // passwordChecker tests

    @Test
    public void passwordChecker_ValidPassword() {
        String password = "Abc-def-123"; // Changed "abc-def-123" to "Abc-def-123" as it is not valid but expects true
        assertTrue("Password should be valid", validator.passwordChecker(password));
    }

    @Test
    public void passwordChecker_notEmpty() {
        String password = " ";
        assertFalse("Password should not be empty", validator.passwordChecker(password));
    }

    @Test
    public void passwordChecker_notNull() {
        assertFalse("Password should not be null", validator.passwordChecker(null));
    }

    @Test
    public void passwordChecker_NotShorterThan8() {
        String password = "abc-12";
        assertFalse("Password should not be shorter than 8 symbols", validator.passwordChecker(password));
    }

    @Test
    public void passwordChecker_NoUppercaseSymbols() {
        String password = "abc1-def234";
        assertFalse("Password should include uppercase symbols", validator.passwordChecker(password));
    }

    @Test
    public void passwordChecker_NoSpecialSymbols() {
        String password = "Abcde12345";
        assertFalse("Password should have special symbols", validator.passwordChecker(password));
    }

    // phoneNumberChecker tests

    @Test
    public void phoneNumberChecker_ValidNumber() {
        String phoneNumber = "+37062589867";
        String prefix = "+370";
        assertTrue("Phone number should be valid", validator.phoneNumberChecker(phoneNumber,prefix));
    }

    @Test
    public void phoneNumberChecker_NotEmpty() {
        String phoneNumber = " ";
        String prefix = "+370";
        assertFalse("Phone number should not be empty", validator.phoneNumberChecker(phoneNumber,prefix));
    }

    @Test
    public void phoneNumberChecker_NotNull() {
        String prefix = "+370";
        assertFalse("Phone number should be null", validator.phoneNumberChecker(null,prefix));
    }

    @Test
    public void phoneNumberChecker_NoLetters() {
        String phoneNumber = "8653836F2";
        String prefix = "+370";
        assertFalse("Phone should not contain letters", validator.phoneNumberChecker(phoneNumber,prefix));
    }

    @Test
    public void phoneNumberChecker_ConvertsPrefix() {
        String phoneNumber = "862385652";
        assertEquals("Local prefixes should be converted", "+37062385652", validator.convertPrefix(phoneNumber)); // Wrong sequence of arguments
    }

    @Test
    public void phoneNumberChecker_LTPrefix() {
        String phoneNumber = "+37062183742";
        String prefix = "+370";
        assertTrue("Phone number should not have an invalid prefix", validator.phoneNumberChecker(phoneNumber,prefix));
    }

    @Test
    public void phoneNumberChecker_LTNumberLength() {
        String phoneNumber = "+37062589666";
        String prefix = "+370";
        assertTrue("Phone number should be correct length", validator.phoneNumberChecker(phoneNumber,prefix));
    }

    @Test
    public void phoneNumberChecker_LTNumberTooLong() {
        String phoneNumber = "+3706553295256233";
        String prefix = "+370";
        assertFalse("Phone number should not be longer than X", validator.phoneNumberChecker(phoneNumber,prefix));
    }

    @Test
    public void phoneNumberChecker_OtherPrefix() {
        String phoneNumber = "+7223283601";
        String prefix = "+72";
        assertTrue("Phone number should be able to have other allowed prefixes", validator.phoneNumberChecker(phoneNumber,prefix));
    }

    // email checker tests

    @Test
    public void emailChecker_ValidEmailAddress() {
        String email = "mariussomka@mail.com";
        assertFalse("Email address should be valid", validator.emailChecker(email));
    }

    @Test
    public void emailChecker_NotEmpty() {
        String email = " ";
        assertFalse("Email address should not be empty", validator.emailChecker(email));
    }

    @Test
    public void emailChecker_NotNull() {
        assertFalse("Email address should not be allowed to be null", validator.emailChecker(null));
    }

    @Test
    public void emailChecker_NoSpecialSymbol() {
        String email = "abcd.mail.com";
        assertFalse("Email address should have special symbols", validator.emailChecker(email));
    }

    @Test
    public void emailChecker_WrongDomain() {
        String email = "abcdefgh@a";
        assertFalse("Email address should not have wrong domain", validator.emailChecker(email));
    }

    @Test
    public void emailChecker_WrongTLD() {
        String email = "abcd@mail.aa";
        assertFalse("Email addresses should not have wrong tld", validator.emailChecker(email)); //.aa is Correct TLD
    }

    @Test
    public void emailChecker_NotAllowedSymbols() {
        String email = "abcd@!@#$Šmail.com";
        assertFalse("Email address should not contain not allowed symbols", validator.emailChecker(email));
    }
}
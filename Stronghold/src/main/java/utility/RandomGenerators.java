package utility;
import java.util.Random;

import org.passay.*;

public class RandomGenerators
{
    public static String randomPassword()
    {
        LengthRule lengthRule = new LengthRule(6, 12);
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase, 1);
        CharacterRule DCR = new CharacterRule(EnglishCharacterData.Digit, 1);
        CharacterRule SCR = new CharacterRule(SpecialCharacterData.NonWord, 1);

        WhitespaceRule WCR = new WhitespaceRule();

        return new PasswordGenerator().generatePassword(8, LCR, UCR, DCR, SCR);
    }

    public static void main(String[] args) {
        System.out.println(randomPassword());
    }

    public static String randomSlogan()
    {
        return null;
    }

    public static String randomCaptcha()
    {
        return null;
    }

    private int randomNumber()
    {
        return 0;
    }
}

enum SpecialCharacterData implements CharacterData
{

    NonWord("INSUFFICIENT_NON_WORD", "!@#$&*%_~.");
    private final String errorCode;
    private final String characters;
    SpecialCharacterData(String code, String charString) {
        this.errorCode = code;
        this.characters = charString;
    }


    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getCharacters() {
        return characters;
    }
}
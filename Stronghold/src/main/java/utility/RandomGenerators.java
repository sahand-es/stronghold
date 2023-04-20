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

    public static String randomSlogan()
    {
        String[] slogans =
                {
                        "Mustache Magic with Mammad - Let the Fun Begin!",
                        "Build a Big Funny Castle with MammaD's Mustache!",
                        "Embrace Fun with Yousef: Mustachioed Castles Make the Difference!",
                        "Experience the Mustache Magic at Yousef's Big Funny Castles!",
                        "Experience the Magic of Armin: Big Fun Mustache Castles at Sharif University!",
                        "Get your Mustache on with Armin at Sharif University - Nothing's Funnier!",
                        "Armin: Let Your Mustache Castles in Sharif University Roar with Sahand!",
                        "Bring the Big FunCastle to Sharif University: Mustache with Sahand, Armin and Youssef!",
                        "Mustache and the Sharif University Gang: Join the Fun Castle!",
                        "Mustache: Boost Your AP Project with Sad Sahand, Armin and Yousef!",
                        "Live the Castle Life with Sahand, Armin and Yousef: Make Your AP Project Memorable!",
                        "Escape the AP Stress with Castle! A Big, Fun Mustached Adventure at Sharif University with Sahand, Armin, and Yousef!",
                };

        return slogans[randomNumber(0, slogans.length - 1)];
    }

    public static String randomCaptcha()
    {
        CharacterRule DCR = new CharacterRule(EnglishCharacterData.Digit, 1);

        return new PasswordGenerator().generatePassword(randomNumber(4,8), DCR);
    }

    public static int randomNumber(int min, int max)
    {
        return (int)(Math.random()*(max-min+1)+min);
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
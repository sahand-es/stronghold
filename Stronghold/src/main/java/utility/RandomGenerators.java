package utility;
import java.util.Random;

import org.passay.*;
import view.Captcha;

public class RandomGenerators {

    /**
     *
     * @return random password that includes at least 1 of each [A-Z][a-z][0-9][!@#$&*%_~.] with length between 6-12
     */
    public static String randomPassword() {
        LengthRule lengthRule = new LengthRule(6, 12);
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase, 1);
        CharacterRule DCR = new CharacterRule(EnglishCharacterData.Digit, 1);
        CharacterRule SCR = new CharacterRule(SpecialCharacterData.NonWord, 1);

        WhitespaceRule WCR = new WhitespaceRule();

        return new PasswordGenerator().generatePassword(randomNumber(6, 12), LCR, UCR, DCR, SCR);
    }

    /**
     *
     * @return random slogan
     */
    public static String randomSlogan() {
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
    public static String randomAvatar() {
        return "/images/avatars/" + randomNumber(1, 9) + ".png";
    }

    /**
     * @return random 4-8 length number string
     */
    //this part is used only in CLI mode
    public static String randomCaptcha() {
        CharacterRule DCR = new CharacterRule(EnglishCharacterData.Digit, 1);

        return new PasswordGenerator().generatePassword(randomNumber(4,8), DCR);
    }



    /**
     *
     * @return random special character of "!@#$&*%_~."
     */
    public static char randomSpecialCharacter()
    {
        String specialCharacters = SpecialCharacterData.NonWord.getCharacters();
        return specialCharacters.charAt(randomNumber(0, specialCharacters.length() - 1));
    }

    /**
     *
     * @param percentage 0-100 % for probability of true
     * @return boolean false or true
     */
    public static boolean randomTrue(int percentage)
    {
        int rand = randomNumber(0, 100);
        return rand <= percentage;
    }

    /**
     *
     * @param min minimum
     * @param max maximum
     * @return random number between min-max
     */
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
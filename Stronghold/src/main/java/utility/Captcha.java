package utility;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Sahand
 */
public class Captcha {
    private String captchaString;

    public Captcha() {
        captchaString = RandomGenerators.randomCaptcha();
    }

    /**
     *
     * @param input string to check if it is identical to captcha
     * @return true when input is identical to captcha
     */
    public boolean checkCaptcha(String input) {
        return input.equals(captchaString);
    }

    /**
     *
     * @return an ascii art of captcha in a string
     */
    public String getCaptcha() {
        return makeAsciiArt();
    }

    /**
     *
     * @return resets initial captcha and Returns an ascii art of new captcha in a String
     */
    public String resetAndGetCaptcha() {
        captchaString = RandomGenerators.randomCaptcha();
        return getCaptcha();
    }

    private String addNoise(String asciiArt) {
        StringBuilder str = new StringBuilder(asciiArt);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                if (RandomGenerators.randomTrue(25))
                    str.setCharAt(i, RandomGenerators.randomSpecialCharacter());
        }

        return str.toString();
    }

    private String makeAsciiArt() {
        int width = captchaString.length() * 11;
        int height = 11;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics1 = image.getGraphics();
        graphics1.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 18));
        Graphics2D graphics = (Graphics2D) graphics1;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(captchaString, 0, 11);

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
            if (sb.toString().trim().isEmpty()) continue;
            if (y != height - 1) sb.append("\n");
        }
        return addNoise(sb.toString());
    }

}

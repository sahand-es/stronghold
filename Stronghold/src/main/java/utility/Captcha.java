package utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Captcha {
    private String captchaString;

    public Captcha() {
        captchaString = RandomGenerators.randomCaptcha();
    }

    public boolean checkCaptcha(String input) {
        return input.equals(captchaString);
    }

    public String getCaptcha() {
        return makeAsciiArt();
    }

    public String resetAndGetCaptcha() {
        captchaString = RandomGenerators.randomCaptcha();
        return getCaptcha();
    }

    public String addNoise(String asciiArt, int noisePercentage) {
        StringBuilder str = new StringBuilder(asciiArt);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                if (RandomGenerators.randomTrue(noisePercentage))
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
        return addNoise(sb.toString(), 25);
    }

}

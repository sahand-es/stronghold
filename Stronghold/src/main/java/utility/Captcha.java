package utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Captcha {
private final String captchaString;

    public Captcha() {
        captchaString = RandomGenerators.randomCaptcha();
    }


    private void makeAsciiArt(int width, int height)
    {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics1 = image.getGraphics();
        graphics1.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 15));
        Graphics2D graphics = (Graphics2D) graphics1;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(captchaString, 0, 24);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++)
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "*");
            if (sb.toString().trim().isEmpty()) continue;
            System.out.println(sb);
        }
    }


}

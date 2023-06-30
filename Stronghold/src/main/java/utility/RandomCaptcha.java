package utility;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RandomCaptcha {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;
    private static final int FONT_SIZE = 30;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 5;
    private static final Color BASE_COLOR = new Color(0, 0, 128); // Navy Blue
    private static final Color[] COLOR_PALETTE = {
            BASE_COLOR,
            BASE_COLOR.darker(),
            BASE_COLOR.darker().darker(),
            BASE_COLOR.darker().darker().darker(),
    };

    private static final Random random = new Random();

    public static String generateString() {
        // Generate random text
        StringBuilder captchaText = new StringBuilder();

        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            captchaText.append(randomChar);
        }
        String outString = captchaText.toString();
        return outString;
    }

    public static BufferedImage generateImage(String captchaText){

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        // Set background color
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);


        // Draw text on the image with random colors, positions, and rotations
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int x = 20 + i * (FONT_SIZE + 10);
            int y = 35;
            int rgb = random.nextInt(256);
            Color randomColor = COLOR_PALETTE[random.nextInt(COLOR_PALETTE.length)];
            graphics.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
            graphics.setColor(randomColor);

            // Generate a random rotation angle between -30 and 30 degrees
            int rotationAngle = random.nextInt(61) - 30;
            graphics.rotate(Math.toRadians(rotationAngle), x, y);
            graphics.drawString(String.valueOf(captchaText.charAt(i)), x, y);
            graphics.rotate(-Math.toRadians(rotationAngle), x, y);
        }

        // Add noise to the image
        for (int i = 0; i < 300; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int rgb = random.nextInt(256);
            Color randomColor = new Color(rgb, rgb, rgb);
            image.setRGB(x, y, randomColor.getRGB());
        }
        // add random line to image
        for (int i = 0; i < 3; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            graphics.setColor(Color.BLACK);
            graphics.setStroke(new BasicStroke(1));
            graphics.drawLine(x1, y1, x2, y2);
        }



        String path = "src/main/resources/captcha/captcha.png";
        // Save the image to a file
        try {
           ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            System.out.println("Error while saving CAPTCHA image: " + e.getMessage());
        }

        graphics.dispose();

        return image;
    }

}

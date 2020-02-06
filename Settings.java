import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Settings {
    static final int SCREEN_SIZE = 500;
    static final int RETAIN_SIZE = 100;
    static BufferedImage image;
    static BufferedImage greyImage;

    static {
        String fileName = new File(".").getAbsolutePath() + "\\images\\map.jpg";
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("no pictures found");
            System.exit(0);
        }
        greyImage = getGreyImage();
    }

    private static BufferedImage getGreyImage() {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage greyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                int red = color.getRed() / 2;
                int green = color.getGreen() / 2;
                int blue = color.getBlue() / 2;
                greyImage.setRGB(i, j, new Color(red, green, blue).getRGB());
            }
        }
        return greyImage;
    }

    private Settings() {}
}

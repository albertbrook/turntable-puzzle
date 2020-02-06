import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

class Screen extends Canvas {
    private static Screen screen;

    private BufferedImage image = Settings.image;
    private BufferedImage greyImage = Settings.greyImage;

    private final int center;
    private final int size;
    private final int location;
    private final int space;

    private int[] degrees = new int[6];
    private int[] indexes = new int[2];

    private Screen() {
        center = Settings.SCREEN_SIZE / 2;
        size = Settings.SCREEN_SIZE;
        location = -size / 2;
        space = (size - Settings.RETAIN_SIZE) / 10;
        setIndexes();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(center, center);
        for (int i = 0; i < 6; i++) {
            int drawSize = size - 2 * i * space;
            int drawLocation = location + i * space;
            g2.rotate(Math.toRadians(degrees[i]));
            g2.setClip(new Ellipse2D.Double(drawLocation, drawLocation, drawSize, drawSize));
            if (i == indexes[0] || i == indexes[1])
                g2.drawImage(greyImage, location, location, size, size, null);
            else
                g2.drawImage(image, location, location, size, size, null);
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    static Screen getScreen() {
        if (screen == null)
            screen = new Screen();
        return screen;
    }

    int[] getDegrees() {
        return degrees;
    }

    int[] getIndexes() {
        return indexes;
    }

    void setIndexes() {
        indexes[0] = 0;
        indexes[1] = 2;
    }
}

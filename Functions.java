import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

class Functions{
    private static Functions functions;

    private Main main;
    private Screen screen = Screen.getScreen();
    private int[] degrees = screen.getDegrees();
    private int[] indexes = screen.getIndexes();

    private Functions(Main main) {
        this.main = main;
        upset();
    }

    void event() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_S:
                        cognate(true);
                        break;
                    case KeyEvent.VK_W:
                        cognate(false);
                        break;
                    case KeyEvent.VK_D:
                        angle(true);
                        break;
                    case KeyEvent.VK_A:
                        angle(false);
                        break;
                    case KeyEvent.VK_R:
                        upset();
                        break;
                }
                screen.repaint();
            }
        };
        main.addKeyListener(keyListener);
        screen.addKeyListener(keyListener);
    }

    private void cognate(boolean flag) {
        if (flag && indexes[0] < 4)
            indexes[0]++;
        else if (!flag && indexes[0] > 0)
            indexes[0]--;
        if (indexes[0] == 0)
            indexes[1] = 2;
        else if (indexes[0] == 2)
            indexes[1] = 4;
        else if (indexes[0] == 4)
            indexes[1] = 0;
        else
            indexes[1] = -1;
    }

    private void angle(boolean flag) {
        int degree = flag ? 36 : -36;
        degrees[indexes[0]] += degree;
        degrees[indexes[0] + 1] -= degree;
        if (indexes[1] != -1) {
            degrees[indexes[1]] += degree;
            degrees[indexes[1] + 1] -= degree;
        }
        setRetainDegree();
    }

    private void setRetainDegree() {
        int degree = 0;
        for (int i = 0; i < degrees.length - 1; i++) {
            degrees[i] %= 360;
            degree += degrees[i];
        }
        degrees[degrees.length - 1] = -degree;
    }

    private void upset() {
        screen.setIndexes();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int r = random.nextInt(10);
            boolean b = random.nextBoolean();
            for (int j = 0; j <= r; j++) {
                angle(b);
            }
            cognate(true);
        }
        screen.setIndexes();
    }

    static Functions getFunctions(Main main) {
        if (functions == null)
            functions = new Functions(main);
        return functions;
    }
}

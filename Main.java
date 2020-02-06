import javax.swing.JFrame;

class Main extends JFrame {
    private Main() {
        setTitle("Turntable Puzzle - AlbertBrook");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(Settings.SCREEN_SIZE, Settings.SCREEN_SIZE);
        setVisible(true);
        setVisible(false);
        int size = Settings.SCREEN_SIZE;
        int differWidth = size - getContentPane().getWidth();
        int differHeight = size - getContentPane().getHeight();
        setSize(size + differWidth, size + differHeight);

        add(Screen.getScreen());
        Functions.getFunctions(this).event();
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}

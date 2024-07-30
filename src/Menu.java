import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;

public class Menu extends GameCanvas implements Runnable {
    private boolean isRunning = false;
    private Graphics g;
    private int selectedOption = 0;

    public Midlet midlet;

    private Image Title;
    private Image Play;
    private Image Exit;

    private int width;
    private int height;
    private int bg_x;
    private int bg_y;
    private int title_x;
    private int title_y;
    private int play_x;
    private int play_y;
    private int exit_x;
    private int exit_y;

    private String version="V1.4";

    public Menu() {
        super(false);
        setFullScreenMode(true);
        g = getGraphics();
        LoadImages();
        InitCoordinates();
    }

    private void LoadImages() {
        Title = Util.LoadImg("/start.png");
        Play = Util.LoadImg("/btn_start.png");
        Exit = Util.LoadImg("/btn_quit.png");
    }

    public void InitCoordinates() {
        width = getWidth();
        height = getHeight();

        int center_x = width / 2;
        int center_y = height / 2;

        bg_x = center_x - Util.bg.getWidth() / 2;
        bg_y = center_y - Util.bg.getHeight() / 2;
        title_x = center_x - Title.getWidth() / 2;
        title_y = center_y - Title.getHeight() / 2 - 70;
        play_x = center_x - Play.getWidth() / 2;
        play_y = center_y - Play.getHeight() / 2 + 120;
        exit_x = center_x - Exit.getWidth() / 2;
        exit_y = center_y - Exit.getHeight() / 2 + 170;
    }

    public void start() {
        isRunning = true;
        Thread t = new Thread(this);
        t.start();
    }

    private int keyTrigger = 0;

    private void tick() {
//        int keys = getKeyStates();
//
//        int inv = 0xffffffff - keyTrigger;
//        int key = inv & keys;
//        keyTrigger &= keys;
//
//        if ((key & DOWN_PRESSED) != 0) {
//            selectedOption = (selectedOption + 1) % 2;
//            keyTrigger |= DOWN_PRESSED;
//        }
//        if ((key & UP_PRESSED) != 0) {
//            selectedOption = (selectedOption - 1 + 2) % 2;
//            keyTrigger |= UP_PRESSED;
//        }
//        if ((key & FIRE_PRESSED) != 0) {
//            executeSelectedOption();
//            keyTrigger |= FIRE_PRESSED;
//        }
    }

    public void run() {
        draw();
        while (isRunning) {
//            tick();
//            if (!isRunning) break;
//            draw();
        }
    }

    public void stop() {
        isRunning = false;
    }

    private void draw() {
        g.setColor(0);
        g.fillRect(0, 0, width, height);
        g.drawImage(Util.bg, bg_x, bg_y, 0);
        g.drawImage(Title, title_x, title_y, Graphics.TOP | Graphics.LEFT);
        if (selectedOption == 0) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(play_x - 32, play_y - 8, 140, 32);
        g.drawImage(Play, play_x, play_y, 0);
        if (selectedOption == 1) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(exit_x - 32, exit_y - 8, 140, 32);
        g.drawImage(Exit, exit_x, exit_y, 0);
        g.drawString(version, 0, height-20, Graphics.TOP | Graphics.LEFT);
        flushGraphics();
    }

    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        if (gameAction == UP || gameAction == LEFT) {
            selectedOption = (selectedOption - 1 + 2) % 2;
        } else if (gameAction == DOWN || gameAction == RIGHT) {
            selectedOption = (selectedOption + 1) % 2;
        } else if (gameAction == FIRE) {
            executeSelectedOption();
        }
        draw();
    }

    private void executeSelectedOption() {
        if (selectedOption == 0) {
            midlet.StartGame();
            midlet.CloseMenu();
        } else if (selectedOption == 1) {
            midlet.exitMIDlet();
        }
    }
}

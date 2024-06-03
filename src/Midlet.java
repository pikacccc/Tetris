import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Midlet extends MIDlet {
    public Display display;
    public TetrisGameCanvas canvas;
    public Menu gameMenu;
    public GameOver gameOver;
    public RecordStores rs;


    public Midlet() {
        rs = new RecordStores("ES.TETRIS", 1);
        display = Display.getDisplay(this);

        canvas = new TetrisGameCanvas();
        canvas.midlet = this;
        gameMenu = new Menu();
        gameMenu.midlet = this;
        gameOver = new GameOver();
        gameOver.midlet = this;
    }


    public void startApp() {
        int nr = rs.getNumRecords();
        if (nr > 0) canvas.HISCORE = rs.getRecord(1);
        OpenMenu();
    }

    protected void pauseApp() {

    }

    public void OpenMenu() {
        gameMenu.start();
        display.setCurrent(gameMenu);
    }

    public void CloseMenu() {
        gameMenu.stop();
    }

    public void StartGame() {
        canvas.start();
        display.setCurrent(canvas);
    }

    public void CloseGame() {
        canvas.stop();
    }

    public void OpenGameOver() {
        gameOver.start();
        display.setCurrent(gameOver);
    }

    public void CloseGameOver() {
        gameOver.stop();
    }

    public void destroyApp(boolean unconditional) {
        canvas.stop();
        rs.setRecord(1, canvas.HISCORE);
        rs.closeRecords();
        gameMenu.stop();
    }

    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
    }
}

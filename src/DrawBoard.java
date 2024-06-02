
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.IOException;


public class DrawBoard {
    public Graphics g = null;
    public int width = 0;
    public int height = 0;

    int cellsize = 8;

    TetrisGameCanvas canvas;

    private Image[] images;

    private Image bg;
    private Image gameBg;

    public Stack stack;

    // mark
    public int w_stack;
    public int h_stack;
    public int w_preview;
    public int w_r;
    public int ofsx_stack;
    public int ofsy_stack;
    public int ofsx_preview;
    public int ofsy_preview;;

    public int bg_x;
    public int bg_y;

    public int gameBg_x;
    public int gameBg_y;

    public DrawBoard(TetrisGameCanvas canvas) {
        this.canvas = canvas;
        this.g = canvas.g;
        this.width = canvas.width;
        this.height = canvas.height;

        int sz = 4;
        if (height > (6 * 21)) {
            sz = 6;
        }
        if (height > (8 * 21)) {
            sz = 8;
        }
        if (height > (10 * 21)) {
            sz = 10;
        }
        if (height > (12 * 21)) {
            sz = 32;
        }
        Image temp = null;
        images = new Image[7];
        String s = "";
        try {
            temp = Image.createImage("/blocks.png");
            for (int i = 0; i < 7; i++)
                images[i] = Image.createImage(temp, i * 32, 0, sz, sz, Sprite.TRANS_NONE);
        } catch (java.io.IOException e) {
            s = e.getMessage();
        }
        cellsize = sz;

        try {
            bg = Image.createImage("/background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            gameBg = Image.createImage("/gamebg.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.Clear();
    }


    public void Clear() {
        g.setColor(0);
        g.fillRect(0, 0, width, height);
    }


    public void MarkUp() {
        w_stack = cellsize * 13;
        h_stack = cellsize * 21;
        w_preview = cellsize * 6;
        w_r = w_preview;

        ofsx_stack = (width - w_stack) / 2 - 72;
        ofsy_stack = (height - h_stack) / 2 + 15;

        ofsx_preview = (width - w_stack - w_preview) / 2 + w_stack;
        ofsy_preview = ofsy_stack + cellsize * 1;

        //后来加的
        bg_x = width / 2 - bg.getWidth() / 2;
        bg_y = height / 2 - bg.getHeight() / 2;
        gameBg_x = width / 2 - gameBg.getWidth() / 2;
        gameBg_y = height / 2 - gameBg.getHeight() / 2;
    }

    public void DrawAll() {
        DrawBg();
        DrawStack();
        DrawScore();
    }

    public void DrawBg() {
        g.drawImage(bg, bg_x, bg_y, Graphics.TOP | Graphics.LEFT);
        g.drawImage(gameBg, gameBg_x, gameBg_y, Graphics.TOP | Graphics.LEFT);
    }

    public void DrawTetramino(Tetramino t, boolean vis) {
        if (vis) {
            if (t.y[0] > -1)
                g.drawImage(images[t.colorIndex], cellsize * t.x[0] + ofsx_stack, cellsize * t.y[0] + ofsy_stack, 0);
            if (t.y[1] > -1)
                g.drawImage(images[t.colorIndex], cellsize * t.x[1] + ofsx_stack, cellsize * t.y[1] + ofsy_stack, 0);
            if (t.y[2] > -1)
                g.drawImage(images[t.colorIndex], cellsize * t.x[2] + ofsx_stack, cellsize * t.y[2] + ofsy_stack, 0);
            if (t.y[3] > -1)
                g.drawImage(images[t.colorIndex], cellsize * t.x[3] + ofsx_stack, cellsize * t.y[3] + ofsy_stack, 0);
        } else {
            g.setColor(0x00000000);
            for (int i = 0; i < 4; ++i) {
                int min_x = cellsize * t.x[i] + ofsx_stack;
                int width = images[t.colorIndex].getWidth();
                int min_y = cellsize * t.y[i] + ofsy_stack;
                int height = images[t.colorIndex].getHeight();
                if (t.y[i] > -1) g.fillRect(min_x, min_y, width, height);
            }
        }
    }

    public void DrawStack() {
        for (int j = 0; j < stack.h; j++) {
            int jj = j * cellsize + ofsy_stack;
            for (int i = 0; i < stack.w; i++) {
                int ii = i * cellsize;
                if (stack.cells[i][j] > 0) {
                    g.drawImage(images[stack.cells[i][j] - 1], ii + ofsx_stack, jj, 0);
                }
            }
        }
    }

    public void DrawPreview(Tetramino t) {
        int min_x = Math.min(t.x[0], Math.min(t.x[1], Math.min(t.x[2], t.x[3])));
        int max_x = Math.max(t.x[0], Math.max(t.x[1], Math.max(t.x[2], t.x[3])));
        int min_y = Math.min(t.y[0], Math.min(t.y[1], Math.min(t.y[2], t.y[3])));
        int max_y = Math.max(t.y[0], Math.max(t.y[1], Math.max(t.y[2], t.y[3])));

        int wx = max_x - min_x;
        int wy = max_y - min_y;

        int ox = ofsx_preview + cellsize * ((4 - wx) / 2 - min_x);
        int oy = ofsy_preview + cellsize * ((4 - wy) / 2 - min_y);
        g.drawImage(images[t.colorIndex], cellsize * t.x[0] + ox, cellsize * t.y[0] + oy, 0);
        g.drawImage(images[t.colorIndex], cellsize * t.x[1] + ox, cellsize * t.y[1] + oy, 0);
        g.drawImage(images[t.colorIndex], cellsize * t.x[2] + ox, cellsize * t.y[2] + oy, 0);
        g.drawImage(images[t.colorIndex], cellsize * t.x[3] + ox, cellsize * t.y[3] + oy, 0);
    }

    public void DrawScore() {

    }

    public void DrawScoreValueOnly() {

    }

    public void DrawHiScore() {

    }

    public void DrawGameOver() {

    }
}


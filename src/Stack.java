public class Stack {
    public int[][] cells;
    public int w = 10;
    public int h = 20;

    public Stack() {
        cells = new int[10][20];
        Clear();
    }

    public void Clear() {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                cells[i][j] = 0;
            }
        }
    }

    public boolean Intersect(Tetramino t) {
        int k = 0;

        for (int index = 0; index < 4; ++index) {
            int x = t.x[index];
            int y = t.y[index];
            if ((x >= 0) && (x < w) && y < h) {
                if (y >= 0) k += cells[x][y];
            } else k += 1;
        }

        if (k == 0) return false;
        else return true;
    }

    public void Fix(Tetramino t) {
        if (t.y[0] >= 0) cells[t.x[0]][t.y[0]] = t.color;
        if (t.y[1] >= 0) cells[t.x[1]][t.y[1]] = t.color;
        if (t.y[2] >= 0) cells[t.x[2]][t.y[2]] = t.color;
        if (t.y[3] >= 0) cells[t.x[3]][t.y[3]] = t.color;
    }

    public boolean HasFullLines() {
        int n = 0;
        for (int j = 0; j < h; j++) {
            int k = 0;
            for (int i = 0; i < w; i++) {
                if (cells[i][j] == 0) k++;
            }
            if (k == 0) n++;
        }
        if (n > 0) return true;
        else return false;
    }

    public boolean IsEmpty() {
        int k = 0;
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (cells[i][j] >= 0) k++;
            }
        }
        if (k == 0) return true;
        else return false;
    }

    public void InvertFullLines() {
        for (int j = 0; j < h; j++) {
            int k = 0;
            for (int i = 0; i < w; i++) {
                if (cells[i][j] == 0) k++;
            }
            if (k == 0)
                for (int i = 0; i < w; i++)
                    cells[i][j] = -cells[i][j];
        }
    }

    public int RemoveFullLines() {
        int n = 0;
        for (int j = h-1; j >= 0; j--) {
            int k = 0;
            for (int i = 0; i < w; i++) {
                if (cells[i][j] == 0) k++;
            }
            if (k == 0) {
                n++;
                for (int ii = 0; ii < w; ii++) {
                    for (int jj = j; jj > 0; jj--) {
                        cells[ii][jj] = cells[ii][jj - 1];
                    }
                    cells[ii][0] = 0;
                }
                j++;
            }
        }
        return n;
    }

}

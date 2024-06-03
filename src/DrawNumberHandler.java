import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class DrawNumberHandler {
    public Image[] images;
    public int dw;
    public int dh;

    public DrawNumberHandler(String ImgName, int dw, int dh) {
        images = new Image[10];
        this.dw = dw;
        this.dh = dh;
        Image temp;
        try {
            temp = Image.createImage(ImgName);
            for (int i = 0; i < 10; i++)
                images[i] = Image.createImage(temp, i * dw, 0, dw, dh, Sprite.TRANS_NONE);
        } catch (java.io.IOException e) {
        }
    }

    public void ShowNumber(Graphics g, int number, int x, int y) {
        int[] numbers = getDigits(number);
        int startX = x - numbers.length * dw / 2;
        int startY = y - this.dh / 2;
        g.setColor(0x272626);
        g.fillRect(x - 3 * dw + 5, startY, 6 * dw - 10, dh);
        for (int i = 0; i < numbers.length; ++i) {
            g.drawImage(images[numbers[i]], startX + i * dw, startY, 0);
        }
    }

    private int[] getDigits(int number) {
        int[] digits;
        if (number == 0) {
            digits = new int[1];
            digits[0] = 0;
            return digits;
        }
        int numDigits = 0;
        int tempNumber = number;
        while (tempNumber != 0) {
            tempNumber /= 10;
            numDigits++;
        }

        digits = new int[numDigits];
        for (int i = numDigits - 1; i >= 0; i--) {
            digits[i] = number % 10;
            number /= 10;
        }

        return digits;
    }

}

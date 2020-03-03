package JabLab.exp2;

import java.awt.*;

public class SignalCanvas extends Canvas {
    int red, green, yellow, x, y, r;

    SignalCanvas() {
        setBackground(Color.white);
    }

    public void setRed(int r) {
        red = r;
    }

    public void setGreen(int g) {
        green = g;
    }

    public void setYellow(int y) {
        yellow = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRadius(int r) {
        this.r = r;
    }

    public void paint(Graphics g) {
        if (red == 1) {
            g.setColor(Color.red);
        } else if (green == 1) {
            g.setColor(Color.green);
        } else if (yellow == 1) {
            g.setColor(Color.yellow);
        }
        g.fillOval(x, y, 2 * r, 2 * r);
    }
}
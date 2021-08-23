import javax.swing.*;
import java.awt.*;

public class Pipes extends JLabel {

    Pipes(int x, int y) {
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setBounds(x,y,120,700);
    }
}


import javax.swing.*;
import java.awt.*;

public class Pipes extends JPanel {
    Pipes(int x, int y) {
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setBounds(x, y, Game.pipeSpacing / Game.numPipes, 700);


    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame implements KeyListener {
    public final int TICK_RATE = 50; //time in ms between ticks
    Bird label;
    final int GAME_DIM = 600;
    Timer tick;
    int velocity;
    Game() {
        this.setTitle("Flappy Bird");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setPreferredSize(new Dimension(GAME_DIM, GAME_DIM));
        this.pack();
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        label = new Bird();

        this.addKeyListener(this);
        this.add(label);
        this.setVisible(true);

        tick = new Timer();
        tick.schedule(new TimerTask() {
            @Override
            public void run() {
                Gravity();
                if(velocity > 0) {
                    ChangeY(velocity);
                    velocity = velocity - 5;
                }
            }
        }, 0, 50);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            velocity = 50;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void Gravity() {
        label.setLocation(label.getX(), label.getY() + 20);
    }
    public void ChangeY(int velocity) {
        label.setLocation(label.getX(), label.getY() - velocity);
    }
}
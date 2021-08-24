import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Game extends JFrame implements KeyListener {
    final int TICK_TIME = 20; //time in ms between ticks
    Bird label;
    public static final int GAME_DIM = 600;
    public static final int sideMargins = 200;
    Timer tick;
    int velocity;
    int sideVelocity = 5;
    public static int numPipes = 4;
    public static int pipeSpacing = (GAME_DIM + sideMargins*2)/numPipes;

    Pipes[] topPipe;
    Pipes[] bottomPipe;

    boolean paused = false;

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

        topPipe = new Pipes[numPipes];

        bottomPipe = new Pipes[numPipes];
        bottomPipe[0] = new Pipes(0,-600);

        Random randNum = new Random();
        randNum.setSeed(1121210);
        int random;

        for (int i = 0; i < topPipe.length;i++) {
            random = randNum.nextInt(40);
            topPipe[i] = new Pipes(pipeSpacing*i, -random*10 - 350);
            this.add(topPipe[i]);
            bottomPipe[i] = new Pipes(pipeSpacing*i, 550 - random*10);
            this.add(bottomPipe[i]);

        }

        play();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            velocity = 35;
        }
        if(e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
            if(paused) {
                play();
                paused = false;
            } else {
                pause();
                paused = true;
            }
        }
    }
    public void pause() {
        tick.cancel();
    }
    public void play() {
        tick = new Timer();
        tick.schedule(new TimerTask() {
            @Override
            public void run() {
                Gravity();
                scrollMap();
            }
        }, 0, TICK_TIME);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void Gravity() {
        label.setLocation(label.getX(), label.getY() - velocity);
        if(velocity > 0) {
            velocity = velocity - 2;
        }
        label.setLocation(label.getX(), label.getY() + 15);

    }
    public void scrollMap() {
        if(checkCollision()) {
            tick.cancel();
        }
        for (Pipes pipes : topPipe) {
            pipes.setLocation(pipes.getX() - sideVelocity, pipes.getY());
            if (pipes.getX() < -sideMargins) {
                pipes.setLocation(GAME_DIM+sideMargins, pipes.getY());
            }
        }
        for (Pipes pipes : bottomPipe) {
            pipes.setLocation(pipes.getX() - sideVelocity, pipes.getY());
            if (pipes.getX() < -sideMargins) {
                pipes.setLocation(GAME_DIM+sideMargins, pipes.getY());
            }
        }

    }

    public boolean checkCollision() {
        for (Pipes pipes : topPipe) {
            if (pipes.getBounds().intersects(label.getBounds())) {
                System.out.println("Intersect");
                return true;
            }
        }
        for (Pipes pipes : bottomPipe) {
            if (pipes.getBounds().intersects(label.getBounds())) {
                System.out.println("Intersect");
                return true;
            }
        }
        return false;
    }
}

import javax.swing.*;
import java.awt.*;

public class Bird extends JLabel {
    private int velocity;
    public final int acceleration = 10;
    Bird() {
        this.setBackground(Color.BLUE);
        this.setOpaque(true);
        this.setBounds(150,200,60,60);
    }
    public int getVelocity() {
        return velocity;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
    public void changeVelocity(int change) {
        this.velocity = this.velocity - change;
    }

}

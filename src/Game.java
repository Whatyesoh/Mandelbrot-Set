import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends JFrame implements KeyListener {
    public static ArrayList<Integer>keys = new ArrayList<>();
    public static Game frame = new Game();
    private Painter painter;
    public Game() {
        this.painter = new Painter();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    public static void main (String [] args) throws InterruptedException {
        frame.setTitle("Mandelbrot Set");
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(frame.painter);
        frame.setVisible(true);
        while(true) {
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keys.contains(e.getKeyCode())) {
            keys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove((Integer) e.getKeyCode());
    }
}

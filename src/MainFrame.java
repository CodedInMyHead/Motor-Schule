import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainPanel panel;
    public int WIDTH = 1200;
    public int HEIGHT = 800;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        setTitle("Lastsimulation V1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        panel = new MainPanel(this);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainPanel panel;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        setTitle("Lastsimulation V1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));

        panel = new MainPanel();
        panel.setPreferredSize(new Dimension(800, 800));

        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel {

    MainPanel() {
        JLabel textLabel = new JLabel("Hello, World!");
        add(textLabel);

        BufferedImage sewCard = null;
        try {
            sewCard = ImageIO.read(new File("src/sew-card.png"));
        } catch (Exception e) {
            Main.getLogger().warning("Failed to read image!");
        }
        final JLabel picLabel = new JLabel(new ImageIcon(sewCard));
        add(picLabel);
    }
}

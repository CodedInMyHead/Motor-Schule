import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainPanel extends JPanel {


    MainPanel(final MainFrame frame) {
        setLayout(null);
        JLabel textLabel = new JLabel("Lastsimulation anhand eines SEW-Drehstromgetriebemotors");
        textLabel.setBounds(100,100,200,200);
        add(textLabel);

        BufferedImage sewCard = null;
        try {
            sewCard = ImageIO.read(new File("src/sew-card.png"));
        } catch (Exception e) {
            DrehstromMotorMain.getLogger().warning("Failed to read image!");
            System.exit(1);
        }

        final JLabel sewLabel = new JLabel(new ImageIcon(sewCard));
        sewLabel.setText("Leistungskennzahlen des Motors");
        sewLabel.setBounds(300,300,488,268);
        add(sewLabel);
    }
}

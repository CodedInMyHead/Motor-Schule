import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        final Frame frame = new Frame();
        final JPanel panel = new MainUI();
        frame.add(panel);
    }
}
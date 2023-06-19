import javax.swing.*;
import java.util.logging.Logger;

public class Main {

    private static Logger logger;
    public static void main(String[] args) {
        logger = Logger.getLogger("main");
        new MainFrame();
    }

    public static Logger getLogger() {
        return logger;
    }
}
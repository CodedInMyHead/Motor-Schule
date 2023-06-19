import common.DrehstromMotor;

import java.util.logging.Logger;

public class DrehstromMotorMain {

    private static final Logger logger = Logger.getLogger("main");
    public static void main(String[] args) {
        DrehstromMotor sewMotor = new DrehstromMotor("R27 DRN71MS4", 1405, 5, 230, 1.26, 0.66, 3.37);
    }

    public static Logger getLogger() {
        return logger;
    }
}
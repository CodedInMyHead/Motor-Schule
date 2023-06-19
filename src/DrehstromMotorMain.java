import common.DrehstromMotor;

import java.util.logging.Logger;

public class DrehstromMotorMain {

    private static final Logger logger = Logger.getLogger("main");
    public static void main(String[] args) {
        DrehstromMotor sewMotor = new DrehstromMotor("R27 DRN71MS4", 1405, 5, 230, 1.26, 0.66, 3.37);

        System.out.println("Leistungsaufnahme " + sewMotor.getLeistungsaufnahme() + " kW");
        System.out.println("Leistungsabgabe " + sewMotor.getLeistungsabgabe() + " kW");
        System.out.println("Verlustleistung " + sewMotor.getVerlustleistung() + " kW");
        System.out.println("Wirkungsgrad " + sewMotor.getWirkungsgrad() + " %");
    }
}
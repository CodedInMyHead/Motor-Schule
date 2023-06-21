import common.DrehstromMotor;
import gui.MotorFrame;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class DrehstromMotorMain {

    private static final Logger logger = Logger.getLogger("main");
    private static MotorFrame frame;
    public static void main(String[] args) {

        final Scanner sc = new Scanner(System.in);
        System.out.println("Willst du ein CLI, gib eine 1 an, bei jedem anderen Input öffnet sich das GUI (1)");
        final String choice = sc.nextLine();

        if (!choice.equals("1")) {
            frame = new MotorFrame(new DrehstromMotor());
        } else {
            while (true) {
                final String modell = getStringInput(sc, "Modell", false);
                final double drehzahl = getDoubleInput(sc, "Drehzahl", false, 0, Double.MAX_VALUE);
                final double drehmoment = getDoubleInput(sc, "Drehmoment", false, 0, Double.MAX_VALUE);
                final double spannung = getDoubleInput(sc, "Spannung", false, 0, Double.MAX_VALUE);
                final double nennstrom = getDoubleInput(sc, "Stromstärke", false, 0, Double.MAX_VALUE);
                final double scheinleistungsQuotient = getDoubleInput(sc, "Scheinleistungs-Faktor", false, -1, 1);
                final double uebersetzung = getDoubleInput(sc, "Getriebe-Übersetzung", false, Double.MIN_VALUE, Double.MAX_VALUE);

                final DrehstromMotor sewMotor = new DrehstromMotor(modell, drehzahl, drehmoment, spannung, nennstrom, scheinleistungsQuotient, uebersetzung);

                System.out.println("Leistungsaufnahme " + sewMotor.getLeistungsaufnahme() + " kW");
                System.out.println("Leistungsabgabe " + sewMotor.getLeistungsabgabe() + " kW");
                System.out.println("Verlustleistung " + sewMotor.getVerlustleistung() + " kW");
                System.out.println("Wirkungsgrad " + sewMotor.getWirkungsgrad() + " %");
                System.out.println("\nWillst du weitermachen? (y/Y)\n");

                sc.nextLine();
                final String doContinue = sc.nextLine();

                if (!doContinue.equalsIgnoreCase("y")) {
                    logger.info("Exiting..");
                    sc.close();
                    System.exit(1);
                }
            }
        }
    }

    public static String getStringInput(final Scanner sc, final String what, boolean isRetry) {
        if(!isRetry) {
            System.out.println("Bitte mache eine Eingabe für: " + what);
        }
        try {
            if(isRetry) {
                sc.nextLine();
            }
            return sc.nextLine();
        } catch (Exception e) {
            logger.info("Es ist ein fehler aufgetreten. Hier ist ein String erwartet. (Bsp.: \"Baum\")");
            return getStringInput(sc, what, true);
        }
    }

    public static double getDoubleInput(final Scanner sc, final String what, final boolean isRetry, final double min, final double max) {
        if(!isRetry) {
            System.out.println("Bitte mache eine Eingabe für: " + what);
        }
        try {
            if(isRetry) {
                sc.nextLine();
            }
            double input = sc.nextDouble();
            if(input < min || input > max) {
                throw new IllegalArgumentException();
            }
            return input;
        } catch (InputMismatchException e) {
            logger.info("Es ist ein Fehler aufgetreten. Hier ist ein double erwartet. (Bsp.: 12,1 oder 1)");
            return getDoubleInput(sc, what, true, min, max);
        } catch (IllegalArgumentException e) {
            logger.info("Es ist ein Fehler aufgetreten. Der/Die " + what + " muss zwischen " + min + " und " + max + " liegen.");
            return getDoubleInput(sc, what, true, min, max);
        }
    }
}

import common.DrehstromMotor;

import java.util.Scanner;
import java.util.logging.Logger;

public class DrehstromMotorMain {

    private static final Logger logger = Logger.getLogger("main");
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {

            // TODO: double steigung;
            // TODO: double verschiebung;
            // TODO: Change motor validation from constructor to input and default to a valid one

            String modell = getStringInput(sc, "Modell", false);
            double drehzahl = getDoubleInput(sc, "Drehzahl", false);
            double drehmoment = getDoubleInput(sc, "Drehmoment", false);
            double spannung = getDoubleInput(sc, "Spannung", false);
            double nennstrom = getDoubleInput(sc, "Stromstärke", false);
            double scheinleistungsQuotient = getDoubleInput(sc, "Scheinleistungs-Faktor", false);
            double uebersetzung = getDoubleInput(sc, "Getriebe-Übersetzung", false);

            DrehstromMotor sewMotor = new DrehstromMotor(modell, drehzahl, drehmoment, spannung, nennstrom, scheinleistungsQuotient, uebersetzung);

            System.out.println("Leistungsaufnahme " + sewMotor.getLeistungsaufnahme() + " kW");
            System.out.println("Leistungsabgabe " + sewMotor.getLeistungsabgabe() + " kW");
            System.out.println("Verlustleistung " + sewMotor.getVerlustleistung() + " kW");
            System.out.println("Wirkungsgrad " + sewMotor.getWirkungsgrad() + " %");

            System.out.println("\nWillst du weitermachen? (y/Y)\n");
            final String doContinue = sc.nextLine();
            if(!doContinue.toLowerCase().equals("y")) {
                logger.info("Exiting..");
                sc.close();
                System.exit(1);
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

    public static double getDoubleInput(final Scanner sc, final String what, boolean isRetry) {
        if(!isRetry) {
            System.out.println("Bitte mache eine Eingabe für: " + what);
        }

        try {
            if(isRetry) {
                sc.nextLine();
            }
            return sc.nextDouble();
        } catch (Exception e) {
            logger.info("Es ist ein fehler aufgetreten. Hier ist ein double erwartet. (Bsp.: 12.1 oder 1)");
            return getDoubleInput(sc, what, true);
        }
    }
}
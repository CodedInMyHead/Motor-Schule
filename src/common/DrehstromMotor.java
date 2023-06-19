package common;

import java.text.DecimalFormat;
import java.util.logging.Logger;

public class DrehstromMotor {

    private static final Logger logger = Logger.getLogger("motor");
    private  String modell;
    private double drehzahl;
    private double drehmoment;
    private double spannung;
    private double nennstrom;
    private double scheinleistungsQuotient;
    private double uebersetzung;
    private double steigung;
    private double verschiebung;
    DecimalFormat percentFormat = new DecimalFormat("##.#");
    DecimalFormat kWFormat = new DecimalFormat("#.###");
    public DrehstromMotor(String modell, double drehzahl, double drehmoment, double spannung, double nennstrom, double scheinleistungsQuotient, double uebersetzung) {
        this(modell, drehzahl, drehmoment, spannung, nennstrom, scheinleistungsQuotient, uebersetzung, 0, 0);
    }

    public DrehstromMotor(String modell, double drehzahl, double drehmoment, double spannung, double nennstrom, double scheinleistungsQuotient, double uebersetzung, double steigung, double verschiebung) {
        this.modell = modell;
        this.drehmoment = drehmoment;
        this.drehzahl = drehzahl;
        this.spannung = spannung;
        this.nennstrom = nennstrom;
        this.scheinleistungsQuotient = scheinleistungsQuotient;
        this.uebersetzung = uebersetzung;
        this.steigung = steigung;
        this.verschiebung = verschiebung;

        if(!isValid()) {
            logger.warning("Fatal: Motor configuration is invalid! Exiting the program..");
            System.exit(1);
        }

        logger.info("Motor des Typs " + modell + " wurde erstellt.");
    }

    public DrehstromMotor() {}

    public double getLeistungsaufnahme() {
        return Double.parseDouble((kWFormat.format((Math.sqrt(3) * spannung * nennstrom * scheinleistungsQuotient) / 1000)).replace(",", "."));
    }

    public double getLeistungsabgabe() {
        return Double.parseDouble((kWFormat.format((( drehmoment * (drehzahl / uebersetzung)) / 9549))).replace(",","."));
    }

    public double getVerlustleistung() {
        return Double.parseDouble((kWFormat.format(getLeistungsaufnahme() - getLeistungsabgabe())).replace(",", "."));
    }

    public double getWirkungsgrad() {
        return Double.parseDouble((percentFormat.format(getLeistungsabgabe() / getLeistungsaufnahme() * 100)).replace(",", "."));
    }

    private boolean isValid() {

        if (spannung < 0) {
            logger.warning("Eine Spannung unter 0V ist nicht erlaubt!");
            return false;
        }
        if(nennstrom < 0) {
            logger.warning("Eine Stromstärke unter 0A ist nicht erlaubt!");
            return false;
        }
        if(scheinleistungsQuotient < -1 || scheinleistungsQuotient > 1) {
            logger.warning("Der Scheinleistungsquotient muss zwischen -1 und 1 liegen!");
            return false;
        }
        if(drehmoment < 0) {
            logger.warning("Ein Drehmoment unter 0 ist nicht erlaubt!");
            return false;
        }
        if (getWirkungsgrad() < 0 || getWirkungsgrad() > 100) {
            logger.warning("Der Wirkungsgrad des Motors muss zwischen 0 und 100 liegen!");
            return false;
        }
        if (getLeistungsabgabe() < 0 || getLeistungsaufnahme() < 0) {
            logger.warning("Die Leistungsabgabe und Leistungsaufnahme des Motors müssen größer als 0 sein!");
            return false;
        }
        return true;
    }
}

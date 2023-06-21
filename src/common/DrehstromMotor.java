package common;

import java.text.DecimalFormat;
import java.util.function.Function;
import java.util.logging.Logger;

public class DrehstromMotor {

    private static final Logger logger = Logger.getLogger("motor");
    private final String modell;
    private final double drehzahl;
    private double drehmoment;
    private double spannung;
    private final double nennstrom;
    private double scheinleistungsQuotient;
    private final double uebersetzung;
    private final double steigung;
    private final double verschiebung;
    public final Function<Double, Double> getDrehzahlFunction = (Double y) -> (double) ((drehmoment - 68.9275) / -0.0455);
    public double oldPab = getLeistungsabgabe();
    public final Function<Double, Double> getFaktor = (Double y) -> (double) (getLeistungsabgabe()/oldPab);
    public final Function<Double, Double> reverseAmpere = (Double y) -> (double) 1000 * (getLeistungsabgabe() / (Math.sqrt(3) * spannung * scheinleistungsQuotient * (getLeistungsabgabe()/getLeistungsaufnahme()*getFaktor.apply(0.0))));
    public static DecimalFormat percentFormat = new DecimalFormat("##.#");
    public static DecimalFormat kWFormat = new DecimalFormat("#.###");
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

    public DrehstromMotor() {
        this("R27 Brötchen Käse", 1405, 5, 230, 1.26, 0.66, 3.37);
    }

    public double getLeistungsaufnahme() {
        return Double.parseDouble((kWFormat.format((Math.sqrt(3) * spannung * nennstrom * scheinleistungsQuotient) / 1000)).replace(",", "."));
    }

    public double getLeistungsabgabe() {
        return Double.parseDouble((kWFormat.format((( drehmoment * ( getDrehzahlFunction.apply(drehmoment)/ uebersetzung)) / 9549))).replace(",","."));
    }

    public double getVerlustleistung() {
        return Double.parseDouble((kWFormat.format(getLeistungsaufnahme() - getLeistungsabgabe())).replace(",", "."));
    }

    public double getWirkungsgrad() {
        return Double.parseDouble((percentFormat.format(getLeistungsabgabe() / getLeistungsaufnahme() * 100)).replace(",", "."));
    }

    private boolean isValid() {

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

    public double getDrehmoment() {
        return this.drehmoment;
    }

    public void setDrehmoment(double val) {
        this.drehmoment = val;
    }

    public String getModel() {
        return this.modell;
    }
}

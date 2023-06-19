package common;

public class DrehstromMotor {

    String modell;

    private double drehzahl;
    private double drehmoment;
    private double spannung;
    private double nennstrom;
    private double scheinleistungsQuotient;
    private double uebersetzung;
    public DrehstromMotor(String modell, double drehzahl, double drehmoment, double spannung, double nennstrom, double scheinleistungsQuotient, double uebersetzung) {
        this.modell = modell;
        this.drehmoment = drehmoment;
        this.drehzahl = drehzahl;
        this.spannung = spannung;
        this.nennstrom = nennstrom;
        this.scheinleistungsQuotient = scheinleistungsQuotient;
        this.uebersetzung = uebersetzung;
    }

    public DrehstromMotor() {}

    public double getLeistungsaufnahme() {
        return Math.sqrt(3) * spannung * nennstrom * scheinleistungsQuotient;
    }

    public double getLeistungsabgabe() {
        return ( drehmoment * (drehzahl / uebersetzung)) / 9549;
    }

    public double getVerlustleistung() {
        return getLeistungsaufnahme() - getLeistungsabgabe();
    }

    public double getWirkungsgrad() {
        return getLeistungsabgabe() / getLeistungsaufnahme();
    }
}

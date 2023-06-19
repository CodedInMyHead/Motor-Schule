package common;

import java.text.DecimalFormat;

public class DrehstromMotor {

    String modell;

    private double drehzahl;
    private double drehmoment;
    private double spannung;
    private double nennstrom;
    private double scheinleistungsQuotient;
    private double uebersetzung;
    DecimalFormat percentFormat = new DecimalFormat("##.#");
    DecimalFormat kWFormat = new DecimalFormat("#.###");
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
}

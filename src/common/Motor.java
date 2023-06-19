package common;

public class Motor {

    private double drehzahl;
    private double drehmoment;
    private double spannung;
    private double nennstrom;
    private double scheinleistungsQuotient;
    private double uebersetzung;
    Motor(double drehzahl, double drehmoment, double spannung,double nennstrom, double scheinleistungsQuotient, double uebersetzung) {
        this.drehmoment = drehmoment;
        this.drehzahl = drehzahl;
        this.spannung = spannung;
        this.nennstrom = nennstrom;
        this.scheinleistungsQuotient = scheinleistungsQuotient;
        this.uebersetzung = uebersetzung;
    }

    Motor() {}

    public double getDrehzahl() {
        return drehzahl;
    }

    public void setDrehzahl(double drehzahl) {
        this.drehzahl = drehzahl;
    }

    public double getDrehmoment() {
        return drehmoment;
    }

    public void setDrehmoment(double drehmoment) {
        this.drehmoment = drehmoment;
    }

    public double getSpannung() {
        return spannung;
    }

    public void setSpannung(double spannung) {
        this.spannung = spannung;
    }

    public double getNennstrom() {
        return nennstrom;
    }

    public void setNennstrom(double nennstrom) {
        this.nennstrom = nennstrom;
    }

    public double getScheinleistungsQuotient() {
        return scheinleistungsQuotient;
    }

    public void setScheinleistungsQuotient(double scheinleistungsQuotient) {
        this.scheinleistungsQuotient = scheinleistungsQuotient;
    }

    public double getUebersetzung() {
        return uebersetzung;
    }

    public void setUebersetzung(double uebersetzung) {
        this.uebersetzung = uebersetzung;
    }
}

public class Position {

    public Position() {
    }

    public Position(String Bezeichnung, double Betrag, Person BezahltVon) {
        this.Bezeichnung = Bezeichnung;
        this.Betrag = Betrag;
        this.BezahltVon = BezahltVon;
    }

    private String Bezeichnung;

    public String getBezeichnung() {
        return Bezeichnung;
    }

    public void setBezeichnung(String Bezeichnung) {
        this.Bezeichnung = Bezeichnung;
    }

    private double Betrag;

    public double getBetrag() {
        return Betrag;
    }

    public void setBetrag(double Betrag) {
        this.Betrag = Betrag;
    }

    private Person BezahltVon;

    public Person getBezahltVon() {
        return BezahltVon;
    }

    public void setBezahltVon(Person BezahltVon) {
        this.BezahltVon = BezahltVon;
    }

}

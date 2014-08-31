
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Gruppe {

    public Gruppe() {
        Personen = new ArrayList<>();
        Positionen = new ArrayList<>();
    }

    public Gruppe(String Bezeichnung) {
        this();
        this.Bezeichnung = Bezeichnung;
    }

    private String Bezeichnung;

    public String getBezeichnung() {
        return Bezeichnung;
    }

    public void setBezeichnung(String Bezeichnung) {
        this.Bezeichnung = Bezeichnung;
    }

    private List<Person> Personen;

    public List<Person> getPersonen() {
        return Personen;
    }

    public void setPersonen(List<Person> Personen) {
        this.Personen = Personen;
    }

    public void addPerson(Person per) {
        this.Personen.add(per);
    }

    private ArrayList<Position> Positionen;

    public ArrayList<Position> getPositionen() {
        return Positionen;
    }

    public void setPositionen(ArrayList<Position> Positionen) {
        this.Positionen = Positionen;
    }

    public void addPosition(Position pos) {
        this.Positionen.add(pos);
    }

    public Map<Person, Double> getSummeProPerson() {

        double anteil = 0.0;
        for (Position pos : Positionen) {
            anteil += pos.getBetrag();
        }
        anteil /= Personen.size();

        Map<Person, Double> map = new HashMap<>();

        for (Person per : Personen) {
            map.put(per, anteil);
        }

        return map;
    }

    public Map<Person, Double> getBezahltProPerson() {
        Map<Person, Double> map = new HashMap<>();

        for (Position pos : Positionen) {
            map.putIfAbsent(pos.getBezahltVon(), 0.0);

            double summe = map.get(pos.getBezahltVon()) + pos.getBetrag();
            map.put(pos.getBezahltVon(), summe);
        }
        return map;
    }
}

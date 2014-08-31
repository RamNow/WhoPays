
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Abrechnung {

    public Abrechnung() {
        Gruppen = new ArrayList<>();
    }

    private List<Gruppe> Gruppen;

    public List<Gruppe> getGruppen() {
        return Gruppen;
    }

    public void setGruppen(List<Gruppe> Gruppen) {
        this.Gruppen = Gruppen;
    }

    public void addGruppe(Gruppe gru) {
        this.Gruppen.add(gru);
    }

    public Map<Person, Double> getSummeProPerson() {
        Map<Person, Double> gesamtMap = new HashMap<>();

        for (Gruppe g : Gruppen) {
            Map<Person, Double> gruppenMap = g.getSummeProPerson();
            addGruppenMapToGesamtMap(gruppenMap, gesamtMap);
        }

        Map<Person, Double> bezahltMap = getBezahltProPerson();
        bildeDifferenzSummeUndBezahlt(gesamtMap, bezahltMap);

        return gesamtMap;
    }

    private void addGruppenMapToGesamtMap(Map<Person, Double> gruppenMap, Map<Person, Double> gesamtMap) {
        for (Map.Entry<Person, Double> entry : gruppenMap.entrySet()) {
            gesamtMap.putIfAbsent(entry.getKey(), 0.0);

            double summe = gesamtMap.get(entry.getKey()) + entry.getValue();
            gesamtMap.put(entry.getKey(), summe);
        }
    }

    private Map<Person, Double> getBezahltProPerson() {
        Map<Person, Double> bezahltMap = new HashMap<>();

        for (Gruppe g : Gruppen) {
            Map<Person, Double> gruppenMap = g.getBezahltProPerson();
            addGruppenMapToGesamtMap(gruppenMap, bezahltMap);
        }

        return bezahltMap;
    }

    private void bildeDifferenzSummeUndBezahlt(Map<Person, Double> gesamtMap, Map<Person, Double> bezahltMap) {

        for (Map.Entry<Person, Double> entry : gesamtMap.entrySet()) {
            double differenz = ((double) bezahltMap.getOrDefault(entry.getKey(), 0.0)) - entry.getValue();
            entry.setValue(differenz);
        }
    }
}

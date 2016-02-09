package de.ramnow.whopays.algorithm;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Payoff {

    public Payoff() {
        Groups = new ArrayList<>();
    }

    private List<Group> Groups;

    public List<Group> getGroups() {
        return Groups;
    }

    public void setGroups(List<Group> groups) {
        this.Groups = groups;
    }

    public void addGroup(Group group) {
        this.Groups.add(group);
    }

    public Map<Person, Double> getTotalPerPerson() {
        Map<Person, Double> totalPerPerson = new HashMap<>();

        for (Group g : Groups) {
            Map<Person, Double> totalPerPersonInGroup = g.getTotalPerPerson();
            addPerGroupMapToTotalMap(totalPerPersonInGroup, totalPerPerson);
        }

        Map<Person, Double> payedPerPerson = getPayedPerPerson();
        calculateDifferenceTotalAndPayed(totalPerPerson, payedPerPerson);

        return totalPerPerson;
    }

    private Map<Person, Double> getPayedPerPerson() {
        Map<Person, Double> payedPerPerson = new HashMap<>();

        for (Group g : Groups) {
            Map<Person, Double> payedPerPersonInGroup = g.getPayedPerPerson();
            addPerGroupMapToTotalMap(payedPerPersonInGroup, payedPerPerson);
        }

        return payedPerPerson;
    }

    private void addPerGroupMapToTotalMap(Map<Person, Double> valuePerPersonInGroup, Map<Person, Double> valuePerPerson) {

        for (Map.Entry<Person, Double> entry : valuePerPersonInGroup.entrySet()) {
            Person payedBy = entry.getKey();

            if (!valuePerPerson.containsKey(payedBy)) {
                valuePerPerson.put(payedBy, 0.0);
            }

            double sum = valuePerPerson.get(payedBy) + entry.getValue();
            valuePerPerson.put(payedBy, sum);
        }
    }

    private void calculateDifferenceTotalAndPayed(Map<Person, Double> totalPerPerson, Map<Person, Double> payedPerPerson) {

        for (Map.Entry<Person, Double> entry : totalPerPerson.entrySet()) {

            Person person = entry.getKey();
            double payedAmount;

            if (!payedPerPerson.containsKey(person)) {
                payedAmount = 0.0;
            }else
            {
                payedAmount = payedPerPerson.get(person);
            }

            double differenz = payedAmount - entry.getValue();
            entry.setValue(differenz);
        }
    }
}

package de.ramnow.whopays.algorithm;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Group {

    public Group() {
        Persons = new ArrayList<>();
        Expenses = new ArrayList<>();
    }

    public Group(String title) {
        this();
        this.Title = title;
    }

    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    private List<Person> Persons;

    public List<Person> getPersons() {
        return Persons;
    }

    public void setPersons(List<Person> persons) {
        this.Persons = persons;
    }

    public void addPerson(Person person) {
        this.Persons.add(person);
    }

    private ArrayList<Expense> Expenses;

    public ArrayList<Expense> getExpenses() {
        return Expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.Expenses = expenses;
    }

    public void addExpense(Expense expense) {
        this.Expenses.add(expense);
    }

    public Map<Person, Double> getTotalPerPerson() {

        double total = 0.0;
        for (Expense expense : Expenses) {
            total += expense.getAmount();
        }
        total /= Persons.size();

        Map<Person, Double> totalPerPerson = new HashMap<>();

        for (Person person : Persons) {
            totalPerPerson.put(person, total);
        }

        return totalPerPerson;
    }

    public Map<Person, Double> getPayedPerPerson() {

        Map<Person, Double> payedPerPerson = new HashMap<>();

        for (Expense expense : Expenses) {
            Person payedFrom = expense.getPayedBy();

            if (!payedPerPerson.containsKey(payedFrom)){
                payedPerPerson.put(payedFrom, 0.0);
            }

            double sum = payedPerPerson.get(payedFrom) + expense.getAmount();
            payedPerPerson.put(payedFrom, sum);
        }
        return payedPerPerson;
    }
}

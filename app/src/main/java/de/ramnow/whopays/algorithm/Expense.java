package de.ramnow.whopays.algorithm;

public class Expense {

    public Expense() {
    }

    public Expense(String Title, double Amount, Person PayedFrom) {
        this.Title = Title;
        this.Amount = Amount;
        this.PayedBy = PayedFrom;
    }

    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String bezeichnung) {
        this.Title = bezeichnung;
    }

    private double Amount;

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        this.Amount = amount;
    }

    private Person PayedBy;

    public Person getPayedBy() {
        return PayedBy;
    }

    public void setPayedBy(Person payedBy) {
        this.PayedBy = payedBy;
    }

}

package de.ramnow.whopays.alorithm;

import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import de.ramnow.whopays.algorithm.*;

public class PayoffTest {

    private static Payoff _payoff;
    private static Person _kappes;
    private static Person _schobbe;
    private static Person _ramelow;

    @BeforeClass
    public static void setUpClass() throws Exception{
        _payoff = new Payoff();

        _kappes = new Person("Kappes");
        _schobbe = new Person("Schobbe");
        _ramelow = new Person("Ramelow");

        Group fleisch = new Group("Fleisch");
        fleisch.addPerson(_kappes);
        fleisch.addPerson(_schobbe);
        fleisch.addPerson(_ramelow);
        Expense melchEinkauf = new Expense("Metzger Melch", 120.00, _schobbe);
        fleisch.addExpense(melchEinkauf);

        Group alkohol = new Group("Alkohol");
        alkohol.addPerson(_schobbe);
        alkohol.addPerson(_ramelow);
        Expense kraemerEinkauf = new Expense("Apfelwein Krämer", 109.50, _schobbe);
        alkohol.addExpense(kraemerEinkauf);
        Expense bierEinkauf = new Expense("Bierkauf", 64.99, _kappes);
        alkohol.addExpense(bierEinkauf);

        _payoff.addGroup(fleisch);
        _payoff.addGroup(alkohol);
    }

    @Test
    public void testGetTotalPerPerson() throws Exception {
        System.out.println("PayoffTest.getTotalPerPerson");

        Map<Person, Double> result = _payoff.getTotalPerPerson();

        assertEquals("Abrechnungswert stimmt nicht überein.", 24.99, (double) result.get(_kappes), 0.0001);
        assertEquals("Abrechnungswert stimmt nicht überein.", 102.255, (double) result.get(_schobbe), 0.0001);
        assertEquals("Abrechnungswert stimmt nicht überein.", -127.245, (double) result.get(_ramelow), 0.0001);
    }
}
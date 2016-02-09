package de.ramnow.whopays.alorithm;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import de.ramnow.whopays.algorithm.Expense;
import de.ramnow.whopays.algorithm.Group;
import de.ramnow.whopays.algorithm.Person;

import static org.junit.Assert.assertEquals;

public class GroupTest {

    private static Group _group;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("GroupTest.setUpClass");

        _group = new Group("Fleisch");
        _group.addPerson(new Person("Kappes"));
        _group.addPerson(new Person("Ramelow"));
        Person schobbe = new Person("Schobbe");
        _group.addPerson(schobbe);

        Expense expense = new Expense("Metzger Melch", 120.00, schobbe);
        _group.addExpense(expense);
    }

    @Test
    public void testGetTitle() throws Exception {
        System.out.println("GroupTest.getTitle");

        Group instance = new Group("Fleisch");
        String expResult = "Fleisch";
        String result = instance.getTitle();
        assertEquals("getTitle liefert flaschen Wert.", expResult, result);
    }

    @Test
    public void testSetTitle() throws Exception {
        System.out.println("GroupTest.setTitle");

        String Bezeichnung = "Alkohol";
        Group instance = new Group();
        instance.setTitle(Bezeichnung);
        assertEquals("setTitle hat nicht funktioniert", Bezeichnung, instance.getTitle());
    }

    @Test
    public void testGetTotalPerPerson() throws Exception {
        System.out.println("GroupTest.getTotalPerPerson");

        Map<Person, Double> map = _group.getTotalPerPerson();

        assertEquals("Anzahl der Personen stimmt nicht überein.", _group.getPersons().size(), map.size());
        assertEquals("Betrag stimmt nicht.", 40.0, map.get(_group.getPersons().get(0)), 0.001);
    }
}
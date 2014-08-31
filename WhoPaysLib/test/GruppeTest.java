
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GruppeTest {

    private static Gruppe _gruppe;

    public GruppeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        _gruppe = new Gruppe("GruppenName");

        _gruppe.getPersonen().add(new Person("Kappes"));
        _gruppe.getPersonen().add(new Person("Ramelow"));
        Person schobbe = new Person("Schobbe");
        _gruppe.getPersonen().add(schobbe);

        Position pos = new Position("Metzger Melch", 120.00, schobbe);
        _gruppe.getPositionen().add(pos);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetBezeichnung() {
        System.out.println("getBezeichnung");
        Gruppe instance = new Gruppe("Fleisch");
        String expResult = "Fleisch";
        String result = instance.getBezeichnung();
        assertEquals("getBezeichnung liefert flaschen Wert.", expResult, result);
    }

    @Test
    public void testSetBezeichnung() {
        System.out.println("setBezeichnung");
        String Bezeichnung = "Alkohol";
        Gruppe instance = new Gruppe();
        instance.setBezeichnung(Bezeichnung);
        assertEquals("setBezeichnung hat nicht funktioniert", Bezeichnung, instance.getBezeichnung());
    }

    @Test
    public void testGetSummeProPerson() {
        System.out.println("getSummeProPerson");

        Map<Person, Double> map = _gruppe.getSummeProPerson();

        assertEquals("Anzahl der Personen stimmt nicht überein.", _gruppe.getPersonen().size(), map.size());
        assertEquals("Betrag stimmt nicht.", 40.0, (double) map.get(_gruppe.getPersonen().get(0)), 0.001);
    }

}

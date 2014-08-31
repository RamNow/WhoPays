import java.util.Map;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class AbrechnungTest {

    public AbrechnungTest() {
    }

    private static Abrechnung _abrechnung;
    private static Person _kappes;
    private static Person _schobbe;
    private static Person _ramelow;

    @BeforeClass
    public static void setUpClass() {
        _abrechnung = new Abrechnung();

        _kappes = new Person("Kappes");
        _schobbe = new Person("Schobbe");
        _ramelow = new Person("Ramelow");

        Gruppe fleisch = new Gruppe("Fleisch");
        fleisch.addPerson(_kappes);
        fleisch.addPerson(_schobbe);
        fleisch.addPerson(_ramelow);
        Position melchEinkauf = new Position("Metzger Melch", 120.00, _schobbe);
        fleisch.addPosition(melchEinkauf);

        Gruppe alkohol = new Gruppe("Alkohol");
        alkohol.addPerson(_schobbe);
        alkohol.addPerson(_ramelow);
        Position kraemerEinkauf = new Position("Apfelwein Krämer", 109.50, _schobbe);
        alkohol.addPosition(kraemerEinkauf);
        Position bierEinkauf = new Position("Bierkauf", 64.99, _kappes);
        alkohol.addPosition(bierEinkauf);

        _abrechnung.addGruppe(fleisch);
        _abrechnung.addGruppe(alkohol);
    }

    @Test
    public void testGetSummeProPerson() {
        System.out.println("getSummeProPerson");

        Map<Person, Double> result = _abrechnung.getSummeProPerson();

        assertEquals("Abrechnungswert stimmt nicht überein.", 24.99, (double) result.get(_kappes), 0.0001);
        assertEquals("Abrechnungswert stimmt nicht überein.", 102.255, (double) result.get(_schobbe), 0.0001);
        assertEquals("Abrechnungswert stimmt nicht überein.", -127.245, (double) result.get(_ramelow), 0.0001);
    }

}

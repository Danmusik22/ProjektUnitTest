package ordination.ordination;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
public class DagligFastTest extends TestCase {

    private DagligFast dagligFast;
    private LocalDate startDato;
    private LocalTime timePlaceholder = LocalTime.of(0, 0);

    @Test
    public void test4dosisOprettet() {
        // Arrange
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 2);
        dagligFast = new DagligFast(startDato, slutDato);
        boolean alledoserOprettet = false;

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);
        for (int i = 0; i < dagligFast.getDoser().length; i++) {
            if (doser[i].equals(dagligFast.getDoser()[i])) {
                alledoserOprettet = true;
            } else {
                alledoserOprettet = false;
            }
        }
        // Assert
        assertTrue(alledoserOprettet);
    }

    public void testStartdatoSenereEndSlut() {
        // arrange
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 9, 30);
        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dagligFast = new DagligFast(startDato, slutDato);
        });
        assertEquals(exception.getMessage(), "Startdato er senere end slutdato");
    }

    @Test
    public void test_doegnDosis_1HvertTidspunkt() {
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 1);
        dagligFast = new DagligFast(startDato, slutDato);

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);

        // Assert
        assertEquals(4.0, dagligFast.doegnDosis());
    }

    @Test
    public void test_doegnDosis_2HvertTidspunkt() {
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 8);
        dagligFast = new DagligFast(startDato, slutDato);

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);

        // Assert
        assertEquals(8.0, dagligFast.doegnDosis());
    }

    @Test
    public void test_doegnDosis_vilkaarligtAntal() {
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 5),
                new Dosis(timePlaceholder, 3),
                new Dosis(timePlaceholder, 0)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 8);
        dagligFast = new DagligFast(startDato, slutDato);

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);

        // Assert
        assertEquals(9.0, dagligFast.doegnDosis());
    }

    @Test
    public void test_samletDosis_1_hverdag_1dag() {
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 1);
        dagligFast = new DagligFast(startDato, slutDato);

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);

        // Assert
        assertEquals(4.0, dagligFast.samletDosis());
    }

    @Test
    public void test_samletDosis_1_hverdag_8dage() {
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1),
                new Dosis(timePlaceholder, 1)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 8);
        dagligFast = new DagligFast(startDato, slutDato);

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);

        // Assert
        assertEquals(32.0, dagligFast.samletDosis());
    }

    @Test
    public void test_samletDosis_2_hverdag_1dag() {
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 1);
        dagligFast = new DagligFast(startDato, slutDato);

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);

        // Assert
        assertEquals(8.0, dagligFast.samletDosis());
    }

    @Test
    public void test_samletDosis_2_hverdag_8dage() {
        Dosis[] doser = new Dosis[] {
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2),
                new Dosis(timePlaceholder, 2)
        };
        LocalDate startDato = LocalDate.of(2022, 10, 1);
        LocalDate slutDato = LocalDate.of(2022, 10, 8);
        dagligFast = new DagligFast(startDato, slutDato);

        // Act
        dagligFast.createDagligDosis(doser[0], doser[1], doser[2], doser[3]);

        // Assert
        assertEquals(64.0, dagligFast.samletDosis());
    }
}
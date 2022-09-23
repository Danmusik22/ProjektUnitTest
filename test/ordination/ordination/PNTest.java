package ordination.ordination;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class PNTest  {
    private LocalDate startDen, slutDen;
    private double antalEnheder;


    @BeforeEach
    public void setUp() {
        startDen = LocalDate.of(2022, 9, 1);
        slutDen = LocalDate.of(2022, 9, 8);
        antalEnheder = 2;
    }

    @Test
    public void testGivDosis_1Dosis() {
        // Arrange
        PN pn = new PN(startDen, slutDen, 2);

        // Act
        pn.givDosis(startDen);

        // Assert
        assertEquals(1, pn.getAntalGangeGivet());
    }

    @Test
    public void testGivDosis_2Dosis2Datoer() {
        // Arrange
        PN pn = new PN(startDen, slutDen, 2);
        LocalDate andenDag = LocalDate.of(2022, 9, 2);

        // Act
        pn.givDosis(startDen);
        pn.givDosis(andenDag);


        // Assert
        assertEquals(2, pn.getAntalGangeGivet());
        assertEquals(2, pn.getDatoer().size());
    }

    @Test
    public void testGivDosis_2Dosis1Dato() {
        // Arrange
        PN pn = new PN(startDen, slutDen, 2);

        // Act
        pn.givDosis(startDen);
        pn.givDosis(startDen);

        // Assert
        assertEquals(2, pn.getAntalGangeGivet());
        assertEquals(2, pn.getDatoer().size());
    }


    @Test
    public void testDoegnDosis_3Dosis3Dage() {
        // Arrange
        PN pn = new PN(startDen, slutDen, antalEnheder);
        pn.givDosis(startDen);
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 3));

        // Act
        double result = pn.doegnDosis();
        // Assert
        assertEquals(2.0, result);
    }

    public void testDoegnDosis_10Dosis3Dage() {
        // Arrange
        PN pn = new PN(startDen, slutDen, antalEnheder);
        pn.givDosis(startDen);
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        // Act
        double result = pn.doegnDosis();
        // Assert
        assertEquals(20.0 / 3, result);
    }

    public void testDoegnDosis_10Dosis6Dage() {
        // Arrange
        PN pn = new PN(startDen, slutDen, antalEnheder);
        pn.givDosis(startDen);
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 2));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 3));
        pn.givDosis(LocalDate.of(2022, 9, 6));
        // Act
        double result = pn.doegnDosis();
        // Assert
        assertEquals(20.0 / 6, result);
    }

    @Test
    public void testSamletDosis_3gange2Enheder() {
        // Arrange
        antalEnheder = 2;
        PN pn = new PN(startDen, slutDen, antalEnheder);
        pn.givDosis(startDen);
        pn.givDosis(startDen);
        pn.givDosis(slutDen);

        // Act
        double result = pn.samletDosis();
        double expected = 6;

        // Assert
        assertEquals(expected,result);
    }

    @Test
    public void testSamletDosis_5gange1Enheder() {
        // Arrange
        antalEnheder = 1;
        PN pn = new PN(startDen, slutDen, antalEnheder);
        pn.givDosis(startDen);
        pn.givDosis(startDen);
        pn.givDosis(startDen);
        pn.givDosis(slutDen);
        pn.givDosis(slutDen);

        // Act
        double result = pn.samletDosis();
        double expected = 5;

        // Assert
        assertEquals(expected,result);
    }

    @Test
    public void testSamletDosis_1gange3Enheder() {
        // Arrange
        antalEnheder = 3;
        PN pn = new PN(startDen, slutDen, antalEnheder);
        pn.givDosis(startDen);


        // Act
        double result = pn.samletDosis();
        double expected = 3;

        // Assert
        assertEquals(expected,result);
    }
}
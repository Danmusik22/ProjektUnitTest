package ordination.controller;

import static org.junit.jupiter.api.Assertions.*;

import ordination.ordination.*;


import org.junit.jupiter.api.*;

import java.time.LocalDate;

class ControllerTest {
    Patient patient;
    Controller cont;
    Laegemiddel fucidin, para;
    LocalDate startDato;

    @BeforeEach
    void setUp() {
        patient = new Patient("1234123456", "Behzad", 90);
        cont = new Controller();
        fucidin = cont.opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        para = cont.opretLaegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
        startDato = LocalDate.of(2022, 10, 1);
    }

    @Test
    public void test_OpretPNOrd_Antal1_Fucidin_1dag() {
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 1);
        double antal = 1;

        // Act
        PN pn = cont.opretPNOrdination(startDato, slutDato, patient, fucidin, 1);

        // Assert
        assertEquals(1, pn.getAntalEnheder());
        assertEquals(fucidin, pn.getLaegemiddel());
        assertEquals(slutDato, pn.getSlutDen());
        assertEquals(1, pn.antalDage());
    }

    @Test
    public void test_OpretPNOrd_Antal2_Para_2Dage() {
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 2);
        double antal = 2;

        // Act
        PN pn = cont.opretPNOrdination(startDato, slutDato, patient, para, antal);

        // Assert
        assertEquals(2, pn.getAntalEnheder());
        assertEquals(para, pn.getLaegemiddel());
        assertEquals(slutDato, pn.getSlutDen());
        assertEquals(2, pn.antalDage());
    }

    @Test
    public void test_OpretPNOrd_UgyldigDato() {
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 9, 30);
        double antal = 2;

        // Act
//        PN pn = cont.opretPNOrdination(startDato,slutDato,patient,para,antal);

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            cont.opretPNOrdination(startDato, slutDato, patient, para, antal);
        });
        assertEquals(exception.getMessage(), "Startdato er senere end slutdato");
    }

    @Test
    public void test_OpretDagligFast() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 2);
        double morgenAntal = 1;
        double middagAntal = 0;
        double aftenAntal = 1;
        double natAntal = 0;

        //Act
        DagligFast df = cont.opretDagligFastOrdination(startDato, slutDato, patient, para,
                morgenAntal, middagAntal, aftenAntal, natAntal);

        //Assert
        assertEquals(2, df.doegnDosis());
        assertEquals(para, df.getLaegemiddel());
        assertEquals(2, df.antalDage());
    }

    @Test
    public void test_OpretDagligFast_() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 2);
        double morgenAntal = 1;
        double middagAntal = 0;
        double aftenAntal = 1;
        double natAntal = 0;

        //Act
        DagligFast df = cont.opretDagligFastOrdination(startDato, slutDato, patient, para,
                morgenAntal, middagAntal, aftenAntal, natAntal);

        //Assert
        assertEquals(2, df.doegnDosis());
        assertEquals(para, df.getLaegemiddel());
        assertEquals(2, df.antalDage());

    }


}
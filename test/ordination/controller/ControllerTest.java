package ordination.controller;

import static org.junit.jupiter.api.Assertions.*;

import ordination.ordination.*;


import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;

class ControllerTest {
    Patient patient, patient20kg, patient60kg,patient200kg;
    Controller cont;
    Laegemiddel paracetamol, fucidin;
    LocalDate startDato;

    @BeforeEach
    void setUp() {
        patient = new Patient("1234123456", "Behzad", 90);

        cont = new Controller();
        paracetamol = cont.opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        fucidin = cont.opretLaegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
        startDato = LocalDate.of(2022, 10, 1);


        patient20kg = cont.opretPatient("1234123456", "Behzad", 20);
        patient60kg = cont.opretPatient("1234123456", "Behzad", 60);
        patient200kg = cont.opretPatient("1234123456", "Behzad", 200);

        LocalDate slutDato = LocalDate.of(2022, 10, 20);
        cont.opretDagligFastOrdination(startDato, slutDato, patient20kg, paracetamol, 1,1,1,1);
        cont.opretDagligFastOrdination(startDato, slutDato, patient60kg, paracetamol, 1,1,1,1);
        cont.opretDagligFastOrdination(startDato, slutDato, patient200kg, paracetamol, 1,1,1,1);

    }

    @Test
    public void test_OpretPNOrd_Antal1_Fucidin_1dag() {
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 1);
        double antal = 1;

        // Act
        PN pn = cont.opretPNOrdination(startDato, slutDato, patient, paracetamol, 1);

        // Assert
        assertEquals(1, pn.getAntalEnheder());
        assertEquals(paracetamol, pn.getLaegemiddel());
        assertEquals(slutDato, pn.getSlutDen());
        assertEquals(1, pn.antalDage());
    }

    @Test
    public void test_OpretPNOrd_Antal2_Para_2Dage() {
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 2);
        double antal = 2;

        // Act
        PN pn = cont.opretPNOrdination(startDato, slutDato, patient, fucidin, antal);

        // Assert
        assertEquals(2, pn.getAntalEnheder());
        assertEquals(fucidin, pn.getLaegemiddel());
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
            cont.opretPNOrdination(startDato, slutDato, patient, fucidin, antal);
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
        DagligFast df = cont.opretDagligFastOrdination(startDato, slutDato, patient, fucidin,
                morgenAntal, middagAntal, aftenAntal, natAntal);

        //Assert
        assertEquals(2, df.doegnDosis());
        assertEquals(fucidin, df.getLaegemiddel());
        assertEquals(2, df.antalDage());
    }

    @Test
    public void test_OpretDagligFast_forskelligeAntal() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 2);
        double morgenAntal = 1;
        double middagAntal = 0;
        double aftenAntal = 1;
        double natAntal = 0;

        //Act
        DagligFast df = cont.opretDagligFastOrdination(startDato, slutDato, patient, fucidin,
                morgenAntal, middagAntal, aftenAntal, natAntal);

        //Assert
        assertEquals(2, df.doegnDosis());
        assertEquals(fucidin, df.getLaegemiddel());
        assertEquals(2, df.antalDage());

    }

    @Test
    public void test_PNAnvendt1(){
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 5);
        double antal = 1;
        PN pn = cont.opretPNOrdination(startDato, slutDato, patient, paracetamol, 1);

        // Act
        cont.ordinationPNAnvendt(pn, LocalDate.of(2022,10,2));

        // Assert
        assertEquals(1, pn.getAntalGangeGivet());
        assertTrue(pn.getDatoer().contains(LocalDate.of(2022,10,2)));
        assertEquals(1, pn.samletDosis());
    }

    @Test
    public void test_PNAnvendt3gange(){
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 5);
        double antal = 1;
        PN pn = cont.opretPNOrdination(startDato, slutDato, patient, paracetamol, 1);
        LocalDate tjekDato = LocalDate.of(2022,10,2);

        // Act
        cont.ordinationPNAnvendt(pn, tjekDato);
        cont.ordinationPNAnvendt(pn, LocalDate.of(2022,10,3));
        cont.ordinationPNAnvendt(pn, LocalDate.of(2022,10,4));

        // Assert
        assertEquals(3, pn.getAntalGangeGivet());
        assertTrue(pn.getDatoer().contains(tjekDato));
        assertEquals(3, pn.samletDosis());
    }

    @Test
    public void test_PNAnvendtForkertDato(){
        // Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 5);
        double antal = 1;
        PN pn = cont.opretPNOrdination(startDato, slutDato, patient, paracetamol, 1);
        LocalDate tjekDato = LocalDate.of(2022,10,6);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->
        {cont.ordinationPNAnvendt(pn, tjekDato);});
        assertEquals(exception.getMessage(), "Ugyldig Dato");
    }

    @Test
    public void test_AnbefaletDosis_24kg_Para(){
        //Arrange
        Patient patient1 = new Patient("1234123456", "Behzad", 24);

        //Act
        double result = cont.anbefaletDosisPrDoegn(patient1, paracetamol);

        //Assert
        assertEquals(24.0,result);
    }

    @Test
    public void test_AnbefaletDosis_25kg_Para(){
        //Arrange
        Patient patient1 = new Patient("1234123456", "Behzad", 25);

        //Act
        double result = cont.anbefaletDosisPrDoegn(patient1, paracetamol);

        //Assert
        assertEquals(37.5,result);
    }

    @Test
    public void test_AnbefaletDosis_119kg_Para(){
        //Arrange
        Patient patient1 = new Patient("1234123456", "Behzad", 119);

        //Act
        double result = cont.anbefaletDosisPrDoegn(patient1, paracetamol);

        //Assert
        assertEquals(178.5,result);
    }

    @Test
    public void test_AnbefaletDosis_120kg_Para(){
        //Arrange
        Patient patient1 = new Patient("1234123456", "Behzad", 120);

        //Act
        double result = cont.anbefaletDosisPrDoegn(patient1, paracetamol);

        //Assert
        assertEquals(240.0,result);
    }

    @Test
    public void test_AntOrdPrVægtPrLægemiddel_Ml20Og20kg(){
        //Arrange = done i Setup

        //Act
        int result = cont.antalOrdinationerPrVægtPrLægemiddel(20,20,paracetamol);

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void test_AntOrdPrVægtPrLægemiddel_Ml20Og200kg(){
        //Arrange = done i Setup

        //Act
        int result = cont.antalOrdinationerPrVægtPrLægemiddel(20,200,paracetamol);

        //Assert
        assertEquals(3, result);
    }

    @Test
    public void test_AntOrdPrVægtPrLægemiddel_Ml20Og200kg_Fucidin(){
        //Arrange = done i Setup

        //Act
        int result = cont.antalOrdinationerPrVægtPrLægemiddel(20,200,fucidin);

        //Assert
        assertEquals(0, result);
    }

    @Test
    public void test_AntOrdPrVægtPrLægemiddel_Ml20Og60kg(){
        //Arrange = done i Setup

        //Act
        int result = cont.antalOrdinationerPrVægtPrLægemiddel(20,60,paracetamol);

        //Assert
        assertEquals(2, result);
    }

    //    ---------------Metode: opretDagligSkaevOrdination ----------------

    @Test
    public void p_ordination_contains_dagligSkaev() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 1);
        Patient p1 = new Patient("234567543","Hans",78.0);
        LocalTime[] klokkeslet = {LocalTime.of(12,0)};
        double[] antal = {1};

        //Act
        DagligSkaev dagligSkæv = cont.opretDagligSkaevOrdination(startDato,slutDato,p1,paracetamol,klokkeslet,antal);

        //Assert
        assertEquals(true,p1.getOrdinationer().contains(dagligSkæv));

    }


    @Test
    public void TC3_p_ordination_notContains_laegemiddel() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 1);
        Patient p1 = new Patient("234567543","Hans",78.0);
        LocalTime[] klokkeslet = {LocalTime.of(12,0)};
        double[] antal = {1};

        //Act
        DagligSkaev dagligSkæv = cont.opretDagligSkaevOrdination(startDato,slutDato,p1,paracetamol,klokkeslet,antal);

        //Assert
        assertEquals("Paracetamol",p1.getOrdinationer().get(0).getLaegemiddel().getNavn());

    }


    @Test
    public void TC4_p_ordination_9dage() {
        //Arrange
        LocalDate slutDato = LocalDate.of(2022, 10, 9);
        Patient p1 = new Patient("234567543","Hans",78.0);
        LocalTime[] klokkeslet = {LocalTime.of(12,0)};
        double[] antal = {1};

        //Act
        DagligSkaev dagligSkæv = cont.opretDagligSkaevOrdination(startDato,slutDato,p1,paracetamol,klokkeslet,antal);

        //Assert
        assertEquals(9,p1.getOrdinationer().get(0).antalDage());

    }



}
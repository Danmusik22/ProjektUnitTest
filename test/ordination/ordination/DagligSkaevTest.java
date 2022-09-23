package ordination.ordination;


import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


public class DagligSkaevTest {

    private DagligSkaev dagligSkaev;
    private LocalDate startdato;
    private LocalDate slutdato;
    private LocalTime tid;
    private LocalTime tid2;
    private LocalTime tid3;
    private LocalTime tid4;

    private int antal;
    private int antal2;
    private int antal3;
    private int antal4;


    @BeforeEach
    public void setup(){
        this.startdato = LocalDate.of(2022,10,1);
        this.slutdato = LocalDate.of(2022,10,4);
    }


    // --------- test af opretDosis() ---------------
    @Test
    public void opret1Dagligt() {
        // Arrange
        dagligSkaev = new DagligSkaev(startdato,startdato);
        tid = LocalTime.of(8, 0);
        antal = 2;

        // Act
        dagligSkaev.opretDosis(tid,antal);

        // Assert
        assertEquals(1,dagligSkaev.getDoser().size());
    }

    @Test
    public void opret1Dagligt_antal() {
        // Arrange
        dagligSkaev = new DagligSkaev(startdato,startdato);
        tid = LocalTime.of(8, 0);
        antal = 2;

        // Act
        dagligSkaev.opretDosis(tid,antal);


        // Assert
        assertEquals(2.0,dagligSkaev.getDoser().get(0).getAntal());
    }

    @Test
    public void opret2Daglig_size() {
        // Arrange
        dagligSkaev = new DagligSkaev(startdato,startdato);
        antal = 2;
        antal2 = 3;
        tid = LocalTime.of(9, 0);
        tid2 = LocalTime.of(12, 0);

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);


        // Assert
        assertEquals(2,dagligSkaev.getDoser().size());

    }
    @Test
    public void opret2Daglig_antal() {
        // Arrange
        dagligSkaev = new DagligSkaev(startdato,startdato);
        antal = 2;
        antal2 = 3;
        tid = LocalTime.of(9, 0);
        tid2 = LocalTime.of(12, 0);

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);


        // Assert
        assertEquals(3.0,dagligSkaev.getDoser().get(1).getAntal());

    }
    @Test
    public void opret4Dagligt_size() {
        // Arrange
        dagligSkaev = new DagligSkaev(startdato,startdato);
        antal = 2;
        antal2 = 1;
        antal3 = 0;
        antal4 = 4;
        tid = LocalTime.of(8, 0);
        tid2 = LocalTime.of(12, 0);
        tid3 = LocalTime.of(18, 0);
        tid4 = LocalTime.of(23, 0);

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);
        dagligSkaev.opretDosis(tid3,antal3);
        dagligSkaev.opretDosis(tid4,antal4);


        // Assert
        assertEquals(4,dagligSkaev.getDoser().size());

    }
    @Test
    public void opret4Dagligt_AntalKl18() {
        // Arrange
        dagligSkaev = new DagligSkaev(startdato,startdato);
        antal = 2;
        antal2 = 1;
        antal3 = 0;
        antal4 = 4;
        tid = LocalTime.of(8, 0);
        tid2 = LocalTime.of(12, 0);
        tid3 = LocalTime.of(18, 0);
        tid4 = LocalTime.of(23, 0);

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);
        dagligSkaev.opretDosis(tid3,antal3);
        dagligSkaev.opretDosis(tid4,antal4);


        // Assert
        assertEquals(0.0,dagligSkaev.getDoser().get(2).getAntal());

    }
    @Test
    public void opret4Dagligt_klokkesletPåIndex2() {
        // Arrange
        dagligSkaev = new DagligSkaev(startdato,startdato);
        antal = 2;
        antal2 = 1;
        antal3 = 0;
        antal4 = 4;
        tid = LocalTime.of(8, 0);
        tid2 = LocalTime.of(12, 0);
        tid3 = LocalTime.of(18, 0);
        tid4 = LocalTime.of(23, 0);

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);
        dagligSkaev.opretDosis(tid3,antal3);
        dagligSkaev.opretDosis(tid4,antal4);

        // Assert
        assertEquals(tid3,dagligSkaev.getDoser().get(2).getTid());

    }


//------------------test af samletDosis()----------------------------





    @Test
    public void enPrDag4dage() {
        // Arrange
        dagligSkaev = new DagligSkaev(this.startdato,this.slutdato);
        tid = LocalTime.of(8, 0);
        antal = 1;

        // Act
        dagligSkaev.opretDosis(tid,antal);

        // Assert
        assertEquals(4.0,dagligSkaev.samletDosis());

    }

    @Test
    public void firePrDag4dage() {
        // Arrange
        dagligSkaev = new DagligSkaev(this.startdato,this.slutdato);
        tid = LocalTime.of(9, 0);
        tid2 = LocalTime.of(12, 0);
        antal = 2;
        antal2 = 2;

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);

        // Assert
        assertEquals(16.0,dagligSkaev.samletDosis());


    }

    @Test
    public void seksPrDag4dage() {
        // Arrange
        dagligSkaev = new DagligSkaev(this.startdato,this.slutdato);
        tid = LocalTime.of(8, 0);
        tid2 = LocalTime.of(12, 0);
        tid3 = LocalTime.of(18, 0);
        tid4 = LocalTime.of(23, 0);
        antal = 2;
        antal2 = 3;
        antal3 = 0;
        antal4 = 1;

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);
        dagligSkaev.opretDosis(tid3,antal3);
        dagligSkaev.opretDosis(tid4,antal4);

        // Assert
        assertEquals(24.0,dagligSkaev.samletDosis());


    }


// ----------------------- test af Døgndosis() ----------------------

    @Test
    public void døgndosis_opret1Dagligt() {
        // Arrange
        dagligSkaev = new DagligSkaev(this.startdato,this.slutdato);
        tid = LocalTime.of(9, 0);
        tid2 = LocalTime.of(12, 0);
        antal = 2;
        antal2 = 2;

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);

        // Assert
        assertEquals(4.0,dagligSkaev.doegnDosis());

    }

    @Test
    public void døgndosis_opret2Dagligt() {
        // Arrange
        LocalDate nydato = LocalDate.of(2022,10,7);
        dagligSkaev = new DagligSkaev(startdato,nydato);
        tid = LocalTime.of(9, 0);
        tid2 = LocalTime.of(12, 0);
        antal = 2;
        antal2 = 1;

        // Act
        dagligSkaev.opretDosis(tid,antal);
        dagligSkaev.opretDosis(tid2,antal2);

        // Assert
        assertEquals(3.0,dagligSkaev.doegnDosis());


    }



}
package ordination.ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class Ordination {
    private LocalDate startDen;
    private LocalDate slutDen;
    private Laegemiddel laegemiddel;

    // TODO Link til Laegemiddel check
    // TODO constructor (med specifikation) check

    public Ordination(LocalDate startDen, LocalDate slutDen) {
        this.startDen = startDen;
        this.slutDen = slutDen;
    }

    public LocalDate getStartDen() {
        return startDen;
    }	

    public LocalDate getSlutDen() {
        return slutDen;
    }

    /**
     * Link til lægemidler:
     * setlægemiddel
     */
    public void setLaegemiddel(Laegemiddel laegemiddel){
        this.laegemiddel = laegemiddel;
    }
    public Laegemiddel getLaegemiddel() {
        return laegemiddel;
    }


    /**
     * Antal hele dage mellem startdato og slutdato. Begge dage inklusive.
     * @return antal dage ordinationen gælder for
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDen, slutDen) + 1;
    }

    @Override
    public String toString() {
        return startDen.toString();
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
     * @return
     */
    public abstract double samletDosis();

    /**
     * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen er gyldig
     * @return
     */
    public abstract double doegnDosis();

    /**
     * Returnerer ordinationstypen som en String
     * @return
     */
    public abstract String getType();
}

package ordination.ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class PN extends Ordination {
    private double antalEnheder;
    private ArrayList<LocalDate> datoer;

    public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
        super(startDen, slutDen);
        this.antalEnheder = antalEnheder;
        datoer = new ArrayList<>();
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (givesDen.isBefore(getStartDen()) || givesDen.isAfter(getSlutDen())) {
            throw new IllegalArgumentException("Ugyldig Dato");
        } else {
            datoer.add(givesDen);
            return true;
        }
    }

    /**
     * beregning af døgndosis - (antalgange * antalEnheder)/Antal dage mellem første
     * og sidste givning.
     *
     * @return double, døgndosis.
     */
    public double doegnDosis() {
        if (datoer.isEmpty()) {
            return 0;
        } else {
            LocalDate førstegivning = datoer.get(0);
            LocalDate sidsteGivning = datoer.get(datoer.size() - 1);

            return (datoer.size() * antalEnheder) / ChronoUnit.DAYS.between(førstegivning, sidsteGivning.plusDays(1));
        }
    }

    @Override
    public String getType() { // type=PN, Daglig osv
        return "PN";
    }


    public double samletDosis() {
        return antalEnheder * datoer.size();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        return datoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public ArrayList<LocalDate> getDatoer() {

        return datoer;
    }
}

package ordination.ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class PN extends Ordination {
    private double antalEnheder;
    private int antalGange;
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
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (givesDen.isAfter(getStartDen()) && givesDen.isBefore(getSlutDen())){
            antalGange++;
            return true;
        }else {
            return false;
        }
    }

    /**
     * beregning af døgndosis - (antalgange * antalEnheder)/Antal dage mellem første
     * og sidste givning.
     * @return double, døgndosis.
     */
    public double doegnDosis() {
        LocalDate førstegivning = datoer.get(0);
        LocalDate sidsteGivning = datoer.get(datoer.size()-1);

        return (antalGange*antalEnheder)/ ChronoUnit.DAYS.between(førstegivning,sidsteGivning);
    }

    @Override
    public String getType() {
        return null;
    }


    public double samletDosis() {
        // TODO
        return 0.0;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        // TODO
        return-1;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}

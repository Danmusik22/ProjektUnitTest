package ordination.ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private ArrayList<Dosis> dosis = new ArrayList();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }



    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis1 = new Dosis(tid, antal);
        dosis.add(dosis1);
    }

    @Override
    public double samletDosis() {
        double samletdosis = 0;

        for (Dosis d1 : dosis){
            samletdosis = samletdosis + d1.getAntal();
        }
        return samletdosis;
    }

    @Override
    public double doegnDosis() {

        return samletDosis()/antalDage();
    }

    @Override
    public String getType() {
        return "Daglig Skæv";
    }
}

package ordination.ordination;

import java.time.LocalDate;

public class DagligFast extends Ordination {

    private Dosis[] dosis = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public void createDagligDosis(Dosis morgendosis, Dosis middagdosis, Dosis aftendosis, Dosis natdosis) {
        dosis[0] = morgendosis;
        dosis[1] = middagdosis;
        dosis[2] = aftendosis;
        dosis[3] = natdosis;
    }

    public Dosis[] getDoser() {
        return dosis;
    }
    @Override
    public double samletDosis() {
        return doegnDosis() * antalDage();
    }

    @Override
    public double doegnDosis() {
        double dagligDosis = 0;
        for (int i = 0; i < dosis.length; i++) {
            dagligDosis += dosis[i].getAntal();
        }
        return dagligDosis;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }
    // TODO
}

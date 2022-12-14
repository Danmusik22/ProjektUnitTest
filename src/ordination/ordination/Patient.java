package ordination.ordination;

import java.util.ArrayList;

public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;
    private ArrayList<Ordination> ordinationer;

    // TODO: Link til Ordination

    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaegt = vaegt;
        ordinationer = new ArrayList<>();
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }

    public ArrayList<Ordination> getOrdinationer() {
        return ordinationer;
    }

    //TODO: Metoder (med specifikation) til at vedligeholde link til Ordination
    public Ordination addOrdination(Ordination ordination){
            ordinationer.add(ordination);
            return ordination;
    }

    public void removeOrdination(Ordination ordination){
        if (ordinationer.contains(ordination)){
            ordinationer.remove(ordination);
        }
    }

    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

}

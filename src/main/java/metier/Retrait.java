package main.java.metier;

public class Retrait extends Operation {
    private String destination;
    public Retrait(String id, double montant ,String destination) {
        super(id, montant);
        this.destination = destination;
    }

    public   String getDestination() {
        return destination;
    }
    @Override
    public String getType() {
        return "Retrait";
    }
//    @Override
//    public String toString() {
//        return "Retrait de " + montant + "€ - Destination: " + destination + " - " + DateUtil.formatDate(date);
//    }
}

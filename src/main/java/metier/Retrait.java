package main.java.metier;
import main.java.util.DateUtil;
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
    @Override
    public String toString() {
        return "Retrait de " + montant + "DH - Destination: " + destination + " - " + DateUtil.formatDate(date);
    }
}

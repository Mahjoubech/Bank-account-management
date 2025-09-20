package service;

public class Retrait extends Operation {
    private String destination;
    public Retrait(double montant, String destination) {
        super(montant);
        this.destination = destination;
    }
    @Override
    public String getType() { return "Retrait"; }
    @Override
    public String getDetails() { return "Destination: " + destination; }
}
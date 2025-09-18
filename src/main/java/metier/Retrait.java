package metier;

public class Retrait extends Operation {
    private String destination;

    public Retrait(double montant, String destination) {
        super(montant);
        this.destination = destination;
    }

    @Override
    public String toString() {
        return super.toString() + " (Retrait, Dest: " + destination + ")";
    }
}
package metier;

public class Versement extends Operation {
    private String source;

    public Versement(double montant, String source) {
        super(montant);
        this.source = source;
    }

    @Override
    public String toString() {
        return super.toString() + " (Versement, Source: " + source + ")";
    }
}
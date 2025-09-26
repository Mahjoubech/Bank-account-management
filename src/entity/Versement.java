package entity;

public class Versement extends Operation {
    private String source;
    public Versement(double montant, String source) {
        super(montant);
        this.source = source;
    }
    @Override
    public String getType() { return "Versement"; }
    @Override
    public String getDetails() { return "Source: " + source; }
}
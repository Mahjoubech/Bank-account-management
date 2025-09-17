package main.java.metier;

public class Versement extends Operation{
    private String source ;
    public Versement(String id, double montant ,String source){
        super(id,montant);
        this.source = source;
    }
    public String getSource() {
        return source;
    }
    @Override
    public String getType() {
        return "Versement";
    }
//    @Override
//    public String toString() {
//        return "Versement de " + montant + "€ - Source: " + source + " - " + DateUtil.formatDate(date);
//    }
}

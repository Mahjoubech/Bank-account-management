package main.java.metier;
import main.java.util.DateUtil;
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
    @Override
    public String toString() {
        return "Versement de " + montant + "DH - Source: " + source + " - " + DateUtil.formatDate(date);
    }
}

package main.java.metier;
import java.time.LocalDateTime;
import util.DateUtil;

public abstract class Operation {
    protected String id;
    protected LocalDateTime  date;
    protected double montant;

    public Operation(String id, double montant) {
        this.id = id;
        this.montant = montant;
        this.date = LocalDateTime.now();
    }
    public String getId() {
        return id;
    }
    public double getMontant() {
        return montant;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public abstract String getType();
//    @Override
//
//        public String toString() {
//            return getType() + " de " + montant + "€ - " + DateUtil.formatDate(date);
//        }

}

package metier;

import java.time.LocalDateTime;
import java.util.UUID;
import util.DateUtil;

public abstract class Operation {
    protected String numero;
    protected LocalDateTime date;
    protected double montant;

    public Operation(double montant) {
        this.numero = UUID.randomUUID().toString();
        this.date = DateUtil.now();
        this.montant = montant;
    }
    public String getNumero() { return numero; }
    public LocalDateTime getDate() { return date; }
    public double getMontant() { return montant; }
    public abstract String getType();
    public abstract String getDetails();
    public void setNumero(String numero) { this.numero = numero; }
    public void setDate(LocalDateTime date) { this.date = date; }
    @Override
    public String toString() {
        return "[" + DateUtil.format(date) + "] #" + numero + " : " + montant + " DH (" + getType() + ", " + getDetails() + ")";
    }
}
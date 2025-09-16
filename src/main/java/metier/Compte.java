package main.java.metier;
import java.util.ArrayList;
import java.util.List;
public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations;

    public Compte(String code , double solde ){
        this.code = code;
        this.solde = solde ;
        this.listeOperations = new ArrayList<>();
    }
    public abstract void  retiret(double montant);
    public abstract double  calculerIntret();
    public abstract void  afficherDetails();
}

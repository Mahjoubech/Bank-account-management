package metier;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations;

    public Compte(String code, double solde) {
        this.code = code;
        this.solde = solde;
        this.listeOperations = new ArrayList<>();
    }

    public String getCode() { return code; }
    public double getSolde() { return solde; }
    public List<Operation> getListeOperations() { return listeOperations; }

    public abstract void retirer(double montant, String destination) throws Exception;
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    public void ajouterOperation(Operation op) {
        if (op instanceof Versement) {
            this.solde += op.getMontant();
        } else if (op instanceof Retrait) {
            this.solde -= op.getMontant();
        }
        listeOperations.add(op);
    }
}
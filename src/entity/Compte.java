package entity;
import util.Validateur;
import java.util.ArrayList;
import java.util.List;

public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations = new ArrayList<>();
    public Compte(String code, double solde) {
        if (!Validateur.isValidCompteCode(code))
            throw new IllegalArgumentException("Format code compte invalide ! (CPT-XXXXX)");
        if (!Validateur.isPositiveDouble(solde))
            throw new IllegalArgumentException("Solde doit être positif !");
        this.code = code;
        this.solde = solde;
    }
    public String getCode() { return code; }
    public double getSolde() { return solde; }
    public List<Operation> getListeOperations() { return listeOperations; }
    public void effectuerVersement(double montant, String source) throws Exception {
        if (!Validateur.isPositiveAmount(montant)) throw new Exception("Montant négatif ou nul !");
        ajouterOperation(new Versement(montant, source));
        solde += montant;
    }
    public abstract void retirer(double montant, String destination) throws Exception;
    public abstract double calculerInteret();
    public abstract void afficherDetails();
    protected void ajouterOperation(Operation op) {
        listeOperations.add(op);
    }

}
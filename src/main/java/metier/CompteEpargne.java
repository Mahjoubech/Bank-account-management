package metier;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(String code, double solde, double tauxInteret) {
        super(code, solde);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public void retirer(double montant, String destination) throws Exception {
        if (solde < montant) {
            throw new Exception("Solde insuffisant !");
        }
        ajouterOperation(new Retrait(montant, destination));
    }

    @Override
    public double calculerInteret() {
        return solde * tauxInteret / 100.0;
    }

    @Override
    public void afficherDetails() {
        System.out.println("\nCompte Epargne [" + code + "]");
        System.out.println("Solde: " + solde + " EUR, Taux: " + tauxInteret + "%");
        for (Operation op : listeOperations) {
            System.out.println(op);
        }
    }
}
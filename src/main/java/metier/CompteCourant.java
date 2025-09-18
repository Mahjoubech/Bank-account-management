package metier;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(String code, double solde, double decouvert) {
        super(code, solde);
        this.decouvert = decouvert;
    }

    @Override
    public void retirer(double montant, String destination) throws Exception {
        if (solde - montant < -decouvert) {
            throw new Exception("Solde insuffisant (dépassement du découvert autorisé) !");
        }
        ajouterOperation(new Retrait(montant, destination));
    }

    @Override
    public double calculerInteret() {
        return 0; // Pas d'intérêt sur compte courant
    }

    @Override
    public void afficherDetails() {
        System.out.println("\nCompte Courant [" + code + "]");
        System.out.println("Solde: " + solde + " EUR, Découvert: " + decouvert);
        for (Operation op : listeOperations) {
            System.out.println(op);
        }
    }
}
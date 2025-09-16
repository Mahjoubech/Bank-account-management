package main.java.metier;

public class CompteCourant extends Compte {
    private double decovert ;
    public CompteCourant(String code , double solde ,double decovert) {
        super(code,solde);
        this.decovert = decovert ;
    }
    @Override
    public void  retiret(double montant){
        if(montant <= 0 ){
            System.out.println("Le montant doit être positif");
        }
        if(this.solde - montant >= -decovert){
          this.solde -= montant;
            System.out.println("Retrait de " + montant + " effectué avec succès.");
        }else{
            System.out.println("Erreur : solde insuffisant, dépassement du découvert autorisé.");

        }
    }
    @Override
    public double  calculerIntret(){
        System.out.println("heeeeey how r u ");

            return 0;
    }

    @Override
    public void  afficherDetails() {
        System.out.println("heeeeey how r u ");

    }
}

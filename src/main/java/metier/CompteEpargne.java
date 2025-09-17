package main.java.metier;

public class  CompteEpargne extends   Compte {
    private  double tauxIntert ;
    public CompteEpargne(String code , double solde  ,double tauxIntert){
        super(code , solde);
        this.tauxIntert = tauxIntert;
    }

    @Override
    public void  retiret(double montant){
        if(montant <= 0 ){
            System.out.println("Le montant doit être positif");
        }
        if(this.solde >= montant){
            this.solde -= montant;
            System.out.println("Retrait de " + montant + " effectué avec succès.");
        }else{
            System.out.println("Erreur : solde insuffisant, dépassement du découvert autorisé.");

        }
    }
    @Override
    public double  calculerIntret(){
        return (solde * tauxIntert) /100;
    }

    @Override
    public void  afficherDetails() {
        System.out.println("Compte Épargne : " + code);
        System.out.println("Solde : " + solde);
        System.out.println("Taux d'intérêt: " + tauxIntert + "%");
        System.out.println("Intérêts annuels: " + calculerIntret());
    }


    public double getTauxIntert(){return tauxIntert;};
}
package ui;

import metier.*;
import util.*;
import java.util.*;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, Compte> comptes = new HashMap<>();

    public static void main(String[] args) {
        int choix;
        do {
            afficherMenu();
            choix = Validator.readInt("Choisissez une option : ");
            switch (choix) {
                case 1: creerCompte(); break;
                case 2: effectuerVersement(); break;
                case 3: effectuerRetrait(); break;
                case 4: effectuerVirement(); break;
                case 5: consulterSolde(); break;
                case 6: consulterOperations(); break;
                case 0: System.out.println("Au revoir !"); break;
                default: System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    private static void afficherMenu() {
        System.out.println("\n--- Menu Banque ---");
        System.out.println("1. Créer un compte");
        System.out.println("2. Effectuer un versement");
        System.out.println("3. Effectuer un retrait");
        System.out.println("4. Effectuer un virement");
        System.out.println("5. Consulter le solde");
        System.out.println("6. Consulter les opérations");
        System.out.println("0. Quitter");
    }

    private static void creerCompte() {
        System.out.println("Type de compte (1: Courant, 2: Epargne) : ");
        int type = Validator.readInt("");
        String code = Validator.readCompteCode("Code du compte (format CPT-XXXXX): ");
        if (comptes.containsKey(code)) {
            System.out.println("Ce code existe déjà !");
            return;
        }
        if (type == 1) {
            double solde = Validator.readDouble("Solde initial: ");
            double decouvert = Validator.readDouble("Découvert autorisé: ");
            comptes.put(code, new CompteCourant(code, solde, decouvert));
        } else if (type == 2) {
            double solde = Validator.readDouble("Solde initial: ");
            double taux = Validator.readDouble("Taux d'intérêt (%): ");
            comptes.put(code, new CompteEpargne(code, solde, taux));
        } else {
            System.out.println("Type de compte invalide !");
        }
    }

    private static Compte selectionnerCompte() {
        String code = Validator.readCompteCode("Code du compte: ");
        Compte cpt = comptes.get(code);
        if (cpt == null) System.out.println("Compte introuvable !");
        return cpt;
    }

    private static void effectuerVersement() {
        Compte cpt = selectionnerCompte();
        if (cpt == null) return;
        double montant = Validator.readDouble("Montant à verser: ");
        String source = Validator.readString("Source du versement: ");
        try {
            cpt.ajouterOperation(new Versement(montant, source));
            System.out.println("Versement effectué !");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void effectuerRetrait() {
        Compte cpt = selectionnerCompte();
        if (cpt == null) return;
        double montant = Validator.readDouble("Montant à retirer: ");
        String dest = Validator.readString("Destination du retrait: ");
        try {
            cpt.retirer(montant, dest);
            System.out.println("Retrait effectué !");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void effectuerVirement() {
        System.out.println("Compte source:");
        Compte cptSrc = selectionnerCompte();
        if (cptSrc == null) return;
        System.out.println("Compte destination:");
        Compte cptDest = selectionnerCompte();
        if (cptDest == null) return;
        double montant = Validator.readDouble("Montant à virer: ");
        try {
            cptSrc.retirer(montant, "Virement sortant");
            cptDest.ajouterOperation(new Versement(montant, "Virement externe"));
            System.out.println("Virement effectué !");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void consulterSolde() {
        Compte cpt = selectionnerCompte();
        if (cpt != null) System.out.println("Solde: " + cpt.getSolde() + " EUR");
    }

    private static void consulterOperations() {
        Compte cpt = selectionnerCompte();
        if (cpt != null) cpt.afficherDetails();
    }
}
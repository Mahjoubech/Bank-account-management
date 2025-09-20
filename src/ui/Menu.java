package ui;

import service.*;
import dao.CompteDao;
import dao.OperationDao;
import util.ConsoleColor;
import util.Helper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        try {
            Connection cnx = dao.DataConnection.getInstance().getConnection();
            CompteDao compteDao = new CompteDao(cnx);
            OperationDao operationDao = new OperationDao(cnx);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println(ConsoleColor.colorize(ConsoleColor.BOLD + ConsoleColor.BLUE, "\n========= MENU BANQUE ========="));
                System.out.println(ConsoleColor.colorize(ConsoleColor.GREEN, "1. Créer Compte Courant"));
                System.out.println(ConsoleColor.colorize(ConsoleColor.GREEN, "2. Créer Compte Epargne"));
                System.out.println(ConsoleColor.colorize(ConsoleColor.YELLOW, "3. Versement"));
                System.out.println(ConsoleColor.colorize(ConsoleColor.YELLOW, "4. Retrait"));
                System.out.println(ConsoleColor.colorize(ConsoleColor.CYAN, "5. Afficher tous les comptes"));
                System.out.println(ConsoleColor.colorize(ConsoleColor.MAGENTA, "6. Afficher opérations de compte"));
                System.out.println(ConsoleColor.colorize(ConsoleColor.RED, "0. Quitter"));
                System.out.print(ConsoleColor.colorize(ConsoleColor.BOLD, "Choix: "));
                int choix = sc.nextInt();
                sc.nextLine();

                try {
                    if (choix == 1) {
                        String code = Helper.generateCompteCode();
                        System.out.println("Code du compte généré : " + code);
                        System.out.print("Solde initial : "); double solde = sc.nextDouble();
                        System.out.print("Découvert autorisé : "); double dec = sc.nextDouble();
                        CompteCourant cc = new CompteCourant(code, solde, dec);
                        compteDao.save(cc);
                        System.out.println(ConsoleColor.colorize(ConsoleColor.GREEN, "Compte courant créé !"));
                    }
                    else if (choix == 2) {
                        String code = Helper.generateCompteCode();
                        System.out.println("Code du compte généré : " + code);
                        System.out.print("Solde initial : "); double solde = sc.nextDouble();
                        System.out.print("Taux intérêt : "); double taux = sc.nextDouble();
                        CompteEpargne ce = new CompteEpargne(code, solde, taux);
                        compteDao.save(ce);
                        System.out.println(ConsoleColor.colorize(ConsoleColor.GREEN, "Compte épargne créé !"));
                    }
                    else if (choix == 3) {
                        System.out.print("Code compte : "); String code = sc.next();
                        Compte c = compteDao.findByCode(code);
                        if (c == null) { /* afficher erreur */ continue; }
                        System.out.print("Montant versement : "); double montant = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Source : "); String source = sc.nextLine();
                        c.effectuerVersement(montant, source);
                        operationDao.save(new Versement(montant, source), code);
                        compteDao.updateSolde(code, c.getSolde()); // Update solde in DB!
                        System.out.println("Versement effectué !");
                    }
                    else if (choix == 4) {
                        System.out.print("Code compte : "); String code = sc.next();
                        Compte c = compteDao.findByCode(code);
                        if (c == null) { System.out.println(ConsoleColor.colorize(ConsoleColor.RED, "Compte introuvable !")); continue; }
                        System.out.print("Montant retrait : "); double montant = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Destination : "); String dest = sc.nextLine();
                        c.retirer(montant, dest);
                        operationDao.save(new Retrait(montant, dest), code);
                        System.out.println(ConsoleColor.colorize(ConsoleColor.GREEN, "Retrait effectué !"));
                    }
                    else if (choix == 5) {
                        List<Compte > comptes = compteDao.findAll();
                        System.out.println(ConsoleColor.colorize(ConsoleColor.BOLD + ConsoleColor.CYAN, "\n====== Comptes ======"));
                        comptes.forEach(Compte::afficherDetails);
                    }
                    else if (choix == 6) {
                        System.out.print("Code compte : "); String code = sc.next();
                        var ops = operationDao.findByCompte(code);
                        System.out.println(ConsoleColor.colorize(ConsoleColor.BOLD + ConsoleColor.YELLOW, "\n====== Operations pour compte " + code + " ======"));
                        ops.forEach(op -> System.out.println(ConsoleColor.colorize(ConsoleColor.YELLOW, op.toString())));
                    }
                    else if (choix == 0) break;
                    else System.out.println(ConsoleColor.colorize(ConsoleColor.RED, "Choix invalide !"));
                } catch (Exception e) {
                    System.out.println(ConsoleColor.colorize(ConsoleColor.RED, "Erreur : " + e.getMessage()));
                }
            }
            sc.close();
        } catch (SQLException e) {
            System.out.println(ConsoleColor.colorize(ConsoleColor.RED, "Erreur connexion base: " + e.getMessage()));
        }
    }
}
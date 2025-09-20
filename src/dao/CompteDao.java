package dao;

import service.Compte;
import service.CompteCourant;
import service.CompteEpargne;
import util.ConsoleColor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompteDao {
    private Connection cnx;

    public CompteDao(Connection cnx) {
        this.cnx = cnx;
    }

    public void save(Compte compte) throws SQLException {
        String sql = "INSERT INTO compte (code, type, solde, decouvert, tauxInteret) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, compte.getCode());
            ps.setString(2, (compte instanceof CompteCourant) ? "courant" : "epargne");
            ps.setDouble(3, compte.getSolde());
            ps.setDouble(4, (compte instanceof CompteCourant) ? ((CompteCourant)compte).getDecouvert() : 0);
            ps.setDouble(5, (compte instanceof CompteEpargne) ? ((CompteEpargne)compte).getTauxInteret() : 0);
            ps.executeUpdate();
            System.out.println(ConsoleColor.colorize(ConsoleColor.GREEN, "Compte enregistré en base!"));
        }
    }

    public List<Compte> findAll() throws SQLException {
        List<Compte> list = new ArrayList<>();
        String sql = "SELECT * FROM compte";
        try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String code = rs.getString("code");
                double solde = rs.getDouble("solde");
                String type = rs.getString("type");
                double decouvert = rs.getDouble("decouvert");
                double tauxInteret = rs.getDouble("tauxInteret");
                if ("courant".equals(type))
                    list.add(new CompteCourant(code, solde, decouvert));
                else
                    list.add(new CompteEpargne(code, solde, tauxInteret));
            }
        }
        return list;
    }

    public Compte findByCode(String code) throws SQLException {
        String sql = "SELECT * FROM compte WHERE code = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String type = rs.getString("type");
                    double solde = rs.getDouble("solde");
                    double decouvert = rs.getDouble("decouvert");
                    double tauxInteret = rs.getDouble("tauxInteret");
                    if ("courant".equals(type))
                        return new CompteCourant(code, solde, decouvert);
                    else
                        return new CompteEpargne(code, solde, tauxInteret);
                }
            }
        }
        return null;
    }
    public void updateSolde(String codeCompte, double nouveauSolde) throws SQLException {
        String sql = "UPDATE compte SET solde = ? WHERE code = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setDouble(1, nouveauSolde);
            ps.setString(2, codeCompte);
            ps.executeUpdate();
        }
    }
    public List<Compte> findBySoldeLessThan(double seuil) throws SQLException {
        return findAll().stream()
                .filter(c -> c.getSolde() < seuil)
                .collect(Collectors.toList());
    }

    public List<Compte> findBySoldeGreaterThan(double seuil) throws SQLException {
        return findAll().stream()
                .filter(c -> c.getSolde() > seuil)
                .collect(Collectors.toList());
    }

}
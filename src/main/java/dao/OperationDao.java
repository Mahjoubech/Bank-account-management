package dao;
import metier.Operation;
import metier.Versement;
import metier.Retrait;
import util.ConsoleColor;
import util.DateUtil;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationDao {
    private Connection cnx;

    public OperationDao(Connection cnx) {
        this.cnx = cnx;
    }

    public void save(Operation op, String codeCompte) throws SQLException {
        String sql = "INSERT INTO operation (numero, code_compte, type, montant, date, details) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, op.getNumero());
            ps.setString(2, codeCompte);
            ps.setString(3, op.getType());
            ps.setDouble(4, op.getMontant());
            ps.setString(5, DateUtil.format(op.getDate()));
            ps.setString(6, op.getDetails());
            ps.executeUpdate();
            System.out.println(ConsoleColor.colorize(ConsoleColor.GREEN, "Operation enregistrée en base!"));
        }
    }

    public List<Operation> findByCompte(String codeCompte) throws SQLException {
        List<Operation> list = new ArrayList<>();
        String sql = "SELECT * FROM operation WHERE code_compte = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, codeCompte);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String numero = rs.getString("numero");
                    String type = rs.getString("type");
                    double montant = rs.getDouble("montant");
                    String dateStr = rs.getString("date");
                    String details = rs.getString("details");
                    LocalDateTime date = LocalDateTime.parse(dateStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    Operation op = null;
                    if ("Versement".equalsIgnoreCase(type))
                        op = new Versement(montant, details);
                    else
                        op = new Retrait(montant, details);
                    op.setNumero(numero);
                    op.setDate(date);
                    list.add(op);
                }
            }
        }
        return list;
    }
}
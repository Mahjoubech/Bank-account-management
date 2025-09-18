package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/servicebank";
    private static final String USER = "root";
    private static final String PASSWORD = "Mahjoub@1230";

    // Private constructor to prevent external instantiation
    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver loaded
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion MySQL établie !");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion : " + ex.getMessage());
            throw ex;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL non trouvé !", e);
        }
    }

    // Singleton accessor
    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Test main
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connexion active !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
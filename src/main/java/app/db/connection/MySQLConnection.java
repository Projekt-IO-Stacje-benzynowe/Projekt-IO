package app.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection conn = null;

    // Dane do połączenia (możesz trzymać w pliku .env lub config w produkcji)
    private static final String URL = "jdbc:mysql://io-stacje.c544m8scom33.eu-north-1.rds.amazonaws.com:3306/io_baza";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin123#";

    public static void makeConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Successful connection to MySQL");
        } catch (SQLException e) {
            System.err.println("❌ Failed connection to MySQL: " + e.getMessage());
        }
    }
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("ℹ️ Connection closed");
            } catch (SQLException e) {
                System.err.println("❌ Failed to close connection: " + e.getMessage());
            }
        }
    }
}


package app.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection conn = null;

    private static final String URL = "jdbc:mysql://io-stacje.c544m8scom33.eu-north-1.rds.amazonaws.com:3306/io_baza";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin123#";

    public static void makeConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Połączono z bazą danych MySQL");
        } catch (SQLException e) {
            System.err.println("❌ Błąd połączenia z MySQL: " + e.getMessage());
        }
    }
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("ℹ️ Połączenie zamknięte.");
            } catch (SQLException e) {
                System.err.println("❌ Błąd przy zamykaniu połączenia: " + e.getMessage());
            }
        }
    }
}


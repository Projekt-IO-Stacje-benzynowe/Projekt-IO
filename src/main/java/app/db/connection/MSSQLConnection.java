package app.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSQLConnection {
    public static Connection conn = null;
    
    public static void MakeConnection(String url, String user, String password){
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych");
        } catch (SQLException err){
            System.err.println("Błąd połączenia: " + err.getMessage());
        }    
    }


    public static void CloseConnection(){
        if (conn != null){
            try{
                conn.close();
            }catch(SQLException e)
            {
                System.err.println(e);
            }
        }
    }


}

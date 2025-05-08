package app.db.repo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import app.db.connection.MSSQLConnection;
import app.model.PromotionsModel;
import app.model.UserModel;

// trzeba bedzie to podzielic na kilka repozytoriów

public class RepositorySQL {
    public static void InsertValue(String tableName, String[][] values){
        String insertSQL = String.format("INSERT INTO %s (ID, Name, ProducerID) VALUES (?, ?, ?)", tableName);
        try (PreparedStatement stmt = MSSQLConnection.conn.prepareStatement(insertSQL)){
            for(String[] row : values){
                stmt.setString(1, row[0]);
                stmt.setString(2, row[1]);
                stmt.setString(3, row[2]);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch(SQLException err){
            System.err.println("Error while inserting values to the table" + err.getMessage());
        }
    }

    public static List<PromotionsModel> GetPromotions(String name){
        String querySQL = """
        SELECT p.* 
        FROM Promotions p 
        JOIN GasStations g ON p.GasStationID = g.ID 
        WHERE g.name = ?
        """;
        List<PromotionsModel> result = new ArrayList<>();     
        try(PreparedStatement stmt = MSSQLConnection.conn.prepareStatement(querySQL)){
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                result.add(new PromotionsModel(rs.getInt("ID"),rs.getString("Name")));
            }
        }catch(SQLException err){
            System.err.println("Error while fetching promotions on chosen GasStation" + err.getMessage());
        }
        return result;
    }
    public static UserModel FindUser(String email){
        String querySQL = "SELECT * FROM Users WHERE email = ?";
        try(PreparedStatement stmt = MSSQLConnection.conn.prepareStatement(querySQL)){
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new UserModel(rs.getInt("ID"),
                                    rs.getString("email"),
                                    rs.getString("password"),
                                    rs.getString("panel"),
                                    rs.getString("nameBranch")); 
            }else{
                return null;
            }
        }catch(SQLException e){
            System.err.println("Error while finding user data " + e);
            return null;
        }
    }

    public static int confirmDelivery(String ID){
        String querySQL = """
         UPDATE Deliveries
         SET status = 'completed' 
         WHERE ID_Delivery = ?
         """;
        try(PreparedStatement stmt = MSSQLConnection.conn.prepareStatement(querySQL)){
            stmt.setString(1, ID);
            return stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error while confirming delivery " + e);
            return 0;            
        }
    }
}

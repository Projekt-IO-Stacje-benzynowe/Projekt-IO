package app.db.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import app.db.connection.MySQLConnection;
import app.model.PromotionsModel;
import app.model.UserModel;

import java.sql.Timestamp;

public class RepositorySQL {
    public static String GetBranchNameForUser(int userID) {
        String querySQL = "SELECT gasStationName FROM UsersGasStation WHERE ID_user = ?"; 

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) { // ZAMIANA TUTAJ
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("gasStationName");
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching branch name for user: " + err.getMessage());
        }
        return null;
    }

    public static void InsertValue(String tableName, String[][] values) {
        String insertSQL = String.format("INSERT INTO %s (ID, Name, ProducerID) VALUES (?, ?, ?)", tableName);
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(insertSQL)) {  // ZAMIANA TUTAJ
            for (String[] row : values) {
                stmt.setString(1, row[0]);
                stmt.setString(2, row[1]);
                stmt.setString(3, row[2]);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException err) {
            System.err.println("Error while inserting values to the table: " + err.getMessage());
        }
    }
    public static List<PromotionsModel> GetPromotions(String name) {
        String querySQL = """
        SELECT p.* 
        FROM Promotions p 
        JOIN Stations g ON p.StationID = g.StationID 
        WHERE g.name = ?
        """;

        System.out.println("Name: " + name);
        List<PromotionsModel> result = new ArrayList<>();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new PromotionsModel(rs.getString("PromotionName"), rs.getString("Description")));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching promotions on chosen GasStation: " + err.getMessage());
        }
        return result;
    }

    public static UserModel FindUser(String email) {
        String querySQL = "SELECT * FROM Users WHERE email = ?";
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) { // ZAMIANA TUTAJ
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new UserModel(
                    rs.getInt("ID"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("panel"),
                    null
                    // rs.getString("nameBranch")
                );
            }else{
                return null;
            }
        }catch (SQLException e) {
            System.err.println("Error while finding user data: " + e.getMessage());
            return null;
        }
    }
    public static int confirmDelivery(String ID) {
        String querySQL = """
         UPDATE Deliveries
         SET status = 'completed' 
         WHERE DeliveryID = ?
         """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) { // ZAMIANA TUTAJ
            stmt.setString(1, ID);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while confirming delivery: " + e.getMessage());
            return 0;
        }
    }

    // private static int GetLastIndexFromDisposals(){
    //     String querySQL = "SELECT MAX(DisposalID) FROM Disposals";
    //     try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
    //         ResultSet rs = stmt.executeQuery();
    //         if(rs.next()){
    //             int id = rs.getInt(1);
    //             return id;
    //         }
    //     }catch(SQLException e){
    //         System.out.println("Error while fetching last row index " + e);
    //     }
    //     return 0;
    // }
    public static void sendRaport(int stationID, int productID, int quantity, String desc, Timestamp date){
        String querySQL = """
            INSERT INTO Disposals (
                DisposalID, 
                StationID, 
                ProductID, 
                Quantity, 
                Reason, 
                ReportDate, 
                DisposalDate, 
                IsDisposed, 
                DisposalConfirmation, 
                Notes
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            // int newID = GetLastIndexFromDisposals() + 1;
            int newID = GetLastIndex("DisposalID", "Disposals") + 1;
            stmt.setInt(1, newID);
            stmt.setInt(2, stationID);
            stmt.setInt(3, productID);
            stmt.setInt(4, quantity);
            stmt.setString(5, desc);
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(7, date);
            stmt.setInt(8, 1);
            stmt.setInt(9, 1);
            stmt.setString(10, null);

            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error while sending report" + e);
        }
    }
    private static int GetLastIndex(String tableName, String columnName){
        String querySQL = "SELECT MAX(" + columnName + ") FROM " + tableName;

        try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                return id;
            }
        }catch(SQLException e){
            System.out.println("Error while fetching last row index " + e);
        }
        return 0;
    }

    public static void sendRequestForDelivery(int stationID, int deliveryID, int productID, int quantity){
        String querySQL = """
            INSERT INTO Deliveries (
                DeliveryID, 
                StationID, 
                ProductID, 
                Quantity,  
                ReportDate,
                ShipmentDate, 
                DeliveryDate,
                Status, 
                ShipmentConfirmation,
                DeliveryConfirmation, 
                Notes
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            int newID = GetLastIndex("Deliveries", "DeliveryID") + 1;
            stmt.setInt(1, newID);
            stmt.setInt(2, stationID);
            stmt.setInt(3, productID);
            stmt.setInt(4, quantity);
            stmt.setString(5, null);
            stmt.setTimestamp(6, null);
            stmt.setTimestamp(7, null);
            stmt.setString(8, "Reported");            
            stmt.setInt(9, 0);
            stmt.setInt(10, 0);
            stmt.setString(11, null);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error while sending report" + e);
        }        
    }   
}       

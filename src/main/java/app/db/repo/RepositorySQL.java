package app.db.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;


import java.util.ArrayList;

import app.db.connection.MySQLConnection;
import app.model.OutletModel;
import app.model.PromotionModel;
import app.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;

import app.model.RewardModel;
import app.model.RewardToIssuanceModel;

public class RepositorySQL {
    public static String GetBranchNameForUser(int userID) {
        String querySQL = "SELECT outletName FROM UsersOutlet WHERE ID_user = ?"; 

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) { // ZAMIANA TUTAJ
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("outletName");
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

    public static ObservableList<PromotionModel> getAllPromotions() {
        String querySQL = """
                SELECT *
                FROM Promotions
                """;
        ObservableList<PromotionModel> result = FXCollections.observableArrayList();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new PromotionModel(
                    rs.getInt("PromotionID"),
                    rs.getString("PromotionName"),
                    rs.getString("Description")));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching promotions on chosen outlet: " + err.getMessage());
        }
        return result;
    }

    public static ObservableList<PromotionModel> getPromotionsForOutlet(String outletName) {
        String querySQL = """
        SELECT p.* 
        FROM Promotions p 
        JOIN Outlets o ON p.OutletsID = o.OutletsID 
        WHERE o.Name = ?
        """;

        ObservableList<PromotionModel> result = FXCollections.observableArrayList();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setString(1, outletName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new PromotionModel(
                    rs.getInt("PromotionID"),
                    rs.getString("PromotionName"),
                    rs.getString("Description")));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching promotions on chosen outlet: " + err.getMessage());
        }
        return result;
    }

    public static ObservableList<OutletModel> getAllOutlets() {
        String querySQL = """
                SELECT *
                FROM Outlets
                """;

        ObservableList<OutletModel> result = FXCollections.observableArrayList();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new OutletModel(
                    rs.getInt("OutletsID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("City"),
                    rs.getString("PostalCode"),
                    rs.getString("Region")));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching outlets: " + err.getMessage());
        }
        return result;
    }

    public static ObservableList<RewardModel> getAllRewards() {
        String querySQL = """
                SELECT *
                FROM RewardProducts
                """;

        ObservableList<RewardModel> result = FXCollections.observableArrayList();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new RewardModel(
                    rs.getInt("RewardProductID"),
                    rs.getInt("PromotionID"),
                    rs.getString("Name"),
                    rs.getInt("UnitPrice"),
                    rs.getInt("RequiredProductsNumber"),
                    null
                ));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching rewards: " + err.getMessage());
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

    public static void sendReport(int outletID, int productID, int quantity, String desc, Timestamp date){
        String querySQL = """
            INSERT INTO Disposals (
                DisposalID, 
                OutletID, 
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
            stmt.setInt(2, outletID);
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

    public static void sendRequestForDelivery(int outletID, int deliveryID, int productID, int quantity){
        String querySQL = """
            INSERT INTO Deliveries (
                DeliveryID, 
                OutletID, 
                RewardProductID, 
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
            stmt.setInt(2, outletID);
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
            System.out.println("Error while sending request " + e);
        }        
    }

    public static ArrayList<Integer> findPromotionsByProductID(int productID) {
        String querySQL = """
            SELECT PromotionID FROM Promotions p
            WHERE p.ProductID = ?
            """;

        ArrayList<Integer> ids = new ArrayList<>();
        try {
            PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL);

            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("PromotionID"));
            }
        } catch (SQLException e) {
            System.out.println("Error while querying promotions: " + e.getMessage());
        }
        return ids;
    }

    public static List<RewardModel> findProductReward(int PromotionID){
        String querySQL = """
            SELECT * FROM RewardProducts p
            WHERE p.PromotionID = ?
            """;
        List<RewardModel> result = new ArrayList<>();
        
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setInt(1, PromotionID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new RewardModel(
                    rs.getInt("RewardProductID"),
                    rs.getInt("PromotionID"),
                    rs.getString("Name"),
                    rs.getInt("UnitPrice"),
                    rs.getInt("RequiredProductsNumber"),
                    null
                ));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching promotions on chosen GasOutlet: " + err.getMessage());
        }
        return result;
    }

    public static void insertRewardToIssuance(
            int issuanceID,
            int rewardProductID,
            int promotionID,
            int outletID,
            Date month,                  
            int quantityIssued,
            double unitPrice,
            double totalValue,
            String notes) {

        String querySQL = """
            INSERT INTO RewardsToIssuance (
                IssuanceID, RewardProductID, PromotionID, OutletID,
                Month, QuantityIssued, UnitPrice, TotalValue, Notes
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {

            stmt.setInt(1, issuanceID);
            stmt.setInt(2, rewardProductID);
            stmt.setInt(3, promotionID);
            stmt.setInt(4, outletID);
            stmt.setDate(5, month);
            stmt.setInt(6, quantityIssued);
            stmt.setDouble(7, unitPrice);
            stmt.setDouble(8, totalValue);
            stmt.setString(9, notes);

            stmt.executeUpdate();
            System.out.println("Dane zostały dodane do RewardsToIssuance.");

        } catch (SQLException e) {
            System.err.println("Błąd podczas dodawania danych: " + e.getMessage());
        }
    }

    public static Integer findOutletIDByName(String name) {
        String querySQL = """
            SELECT OutletsID FROM Outlets
            WHERE Name = ?
        """;

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("OutletsID");
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas wyszukiwania OutletID: " + e.getMessage());
        }
        return null;
    }

    public static List<RewardToIssuanceModel> FetchRewardToIssuance(int outletID) {
        String query = """
            SELECT * FROM RewardsToIssuance
            WHERE OutletID = ?
        """;
    
        List<RewardToIssuanceModel> result = new ArrayList<>();
    
        try ( PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query)) {
    
            stmt.setInt(1, outletID);
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                RewardToIssuanceModel model = new RewardToIssuanceModel(
                    rs.getObject("IssuanceID", Integer.class),
                    rs.getObject("RewardProductID", Integer.class),
                    rs.getObject("PromotionID", Integer.class),
                    rs.getObject("OutletID", Integer.class),
                    rs.getDate("Month"),
                    rs.getObject("QuantityIssued", Integer.class),
                    rs.getObject("UnitPrice", Integer.class),
                    rs.getObject("TotalValue", Integer.class),
                    rs.getString("Notes")
                );
                result.add(model);
            }
    
        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania danych z RewardsToIssuance: " + e.getMessage());
        }
    
        return result;
    }

    public static Integer getMaxIssuanceID() {
        String query = "SELECT MAX(IssuanceID) FROM RewardsToIssuance";
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            if (rs.next()) {
                return rs.getInt(1);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteByIssuanceID(int issuanceID) {
        String sql = "DELETE FROM RewardsToIssuance WHERE IssuanceID = ?";

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(sql)) {

            stmt.setInt(1, issuanceID);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Usunięto " + rowsDeleted + " wiersz z RewardsToIssuance.");
            } else {
                System.out.println("Nie znaleziono wiersza z IssuanceID = " + issuanceID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getMaxIssuanceIDFromRewardIssuance() {
        String query = "SELECT MAX(IssuanceID) FROM RewardIssuance";
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            if (rs.next()) {
                return rs.getInt(1);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertRewardIssuance(RewardToIssuanceModel model) {
        String sql = "INSERT INTO RewardIssuance " +
                "(IssuanceID, RewardProductID, PromotionID, OutletID, Month, QuantityIssued, UnitPrice, TotalValue, Notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement stmt = MySQLConnection.conn.prepareStatement(sql)) {

            stmt.setInt(1, getMaxIssuanceIDFromRewardIssuance() + 1);
            stmt.setInt(2, model.getRewardProductID());
            stmt.setInt(3, model.getPromotionID());
            stmt.setInt(4, model.getOutletID());
            stmt.setDate(5, model.getMonth());
            stmt.setInt(6, model.getTotalValue());
            stmt.setDouble(7, model.getUnitPrice());
            stmt.setDouble(8, model.getTotalValue());
            stmt.setString(9, model.getNotes());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getMaxSaleID() {
        String query = "SELECT MAX(SaleID) FROM Sales";
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            if (rs.next()) {
                return rs.getInt(1);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertSale(int saleID,int PromotionID, int OutletID, int ProductID, Date month, int soldQuantity, double GrossValue, double Margin ){
        String sql = "INSERT INTO Sales " +
                "(SaleID, PromotionID, StationID, ProductID, Month, QuantitySold, GrossValue, Margin) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement stmt = MySQLConnection.conn.prepareStatement(sql)) {

            stmt.setInt(1, saleID);
            stmt.setInt(2, PromotionID);
            stmt.setInt(3, OutletID);
            stmt.setInt(4, ProductID);
            stmt.setDate(5, month);
            stmt.setInt(6, soldQuantity);
            stmt.setDouble(7, GrossValue);
            stmt.setDouble(8, Margin);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Double getProductPriceById(int productId) {
            String query = "SELECT Price FROM Products WHERE ProductID = ?";
            
            try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query)) {
                stmt.setInt(1, productId);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble("Price");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Błąd podczas pobierania ceny produktu: " + e.getMessage());
            }
            
            return null;
        }

    public static Double getPromotionPrice(int promotionID) {
        String query = "SELECT Price FROM Promotions WHERE PromotionID = ?";
        
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query)) {
            stmt.setInt(1, promotionID);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("Price");
                }
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania ceny produktu: " + e.getMessage());
        }
        return 0.0;
    }

}       

package app.db.repo;

import app.db.connection.MySQLConnection;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.model.ProductModel;
import app.model.PromotionModel;
import app.model.RequestModel;
import app.model.UserModel;
import app.model.RewardModel;
import app.model.RewardToIssuanceModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *  RepositorySQL class provides methods to interact with the SQL database.
 *  It includes all the methods for fetching branch names, inserting values, and retrieving promotions, outlets, rewards, and products.
 */
public class RepositorySQL {

    /**
     *  Fetches the branch name for a given user ID.
     * @param userID
     * @return
     */
    public static String getBranchNameForUser(int userID) {
        String querySQL = "SELECT outletName FROM UsersOutlet WHERE ID_user = ?"; 

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
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

    /**
     *  Inserts values into a specified table.
     * @param tableName
     * @param values
     */
    public static void insertValue(String tableName, String[][] values) {
        String insertSQL = String.format("INSERT INTO %s (ID, Name, ProducerID) VALUES (?, ?, ?)", tableName);
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(insertSQL)) {
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

    /**
     *  Fetches all promotions from the database.
     * @return
     */
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
                    rs.getString("Description"),
                    rs.getInt("ProductID"),
                    rs.getTimestamp("StartDate").toLocalDateTime().toLocalDate(),
                    rs.getTimestamp("EndDate").toLocalDateTime().toLocalDate(),
                    rs.getDouble("Price")
                ));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching promotions on chosen outlet: " + err.getMessage());
        }
        return result;
    }

    /**
     *  Fetches promotions for a specific outlet by its name.
     * @param outletName
     * @return
     */
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
                    rs.getString("Description"),
                    rs.getInt("ProductID"),
                    rs.getTimestamp("StartDate").toLocalDateTime().toLocalDate(),
                    rs.getTimestamp("EndDate").toLocalDateTime().toLocalDate(),
                    rs.getDouble("Price")
                ));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching promotions on chosen outlet: " + err.getMessage());
        }
        return result;
    }

    /**
     *  Fetches all outlets from the database.
     * @return
     */
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

    /**
     *  Fetches all rewards from the database.
     * @return
     */
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

    /**
     *  Fetches all products from the database.
     * @return
     */
    public static ObservableList<ProductModel> getAllProducts() {
        String querySQL = """
                SELECT *
                FROM Products
                """;

        ObservableList<ProductModel> result = FXCollections.observableArrayList();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new ProductModel(
                    rs.getInt("ProductID"),
                    rs.getString("Name"),
                    rs.getString("Brand")
                ));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching products: " + err.getMessage());
        }
        return result;
    }

    /**
     *  Fetches all future deliveries for a specific outlet from the database.
     * @return
     */
    public static ObservableList<DeliveryModel> getDeliveriesForOutlet(Integer outletID) {
        String querySQL = """
                SELECT d.DeliveryID, d.OutletID, d.RewardProductID, d.Quantity, d.ShipmentDate, p.Name AS RewardName
                FROM Deliveries d
                JOIN RewardProducts p ON d.RewardProductID = p.RewardProductID
                WHERE d.OutletID = ? AND d.ShipmentDate IS NOT NULL AND d.ShipmentDate > CAST(NOW() AS DATE) AND d.DeliveryDate IS NULL
                """;

        ObservableList<DeliveryModel> result = FXCollections.observableArrayList();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setInt(1, outletID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new DeliveryModel(
                    rs.getInt("DeliveryID"),
                    rs.getInt("OutletID"),
                    rs.getInt("RewardProductID"),
                    rs.getString("RewardName"),
                    rs.getInt("Quantity"),
                    rs.getTimestamp("ShipmentDate").toLocalDateTime().toLocalDate()
                ));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching deliveries for outlet: " + err.getMessage());
        }
        return result;
    }

    /**
     *  Fetches all requests for a specific outlet from the database.
     * @param outletID
     * @return
     */
    public static ObservableList<RequestModel> getRequestsForOutlet(Integer outletID) {
        String querySQL = """
                SELECT d.DeliveryID, d.RewardProductID, rp.Name, d.Quantity, d.ReportDate
                FROM Deliveries d
                JOIN RewardProducts rp on d.RewardProductID = rp.RewardProductID
                WHERE d.ShipmentDate IS NULL AND d.outletID = ?
                """;

        ObservableList<RequestModel> result = FXCollections.observableArrayList();
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setInt(1, outletID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new RequestModel(
                    rs.getInt("DeliveryID"),
                    rs.getInt("RewardProductID"),
                    rs.getString("Name"),
                    rs.getInt("Quantity"),
                    rs.getTimestamp("ReportDate").toLocalDateTime().toLocalDate()
                ));
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching requests: " + err.getMessage());
        }
        return result;
    }

    /**
     *  Deletes a delivery from the database by its ID.
     * @param deliveryID
     * @return
     */
    public static boolean deleteDelivery(Integer deliveryID) {
        String querySQL = """
                DELETE
                FROM Deliveries d
                WHERE d.DeliveryID = ?
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setInt(1, deliveryID);
            System.out.println(stmt.toString());
            int rs = stmt.executeUpdate();
            return rs == 1;
        } catch (SQLException err) {
            System.err.println("Error while deleting delivery: " + err.getMessage());
            return false;
        }
    }

    /**
     *  Adds a new delivery to the database.
     * @param delivery
     * @return
     */
    public static boolean addDelivery(DeliveryModel delivery) {
        String querySQL = """
                INSERT
                INTO Deliveries (OutletID, RewardProductID, Quantity, ShipmentDate, Status)
                VALUES(?, ?, ?, ?, "Shipped")
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
            stmt.setInt(1, delivery.getOutletID());
            stmt.setInt(2, delivery.getRewardID());
            stmt.setInt(3, delivery.getQuantity());
            stmt.setTimestamp(4, Timestamp.valueOf(delivery.getShipmentDate().atStartOfDay()));
            int rs = stmt.executeUpdate();
            return rs == 1;
        } catch (SQLException e) {
            System.err.println("Error while adding delivery: " + e.getMessage());
            return false;
        }
    }

    /**
     *  Modifies an existing delivery in the database.
     * @param rewardID
     * @param quantity
     * @param deliveryDate
     * @param deliveryID
     * @return
     */
    public static boolean modifyDelivery(Integer rewardID, Integer quantity, LocalDate deliveryDate, Integer deliveryID) {
        String querySQL = """
                UPDATE Deliveries
                SET RewardProductID = ?, Quantity = ?, ShipmentDate = ?, Status = "Shipped"
                WHERE DeliveryID = ?
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setInt(1, rewardID);
            stmt.setInt(2, quantity);
            stmt.setTimestamp(3, Timestamp.valueOf(deliveryDate.atStartOfDay()));
            stmt.setInt(4, deliveryID);
            int rs = stmt.executeUpdate();
            return rs == 1;
        } catch (SQLException err) {
            System.err.println("Error while modifying delivery: " + err.getMessage());
            return false;
        }
    }

    public static boolean createPromotion( String promotionName, String description, LocalDate startDate, LocalDate endDate, Integer rewardID, Integer productID, Integer outletID, Integer quantity) {
        String querySQL = """
                INSERT
                INTO Promotions (PromotionName, Description, StartDate, EndDate, ProductID, OutletsID, Price, isActive)
                VALUES(?, ?, ?, ?, ?, ?, ?, true)
                """;
        int rs1, rs3;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setString(1, promotionName);
            stmt.setString(2, description);
            stmt.setTimestamp(3, Timestamp.valueOf(startDate.atStartOfDay()));
            stmt.setTimestamp(4, Timestamp.valueOf(endDate.atStartOfDay()));
            stmt.setInt(5, productID);
            stmt.setInt(6, outletID);
            stmt.setDouble(7, quantity);
            rs1 = stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println("Error while modifying promotion: " + err.getMessage());
            return false;
        }

        int promotionID;
        String querySQL2 = """
                SELECT LAST_INSERT_ID() AS PromotionID
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL2)){
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                promotionID = rs.getInt("PromotionID");
            } else {
                System.err.println("Error while fetching last inserted promotion ID.");
                return false;
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching last inserted promotion ID: " + err.getMessage());
            return false;
        }

        String querySQL3 = """
                Update RewardProducts
                SET PromotionID = ?
                WHERE RewardProductID = ?
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL3)){
            stmt.setInt(1, promotionID);
            stmt.setInt(2, rewardID);
            rs3 = stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println("Error while modifying reward for promotion: " + err.getMessage());
            return false;
        }
        return rs1 == 1 && rs3 == 1;
    }

    /**
     *  Modifies an existing promotion to the database.
     * @param promotionName
     * @param description
     * @param startDate
     * @param endDate
     * @param rewardID
     * @param productID
     * @param quantity
     * @return
     */
    public static boolean modifyPromotion(Integer promotionID, String promotionName, String description, LocalDate startDate, LocalDate endDate, Integer rewardID, Integer productID, Integer quantity) {
        String querySQL = """
                UPDATE Promotions
                SET PromotionName = ?, Description = ?, StartDate = ?, EndDate = ?, ProductID = ?, Price = ?
                WHERE PromotionID = ?
                """;
        int rs1, rs2;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setString(1, promotionName);
            stmt.setString(2, description);
            stmt.setTimestamp(3, Timestamp.valueOf(startDate.atStartOfDay()));
            stmt.setTimestamp(4, Timestamp.valueOf(endDate.atStartOfDay()));
            stmt.setInt(5, productID);
            stmt.setDouble(6, quantity);
            stmt.setInt(7, promotionID);
            rs1 = stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println("Error while modifying promotion: " + err.getMessage());
            return false;
        }

        String querySQL2 = """
                Update RewardProducts
                SET PromotionID = ?
                WHERE RewardProductID = ?
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL2)){
            stmt.setInt(1, promotionID);
            stmt.setInt(2, rewardID);
            rs2 = stmt.executeUpdate();
        } catch (SQLException err) {
            System.err.println("Error while modifying reward for promotion: " + err.getMessage());
            return false;
        }
        return rs1 == 1 && rs2 == 1;
    }

    /**
     *  Deletes a promotion from the database by its ID.
     * @param promotionID
     * @return
     */
    public static boolean deletePromotion(Integer promotionID) {
        String querySQL = """
                DELETE
                FROM Promotions
                WHERE PromotionID = ?
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setInt(1, promotionID);
            int rs = stmt.executeUpdate();
            return rs == 1;
        } catch (SQLException err) {
            System.err.println("Error while deleting promotion: " + err.getMessage());
            return false;
        }
    }
    
    /**
     *  Fetches the reward ID for a specific promotion.
     * @param promotionID
     * @return
     */
    public static int getRewardIDForPromotion(int promotionID) {
        String querySQL = """
                SELECT RewardProductID
                FROM RewardProducts
                WHERE PromotionID = ?
                """;
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)){
            stmt.setInt(1, promotionID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("RewardProductID");
            }
        } catch (SQLException err) {
            System.err.println("Error while fetching reward ID for promotion: " + err.getMessage());
        }
        return -1; // Return -1 if no reward found
    }

    /**
     *  Finds a user by their email address.
     * @param email
     * @return
     */
    public static UserModel findUser(String email) {
        String querySQL = "SELECT * FROM Users WHERE email = ?";
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserModel(
                    rs.getInt("ID"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("panel"),
                    null
                );
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error while finding user data: " + e.getMessage());
            return null;
        }
    }

    /**
     *  Confirms the delivery by updating its status in the database.
     * @param ID
     * @return
     */
    public static int confirmDelivery(String ID) {
        String querySQL = """
            UPDATE Deliveries
            SET status = 'completed' 
            WHERE DeliveryID = ?
            """;
        try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
            stmt.setString(1, ID);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while confirming delivery: " + e.getMessage());
            return 0;
        }
    }

    /**
     *  Sends a report for disposal of a product.
     * @param outletID
     * @param productID
     * @param quantity
     * @param desc
     * @param date
     */
    public static void sendReport(int outletID, int productID, int quantity, String desc, Timestamp date) {
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

        try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
            int newID = getLastIndex("Disposals", "DisposalID") + 1;
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
        } catch(SQLException e) {
            System.out.println("Error while sending report" + e);
        }
    }

    /**
     *  Retrieves the last index of a specified column in a table.
     * @param tableName
     * @param columnName
     * @return
     */
    private static int getLastIndex(String tableName, String columnName) {
        String querySQL = "SELECT MAX(" + columnName + ") FROM " + tableName;

        try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                return id;
            }
        } catch(SQLException e) {
            System.out.println("Error while fetching last row index " + e);
        }
        return 0;
    }   
    
    /**
     *  Checks if a reward product exists in the database by its ID.
     * @param rewardProductId
     * @return
     */
    public static boolean doesRewardProductExist(int rewardProductId) {
        String query = "SELECT 1 FROM RewardProducts WHERE RewardProductID = ? LIMIT 1";

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query)) {

            stmt.setInt(1, rewardProductId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();  // true jeśli istnieje, false jeśli nie
            }

        } catch (SQLException e) {
            System.err.println("Błąd bazy danych: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  Retrieves the maximum DeliveryID from the Deliveries table.
     * @return
     */
    public static Integer getMaxDeliveryId() {
        String query = "SELECT MAX(DeliveryID) AS MaxID FROM Deliveries";

        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int maxId = rs.getInt("MaxID");
                return rs.wasNull() ? null : maxId;
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania max DeliveryID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  Sends a request for delivery of a product to a specific outlet.
     * @param outletID
     * @param deliveryID
     * @param productID
     * @param quantity
     */
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
        try(PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
            int newID = getLastIndex("Deliveries", "DeliveryID") + 1;
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
        } catch(SQLException e) {
            System.out.println("Error while sending request " + e);
        }        
    }

    /**
     *  Finds promotions by product ID.
     * @param productID
     * @return
     */
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

    /**
     *  Finds product rewards by promotion ID.
     * @param PromotionID
     * @return
     */
    public static List<RewardModel> findProductReward(int PromotionID) {
        String querySQL = """
            SELECT * FROM RewardProducts p
            WHERE p.PromotionID = ?
            """;
        List<RewardModel> result = new ArrayList<>();
        
        try (PreparedStatement stmt = MySQLConnection.conn.prepareStatement(querySQL)) {
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

    /**
     *  Inserts a reward issuance into the RewardsToIssuance table.
     * @param issuanceID
     * @param rewardProductID
     * @param promotionID
     * @param outletID
     * @param month
     * @param quantityIssued
     * @param unitPrice
     * @param totalValue
     * @param notes
     */
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

    /**
     *  Finds the OutletID by its name.
     * @param name
     * @return
     */
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

    /**
     *  Fetches all reward issuances for a specific outlet.
     * @param outletID
     * @return
     */
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

    /**
     *  Retrieves the maximum IssuanceID from the RewardsToIssuance table.
     * @return
     */
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

    /**
     *  Deletes a reward issuance by its IssuanceID from the RewardsToIssuance table.
     * @param issuanceID
     */
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

    /**
     *  Retrieves the maximum IssuanceID from the RewardIssuance table.
     * @return
     */
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

    /**
     *  Inserts a new reward issuance into the RewardIssuance table.
     * @param model
     */
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

    /**
     *  Retrieves the maximum SaleID from the Sales table.
     * @return
     */
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

    /**
     *  Inserts a new sale into the Sales table.
     * @param saleID
     * @param PromotionID
     * @param OutletID
     * @param ProductID
     * @param month
     * @param soldQuantity
     * @param GrossValue
     * @param Margin
     */
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

    /**
     *  Retrieves the price of a product by its ID.
     * @param productId
     * @return
     */
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

    /**
     *  Retrieves the price of a promotion by its ID.
     * @param promotionID
     * @return
     */
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

package app.service.business_panel;

import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Service class for generating sales reports in the business panel.
 * This class connects to a MySQL database to retrieve sales data and generates PDF reports with charts.
 */
public class ReportGenerator {

    // Datebase connection details
    private static final String DB_URL = "jdbc:mysql://io-stacje.c544m8scom33.eu-north-1.rds.amazonaws.com:3306/io_baza";
    private static final String USER = "admin";
    private static final String PASS = "admin123#";

    /**
     *  Generates a sales report for the last 12 months.
     *  This method connects to a MySQL database, retrieves sales data for the specified station and product,
     *  generates three charts (total sales, year-over-year sales difference, and total units sold),
     *  and creates a PDF report containing these charts.
     *  If the stationID or productID is 0, the report includes all stations or products respectively.
     * @param outputFilePath
     * @param stationID
     * @param productID
     * @throws Exception
     */
    public void generateSalesReport2024(String outputFilePath, int stationID, int productID) throws Exception {
        int [] reportType = new int [3]; // used to customize report title
        reportType [0] = 1; // 1 means sales report
        reportType [1] = stationID;
        reportType [2] = productID;

        Map<String, Double> sales2024 = new LinkedHashMap<>(); // to storage sales / quantity value from database
        Map<String, Double> sales2023 = new LinkedHashMap<>();
        Map<String, Double> quantity2024 = new LinkedHashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // validate input - stationID, and productID
            int minS = getMinStationId(conn);
            int maxS = getMaxStationId(conn);
            if( (stationID < minS || stationID > maxS) && stationID != 0){
                System.out.print("Wrong OutletID");
                return;
            }
            int minP = getMinProductId(conn);
            int maxP = getMaxProductId(conn);
            if( productID < minP || productID > maxP){
                System.out.print("Wrong ProductID");
                return;
            }
            LocalDate now = LocalDate.now();
            YearMonth currentMonth = YearMonth.from(now).minusMonths(1);

            for (int i = 11; i >= 0; i--) {// collecting sales, and quantity for each month
                YearMonth month2024 = currentMonth.minusMonths(i);
                YearMonth month2023 = month2024.minusYears(1);

                String label = String.format("%02d / %d", month2024.getMonthValue(), month2024.getYear());
                String date2024 = month2024 + "-01";
                String date2023 = month2023 + "-01";


                    sales2024.put(label, getTotalSales(conn, date2024, stationID, productID));
                    sales2023.put(label, getTotalSales(conn, date2023, stationID, productID));
                    quantity2024.put(label, getTotalQuantity(conn, date2024, stationID, productID));

            }
        }
        // create 3 charts for collected data
        JFreeChart chartSales = createStyledChart("Monthly Total Sales (PLN)", "Month", "PLN", sales2024, new Color(220, 53, 69));
        JFreeChart chartDiff = createStyledChart("Year-over-Year Sales Difference (PLN)", "Month", "PLN", computeDifference(sales2024, sales2023), new Color(255, 159, 64));
        JFreeChart chartQuantity = createStyledChart("Monthly Total Units Sold", "Month", "Units", quantity2024, new Color(40, 167, 69));
        // generate final PDF report
        generatePDF(outputFilePath, reportType, chartSales, chartDiff, chartQuantity);
    }

    /**
     *  Retrieves the total sales for a specific month, station, and product from the database.
     *  If stationId or productId is 0, it retrieves sales for all stations or products respectively.
     * @param conn
     * @param date
     * @param stationId
     * @param productId
     * @return
     * @throws SQLException
     */
    private double getTotalSales(Connection conn, String date, int stationId, int productId) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT SUM(GrossValue) FROM Sales WHERE MONTH(Month) = MONTH(?) AND YEAR(Month) = YEAR(?)");

        if (stationId > 0) sql.append(" AND OutletID = ?"); // if we choose a specific station
        if (productId > 0) sql.append(" AND ProductID = ?"); // if we choose a specific product

        try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            ps.setString(idx++, date);
            ps.setString(idx++, date);
            if (stationId > 0) ps.setInt(idx++, stationId);
            if (productId > 0) ps.setInt(idx++, productId);

            ResultSet rs = ps.executeQuery(); // execute query
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }


    //helper to calculate total quantity for specyfic month, station, and product ( or for all products / all stations )
    private double getTotalQuantity(Connection conn, String date, int stationId, int productId) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT SUM(QuantitySold) FROM Sales WHERE MONTH(Month) = MONTH(?) AND YEAR(Month) = YEAR(?)");

        if (stationId > 0) sql.append(" AND OutletID = ?");// if we choose specific station
        if (productId > 0) sql.append(" AND ProductID = ?");// if we choose specific product

        try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            ps.setString(idx++, date);
            ps.setString(idx++, date);
            if (stationId > 0) ps.setInt(idx++, stationId);
            if (productId > 0) ps.setInt(idx++, productId);

            ResultSet rs = ps.executeQuery(); // execute query
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }

    // Generates a PDF reward-products report for a given parameters - station (stationID),
    // for last 12 months. If stationID, is equal 0, our report is made for all stations
    // PDF include 3 charts :
    // 1) value of issued products
    // 2) year to year comparison of value
    // 3) quantity of issued products
    public void generateRewardProductsReport(String outputFilePath, int stationNumber) throws Exception {
        int [] reportType = new int [3];// to customize report title
        reportType [0] = 2;// 2 means it is reward-products report
        reportType [1] = stationNumber;

        Map<String, Double> rewardQuantity = new LinkedHashMap<>(); // to storage quantity / value from database
        Map<String, Double> rewardValue = new LinkedHashMap<>();
        Map<String, Double> rewardValueLastYear = new LinkedHashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            int minS = getMinStationId(conn); // validate input - stationID, and productID
            int maxS = getMaxStationId(conn);

            if( (stationNumber < minS || stationNumber > maxS) && stationNumber != 0){
                System.out.print("Wrong OutletID");
                return;
            }


            LocalDate now = LocalDate.now();
            YearMonth currentMonth = YearMonth.from(now).minusMonths(1); // ostatni pełny miesiąc

            for (int i = 11; i >= 0; i--) { // collecting sales, and quantity for each month
                YearMonth thisMonth = currentMonth.minusMonths(i);
                YearMonth lastYear = thisMonth.minusYears(1);

                String label = String.format("%02d / %d", thisMonth.getMonthValue(), thisMonth.getYear());
                String dateThis = thisMonth.toString() + "-01";
                String dateLast = lastYear.toString() + "-01";

                rewardQuantity.put(label, getTotalRewardQuantity(conn, dateThis, stationNumber));
                rewardValue.put(label, getTotalRewardValue(conn, dateThis, stationNumber));
                rewardValueLastYear.put(label, getTotalRewardValue(conn, dateLast, stationNumber));
            }
        }
        // create 3 charts for collected data
        JFreeChart chartQty = createStyledChart("Monthly Issued Rewards (Units)", "Month", "Units", rewardQuantity, new Color(23, 162, 184));
        JFreeChart chartVal = createStyledChart("Monthly Value of Issued Rewards (PLN)", "Month", "PLN", rewardValue, new Color(108, 117, 125));
        JFreeChart chartDiff = createStyledChart("YoY Reward Value Difference (PLN)", "Month", "PLN", computeDifference(rewardValue, rewardValueLastYear), new Color(255, 193, 7));
        // generate final report
        generatePDF(outputFilePath, reportType ,chartQty, chartVal, chartDiff);
    }


    private double getTotalRewardQuantity(Connection conn, String date, int stationId) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT SUM(QuantityIssued) FROM RewardIssuance WHERE MONTH(Month) = MONTH(?) AND YEAR(Month) = YEAR(?)");
        if (stationId > 0) {
            sql.append(" AND OutletID = ?");
        }

        try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            ps.setString(idx++, date);
            ps.setString(idx++, date);
            if (stationId > 0) ps.setInt(idx++, stationId);

            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }

    //helper to calculate total value of issued products for specyfic month and  station ( or for all stations )
    private double getTotalRewardValue(Connection conn, String date, int stationId) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT SUM(TotalValue) FROM RewardIssuance WHERE MONTH(Month) = MONTH(?) AND YEAR(Month) = YEAR(?)");
        if (stationId > 0) {// if we choose specific station
            sql.append(" AND OutletID = ?");
        }

        try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            ps.setString(idx++, date);
            ps.setString(idx++, date);
            if (stationId > 0) ps.setInt(idx++, stationId);

            ResultSet rs = ps.executeQuery(); // execute query
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }


    // helpers for all types of reports

    // helper to compute year to year value difference ( 2 chart in all types of reports )
    private Map<String, Double> computeDifference(Map<String, Double> current, Map<String, Double> previous) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (String key : current.keySet()) {
            double diff = current.getOrDefault(key, 0.0) - previous.getOrDefault(key, 0.0);
            result.put(key, diff);
        }
        return result;
    }

    // method to create chart
    private JFreeChart createStyledChart(String title, String xAxis, String yAxis, Map<String, Double> data, Color barColor) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : data.entrySet()) {// use data from Map data
            dataset.addValue(entry.getValue(), yAxis, entry.getKey());
        }
        // creating bar chart using dataset
        JFreeChart chart = ChartFactory.createBarChart(
                title, xAxis, yAxis, dataset,
                PlotOrientation.VERTICAL, false, true, false);
        //Customizing chart apperance
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setRangeGridlinePaint(Color.DARK_GRAY);


        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, barColor);
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setShadowVisible(false);
        chart.setTitle(new TextTitle(title, new java.awt.Font("SansSerif", java.awt.Font.BOLD, 16)));
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));

        chart.getCategoryPlot().getDomainAxis().setMaximumCategoryLabelLines(2);
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions( // rotate X - axis labels to avoid overlap
                org.jfree.chart.axis.CategoryLabelPositions.UP_45
        );

        return chart;
    }


    // Method to generate final PDF with 3 charts
    private void generatePDF(String filename, int [] reportType, JFreeChart... charts) throws Exception {
        // initialize PFD document a4 size with 50 margins

        // Define font for the title
        com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 16, com.itextpdf.text.Font.BOLD);
        Paragraph title;
        // generate appropriate report title based on report type values
        if(1 == reportType [0]){
            if( 0 == reportType [1]){
                if(0 == reportType [2]){
                    title = new Paragraph("Sales Report – All Outlets - All Products " , titleFont);
                }else{
                    title = new Paragraph("Sales Report – All Outlets - Product Number " + reportType[2] , titleFont);
                }
            }else{
                if(0 == reportType [2]){
                    title = new Paragraph("Sales Report – Outlet Number - " + reportType[1] + " - All Products " , titleFont);
                }else{
                    title = new Paragraph("Sales Report – Outlet Number - " + reportType[1] + " - Products Number " + reportType[2] , titleFont);
                }
            }
        }else{
            if(reportType[1] == 0){
                title = new Paragraph("Reward-Products Report – All Outlets" , titleFont);
            }else {
                title = new Paragraph("Reward-Products Report – Outlet Number " + reportType[1] , titleFont);
            }
        }
        String filname = title.toString();
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(filname + ".pdf"));
        document.open();
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        // Add current date of report generation
        com.itextpdf.text.Font dateFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10);
        Paragraph date = new Paragraph("Generated on: " + LocalDate.now(), dateFont);
        date.setSpacingAfter(10);
        document.add(date);

        document.add(Chunk.NEWLINE);

        // add charts to document
        for (JFreeChart chart : charts) {
            BufferedImage image = chart.createBufferedImage(500, 300);
            Image img = Image.getInstance(image, null);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
            document.add(Chunk.NEWLINE);
        }

        document.close();// finalize and close the document
    }

    // 4 heplers to validate input in generating report
    private int getMinStationId(Connection conn) throws SQLException {
        String sql = "SELECT MIN(OutletsID) FROM Outlets";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : -1;
        }
    }

    private int getMaxStationId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(OutletsID) FROM Outlets";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : -1;
        }
    }
    public int getMinProductId(Connection conn) throws SQLException {
        String sql = "SELECT MIN(ProductID) FROM Products";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : -1;
        }
    }

    public int getMaxProductId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(ProductID) FROM Products";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : -1;
        }
    }
}

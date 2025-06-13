package app.service.business_panel;


import com.itextpdf.text.Font;
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
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportGenerator {

    private static final String DB_URL = "jdbc:mysql://io-stacje.c544m8scom33.eu-north-1.rds.amazonaws.com:3306/io_baza";
    private static final String USER = "admin";
    private static final String PASS = "admin123#";

    public void generateSalesReport2024(String outputFilePath, int outletNumber) throws Exception {
        Map<String, Double> sales2024 = new LinkedHashMap<>();
        Map<String, Double> sales2023 = new LinkedHashMap<>();
        Map<String, Double> quantity2024 = new LinkedHashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            for (int month = 1; month <= 12; month++) {
                String label = String.format("%02d", month);
                String date2024 = String.format("2024-%02d-01", month);
                String date2023 = String.format("2023-%02d-01", month);
                if(outletNumber == 0) {//numer stacji == 0 oznacza że chcemy raport z sumy sprzedaży wszystkich stacji
                    sales2024.put(label, getTotalSales(conn, date2024));
                    sales2023.put(label, getTotalSales(conn, date2023));
                    quantity2024.put(label, getTotalQuantity(conn, date2024));
                }else {
                    sales2024.put(label, getTotalSalesForOutlet(conn, date2024, outletNumber));
                    sales2023.put(label, getTotalSalesForOutlet(conn, date2023, outletNumber));
                    quantity2024.put(label, getTotalQuantityForOutlet(conn, date2024, outletNumber));
                }
            }
        }

        JFreeChart chartSales = createStyledChart("Monthly Total Sales (PLN)", "Month", "PLN", sales2024, new Color(220, 53, 69));
        JFreeChart chartDiff = createStyledChart("Year-over-Year Sales Difference (PLN)", "Month", "PLN", computeDifference(sales2024, sales2023), new Color(255, 159, 64));
        JFreeChart chartQuantity = createStyledChart("Monthly Total Units Sold", "Month", "Units", quantity2024, new Color(40, 167, 69));

        generatePdf(outputFilePath, outletNumber, chartSales, chartDiff, chartQuantity);
    }

    private double getTotalSales(Connection conn, String date) throws SQLException {
        String sql = "SELECT SUM(GrossValue) FROM Sales" +
                " WHERE MONTH(Month) = MONTH(?)" +
                " AND YEAR(Month) = YEAR(?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, date);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }
    private double getTotalSalesForOutlet(Connection conn, String date, int outletId) throws SQLException {
        String sql = "SELECT SUM(GrossValue) FROM Sales " +
                "WHERE OutletID = ? " +
                "AND MONTH(Month) = MONTH(?) " +
                "AND YEAR(Month) = YEAR(?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, outletId);      //  outlet ID
            ps.setString(2, date);        // month
            ps.setString(3, date);        // year

            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }
    private double getTotalQuantity(Connection conn, String date) throws SQLException {
        String sql = "SELECT SUM(QuantitySold) FROM Sales WHERE MONTH(Month) = MONTH(?) AND YEAR(Month) = YEAR(?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, date);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }
    private double getTotalQuantityForOutlet(Connection conn, String date, int outletId) throws SQLException {
        String sql = "SELECT SUM(QuantitySold) FROM Sales " +
                "WHERE OutletID = ? " +
                "AND MONTH(Month) = MONTH(?) " +
                "AND YEAR(Month) = YEAR(?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, outletId);
            ps.setString(2, date);
            ps.setString(3, date);

            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }

    private Map<String, Double> computeDifference(Map<String, Double> current, Map<String, Double> previous) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (String key : current.keySet()) {
            double diff = current.getOrDefault(key, 0.0) - previous.getOrDefault(key, 0.0);
            result.put(key, diff);
        }
        return result;
    }

    private JFreeChart createStyledChart(String title, String xAxis, String yAxis, Map<String, Double> data, Color barColor) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), yAxis, entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                title, xAxis, yAxis, dataset,
                PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setRangeGridlinePaint(Color.DARK_GRAY);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, barColor);
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setShadowVisible(false);
        chart.setTitle(new TextTitle(title, new java.awt.Font("SansSerif", java.awt.Font.BOLD, 16)));
        //chart.getTitle().setFont(new Font("SansSerif", java.awt.Font.BOLD, 16));
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));

        return chart;
    }

    private void generatePdf(String filename,int outletNumber, JFreeChart... charts) throws Exception {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph title;
        if(outletNumber == 0){
            title = new Paragraph("Sales Report – All Outlets", titleFont);
        }else{
            title = new Paragraph("Sales Report – Outlet number " + outletNumber, titleFont);
        }

        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10);
        Paragraph date = new Paragraph("Generated on: " + LocalDate.now(), dateFont);
        date.setSpacingAfter(10);
        document.add(date);

        document.add(Chunk.NEWLINE);

        for (JFreeChart chart : charts) {
            BufferedImage image = chart.createBufferedImage(500, 300);
            Image img = Image.getInstance(image, null);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
            document.add(Chunk.NEWLINE);
        }

        document.close();
    }
}

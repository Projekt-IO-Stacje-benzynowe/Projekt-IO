package app.service.business_panel;

import java.time.LocalDate;

/**
 *  Service class for generating reports in the business panel.
 */
public class raportChoice {
    public static void generate(int typeOfReport, int stationNumber, int productID){
        ///////////////////////
        //chose typeOfReport
        //1 - general sales
        //2 - promotion products reports
        /////////////////////
        //chose subtypeOfReport
        //0 - report for all stations
        //other number - report for station number x
        //////////////////////////
        //product number - only to use in sales report (0 - all products)
        if( typeOfReport != 1 && typeOfReport != 2){
            System.out.println("This type of report doesn't exist");
            return;
        }
        LocalDate today = LocalDate.now();
        try {
            ReportGenerator service = new ReportGenerator();
            switch(typeOfReport){
                case 1:
                    service.generateSalesReport2024("Sales-Report" + today.getYear() + "_" + (today.getMonthValue() - 1) + ".pdf", stationNumber, productID);
                    break;
                case 2:
                    service.generateRewardProductsReport("Reward-Products-Report" + today.getYear() + "_" + (today.getMonthValue() - 1) + ".pdf", stationNumber);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

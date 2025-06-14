package app.model;

import java.util.TreeMap;

public class PanelList {
    private static TreeMap<String, String> panelMap = PanelList.initializeMap();

    private static TreeMap<String, String> initializeMap() {
        TreeMap<String, String> result = new TreeMap<>();
        result.put("sidebar", "/view/shared/sidebar.fxml");

        result.put("rewards", "/view/control_panel/rewards/rewardsPanel.fxml");
        result.put("login", "/view/login.fxml");

        result.put("branch", "/view/branch_panel/menu_branch_panel.fxml");
        result.put("confirm_delivery", "/view/branch_panel/confirm_delivery_panel.fxml");
        result.put("additional_delivery", "/view/branch_panel/additional_delivery_panel.fxml");
        result.put("promotions_table", "/view/branch_panel/discount_table_promotions_panel.fxml");
        result.put("delivery_panel", "/view/branch_panel/delivery_panel.fxml");
        result.put("report_product", "/view/branch_panel/report_panel.fxml");
        result.put("rewards_table", "/view/branch_panel/table_rewards_panel.fxml");

        result.put("logistics_main", "/view/control_panel/logistics_coordinator_section/logistics_main_panel.fxml");
        result.put("plan_delivery", "/view/control_panel/logistics_coordinator_section/plan_delivery_panel.fxml");
        result.put("choose_delivery", "/view/control_panel/logistics_coordinator_section/choose_delivery_panel.fxml");
        result.put("modify_delivery", "/view/control_panel/logistics_coordinator_section/modify_delivery_panel.fxml");
        result.put("view_requests", "/view/control_panel/logistics_coordinator_section/view_requests_panel.fxml");

        result.put("promotions_main", "/view/control_panel/promotions_coordinator_section/promotions_main_panel.fxml");
        result.put("create_promotion", "/view/control_panel/promotions_coordinator_section/create_promotion_panel.fxml");
        result.put("modify_promotion", "/view/control_panel/promotions_coordinator_section/modify_promotion_panel.fxml");

        result.put("Analysis", "/view/business_panel/buttonPage.fxml" );
        result.put("rewardsBusiness", "/view/business_panel/rewardsBusiness.fxml");
        result.put("ProductPage", "/view/business_panel/productsPanel.fxml");
        result.put("choose_product_business", "/view/business_panel/GeneralOrOutlet.fxml");
        result.put("allOutlets", "/view/business_panel/allOutlets.fxml");
        result.put("allOutletsRewards", "/view/business_panel/allOutletsRewards.fxml");
        return result;
    }

    public static String getFXMLFile(String key) {
        return panelMap.get(key);
    }
}

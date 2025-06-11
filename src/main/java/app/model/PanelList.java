package app.model;

import java.util.TreeMap;

public class PanelList {
    private static TreeMap<String, String> panelMap = PanelList.initializeMap();

    private static TreeMap<String, String> initializeMap() {
        TreeMap<String, String> result = new TreeMap<>();
        result.put("rewards", "/view/control_panel/rewards/Main.fxml");
        result.put("Main", "/view/control_panel/logistics/logistics_main_panel.fxml");
        result.put("login", "/view/login.fxml");
        result.put("branch", "/view/branch_panel/Main.fxml");
        result.put("confirm_delivery", "/view/branch_panel/confirm_delivery.fxml");
        result.put("additional_delivery", "/view/branch_panel/additional_delivery.fxml");
        result.put("table_promotions", "/view/branch_panel/discount_panel_table_promotions.fxml");
        result.put("delivery_menu", "/view/branch_panel/delivery_panel.fxml");
        result.put("report_product", "/view/branch_panel/reportPanel.fxml");
        result.put("rewards_table", "/view/branch_panel/tableRewards.fxml");
        result.put("promotions_table_scene", "/view/branch_panel/discount_panel_table_promotions.fxml");
        result.put("plan_delivery", "/view/control_panel/logistics/plan_delivery_panel.fxml");
        result.put("choose_delivery", "/view/control_panel/logistics/choose_delivery_panel.fxml");
        result.put("modify_delivery", "/view/control_panel/logistics/modify_delivery_panel.fxml");
        return result;
    }

    public static String getFXMLFile(String key) {
        return panelMap.get(key);
    }
}

package app.model;

import java.util.TreeMap;

public class PanelList {
    private static TreeMap<String, String> panelMap = PanelList.initializeMap();

    private static TreeMap<String, String> initializeMap() {
        TreeMap<String, String> result = new TreeMap<>();
        result.put("Main", "/view/control_panel/Main.fxml");
        result.put("login", "/view/login.fxml");
        result.put("branch", "/view/branch_panel/main_branch.fxml");
        result.put("confirm_delivery", "/view/branch_panel/confirm_delivery.fxml");
        result.put("additional_delivery", "/view/branch_panel/additional_delivery.fxml");
        result.put("table_promotions", "/view/branch_panel/discount_panel_table_promotions.fxml");
        result.put("delivery_menu", "/view/branch_panel/delivery_panel.fxml");
        result.put("reportProduct", "/view/branch_panel/reportPanel.fxml");
        result.put("rewards_table", "/view/branch_panel/tableRewards.fxml");
        result.put("promotions_table_scene", "/view/branch_panel/discount_panel_table_promotions.fxml");
        return result;
    }

    public static String getFXMLFile(String key) {
        return panelMap.get(key);
    }
}

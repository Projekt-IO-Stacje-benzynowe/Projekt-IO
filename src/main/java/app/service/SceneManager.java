package app.service;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.model.PanelList;

public class SceneManager {
    private static MainController mainController;
    private static Stage primaryStage;
    private static BorderPane mainContent; // np. root z main.fxml
    private static Map<String, Scene> scenes = new HashMap<>();

    public static void setMainController(MainController controller) {
        mainController = controller;
    }

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void setMainRoot(BorderPane root) {
        mainContent = root;
    }

    public static void setPanel(String panelName) {
        String panelFxml;
        switch(panelName) {
            case "branch": panelFxml = "/Shared/panels/BranchPanel.fxml"; break;
            case "business": panelFxml = "/Shared/panels/BusinessPanel.fxml"; break;
            case "logistyk": panelFxml = "/view/control_panel/logistics/logistics_main_panel.fxml"; break;
            default: throw new IllegalArgumentException("Unknown panel");
        }
        try { // Tutaj pobieramy nowy fxml, i ładujemy go, oraz przypisujemy tej scenie jej kontroler "główny" czyli kontroler naszej sceny bazowej
            FXMLLoader loader =  new FXMLLoader(SceneManager.class.getResource(panelFxml));
            Node panel = loader.load();
            Object controller = loader.getController();
            if (controller instanceof DynamicContentController) {
                ((DynamicContentController) controller).setMainController(mainController); // <--- tutaj przypisujemy kontrolerowi nowej sceny kontroler główny
            }
            mainContent.setCenter(panel); // <---- dajemy nowy panel do środka
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Parent setSubPanel(MainController mainController, String name) {
        try {
            String fxmlFile = PanelList.getFXMLFile(name);

            FXMLLoader loader =  new FXMLLoader(SceneManager.class.getResource(fxmlFile));
            Parent content = loader.load();

            // PRZEKAZUJEMY REFERENCJĘ DO MAINCONTROLLER
            Object controller = loader.getController();
            if (controller instanceof DynamicContentController) {
                ((DynamicContentController) controller).setMainController(mainController);
            }
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    


    public static void addScene(String name){
        if (scenes.containsKey(name)) {
            return;
        }
        
        String fxmlFile = PanelList.getFXMLFile(name);
        System.out.println(name);
        System.out.println(fxmlFile);

        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
            Scene scene = new Scene(root);
            scenes.put(name, scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null && primaryStage != null) {
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Scena " + name + " nie istnieje!");
        }
    }

    public static boolean isScene(String name){
        return scenes.containsKey(name);
    }

    public static void clearScene(String name){
        scenes.remove(name);
    }

    public static void clear(){
        scenes.clear();
    }
}

package app.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import app.model.PanelList;

public class SceneManager {
    private static Stage primaryStage;
    private static Map<String, Scene> scenes = new HashMap<>();

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    // Powinno być zrefaktoryzowane, żeby używało PanelList i tylko argumentu "name"
    public static void addScene(String name){
        if(scenes.containsKey(name)){return;}
        
        String fxmlFile = PanelList.getFXMLFile(name);

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

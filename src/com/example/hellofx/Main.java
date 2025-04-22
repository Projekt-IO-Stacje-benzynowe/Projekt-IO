package com.example.hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Szymon Dymał");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 300, Color.RED);

        Image ikona = new Image("icon.png");
        stage.getIcons().add(ikona);
        stage.setScene(scene);
        stage.setTitle("Szymon Dymał");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
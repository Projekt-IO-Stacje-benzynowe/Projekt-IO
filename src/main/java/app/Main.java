package app;


import java.util.logging.*;
import java.io.IOException;
import java.util.Locale;

import javafx.application.Application;
import javafx.stage.Stage;

import app.service.SceneManager;

/**
 *  Main class for the application, responsible for starting the JavaFX application,
 *  configuring logging, and setting the default locale.
 */
public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage){
        // Setting the default locale to English to comply with the requirement
        // that the application should be in English
        // but also the weeek starts on Monday
        Locale.setDefault(Locale.UK);
        SceneManager.setStage(primaryStage);
        SceneManager.addScene("login");
        SceneManager.showScene("login");


    }
    public static void main(String[] args) {
        // Configure the logger to write to a file and console 
        configureLogger();
        
        logger.info("The application is starting");
        
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            logger.severe("Critical error: " + e.getMessage());
            e.printStackTrace();
        }
        
        logger.info("The application has finished working");
    }
    
    private static void configureLogger() {
        try {
            // Ustawienie poziomu logowania
            Logger rootLogger = Logger.getLogger("");
            rootLogger.setLevel(Level.ALL);
            
            // Usuń istniejące handlery (domyślna konsola)
            for (Handler handler : rootLogger.getHandlers()) {
                rootLogger.removeHandler(handler);
            }
            
            // Handler do pliku
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            rootLogger.addHandler(fileHandler);
            
            // Opcjonalnie: handler do konsoli
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            rootLogger.addHandler(consoleHandler);
            
        } catch (IOException e) {
            System.err.println("Login configuration failed: " + e.getMessage());
        }
    }
} 

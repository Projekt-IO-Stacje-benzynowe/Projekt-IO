package app.controllers.shared;

/**
 *  Interface for controllers that manage dynamic content in the application.
 *  This interface allows controllers to set a reference to the main controller,
 *  enabling them to interact with the main application window and switch between different views.
 *  It is used to ensure that dynamic content controllers can communicate with the main controller
 *  and perform actions such as navigating to different sections of the application.
 *  It is purposefully empty and serves as a marker interface.
 */
public interface DynamicContentController {
    void setMainController(MainController mainController);
}


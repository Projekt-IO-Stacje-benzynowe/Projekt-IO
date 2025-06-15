package app.controllers.shared;

import app.service.SceneManager;
import app.service.Session;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *  Controller for the sidebar in the application, handling navigation and user interactions.
 */
public class SidebarController implements DynamicContentController {
    private MainController mainController;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private VBox Box1;
    @FXML private Button homeButton,  logoutButton;
    @FXML private Label homeLabel,  logoutLabel;
    @FXML private HBox homeHBox,  logoutHBox;

    @FXML
    public void initialize() {
        System.out.println("SidebarController loaded! homeButton: " + homeButton);
        homeLabel.setVisible(false);
        logoutLabel.setVisible(false);
        setupButton(homeButton, homeLabel, homeHBox, this::handleHomeClick);
        setupButton(logoutButton, logoutLabel, logoutHBox, this::handleLogoutClick);
        ImageView homeIcon = (ImageView) homeButton.getGraphic();
        ImageView logIcon = (ImageView) logoutButton.getGraphic();
        homeIcon.fitHeightProperty().bind(Box1.widthProperty().multiply(0.325));
        logIcon.fitHeightProperty().bind(Box1.widthProperty().multiply(0.25));
        Box1.setAlignment(Pos.CENTER);
    }

    /**
     * Sets up the button with hover effects and action handling.
     * @param button
     * @param label
     * @param container
     * @param action
     */
    private void setupButton(Button button, Label label, HBox container, Runnable action) {
        if (button != null && label != null && container != null) {
            // The icon will be moved to the left when hovered
            button.setOnMouseEntered(_ -> {
                animateMove(button, -10);
                showLabel(label);
            });

            // The icon will return to its original position when the mouse exits
            button.setOnMouseExited(_ -> {
                animateMove(button, 0);
                hideLabel(label);
            });

            // The button will execute the action when clicked
            button.setOnAction(_ -> action.run());
        }
    }

    /**
     * Animates the button movement to the specified offset.
     * @param button
     * @param offsetX
     */
    private void animateMove(Button button, double offsetX) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(200), button);
        tt.setToX(offsetX);
        tt.play();
    }

    /**
     * Shows the label with a fade-in effect.
     * @param label
     */
    private void showLabel(Label label) {
        label.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(200), label);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    /**
     * Hides the label with a fade-out effect.
     * @param label
     */
    private void hideLabel(Label label) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), label);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(_ -> label.setVisible(false));
        ft.play();
    }

    /**
     * Handles the home button click event.
     * It navigates to the user's panel.
     */
    private void handleHomeClick() {
        if (mainController != null) {
            mainController.showDynamicContent(Session.getUser().getPanel());
        }
    }

    /**
     * Handles the logout button click event.
     * It ends the session, clears the scene history, and navigates to the login scene.
     */
    private void handleLogoutClick() {
        Session.endSession(); // kończymy sesję
        SceneManager.clear(); // usuwamy z historii hashmapy stare sceny
        SceneManager.addScene("login");
        Stage stage = (Stage) homeButton.getScene().getWindow(); // każdy przycisk ma dostęp do swojej sceny więc pobieramy ją w celu zamknięcia
        stage.close(); // zamykamy
        SceneManager.showScene("login"); //pokazujemy panel logowania
    }
}

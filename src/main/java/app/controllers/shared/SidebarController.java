package app.controllers.shared;


import app.model.PanelList;
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

public class SidebarController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML private VBox Box1;

    @FXML private Button homeButton, settingsButton, logoutButton;
    @FXML private Label homeLabel, settingsLabel, logoutLabel;
    @FXML private HBox homeHBox, settingsHBox, logoutHBox;

    @FXML
    public void initialize() {
        System.out.println("SidebarController loaded! homeButton: " + homeButton);
        homeLabel.setVisible(false);
        settingsLabel.setVisible(false);
        logoutLabel.setVisible(false);
        setupButton(homeButton, homeLabel, homeHBox, this::handleHomeClick);
        setupButton(settingsButton, settingsLabel, settingsHBox, this::handleSettingsClick);
        setupButton(logoutButton, logoutLabel, logoutHBox, this::handleLogoutClick);
        ImageView homeIcon = (ImageView) homeButton.getGraphic();
        ImageView settingsIcon = (ImageView) settingsButton.getGraphic();
        ImageView logIcon = (ImageView) logoutButton.getGraphic();
        homeIcon.fitHeightProperty().bind(Box1.widthProperty().multiply(0.325));
        settingsIcon.fitHeightProperty().bind(Box1.widthProperty().multiply(0.25));
        logIcon.fitHeightProperty().bind(Box1.widthProperty().multiply(0.25));
        Box1.setAlignment(Pos.CENTER);
    }

    private void setupButton(Button button, Label label, HBox container, Runnable action) {
        if (button != null && label != null && container != null) {
            button.setOnMouseEntered(_ -> {
                animateMove(button, -10); // ikonka w lewo
                showLabel(label);
            });

            button.setOnMouseExited(_ -> {
                animateMove(button, 0); // powrót
                hideLabel(label);
            });

            button.setOnAction(_ -> action.run());
        }
    }

    private void animateMove(Button button, double offsetX) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(200), button);
        tt.setToX(offsetX);
        tt.play();
    }

    private void showLabel(Label label) {
        label.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(200), label);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void hideLabel(Label label) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), label);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(_ -> label.setVisible(false));
        ft.play();
    }

    private void handleHomeClick() {
        if (mainController != null) {
            mainController.showDynamicContent(Session.user.getPanel());
        }
    }

    private void handleSettingsClick() {
        if (mainController != null) {
           // mainController.changeContent("/fxmls/settings.fxml");
        }
    }

    private void handleLogoutClick() {
        Session.endSession(); // kończymy sesję
        SceneManager.clear(); // usuwamy z historii hashmapy stare sceny
        SceneManager.addScene("login");
        Stage stage = (Stage) homeButton.getScene().getWindow(); // każdy przycisk ma dostęp do swojej sceny więc pobieramy ją w celu zamknięcia
        stage.close(); // zamykamy
        SceneManager.showScene("login"); //pokazujemy panel logowania
    }
}

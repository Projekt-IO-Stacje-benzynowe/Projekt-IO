package app.controllers.control_panel;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SidebarController {
    private ChangeListener<Number> widthListener;
    private MainController mainController;

    @FXML private VBox Box1;

    @FXML private Button homeButton, settingsButton, logoutButton;
    @FXML private Label homeLabel, settingsLabel, logoutLabel;
    @FXML private HBox homeHBox, settingsHBox, logoutHBox;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        homeLabel.setVisible(false);
        settingsLabel.setVisible(false);
        logoutLabel.setVisible(false);

        setupButton(homeButton, homeLabel, homeHBox, this::handleHomeClick);
        setupButton(settingsButton, settingsLabel, settingsHBox, this::handleSettingsClick);
        setupButton(logoutButton, logoutLabel, logoutHBox, this::handleLogoutClick);

        Box1.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            double iconSize = width * 0.25;
            resizeIcon(homeButton, iconSize * 1.3);
            resizeIcon(settingsButton, iconSize);
            resizeIcon(logoutButton, iconSize);
        });

        Box1.sceneProperty().addListener((obs,oldScene, newScene)->{
            if(newScene != null){
                newScene.windowProperty().addListener((obs2, oldWin, newWin)->{
                    if(newWin != null){
                        newWin.widthProperty().addListener((o, oldW, newW)->{
                        });
                    }
                });
            }
        });
        Box1.setAlignment(Pos.CENTER);
        forceResize();
    }

    private void forceResize(){
        double width = Box1.getWidth();
        double iconSize = width * 0.25;
        resizeIcon(homeButton, iconSize*1.3);
        resizeIcon(settingsButton, iconSize);
        resizeIcon(logoutButton, iconSize);
    }
    private void resizeIcon(Button button, double size) {
        if (button.getGraphic() instanceof ImageView iv) {
            iv.setFitWidth(size);
            iv.setFitHeight(size);
        }
    }

    private void setupButton(Button button, Label label, HBox container, Runnable action) {
        if (button != null && label != null && container != null) {
            button.setOnMouseEntered(e -> {
                animateMove(button, -10); // ikonka w lewo
                showLabel(label);
            });

            button.setOnMouseExited(e -> {
                animateMove(button, 0); // powrót
                hideLabel(label);
            });

            button.setOnAction(e -> action.run());
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
        ft.setOnFinished(e -> label.setVisible(false));
        ft.play();
    }

    private void handleHomeClick() {
        if (mainController != null) {
            mainController.changeContent("/fxmls/home.fxml");
        }
    }

    private void handleSettingsClick() {
        if (mainController != null) {
            mainController.changeContent("/fxmls/settings.fxml");
        }
    }

    private void handleLogoutClick() {
        System.out.println("Logout clicked");
    }
}

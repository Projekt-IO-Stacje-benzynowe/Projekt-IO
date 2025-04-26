

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class Screen2Controller {

    @FXML
    private void goToScreen1(ActionEvent event) {
        SceneManager.showScene("screen1");
    }
}
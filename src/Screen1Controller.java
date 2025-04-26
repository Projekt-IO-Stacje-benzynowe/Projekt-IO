
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class Screen1Controller {

    @FXML
    private void goToScreen2(ActionEvent event) {
        SceneManager.showScene("screen2");
    }
}

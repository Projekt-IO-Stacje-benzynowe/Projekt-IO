package app.controllers.control_panel.logistics_coordinator_section;

import app.model.PromotionsModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.MainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainController {
    public BorderPane background;
    public VBox sidebar;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    public HBox topBar;
    @FXML
    public BorderPane mainContent;
    private TableView<PromotionsModel> promotionsTableView;
    @FXML
    private StackPane tablePane;
    @FXML
    private VBox sidebarContainer;
    @FXML
    private Text testText;
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/control_panel/sidebar.fxml"));
            sidebarContainer = loader.load(); // Ten VBox jest root z sidebar.fxml

            SidebarController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setMainController(this);
            }
            sidebarContainer.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebarContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            // Dodaj do mainContainer na indeksie 1 (pod top, nad contentArea)
            mainContainer.getChildren().add(sidebarContainer);
            topBar.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.08)); // lub dowolna proporcja
            background.prefWidthProperty().bind(mainContainer.widthProperty());
            background.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebar.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            mainContent.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.7));

            promotionsTableView = MainService.getAllPromotionsTable();
            promotionsTableView.setOnMouseClicked(e -> clickTable(e));
            boolean check = tablePane.getChildren().add(promotionsTableView);
            System.out.println(check);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickTable(MouseEvent event) {
        String text = promotionsTableView.getSelectionModel().getSelectedItem().getName();
        testText.setText(text);
    }

    public void logOut(ActionEvent event) {
        Session.EndSession();
        SceneManager.clear();
        SceneManager.addScene("login");
        SceneManager.showScene("login");
    }

    public void goToViewRequests(ActionEvent event) {
        testText.setText("view requests?");
    }

    public void goToPlanNewDelivery(ActionEvent event) {
        testText.setText("view requests?");
    }

    public void goToModifyDelivery(ActionEvent event) {
        testText.setText("view requests?");
    }
}

package app.controllers.control_panel.logistics_coordinator_section;

import app.model.PromotionsModel;
import app.service.control_panel.logistics_coordinator_section.MainService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainController {
    public BorderPane background;
    public VBox sidebar;
    public StackPane imagePane;
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
    private ImageView szczyp;
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
            imagePane.prefWidthProperty().bind(mainContent.widthProperty());
            imagePane.prefHeightProperty().bind(mainContent.heightProperty());
            topBar.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.08)); // lub dowolna proporcja
            background.prefWidthProperty().bind(mainContainer.widthProperty());
            background.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebar.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            mainContent.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.7));
            szczyp.fitWidthProperty().bind(imagePane.widthProperty().multiply(0.95));
            szczyp.fitHeightProperty().bind(imagePane.heightProperty().multiply(0.95));

            promotionsTableView = MainService.getPromotionsTable("Stacja Centralna");
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
}

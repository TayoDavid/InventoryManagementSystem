package IMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {
    @FXML
    private Button submit;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void mainController(ActionEvent event) throws Exception {
        if(!"".equals(username.getText()) && !"".equals(password.getText())) {
            Stage newStage = new Stage();
            Parent root = (Parent)FXMLLoader.load(getClass().getResource("/IMS/AddNewUser.fxml"));
            newStage.setTitle("Manage Store");
            newStage.setScene(new Scene(root));
            newStage.show();
            newStage.setResizable(false);
            Stage loginPage = (Stage)submit.getScene().getWindow();
            loginPage.close();
        }

    }
}

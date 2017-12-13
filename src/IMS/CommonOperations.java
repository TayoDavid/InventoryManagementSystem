package IMS;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CommonOperations {

    public void dashboardHandler(ActionEvent e){

        Stage newStage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/IMS/PointOfSales.fxml"));
            newStage.setTitle("Manage Store");
            newStage.setScene(new Scene(root));
            newStage.show();
            newStage.setResizable(false);

        } catch (IOException ex) {
            Logger.getLogger(CommonOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

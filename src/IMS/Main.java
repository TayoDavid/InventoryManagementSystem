package IMS;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {       

    public void start(Stage primaryStage) {
        try {
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("Login.fxml"));
            primaryStage.setTitle("Login Page");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (IOException var3) {
            System.out.println(var3.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

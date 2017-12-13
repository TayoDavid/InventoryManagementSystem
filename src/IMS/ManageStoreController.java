package IMS;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageStoreController implements Initializable{

    // Elements from the TableView.
    @FXML
    private TableView<MartUser> userTable;
    @FXML
    private TableColumn<MartUser, Integer> user_id;
    @FXML
    private TableColumn<MartUser, String> first_name;
    @FXML
    private TableColumn<MartUser, String> last_name;
    @FXML
    private TableColumn<MartUser, String> state;
    @FXML
    private TableColumn<MartUser, String> phone_number;
    @FXML
    private TableColumn<MartUser, String> email;
    @FXML
    private Button grant;

    private ObservableList<MartUser> data;

    private DBConnect dbconnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbconnection = new DBConnect();
        user_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("fname"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("lname"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        try {
            Connection connect = dbconnection.Connect();
            data = FXCollections.observableArrayList();

            String query = "SELECT id, first_name, last_name, state, phone_number, email FROM mart_user";

            // Execute query and store result in a ResultSet
            ResultSet result = connect.createStatement().executeQuery(query);

            while (result.next()) {
                data.add( new MartUser( result.getInt(1), result.getNString("first_name"), result.getNString("last_name"), result.getString(4), result.getNString("phone_number"), result.getString(6) ));
            }

        } catch (SQLException e) {
            System.err.println(e.getCause());
        }

        user_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("fname"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("lname"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        userTable.setItems(data);
    }
}


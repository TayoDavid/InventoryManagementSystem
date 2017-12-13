package IMS;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddNewUserController implements Initializable {
    private DBConnect dbConnection;
    Connection connect = null;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField dob;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private TextArea address;
    @FXML
    private TextField state;
    @FXML
    private TextField country;
    @FXML
    private TextField phone_number;
    @FXML
    private Button btnAddUser;
    @FXML
    private Button btnDashboard;
    @FXML
    private ComboBox<String> stores;
    @FXML
    private ComboBox<String> sales;
    @FXML
    private ComboBox<String> purchases;
    @FXML
    private ComboBox<String> product;
    @FXML
    private ComboBox<String> warehouse;
    @FXML
    private ComboBox<String> report;
    @FXML
    private Label fetchUserName;
    @FXML
    private ImageView userImage;
    @FXML
    private TextField userId;
    @FXML
    private TextField proFirstName;
    @FXML
    private TextField proLastName;
    @FXML
    private TextField proUsername;
    @FXML
    private TextField proEmail;
    @FXML
    private TextField proStatus;
    @FXML
    private TextField userType;

    /**
     * Initial the Comboboxes
     */
    ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> store = FXCollections.observableArrayList("Stores", "Add New Stores");
    ObservableList<String> sale = FXCollections.observableArrayList("Sales", "Add New Sales");
    ObservableList<String> purchase = FXCollections.observableArrayList("Purchases", "Add Purchase");
    ObservableList<String> products = FXCollections.observableArrayList("Product");
    ObservableList<String> warehouses = FXCollections.observableArrayList("Warehouse", "Add New Warehouse");
    ObservableList<String> reports = FXCollections.observableArrayList("Report");

    @FXML
    private TextField username;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;

    public void initialize(URL url, ResourceBundle rb) {
        this.dbConnection = new DBConnect();
        Statement prefetchedstatement = null;
        @SuppressWarnings("unused")
		Statement noOfRowsStatement = null;
        this.gender.setItems(this.genderList);
        this.stores.setItems(this.store);
        this.sales.setItems(this.sale);
        this.purchases.setItems(this.purchase);
        this.product.setItems(this.products);
        this.warehouse.setItems(this.warehouses);
        this.report.setItems(this.reports);

        try {
            this.connect = this.dbConnection.Connect();
            prefetchedstatement = this.connect.createStatement();
            ResultSet result = prefetchedstatement.executeQuery("SELECT mart_user_id, first_name, last_name, username, email FROM mart_user WHERE mart_user_id = 4");

            while(result.next()) {
                int mart_user_id = result.getInt(1);
                String fName = result.getString(2);
                String lName = result.getString(3);
                String uName = result.getString(4);
                String emailAddr = result.getString(5);

                this.fetchUserName.setText(uName);
                this.userId.setText("" + mart_user_id);
                this.proFirstName.setText(fName);
                this.proLastName.setText(lName);
                this.proEmail.setText(emailAddr);
                this.proUsername.setText(fName);
            }
        } catch (SQLException var11) {
            System.out.println("Couldn't fetch data from database.");
            System.out.println(var11.getCause());
        }

    }

    @FXML
    public void addNewUser(ActionEvent event) throws SQLException {
        String fname = this.firstName.getText();
        String lname = this.lastName.getText();
        String userName = this.username.getText();
        String userGender = (String)this.gender.getValue();
        String userEmail = this.email.getText();
        String dateOfBirth = this.dob.getText();
        String addr = this.address.getText();
        String stateOfOrigin = this.state.getText();
        String nationality = this.country.getText();
        String phoneNumber = this.phone_number.getText();
        String passkey = this.password1.getText();
        String confirmPasskey = this.password2.getText();
        if(passkey.equals(confirmPasskey)) {
            try {
                this.connect = this.dbConnection.Connect();
                Statement statement = this.connect.createStatement();
                statement.executeUpdate("INSERT INTO mart_user  VALUES (NULL, '" + fname + "', '" + lname + "', '" + userName + "', '" + passkey + "', '" + userGender + "', '" + dateOfBirth + "', '" + addr + "', '" + stateOfOrigin + "', '" + nationality + "', '" + phoneNumber + "', '" + userEmail + "')");
            } catch (SQLException e) {
                System.out.println("Error inserting into the database");
            } finally {
                this.connect.close();
            }
        } else {
            System.out.println("Passwords do not match.");
        }

    }

    @FXML
    public void dashboardBtnClick(ActionEvent event) {
        CommonOperations perform = new CommonOperations();
        perform.dashboardHandler(event);
        Stage loginPage = (Stage)this.btnDashboard.getScene().getWindow();
        loginPage.hide();
    }
}

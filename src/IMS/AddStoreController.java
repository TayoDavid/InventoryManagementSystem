package IMS;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddStoreController implements Initializable {
    DBConnect dbConnect = new DBConnect();
    Connection connection = null;
    Statement statement = null;
    Statement AddStoreStatement = null;
    ResultSet result;

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
    private TextField storeName;
    @FXML
    private TextField storeUniqueId;
    @FXML
    private TextField bizType;
    @FXML
    private TextArea storeAddr;
    @FXML
    private TextField storeCity;
    @FXML
    private TextField storeState;
    @FXML
    private TextField country;
    @FXML
    private TextField storeLine;
    @FXML
    private TextField email;
    @FXML
    private Button btnAddStore;
    @FXML
    private Label fetchUserName;
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

    public void initialize(URL url, ResourceBundle rb) {
        this.connection = this.dbConnect.Connect();
        ObservableList<String> store = FXCollections.observableArrayList(new String[]{"Stores", "Add New Stores"});
        ObservableList<String> sale = FXCollections.observableArrayList(new String[]{"Sales", "Add New Sales"});
        ObservableList<String> purchase = FXCollections.observableArrayList(new String[]{"Purchases", "Add Purchase"});
        ObservableList<String> products = FXCollections.observableArrayList(new String[]{"Product"});
        ObservableList<String> warehouses = FXCollections.observableArrayList(new String[]{"Warehouse", "Add New Warehouse"});
        ObservableList<String> reports = FXCollections.observableArrayList(new String[]{"Report"});
        this.stores.setItems(store);
        this.sales.setItems(sale);
        this.purchases.setItems(purchase);
        this.product.setItems(products);
        this.warehouse.setItems(warehouses);
        this.report.setItems(reports);

        try {
            Statement prefetchedstatement = this.connection.createStatement();
            this.result = prefetchedstatement.executeQuery("SELECT mart_user_id, first_name, last_name, username, email FROM mart_user WHERE mart_user_id = 1");

            while(this.result.next()) {
                int mart_user_id = this.result.getInt(1);
                String fName = this.result.getString(2);
                String lName = this.result.getString(3);
                String uName = this.result.getString(4);
                String emailAddr = this.result.getString(5);
                this.fetchUserName.setText(uName);
                this.userId.setText("" + mart_user_id);
                this.proFirstName.setText(fName);
                this.proLastName.setText(lName);
                this.proEmail.setText(emailAddr);
                this.proUsername.setText(fName);
            }

            this.connection.close();
            this.result.close();
        } catch (SQLException var15) {
            System.out.println("Error inserting into the database");
            System.out.println(var15.getCause());
        }

    }

    @FXML
    private void dashboardBtnClick(ActionEvent event) {
        CommonOperations perform = new CommonOperations();
        perform.dashboardHandler(event);
        Stage loginPage = (Stage)this.btnDashboard.getScene().getWindow();
        loginPage.hide();
    }

    @FXML
    private void addNewStore() {
        String stName = this.storeName.getText();
        String uniqueId = this.storeUniqueId.getText();
        String businessType = this.bizType.getText();
        String address = this.storeAddr.getText();
        String city = this.storeCity.getText();
        String state = this.storeState.getText();
        String nationality = this.country.getText();
        String phone = this.storeLine.getText();
        String mail = this.email.getText();

        try {
            this.connection = this.dbConnect.Connect();
            this.AddStoreStatement = this.connection.createStatement();
            this.AddStoreStatement.executeUpdate("INSERT INTO stores (store_name, store_unique_id, business_type, email, address, city, state_or_province, country, phone) VALUES('" + stName + "', '" + uniqueId + "', '" + businessType + "', '" + mail + "', '" + address + "', '" + city + "', '" + state + "', '" + nationality + "', '" + phone + "')");
        } catch (SQLException var11) {
            System.out.println("SQL error. " + var11.getMessage());
        }

    }
}

package IMS;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ManagePurchaseController implements Initializable {
    
	DBConnect dbConnect = new DBConnect();
    Connection connection = null;
    Statement statement = null;
    ResultSet result;
    
    @FXML
    private Button btnDashboard;
    @FXML
    private ComboBox<String> cmbStores;
    @FXML
    private ComboBox<String> cmbSales;
    @FXML
    private ComboBox<String> cmbPurchases;
    @FXML
    private ComboBox<String> cmbProducts;
    @FXML
    private ComboBox<String> cmbWarehouses;
    @FXML
    private ComboBox<String> cmbReport;
    @FXML
    private Label product_alert;
    @FXML
    private Label fetchUserName;
    @FXML
    private TextField added_date;
    @FXML
    private TextField supplier_inv_number;
    @FXML
    private TextArea memo;
    @FXML
    private ComboBox<String> category;
    @FXML
    private ComboBox<String> vendors;
    @FXML
    private Button btnAddNewVendor;
    @FXML
    private ComboBox<String> item_id_and_name;
    @FXML
    private TextField quantity;
    @FXML
    private TextField cost_per_unit;
    @FXML
    private ComboBox<String> warehouses;
    @FXML
    private Button btnAddProduct;
    @FXML
    private TableView<?> productsTable;
    @FXML
    private TableColumn<?, ?> tableProdId;
    @FXML
    private TableColumn<?, ?> tblProdName;
    @FXML
    private TableColumn<?, ?> tableItemQty;
    @FXML
    private TableColumn<?, ?> tableItemCost;
    @FXML
    private TableColumn<?, ?> tableWarehouse;
    @FXML
    private TableColumn<?, ?> tableTotal;
    @FXML
    private Label grand_total;
    @FXML
    private ComboBox<?> payment_mode;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnPrint;
    @FXML
    private TextField payable;
    @FXML
    private TextField paid;
    @FXML
    private TextField balance;

    public void initialize(URL url, ResourceBundle rb) {
        connection = dbConnect.Connect();
        ObservableList<String> store = FXCollections.observableArrayList(new String[]{"Stores", "Add New Stores"});
        ObservableList<String> sale = FXCollections.observableArrayList(new String[]{"Sales", "Add New Sales"});
        ObservableList<String> purchase = FXCollections.observableArrayList(new String[]{"Purchases", "Add Purchase"});
        ObservableList<String> products = FXCollections.observableArrayList(new String[]{"Product"});
        ObservableList<String> warehouse = FXCollections.observableArrayList(new String[]{"Warehouse", "Add New Warehouse"});
        ObservableList<String> reports = FXCollections.observableArrayList(new String[]{"Report"});
        this.cmbStores.setItems(store);
        this.cmbSales.setItems(sale);
        this.cmbPurchases.setItems(purchase);
        this.cmbProducts.setItems(products);
        this.cmbWarehouses.setItems(warehouse);
        this.cmbReport.setItems(reports);
        LocalDate date = LocalDate.now();
        this.added_date.setText("" + date);

        try {
            ArrayList<String> categories = new ArrayList<>();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT name FROM category");

            while(result.next()) {
                categories.add(result.getString(1));
            }

            ObservableList<String> cat = FXCollections.observableArrayList(categories);
            category.setItems(cat);
        } catch (SQLException e) {
            System.out.println("Error fetching list of categories from the database.");
        }

    }

    public void addProduct() {
    }

    public void fetchVendors() {
    }
}

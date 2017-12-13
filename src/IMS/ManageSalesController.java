package IMS;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageSalesController implements Initializable {
    
	DBConnect dbConnect = new DBConnect();
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStmt;
    ResultSet result;
    String getProdName;
    double amt_payable;
    int count = 0;

    @FXML
    private Label storeName;
    @FXML
    private Button btnAddNewVendor;
    @FXML
    private Label added_date;
    @FXML
    private Button btnAddProduct;
    @FXML
    private TextField quantity;
    @FXML
    private Label cost;
    @FXML
    private ComboBox<String> warehouses;
    @FXML
    private TextField payable;
    @FXML
    private TextField paid;
    @FXML
    private Label balance;
    @FXML
    private ComboBox<String> category;
    @FXML
    private ComboBox<String> fetchProd;
    @FXML
    private Label totalAmount;
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

    // Table items
    private TableView<ManageSalesProductTable> addedProdTable;
    @FXML
    private TableColumn<ManageSalesProductTable, Integer> tblSerial_number;
    @FXML
    private TableColumn<ManageSalesProductTable, String> tblProductName;
    @FXML
    private TableColumn<ManageSalesProductTable, String> tblQuantity;
    @FXML
    private TableColumn<ManageSalesProductTable, String> tblPrice;
    @FXML
    private TableColumn<ManageSalesProductTable, String> tblTax;
    @FXML
    private TableColumn<ManageSalesProductTable, String> tblTotal;

    @SuppressWarnings("unused")
	private ObservableList<ManageSalesProductTable> tableData;

    @FXML
    private ComboBox<String> payment_mode;
    @FXML
    private Button commitTransaction;
    @FXML
    private Button printer;

    ObservableList<String> store = FXCollections.observableArrayList(new String[]{"Stores", "Add New Stores"});
    ObservableList<String> sale = FXCollections.observableArrayList(new String[]{"Sales", "Add New Sales"});
    ObservableList<String> purchase = FXCollections.observableArrayList(new String[]{"Purchases", "Add Purchase"});
    ObservableList<String> products = FXCollections.observableArrayList(new String[]{"Product"});
    ObservableList<String> warehouse = FXCollections.observableArrayList(new String[]{"Warehouse", "Add New Warehouse"});
    ObservableList<String> reports = FXCollections.observableArrayList(new String[]{"Report"});

    public void initialize(URL url, ResourceBundle rb) {
        connection = dbConnect.Connect();
        cmbStores.setItems(store);
        cmbSales.setItems(sale);
        cmbPurchases.setItems(purchase);
        cmbProducts.setItems(products);
        cmbWarehouses.setItems(warehouse);
        cmbReport.setItems(reports);
        LocalDate date = LocalDate.now();
        added_date.setText("" + date);

        ArrayList<String> prod_name;
        try {
            prod_name = new ArrayList<>();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT name FROM category");

            while(result.next()) {
                prod_name.add(result.getString(1));
            }

            ObservableList<String> prodCats = FXCollections.observableArrayList(prod_name);
            category.setItems(prodCats);
        } catch (SQLException e) {
            System.out.println("Error fetching list of categories from the database.");
        }

        try {
            prod_name = new ArrayList<>();
            statement = connection.createStatement();
            ResultSet prodResult = statement.executeQuery("SELECT product_name FROM stock");

            while(prodResult.next()) {
                prod_name.add(prodResult.getString(1));
            }

            ObservableList<String> nameOfProds = FXCollections.observableArrayList(prod_name);
            fetchProd.setItems(nameOfProds);
        } catch (SQLException e) {
            System.out.println("Error fetching name of product from the database.");
        }

//        tblSerial_number.setCellValueFactory(new PropertyValueFactory<>("ser_number"));
//        tblProductName.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
//        tblQuantity.setCellValueFactory(new PropertyValueFactory<>("qty_of_prod"));
//        tblPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
//        tblTax.setCellValueFactory(new PropertyValueFactory<>("tax_amount"));
//        tblTotal.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
    }

    public void getPrice(ActionEvent event) {
        try {
            Statement priceRetrieverStatement = connection.createStatement();
            ResultSet costResult = priceRetrieverStatement.executeQuery("SELECT sales_price FROM stock WHERE product_name = '" + (String)fetchProd.getValue() + "'");

            while(costResult.next()) {
                cost.setText(costResult.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving the price of the product.");
            System.out.println(e.getCause());
        }

        if(!cost.getText().equals("") && !quantity.getText().equals("")) {
            amt_payable = Double.valueOf(cost.getText()).doubleValue() * Double.valueOf(quantity.getText()).doubleValue();
            payable.setText(amt_payable + "");
        }

    }

    public void setSomeFields() {
        if(!payable.getText().equals("") && !paid.getText().equals("")) {
            double customer_balance = Double.valueOf(paid.getText()).doubleValue() - Double.valueOf(payable.getText()).doubleValue();
            balance.setText(customer_balance + "");
        }

    }

    public void addProductToTable() {
        count = count + 1;
        String nameOfProd = fetchProd.getValue();
        String qty = quantity.getText();
        String price = cost.getText();
        String total = payable.getText();
        double tax = 0.5 * Double.valueOf(total);
        String strTax = "" + tax;
        
        final ObservableList<ManageSalesProductTable> data = FXCollections.observableArrayList(
                new ManageSalesProductTable(count, nameOfProd, qty, price, total, strTax)
        );

//        ObservableList<ManageSalesProductTable> data =
//        tableData.add(data);

        addedProdTable.setItems(data);

        tblSerial_number.setCellValueFactory(new PropertyValueFactory<>("ser_number"));
        tblProductName.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
        tblQuantity.setCellValueFactory(new PropertyValueFactory<>("qty_of_prod"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        tblTax.setCellValueFactory(new PropertyValueFactory<>("tax_amount"));
        tblTotal.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
//        addedProdTable.setItems((ObservableList)null);


    }

    public void addProductForSale() {
    }
}

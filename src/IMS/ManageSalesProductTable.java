package IMS;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ManageSalesProductTable {
    private final SimpleIntegerProperty ser_number;
    private final SimpleStringProperty prod_name;
    private final SimpleStringProperty qty_of_prod;
    private final SimpleStringProperty unit_price;
    private final SimpleStringProperty tax_amount;
    private final SimpleStringProperty total_amount;

    public ManageSalesProductTable(int s_n, String name, String qty, String price, String tax, String sum) {
        this.ser_number = new SimpleIntegerProperty(s_n);
        this.prod_name = new SimpleStringProperty(name);
        this.qty_of_prod = new SimpleStringProperty(qty);
        this.unit_price = new SimpleStringProperty(price);
        this.tax_amount = new SimpleStringProperty(tax);
        this.total_amount = new SimpleStringProperty(sum);
    }

    public int getSerialNumber() {
        return ser_number.get();
    }

    public void setSerialNumber(int s_n) {
        this.ser_number.set(s_n);
    }

    public String getProdName() {
        return prod_name.get();
    }

    public void setProdName(String name) {
        prod_name.set(name);
    }

    public String getPrice() {
        return unit_price.get();
    }

    public void setPrice(String price) {
        unit_price.set(price);
    }

    public String getQuantity() {
        return qty_of_prod.get();
    }

    public void setQuantity(String qty) {
        qty_of_prod.set(qty);
    }

    public String getTax() {
        return tax_amount.get();
    }

    public void setTax(String tax) {
        tax_amount.set(tax);
    }

    public String getTotal() {
        return total_amount.get();
    }

    public void setTotal(String sum) {
        total_amount.set(sum);
    }
}

package IMS;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MartUser {
    private final IntegerProperty id;
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty state;
    private final StringProperty phone_number;
    private final StringProperty email;

    MartUser(Integer id, String fname, String lname, String state, String phone_number, String email) {
        this.id = new SimpleIntegerProperty(id.intValue());
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.state = new SimpleStringProperty(state);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.email = new SimpleStringProperty(email);
    }

    public IntegerProperty getId() {
        return this.id;
    }

    public void setId(Integer newId) {
        this.id.set(newId.intValue());
    }

    public String getFirstName() {
        return (String)this.fname.get();
    }

    public void setFirstName(String fName) {
        this.fname.set(fName);
    }

    public String getLastName() {
        return (String)this.lname.get();
    }

    public void setLasstName(String lName) {
        this.lname.set(lName);
    }

    public String getState() {
        return (String)this.state.get();
    }

    public void setState(String storeState) {
        this.state.set(storeState);
    }

    public String getPhoneNum() {
        return (String)this.phone_number.get();
    }

    public void setPhoneNum(String phoneNum) {
        this.phone_number.set(phoneNum);
    }

    public String getEmail() {
        return (String)this.email.get();
    }

    public void setEmail(String email) {
        this.lname.set(email);
    }

    public IntegerProperty IdProperty() {
        return this.id;
    }

    public StringProperty FnameProperty() {
        return this.fname;
    }

    public StringProperty LnameProperty() {
        return this.lname;
    }

    public StringProperty stateProperty() {
        return this.state;
    }

    public StringProperty phoneProperty() {
        return this.phone_number;
    }

    public StringProperty emailProperty() {
        return this.email;
    }

}

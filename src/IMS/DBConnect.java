//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package IMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    public Connection Connect() {
        try {
            String host = "jdbc:mysql://localhost:3306/InventoryManagementSystem";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(host, user, password);
            return connection;
        } catch (SQLException | ClassNotFoundException var5) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, (String)null, var5);
            return null;
        }
    }
}

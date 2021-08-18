/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author CHIEN
 */
public class Utils {

    public static Connection cnn = null;

    public static synchronized Connection myConnection() {
        try {
            String url = "jdbc:sqlserver://DESKTOP-BFNI15L\\SQLEXPRESS:1433;databaseName=" + "QLCoffee";
            String user = "sa";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối thành công");
        } catch (Exception e) {
            System.out.println("Kết nối lỗi");
            cnn = null;
        }
        return cnn;
    }

    public static synchronized void closeConnection() {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (Exception e) {
            } finally {
                cnn = null;
            }
        }
    }
}

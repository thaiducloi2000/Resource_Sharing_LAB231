/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PC
 */
public class DBUtils {
    public static Connection openConection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=LAB231_01";
            Connection con=DriverManager.getConnection(url, "sa", "123456789");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package za.co.tmf.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mpumzi Mbula
 */
public class DBConnection {

    private static Connection con;

    public static Connection getConnection() throws SQLException {
        
        String url = "jdbc:derby://localhost:1527/TMFDB";
        String username = "administrator";
        String password = "admin";
        
        con = DriverManager.getConnection(url, username, password);
        return con;
    }
}

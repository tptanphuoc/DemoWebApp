package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

//kết nối với SQL
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

//private database infor
    private final String serverName = "localhost";
    private final String dbName = "WebApp";
    private final String portNumber = "1500";
    private final String instance = "";
    private final String userID = "tanphuoc";
    private final String password = "1234";

//test thử kết nối
    public static void main(String[] args) {
        try {
            System.out.println(new DBConnect().getConnection());
            System.out.println("Kết nối OKE!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package ImplProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ishop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "198823";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("org.gjt.mm.mysql.Driver");
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't connect to DB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("can't find jdbc class");
        }
    }
}



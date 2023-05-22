package DBConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//this will be singleton class
public class Connect {
    private static Connection conn;
    private static Connect instance=new Connect();
    private Connect() {
        connect();
    }
    /**
     * Connect to a sample database
     */
    public static void connect() {
        try {
            // url for when creating the jar and put him in the release
//            String url = "jdbc:sqlite:../dev/resource/HRDB";
            //works withe view here
            String url = "jdbc:sqlite:dev/resource/HRDB";
            //works for jar
//            String url ="jdbc:sqlite::resource:HRDB";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * disconnecting from the database
     */
    public static void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return the connection
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * close the connection when we are done with the DB
     */
    public static void CloseConnection() {
        try
            {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}
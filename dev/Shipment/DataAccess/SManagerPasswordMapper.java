package Shipment.DataAccess;

import resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;

public class SManagerPasswordMapper {
    private static SManagerPasswordMapper instance;
    private static String sManagerPassword;


    public static SManagerPasswordMapper getInstance() {
        if (instance == null){
            instance = new SManagerPasswordMapper();
        }
        return instance;
    }

    /**
     * we will take the manager password from the database
     */
    private SManagerPasswordMapper() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Passwords WHERE Role = 'ShipManager'");
            while (rs.next()) {
                sManagerPassword = rs.getString("Pass");
            }
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

    /**
     * @return the current password
     */
    public String getSManagerPassword() {
        return sManagerPassword;
    }

    /**
     * @param managerPassword the new password
     * we will update the database
     */
    public void setSManagerPassword(String managerPassword) {
        SManagerPasswordMapper.sManagerPassword = managerPassword;
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Passwords SET Pass = '"+managerPassword+"' WHERE Role = 'ShipManager'");
        }
        catch (SQLException e) {
            System.out.println("i have a problem sorry");
        }
    }

}
